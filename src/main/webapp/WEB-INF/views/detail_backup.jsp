<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Codeum Shopping</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico"/>
    <link href="/static/css/styles.css" rel="stylesheet" type="text/css">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- port one 결제 창 연동에 필요한 라이브러리 -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <!-- Core theme JS-->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="/static/js/member.js" type="text/javascript"></script>
</head>
<body>


<!-- 상품 상세보기 페이지 -->

<section>
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                                       src="<c:url value='/upload/${product.savedProductFileName[0]}'/>" alt="..."/>
            </div>
            <div class="col-md-6">
                <div class="fs-2 mb-3">
                    <h3>${product.productName}</h3>
                    <ul style="list-style: none">
                        <c:forEach items="${product.productHashtagName}" var="productHashtag">
                            <li style="float: left; margin-right: 15px;">#${productHashtag}</li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="fs-2 mb-3">
                    <p><span>PRICE : ₩${product.productPrice}</span> </p>
                </div>

                <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="addLikeList()">
                    관심 상품 등록
                </button>
            </div>
        </div>
    </div>
    </div>
</section>
<!-- Related items section-->
<section>
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">상품 사진</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach items="${product.savedProductFileName}" var="productImg">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product images-->
                        <img class="card-img-top" src="<c:url value='/upload/${productImg}'/>" alt="..."/>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>

    <!-- 상품 설명-->
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <h1 class="display-5 fw-bolder">${product.productContent}</h1>
    </div>
    <div>
        <input type="radio" name="pay_method" value="card" checked>카드
        <input type="radio" name="pay_method" value="phone">휴대폰
        <input type="radio" name="pay_method" value="vbank">가상계좌
        <p>
            <button onclick="requestPay()" class="btn btn-primary btn-order">결제</button>
            <button type="button" class="btn btn-primary btn-order">취소</button>
        </p>
    </div>
</section>


<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<script type="text/javascript">
    const addLikeList = () => {
        const token = localStorage.getItem("token")
        axios.post("/api/interest/add/${productId}", null, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then((response) => {
            console.log(response)
            alert("관심 상품으로 등록되었습니다.")
        }).catch((error) => {
            console.log(error.message)
            alert("관심 상품 등록에 실패했습니다.")
        })
    }
</script>
<!-- 결제 창 호출 스크립트 -->
<script>
    const IMP = window.IMP;
    IMP.init("imp61605278");

    function requestPay() {

        // 결제 수단 설정
        let pay_method = $(":input:radio[name=pay_method]:checked").val();
        console.log(pay_method);

        IMP.request_pay({
            pg: 'kcp.T0000',
            pay_method: pay_method,
            merchant_uid: "${orders.merchantId}",
            name: "${product.productName}",
            amount: ${product.productPrice},
            buyer_email: "${member.memberEmail}",
            buyer_name: "${member.id}",
            buyer_tel: "${member.memberPhone}",
            buyer_addr: "${member.memberAddress}",
            buyer_postcode: "${member.memberPostCode}"
        }, rsp => { // callback
            if (rsp.success) {
                alert("결제완료");
                // axios로 HTTP 요청
                axios({
                    url: "{서버의 결제 정보를 받는 endpoint}",
                    method: "post",
                    headers: {"Content-Type": "application/json"},
                    data: {
                        imp_uid: rsp.imp_uid,
                        merchant_uid: rsp.merchant_uid
                    }
                }).then((data) => {
                    // 서버 결제 API 성공시 로직
                    // "/orders/save"
                })
            } else {
                console.log(rsp)
                alert(`결제에 실패하였습니다. 에러 내용: ${rsp.error_msg}`);
            }
        });
    }
</script>
</body>
</html>