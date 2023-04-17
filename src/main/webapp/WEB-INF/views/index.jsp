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
            <input type="text" class="form-control form-control-sm" placeholder="검색어를 입력하세요.">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button">Search</button>
            </div>
        </div>
    </div>
</header>


<section class="py-0 mx-auto">
    <div class="container text-center mb-5 mt-5">
        <h4 class="pt-4">상점 리스트</h4>
    </div>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <c:forEach items="${storeList}" var="store" varStatus="loop">
            <c:if test="${loop.index % 3 == 0}">
            <div class="carousel-item${loop.index == 0 ? ' active' : ''}">
                <div class="row">
                    </c:if>
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img class="card-img-top" src="<c:url value='${store.savedStoreFileName}'/>"/>
                            <div class="card-body">
                                <p class="card-text">${store.storeName}</p>
                            </div>
                            <div class="card-footer">
                                <div class="d-flex justify-content-between align-items-center">
                                    <a class="btn btn-sm btn-outline-secondary" href="<c:url value='/store/storedetail/${store.storeId}'/>">매장 상세보기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${(loop.index + 1) % 3 == 0 || loop.last}">
                </div>
                </c:if>
                </c:forEach>
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
    </div>
</section>

<section class="py-5">
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
<script type="text/javascript">
    let offset = 0;
    let limit = 8;
    let loading = false;

    $(document).ready(function() {
        loadProductList(0, 8); // 초기 페이지 로딩 시 첫 번째 페이지 데이터를 받아옵니다.
    });

    function loadProductList(offset, limit) {
        $.ajax({
            url: "/product/productlist?offset=" + offset + "&limit=" + limit,
            type: "GET",
            success: function(data) {
                let productList = $("#product-list"); // 상품 리스트를 담을 DOM 객체를 가져옵니다.
                productList.empty(); // 이전에 생성된 상품 리스트를 모두 지웁니다.

                if (data.length > 0) {
                    $.each(data, function (index, product) {
                        // 상품 리스트를 반복문을 통해 카드로 만듭니다.
                        let card = '<div class="col mb-5">' +
                            '    <div class="card h-100">' +
                            '        <img class="card-img-top" src="/upload/thumbnails/' + product.productImgThumbnail[0] + '"/>' +
                            '        <div class="card-body p-4">' +
                            '            <div class="text-center">' +
                            '                <h5 class="fw-bolder">' + product.productName + '</h5>' +
                            '                ' + product.productPrice +
                            '            </div>' +
                            '        </div>' +
                            '        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">' +
                            '            <div class="text-center">' +
                            '                <a class="btn btn-outline-dark mt-auto" href="/product/productdetail/' + product.productId + '">상품 상세보기</a>' +
                            '            </div>' +
                            '        </div>' +
                            '    </div>' +
                            '</div>';
                        productList.append(card); // 상품 리스트에 추가합니다.
                    });
                }
            }
        });
    }

    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMore();
        }
    });

    function loadMore() {
        if (loading) {
            return;
        }

        loading = true;

        offset++;

        $.ajax({
            url: "/product/productlist?offset=" + offset + "&limit=" + limit,
            type: "GET",
            success: function(data) {
                if (data.length > 0) {
                    let productList = $("#product-list"); // 상품 리스트를 담을 DOM 객체를 가져옵니다.
                    data.forEach(function(product) {
                        // 상품 카드 생성 코드
                        let card = '<div class="col mb-5">' +
                            '    <div class="card h-100">' +
                            '        <img class="card-img-top" src="/upload/thumbnails/' + product.productImgThumbnail[0] + '"/>' +
                            '        <div class="card-body p-4">' +
                            '            <div class="text-center">' +
                            '                <h5 class="fw-bolder">' + product.productName + '</h5>' +
                            '                ' + product.productPrice +
                            '            </div>' +
                            '        </div>' +
                            '        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">' +
                            '            <div class="text-center">' +
                            '                <a class="btn btn-outline-dark mt-auto" href="/product/productdetail/' + product.productId + '">상품 상세보기</a>' +
                            '            </div>' +
                            '        </div>' +
                            '    </div>' +
                            '</div>';
                        productList.append(card); // 상품 리스트에 추가합니다.
                    });
                }
                loading = false;
            }
        });
    }
</script>
</body>
</html>
