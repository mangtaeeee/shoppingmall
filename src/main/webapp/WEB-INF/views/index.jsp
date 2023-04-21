<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Codeum Shopping</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/static/assets/css/styles.css" rel="stylesheet" type="text/css">
    <style>
        .carousel-control-prev-icon,
        .carousel-control-next-icon {
            background-color: #bcbcbc;
        }
    </style>
</head>
<body>
<!-- Navigation-->
<%@include file="nav-bar.jsp"%>

<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Codeum Shopping</h1>
            <p class="lead fw-normal text-white-50 mb-0">Stores & Products</p>
        </div>
        <div class="input-group mt-5 w-50 mx-auto d-flex justify-content-center">
            <input id="search-keyword" type="text" class="form-control form-control-sm" placeholder="검색어를 입력하세요.">
            <div class="input-group-btn">
                <button class="btn btn-outline-secondary" onclick="searchByKeyword()" type="button">Search</button>
                <button class="btn btn-outline-secondary" onclick="location.href='/'" type="button">reset</button>
            </div>
        </div>
    </div>
</header>


<section class="py-0 mx-auto">
    <div id="store-list-container" class="container text-center mb-5 mt-5">
        <h4 class="pt-4">상점 리스트</h4>
    </div>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div id="store-list" class="carousel-inner">
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</section>

<section class="py-5 px-5">
    <div class="container text-center mb-4">
        <h4 class="pt-4">상품 리스트</h4>
    </div>
    <div class="card-deck" id="productList">
        <div id="product-list" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"></div>
    </div>
</section>

<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/static/assets/js/member.js" type="text/javascript"></script>
<script src="/static/assets/js/product.js" type="text/javascript"></script>
</body>
</html>
