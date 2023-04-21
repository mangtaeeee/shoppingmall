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
    <link rel="icon" type="image/x-icon" href="/favicon.ico"/>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/static/assets/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- Navigation-->
<%@include file="nav-bar.jsp" %>
<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Codeum Shopping</h1>
            <p class="lead fw-normal text-white-50 mb-0">Stores & Products</p>
        </div>
    </div>
</header>


<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                                       src="<c:url value='/upload/${product.savedProductFileName[0]}'/>" alt="..."/>
            </div>
            <div class="col-md-5">
                <c:forEach items="${product.productHashtagName}" var="productHashtag">
                    <div class="small mb-1">
                        <ul style="list-style: none">
                            <li style="float: left; margin-right: 10px;">#${productHashtag}</li>
                        </ul>
                    </div>
                </c:forEach>
                <br/>
                <h1 class="display-5 fw-bolder mx-4">${product.productName}</h1>
                <div class="fs-3 mb-3 mx-4">
                    <span>PRICE : ₩${product.productPrice}</span>
                </div>
                <p class="lead mx-4">${product.productContent}</p>
                <div class="d-flex mx-4">
                    <select class="form-select" id="pay_method" style="width:120px">
                        <option value="card" selected>카드</option>
                        <option value="phone">휴대폰</option>
                        <option value="vbank">가상계좌</option>
                    </select>
                    <div class="d-flex mx-2">
                        <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="requestPay()">
                            <i class="bi-cart-fill me-1"></i>
                            구매하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Related items section-->

<section class="py-5 bg-light">
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">Related Pictures</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach items="${product.savedProductFileName}" var="productImg">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="<c:url value='/upload/${productImg}'/>" alt="..."/>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


</body>
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
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<!-- 결제 창 호출 스크립트 -->
<script>
    // 사전검증 API 사용을 위한 토큰 발급
    let Api_token;
    window.onload = function () {
        axios.post("/api/payment/getToken")
            .then((response) => {
                console.log(response.data);
                Api_token = response.data.response.access_token;
            }).catch((error) =>{
            alert("api 토큰 발행 실패");
        })
    }


    const IMP = window.IMP;
    IMP.init("imp61605278");

    function requestPay() {

        let loggedIn = localStorage.getItem("token");

        if (!loggedIn) {
            alert("로그인 화면으로 이동합니다")
            $('#signin').modal('show');
            return
        }

        //로컬스토리지에 저장된 token을 이용해서 member 정보 가져오고 주문 테이블에 데이터 생성
        axios({
            url: "/api/orders/create-order",
            method: "post",
            data: {
                userId: localStorage.getItem("token"),
                productId: "${productId}",
                payMethod: $("#pay_method option:selected").val()
            }
        }).then((response) => {
            ordersData = response.data;
            console.log(ordersData)

            axios({
                url: "/api/payment/prepare",
                method: "post",
                headers: { "Content-Type": "application/json" },
                data: {
                    merchant_uid: ordersData.merchantId, // 가맹점 주문번호
                    amount: "" + ordersData.ordersAmount, // 결제 예정금액
                    token: Api_token
                }
            }).then((response) => {
                console.log(response.data)

                IMP.request_pay({
                    pg: 'kcp.T0000',
                    pay_method: ordersData.payMethod,
                    merchant_uid: ordersData.merchantId,
                    name: ordersData.ordersProduct,
                    amount: ${product.productPrice},
                    buyer_email: ordersData.buyerEmail,
                    buyer_name: ordersData.buyerName,
                    buyer_tel: ordersData.buyerTel,
                    buyer_addr: ordersData.buyerAddr,
                    buyer_postcode: ordersData.buyerPostcode
                }, rsp => {
                    if (rsp.success) {
                        alert("결제완료");
                        // axios로 HTTP 요청
                        axios({
                            url: "/api/payment/complete",
                            method: "post",
                            headers: {"Content-Type": "application/json"},
                            data: {
                                imp_uid: rsp.imp_uid,
                                merchant_uid: rsp.merchant_uid,
                                amount: "" + ordersData.ordersAmount,
                                token: Api_token
                            }
                        }).then((response) => {
                            // 구매완료 화면으로 이동
                            console.log(response.data)
                            alert("메인으로 이동합니다.");
                            location.href = "/"
                        })
                    } else {
                        console.log(rsp)
                        alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                    }
                })
            }).catch((error) => {
                alert(error.message);
                console.log(error.message)
            })
        }).catch((error) => {
            alert(error.message);
            console.log(error.message)
        });
    }
</script>
</body>
</html>