<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>
<!-- Navigation-->
<%@include file="nav-bar.jsp"%>

<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Codeum Shopping</h1>
            <p class="lead fw-normal text-white-50 mb-0">products of interest</p>
        </div>
    </div>
</header>

<section class="py-0 pb-5 px-5">
    <div id="like-list-container" class="container text-center mb-5 mt-5">
        <h4 class="pt-4">관심 상품 리스트</h4>
        <c:if test="${empty likeList}">
            <p class="lead py-5">아직 등록된 상품이 없습니다.</p>
        </c:if>
    </div>
    <c:if test="${not empty likeList}">
        <c:forEach items="${likeList}" var="like">
            <div class="card" style="width: 18rem;">
                <img src="<c:url value='/upload/${like.path}'/>" class="card-img-top" >
                <div class="card-body">
                    <h5 class="card-title">${like.name}</h5>
                </div>
            </div>
        </c:forEach>
    </c:if>
</section>

<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/js/scripts.js" type="text/javascript"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/static/assets/js/member.js" type="text/javascript"></script>
</body>
</html>
