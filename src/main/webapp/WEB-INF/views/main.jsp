<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="py-0 mx-auto">
    <div class="container text-center mb-5 mt-5">
        <h4 class="pt-4">상점 리스트</h4>
        <hr class="mx-auto" style="width:10%">
        <c:if test="${empty storeList}">
            <p class="lead py-5">아직 등록된 상점이 없습니다.</p>
        </c:if>
    </div>
    <c:if test="${not empty storeList}">
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
    </c:if>
</section>

<section class="py-5">
    <div class="container text-center mb-4">
        <h4 class="pt-4">상품 리스트</h4>
    </div>
    <div class="card-deck" id="productList">
        <div id="product-list" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"></div>
    </div>
</section>
