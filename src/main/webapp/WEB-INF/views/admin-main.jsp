<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        메인 페이지
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
                <a class="nav-link text-white active bg-gradient-primary" href="/admin/main">
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
                <a class="nav-link text-white" href="#">
                    <span class="nav-link-text ms-1">로그인</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="#">
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
                    <!-- 데이터 출력 -->
                    <c:choose>
                        <c:when test="${not empty adminMainList}">
                            <c:forEach var="list" items="${adminMainList}" varStatus="status">
                                <div class="col-8">
                                    <div class="card-header p-3">
                                        <h5 class="mb-0 col-2"></h5>
                                    </div>
                                    <div class="card-body p-3 pb-0">
                                        <div class="form-group">
                                            <label>상점이름 : ${list.adminStoreName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label>상점설명 : ${list.adminStoreContent}</label>
                                        </div>
                                        <div class="form-group">
                                            <label>해시태그 :</label>
                                            <c:choose>
                                                <c:when test="${not empty list.productHashtagList}">
                                                    <c:forEach var="hashTag" items="${list.productHashtagList}"
                                                               varStatus="status">
                                                        <label>#${hashTag.productHashtagName[status.index]}</label>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="form-group">
                                            <label>상품 : </label>
                                            <c:choose>
                                                <c:when test="${not empty list.productImgList}">
                                                    <c:forEach var="productImg" items="${list.productImgList}"
                                                               varStatus="status">
                                                        <img class="card-img-top" src="<c:url value='/Library/Codeum/fileupload/thumbnails/${productImg.productImgThumbnail[status.index]}'/>"/>
                                                    </c:forEach>
                                                </c:when>
                                            </c:choose>

                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                    </c:choose>
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