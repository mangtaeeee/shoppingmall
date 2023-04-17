let emailVal
let passwordVal
let nameVal
let addressVal
let phoneNumberVal

$("#signup").on("show.bs.modal", function (e) {
    let button = document.getElementById("go-to-sms-auth")
    if (document.getElementById("smsAuth").checked) {
        button.disabled = true
        button.innerText = "SMS 인증 완료"
    } else {
        button.disable = false
        button.innerText = "SMS 인증하기"
    }
})

window.onload = function() {
    if (localStorage.getItem("token")) {
        document.getElementById("signin-nav").style.display = "none"
        document.getElementById("logout-nav").style.display = "inline"
    } else {
        document.getElementById("signin-nav").style.display = "inline"
        document.getElementById("logout-nav").style.display = "none"
    }
}

const singin = () => {

    let email = document.getElementById("signin-email").value;
    let password = document.getElementById("signin-password").value;

    axios.post("/api/user/signin", {
        email: email,
        password: password
    }).then((response) => {
        alert("로그인에 성공했습니다.")
        console.log(response.data)
        window.localStorage.setItem("token", response.data)
        window.location.href = "/"
    }).catch((error) => {
        alert(error.response.data.message)
    })
}

const logOut = () => {
    localStorage.clear()
    window.location.href = "/"
}

const oninputPhone = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

const signUp = () => {
    let email = document.getElementById("email").value;
    let userChecked = document.getElementById("user-check").checked;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let smsAuth = document.getElementById("smsAuth").checked;

    if(userChecked === false) {
        alert("중복 체크를 완료해주세요.")
        return
    }

    if (email.trim() === '' || password.trim() === '' || name.trim() === '' || address.trim() === '' || phoneNumber.trim() === '') {
        alert("회원 정보를 전부 입력해주세요.")
        return
    } else {
        axios.post("/api/user/signup", {
            email: email,
            password: password,
            name: name,
            address: address,
            phone: phoneNumber,
            auth: smsAuth
        }).then((response) => {
            console.log(response)
            alert("회원 가입에 성공했습니다.")
            $("#signup").modal("hide")
            $("#signin").modal("show")
        }).catch((error) => {
            alert(error.response.data.message)
        })
    }
}

const sendAuthSms = () => {
    let rawPhoneNum = document.getElementById("phoneNumber").value
    phoneNumber = rawPhoneNum.replace(/-/g, '');

    if (document.getElementById("phoneNumber").value.trim() === '') {
        alert('전화번호를 입력해주세요!')
        return
    }

    console.log(phoneNumber)

    axios.post("/api/user/send-auth", {
        to: phoneNumber
    }).then((response) => {
        alert("인증 메시지 발송 성공!")
        let expiration = new Date().getTime() + (3 * 60 * 1000);
        localStorage.removeItem("authKey")
        localStorage.setItem("authKey", response.data)
        localStorage.setItem("authKeyExpiration", expiration.toString())

        localStorage.setItem("email", $("#email").val())
        localStorage.setItem("password", $("#password").val())
        localStorage.setItem("name", $("#name").val())
        localStorage.setItem("address", $("#address").val())
        localStorage.setItem("phoneNumber", $("#phoneNumber").val())

        $("#signup").modal("hide")
        $("#sms-auth").modal("show")
    }).catch((error) => {
        console.log(error.response.data.message)
        alert("인증 메시지 발송 실패!")
    })
}

const checkAuthKey = () => {
    let expirationString = localStorage.getItem("authKeyExpiration")
    let expiration = parseInt(expirationString)

    if (expiration && new Date().getTime() > expiration) {
        localStorage.removeItem("authKey")
        localStorage.removeItem("authKeyExpiration")
    }

    if(localStorage.getItem("authKey") == null) {
        alert("인증번호를 재발급 받으세요!")
        return
    }

    let authKey = document.getElementById("auth-code").value;

    axios.post("/api/user/check-auth", {
        authKey: authKey,
        checkKey: localStorage.getItem("authKey")
    }).then((response) => {
        console.log(response)
        localStorage.removeItem("authKey")
        localStorage.removeItem("authKeyExpiration")
        alert("SMS 인증 성공!")

        $("#email").val(localStorage.getItem("email"))
        $("#password").val(localStorage.getItem("password"))
        $("#name").val(localStorage.getItem("name"))
        $("#address").val(localStorage.getItem("address"))
        $("#phoneNumber").val(localStorage.getItem("phoneNumber"))

        localStorage.removeItem("email")
        localStorage.removeItem("password")
        localStorage.removeItem("name")
        localStorage.removeItem("address")
        localStorage.removeItem("phoneNumber")

        document.getElementById("smsAuth").checked = true

        $("#sms-auth").modal("hide")
        $("#signup").modal("show")
    }).catch((error) => {
        console.log(error)
        document.getElementById("smsAuth").checked = false
        alert(error.response.data.message)
    })
}

const searchAddress = () => {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('address').value = data.zonecode + "," + roadAddr
        }
    }).open();
}

const toLikeList = () => {
    let token = localStorage.getItem("token");
    if (!token) {
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/";
    } else {
        window.location.href = "/like-list";
    }
};

const duplicateCheck = () => {
    let email = document.getElementById("email").value

    if (email.trim() == '') {
        alert("이메일을 입력해주세요.")
        return
    }

    axios.post("/api/user/check-user", {
        email: email
    }).then((response) => {
        alert("사용 가능한 아이디 입니다.")
        document.getElementById("user-check").checked = true
        document.getElementById("duplicate-check-btn").disabled = true
        document.getElementById("duplicate-check-btn").innerText = "중복체크 완료"
    }).catch((error) => {
        alert(error.response.data.message)
    })
}

const checkActivate = () => {
    document.getElementById("user-check").checked = false
    document.getElementById("duplicate-check-btn").disabled = false
    document.getElementById("duplicate-check-btn").innerText = "중복체크"
}