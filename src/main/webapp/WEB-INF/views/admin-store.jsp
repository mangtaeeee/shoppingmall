<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        상점 관리 페이지
    </title>
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link id="pagestyle" href="/static/assets/css/material-dashboard.css?v=3.0.5" rel="stylesheet"/>
    <script defer data-site="YOUR_DOMAIN_HERE" src="https://api.nepcha.com/js/nepcha-analytics.js"></script>
</head>

<body class="g-sidenav-show  bg-gray-200">
<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark"
       id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
           aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="#"
           target="_blank">
            <img src="/static/assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold text-white">ADMIN DASHBOARD</span>
        </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin/main">
                    <span class="nav-link-text ms-1">메인</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white active bg-gradient-primary" href="/admin/store">
                    <span class="nav-link-text ms-1">상점 관리</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin/uploadproduct">
                    <span class="nav-link-text ms-1">상품 등록</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin/productmanage">
                    <span class="nav-link-text ms-1">상품 관리</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin">
                    <span class="nav-link-text ms-1">로그인</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/admin/join">
                    <span class="nav-link-text ms-1">회원가입</span>
                </a>
            </li>
        </ul>
    </div>
</aside>
<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur"
         data-scroll="true">
        <div class="container-fluid py-1 px-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                    <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Pages</a>
                    </li>
                    <li class="breadcrumb-item text-sm text-dark active" aria-current="page">상점관리</li>
                </ol>
                <h6 class="font-weight-bolder mb-0">상점관리</h6>
            </nav>
            <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
                <div class="ms-md-auto pe-md-3 d-flex align-items-center">
                    <div class="input-group input-group-outline">

                    </div>
                </div>
                <ul class="navbar-nav  justify-content-end">
                    <li class="nav-item d-flex align-items-center">
                        <a href="../pages/sign-in.html" class="nav-link text-body font-weight-bold px-0">
                            <i class="fa fa-user me-sm-1"></i>
                            <span class="d-sm-inline d-none">Sign In</span>
                        </a>
                    </li>

                </ul>
                </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->
    <div class="container-fluid py-4">
        <div class="row">
            <div class="col-lg-10 col-md-10 mx-auto">
                <div class="card mt-4">
                    <div class="col-10">
                        <div class="card-header p-3">
                            <h5 class="mb-0 col-2">상점관리</h5>
                        </div>
                        <div class="form-group" style="float: right">
                            <div id="image_container">
                                <!-- 사진 미리보기 -->
                            </div>
                        </div>
                        <div class="card-body p-3 pb-0">
                            <input type="hidden" id="sample6_postcode" placeholder="우편번호">
                            <input type="hidden" id="sample6_address" placeholder="주소"><br>

                            <input type="hidden" id="sample6_extraAddress" placeholder="참고항목">

                            <div class="form-group">
                                <label for="adminStoreName">상점명:</label>
                                <input type="text" name="adminStoreName" class="form-control" id="adminStoreName">
                            </div>
                            <div class="form-group">
                                <label for="adminStoreAddress">주소:</label><br>
                                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><input
                                    type="text"
                                    id="sample6_detailAddress"
                                    placeholder="상세주소"><input
                                    type="button" onclick="address_ok()" id="addressok" value="확인" disabled><br>
                                <input type="text" class="form-control" name="adminStoreAddress" id="adminStoreAddress"
                                       disabled>
                            </div>
                            <div class="form-group">
                                <label for="adminStorePhone">전화번호:</label>
                                <input type="text" class="form-control" name="adminStorePhone" id="adminStorePhone">
                            </div>
                            <div class="form-group">
                                <label for="adminStoreContent">상점설명:</label>
                                <textarea class="form-control" style="overflow-y:scroll" rows="5"
                                          name="adminStoreContent" id="adminStoreContent"></textarea>
                            </div>
                            <div class="form-group">
                                <button class="btn w-100 mb-0 toast-btn" type="button"><label
                                        for="adminStoreImg">사진추가</label>
                                    <input type="file" class="form-control form-control-sm" name="adminStoreImg"
                                           id="adminStoreImg" onchange="fileUpload(event);" style="display: none"
                                           multiple>
                                </button>
                            </div>

                            <button class="btn bg-gradient-danger" type="button" data-target="dangerToast">
                                수정
                            </button>
                            <button class="btn bg-gradient-success" id="store-add-btn" type="button"
                                    onclick="store_add()"
                                    data-target="successToast">
                                확인
                            </button>
                        </div>
                    </div>
                    <div class="col-2">
                    </div>
                </div>
            </div>
        </div>
        <div class="position-fixed bottom-1 end-1 z-index-2">
            <div class="toast fade hide p-2 bg-white" role="alert" aria-live="assertive" id="successToast"
                 aria-atomic="true">
                <div class="toast-header border-0">
                    <i class="material-icons text-success me-2">
                        check
                    </i>
                    <span class="me-auto font-weight-bold">Material Dashboard </span>
                    <small class="text-body">11 mins ago</small>
                    <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
                </div>
                <hr class="horizontal dark m-0">
                <div class="toast-body">
                    Hello, world! This is a notification message.
                </div>
            </div>
            <div class="toast fade hide p-2 mt-2 bg-gradient-info" role="alert" aria-live="assertive" id="infoToast"
                 aria-atomic="true">
                <div class="toast-header bg-transparent border-0">
                    <i class="material-icons text-white me-2">
                        notifications
                    </i>
                    <span class="me-auto text-white font-weight-bold">Material Dashboard </span>
                    <small class="text-white">11 mins ago</small>
                    <i class="fas fa-times text-md text-white ms-3 cursor-pointer" data-bs-dismiss="toast"
                       aria-label="Close"></i>
                </div>
                <hr class="horizontal light m-0">
                <div class="toast-body text-white">
                    Hello, world! This is a notification message.
                </div>
            </div>
            <div class="toast fade hide p-2 mt-2 bg-white" role="alert" aria-live="assertive" id="warningToast"
                 aria-atomic="true">
                <div class="toast-header border-0">
                    <i class="material-icons text-warning me-2">
                        travel_explore
                    </i>
                    <span class="me-auto font-weight-bold">Material Dashboard </span>
                    <small class="text-body">11 mins ago</small>
                    <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
                </div>
                <hr class="horizontal dark m-0">
                <div class="toast-body">
                    Hello, world! This is a notification message.
                </div>
            </div>
            <div class="toast fade hide p-2 mt-2 bg-white" role="alert" aria-live="assertive" id="dangerToast"
                 aria-atomic="true">
                <div class="toast-header border-0">
                    <i class="material-icons text-danger me-2">
                        campaign
                    </i>
                    <span class="me-auto text-gradient text-danger font-weight-bold">Material Dashboard </span>
                    <small class="text-body">11 mins ago</small>
                    <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
                </div>
                <hr class="horizontal dark m-0">
                <div class="toast-body">
                    Hello, world! This is a notification message.
                </div>
            </div>
        </div>
        <footer class="footer py-4  ">

        </footer>
    </div>
