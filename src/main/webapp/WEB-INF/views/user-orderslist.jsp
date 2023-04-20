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
    <link id="pagestyle" href="/static/assets/css/material-dashboard.css?v=3.0.5" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="/static/assets/css/styles.css" rel="stylesheet" type="text/css">
    <style>
        h1, .h1, h2, .h2, h3, .h3, h4, .h4, h5, .h5, h6, .h6 {
            margin-top: 0;
            margin-bottom: 0.5rem;
            font-weight: 400;
            line-height: 1.2;
            color: #fff;
            font-family:"";
        }
        .bg-dark {
            --bs-bg-opacity: 1;
            background-color: rgba(var(--bs-dark-rgb), var(--bs-bg-opacity)) !important;
        }
        .navbar .navbar-brand {
            color: #344767;
            font-size: 1.3em;
        }
    </style>
</head>

<body class="g-sidenav-show  bg-gray-200">
<!-- Navigation-->
<%@include file="nav-bar.jsp"%>

<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Codeum Shopping</h1>
            <p class="lead fw-normal text-white-50 mb-0">Stores & Products</p>
        </div>
    </div>
</header>

<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
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
                                        결제방법
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                        구매일시
                                    </th>
                                    <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <c:choose>
                                    <c:when test="${not empty list.content}">
                                    <c:forEach var="orderslist" items="${list.content}" varStatus="status">
                                    <td>
                                        <!-- 상품 번호 정렬 -->
                                        <c:out value="${status.index +1}"/>
                                    </td>
                                    <td>
                                        <div class="d-flex px-2 py-1">
                                            <div>
                                                <!-- 이미지 0번째 -->
                                                <img class="avatar avatar-sm me-3 border-radius-lg"
                                                     src="<c:url value='/upload/${orderslist.savedProductFileName[0]}' />">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="d-flex flex-column justify-content-center">
                                            <!-- 상품명 -->
                                            <c:out value="${orderslist.ordersProduct}"/>
                                        </div>
                                    </td>
                                    <td class="align-middle text-center text-sm">
                                        <!-- 상품가격 -->
                                        <c:out value="${orderslist.ordersAmount}"/>
                                    </td>
                                    <td class="align-middle text-center text-sm">
                                        <!-- 결제방법 -->
                                        <c:out value="${orderslist.payMethod}"/>
                                    </td>
                                    <td class="align-middle text-center">
                                        <!-- 등록일시 -->
                                        <c:out value="${orderslist.ordersDate}"/>
                                    </td>
                                    <td class="align-middle">
                                        <input type="hidden" c:out value="" id="ordersListId">
                                        <!-- yn이 false 일떄 재등록 true 일때 삭제 -->
                                        <c:if test="${orderslist.ordersDelYn eq false}">
                                            <input type="button" value="환불요청">
                                        </c:if>
                                        <c:if test="${orderslist.ordersDelYn eq true}">
                                            <span>환불완료</span>
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
                        <c:forEach var="i" begin="0" end="${list.totalPages - 1}">
                            <c:choose>
                                <c:when test="${not empty list and list.number eq i}">
                                    <li class="page-item active"><a class="page-link" href="?page=${i}">${i + 1}</a>
                                    </li>
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
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/static/assets/js/member.js" type="text/javascript"></script>
<script defer data-site="YOUR_DOMAIN_HERE" src="https://api.nepcha.com/js/nepcha-analytics.js"></script>
</body>
