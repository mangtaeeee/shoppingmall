<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        로그인 페이지
    </title>
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link id="pagestyle" href="/static/assets/css/material-dashboard.css?v=3.0.5" rel="stylesheet"/>
    <!-- bootstrap 적용 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

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
                <a class="nav-link text-white " href="/admin/main">
                    <span class="nav-link-text ms-1">메인</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white " href="/admin/store">
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
                <a class="nav-link text-white active bg-gradient-primary" href="/admin">
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
<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true">
        <div class="container-fluid py-1 px-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                    <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Pages</a>
                    </li>
                    <li class="breadcrumb-item text-sm text-dark active" aria-current="page">로그인</li>
                </ol>
            </nav>
            <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
                <div class="ms-md-auto pe-md-3 d-flex align-items-center">
                    <div class="input-group input-group-outline">

                    </div>
                </div>
                <ul class="navbar-nav  justify-content-end">
                    <li class="nav-item d-flex align-items-center">

                    </li>

                </ul>
                </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->
    <div class="container my-auto">
        <div class="row">
            <div class="col-lg-4 col-md-8 col-12 mx-auto">
                <div class="card mt-4">
                    <!-- 데이터 출력 -->
                    <div class="card z-index-0 fadeIn3 fadeInBottom">
                        <div class="card-body">
                            <form class="text-start" >
                                <div class="input-group input-group-outline my-3">
                                    <label for="email" class="form-label">아이디</label>
                                    <input type="text" name="email" id="email" class="form-control">
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <label for="password" class="form-label">비밀번호</label>
                                    <input type="password" name="password" id="password" class="form-control">
                                </div>


                                <div class="text-center">
                                    <input type="button" class="btn bg-gradient-success w-100 mb-0 toast-btn" id="login" value="로그인"/>

                                </div>
                                <div>
                                    <button type="reset" class="btn bg-gradient-danger w-100 mb-0 toast-btn">취소</button>
                                </div>
                                <div>
                                    <input type="button" class="btn bg-gradient-info w-100 mb-0 toast-btn" id="join" value="회원가입"/>
                                </div>

                            </form>
                        </div>
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


</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>

    document.getElementById("join").onclick = function login() {
        window.location.href = "/admin/join"
    },



    document.getElementById("login").onclick = function login() {

        let email = document.getElementById("email").value
        let password = document.getElementById("password").value

        axios.post("/api/admin/login", {
            email : email,
            password : password
        })
            .then((response) => {
                localStorage.clear()
                console.log(response)
                localStorage.setItem("adminemail",response.data.adminEmail)
                alert("로그인 성공 ")
                window.location.href = "/admin/main"
            })
            .catch(() => {
                alert("다시 시도해 주세요")
            })
    }
</script>
</html>