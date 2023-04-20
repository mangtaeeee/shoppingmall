<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        상품관리 페이지
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
                <a class="nav-link text-white active bg-gradient-primary" href="/admin/productmanage">
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
                    <li class="breadcrumb-item text-sm text-dark active" aria-current="page">메인</li>
                </ol>
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
                    <div class="card-body px-0 pb-2">
                        <div class="table-responsive p-0">
                            <table class="table align-items-center mb-0">
                                <thead>
                                <tr>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        번호
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        대표사진
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        상품명
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        가격
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        등록일시
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <c:choose>
                                    <c:when test="${not empty list.content}">
                                    <c:forEach var="prouctlist" items="${list.content}" varStatus="status">
                                    <td>
                                        <!-- 상품 번호 정렬 -->
                                        <c:out value="${status.index +1}"/>
                                    </td>
                                    <td>
                                        <div class="d-flex px-2 py-1">
                                            <div>
                                                <!-- 이미지 0번째 -->
                                                <img class="avatar avatar-sm me-3 border-radius-lg"
                                                     src="<c:url value='/upload/${prouctlist.savedProductFileName[0]}' />">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="d-flex flex-column justify-content-center">
                                            <!-- 상품명 -->
                                            <c:out value="${prouctlist.productName}"/>
                                        </div>
                                    </td>
                                    <td class="align-middle text-center text-sm">
                                        <!-- 상품가격 -->
                                        <c:out value="${prouctlist.productPrice}"/>
                                    </td>
                                    <td class="align-middle text-center">
                                        <!-- 등록일시 -->
                                        <c:out value="${prouctlist.createdDate}"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="hidden" c:out value="" id="productListId">
                                        <!-- yn이 false 일떄 재등록 true 일때 삭제 -->
                                        <c:if test="${prouctlist.productDelYn eq false}">
                                            <input type="button" onclick="ynTrue(${prouctlist.productId})" value="재등록">
                                        </c:if>
                                        <c:if test="${prouctlist.productDelYn eq true}">
                                            <input type="button" onclick="ynFalse(${prouctlist.productId})" value="삭제">
                                        </c:if>
                                    </td>
                                </tr>
                                </c:forEach>
                                </c:when>
                                </c:choose>
                                </tbody>
                            </table>
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
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${not empty list.content}">
                        <c:if test="${not empty list and list.number > 0}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${list.number - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>

                        <c:set var="startPage" value="${(list.number / 10) * 10}" />
                        <c:set var="endPage" value="${(startPage + 9 >= list.totalPages) ? list.totalPages - 1 : startPage + 9}" />

                        <c:forEach var="i" begin="${startPage}" end="${endPage}">
                            <c:choose>
                                <c:when test="${not empty list and list.number eq i}">
                                    <li class="page-item active"><a class="page-link" href="?page=${i}">${i + 1}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="?page=${i}">${i + 1}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${not empty list and list.number < list.totalPages - 1}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${list.number + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </c:when>
                </c:choose>
            </ul>
        </footer>

    </div>
</main>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
    if (window.localStorage.getItem("adminemail") == null) {
        alert("로그인 해주세요!!!!!")
        window.location.href = "/admin"
    }

    if (window.localStorage.getItem("adminemail") == null) {
        alert("로그인 해주세요!!!!!")
        window.location.href = "/admin"
    }

    function ynTrue(productListId) {
        let data = {
            productDelYn: true
        }

        $.ajax({
            type: 'PATCH',
            url: '/product/productedit/' + productListId,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.reload();
        }).fail(function (error) {
            console.log(error)
        });

    }

    function ynFalse(productListId) {
        let data = {
            productDelYn: false
        }


        $.ajax({
            type: 'PATCH',
            url: '/product/productedit/' + productListId,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.reload();
        }).fail(function (error) {
            console.log(error)
        });
    }


</script>
