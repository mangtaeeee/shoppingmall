<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- bootstrap 적용 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>
<body>
<div class="container">
    <!-- Modal -->
    <div class="modal fade" data-bs-backdrop="static" id="emailAuthNumber" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">이메일 인증</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form>
                        <div class="form-floating">
                            <input id="auth_number" type="text" class="form-control"  placeholder="인증번호 8자리를 입력해주세요!"  maxlength="8">
                            <label for="auth_number">인증번호</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="joinAuthor()">확인</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">나가기</button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-6 mx-auto">
        <form class="form-control" >
            <label for="email" class="form-label">이메일</label>
            <input type="text" name="email" id="email" class="form-control">
            <div class="input-group-addon" >
                <button type="button" id="mail-Check-Btn"  class="btn btn-primary" href="#!" data-bs-toggle="modal" data-bs-target="#emailAuthNumber" disabled >이메일인증하기</button>
            </div>
            <span id="mail-check-warn"></span>
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" name="password" id="password" class="form-control">
            <div class="d-grid">
                <input type="button" class="btn btn-primary btn-block my-3" id="join-Btn" value="회원가입" disabled/>
            </div>
            <button type="reset" class="btn btn-secondary">다시입력</button>
            <button type="button" id="joinCancel" class="btn btn-danger">취소</button>

        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    let emailcode = null;
    let emailAuth = false;

    const idForm = document.querySelector('#email');
    const mail_check = document.querySelector('#mail-Check-Btn');
    const join_button = document.querySelector('#join-Btn');

    const pushValue = () => {
        idForm.addEventListener('keyup', () => {
            if(idForm.value){
                mail_check.disabled = false;
            } else {
                mail_check.disabled = true;
            }
        });
    }

    const joinAuthor = () => {
        let authString = $('#auth_number').val();

        axios.get("/api/admin/email/"+ authString)
            .then(() => {
                alert("인증번호가 확인 되었습니다.")
                emailAuth = true
                mail_check.disabled = true;
                join_button.disabled = false;
                $('#emailAuthNumber').modal('hide')
            })
            .catch((error) => {
                console.log(error.data)
                alert("인증번호가 정확하지 않습니다.다시 입력해주세요")
                $('#emailAuthNumber').modal('show')
            })

    }


    $('#mail-Check-Btn').on('click',function() {
        const email = $('#email').val();
        axios.post("/api/admin/email",{
            email : email
        }).then((response) => {
            emailcode = response.data
            alert("인증번호가 전송되었습니다.")
        })
    })

    $('#join-Btn').on('click',function() {
        const email = $('#email').val();
        const password = $('#password').val();
        const auth = emailAuth;
        axios.post("/api/admin/join",{
            email : email,
            password : password,
            auth : auth
        }).then((response) => {
            console.log(response)
            alert("회원가입이 완료되었습니다.")
            window.location.href = "/admin"
        }).catch((error) => {
            console.log(error)
        })
    })
    $('#joinCancel').on('click',function() {
        window.location.href = "/admin"
    })
    pushValue();
</script>
</html>