</main>
<div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
        <i class="material-icons py-2">settings</i>
    </a>
    <div class="card shadow-lg">
        <div class="card-header pb-0 pt-3">
            <div class="float-start">
                <h5 class="mt-3 mb-0">Material UI Configurator</h5>
                <p>See our dashboard options.</p>
            </div>
            <div class="float-end mt-4">
                <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <!-- End Toggle Button -->
        </div>
        <hr class="horizontal dark my-1">
        <div class="card-body pt-sm-3 pt-0">
            <!-- Sidebar Backgrounds -->
            <div>
                <h6 class="mb-0">Sidebar Colors</h6>
            </div>
            <a href="javascript:void(0)" class="switch-trigger background-color">
                <div class="badge-colors my-2 text-start">
                    <span class="badge filter bg-gradient-primary active" data-color="primary"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-success" data-color="success"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-warning" data-color="warning"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-danger" data-color="danger"
                          onclick="sidebarColor(this)"></span>
                </div>
            </a>
            <!-- Sidenav Type -->
            <div class="mt-3">
                <h6 class="mb-0">Sidenav Type</h6>
                <p class="text-sm">Choose between 2 different sidenav types.</p>
            </div>
            <div class="d-flex">
                <button class="btn bg-gradient-dark px-3 mb-2 active" data-class="bg-gradient-dark"
                        onclick="sidebarType(this)">Dark
                </button>
                <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-transparent"
                        onclick="sidebarType(this)">Transparent
                </button>
                <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-white" onclick="sidebarType(this)">
                    White
                </button>
            </div>
            <p class="text-sm d-xl-none d-block mt-2">You can change the sidenav type just on desktop view.</p>
            <!-- Navbar Fixed -->
            <div class="mt-3 d-flex">
                <h6 class="mb-0">Navbar Fixed</h6>
                <div class="form-check form-switch ps-0 ms-auto my-auto">
                    <input class="form-check-input mt-1 ms-auto" type="checkbox" id="navbarFixed"
                           onclick="navbarFixed(this)">
                </div>
            </div>
            <hr class="horizontal dark my-3">
            <div class="mt-2 d-flex">
                <h6 class="mb-0">Light / Dark</h6>
                <div class="form-check form-switch ps-0 ms-auto my-auto">
                    <input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version"
                           onclick="darkMode(this)">
                </div>
            </div>
            <hr class="horizontal dark my-sm-4">
        </div>
    </div>
</div>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script async defer src="https://buttons.github.io/buttons.js"></script>
<script src="/static/assets/js/material-dashboard.min.js?v=3.0.5"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>

    let address;


    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
                document.getElementById("addressok").disabled = false;
            }
        }).open();
    }

    function address_ok() {
        let address = document.getElementById("sample6_address").value;
        let address2 = document.getElementById("sample6_detailAddress").value;

        document.getElementById("adminStoreAddress").value = address + " " + address2;
    }


    function store_add() {
        let formData = new FormData();
        let storeImgFile = document.getElementById("adminStoreImg");

        formData.append("adminStoreName", $("#adminStoreName").val())
        formData.append("adminStoreAddress", $("#adminStoreAddress").val())
        formData.append("adminStorePhone", $("#adminStorePhone").val())
        formData.append("adminStoreContent", $("#adminStoreContent").val())

        formData.append("storeImgFile", storeImgFile.files[0])


        axios.post("/api/admin/store/upload", formData, {
            header: {
                'Context-Type': 'multipart/form-data',
            }, transformRequest: (data, header) => {
                return data;
            },
        }).then((response) => {
            alert("성공 했습니다.")
            window.location.href = "/admin/main"
        }).catch((error) => {
            console.log(error.response.data.message)
        })


    }

    function fileUpload() {
        const imgContainer = document.getElementById('image_container');
        if (imgContainer.childElementCount != 0) imgContainer.removeChild(imgContainer.lastChild);
        let imgDiv = document.createElement("div");
        imgContainer.appendChild(imgDiv);

        for (let image of event.target.files) {
            let reader = new FileReader();

            reader.onload = function (event) {
                let img = document.createElement("img");
                img.setAttribute("src", event.target.result);
                img.setAttribute("class", "col-lg-7 img-fluid");
                img.setAttribute("style", "margin-right: 15px");
                imgDiv.appendChild(img);
            };
            console.log(image);
            reader.readAsDataURL(image);
        }
    }


</script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }

</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

</body>

</html>