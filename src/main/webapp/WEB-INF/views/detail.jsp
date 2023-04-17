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
        <h2 class="fw-bolder mb-4">Related products</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..."/>
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale
                    </div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..."/>
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Special Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$20.00</span>
                            $18.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale
                    </div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..."/>
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Sale Item</h5>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$50.00</span>
                            $25.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..."/>
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Popular Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            $40.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



</body>
<!-- 결제 창 호출 스크립트 -->
<script>
    const IMP = window.IMP;
    IMP.init("imp61605278");

    function requestPay() {

        // 결제 수단 설정
        let pay_method = $("#pay_method option:selected").val();
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
</html>
