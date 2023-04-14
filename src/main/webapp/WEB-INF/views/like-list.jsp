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
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- Navigation-->
<%@include file="nav-bar.jsp"%>

<section class="py-5">
    <h4>관심 상품 리스트</h4>
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="input-here">
        </div>
    </div>
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
<script src="/js/member.js" type="text/javascript"></script>
<script type="text/javascript">
    let container = document.getElementById("input-here")
    let token = localStorage.getItem("token")

    axios.get("/api/interest/get", {
        headers: {
            Authorization: "Bearer " + token
        }
    }).then((response) => {
        console.log(response.data)
        for (let i = 0; i < response.data.length; i++) {
            let card = createCard(response.data[i].path, response.data[i].name, response.data[i].price, response.data[i].productId )
            container.appendChild(card);ㄹ
        }
    }).catch((error) => {
        console.log(error)
        alert(error.response.data.message)
    })

    function createCard(imageUrl, productName, productPrice, productId) {
        // 카드 요소 생성
        let cardDiv = document.createElement('div');
        cardDiv.classList.add('col', 'mb-5');

        let card = document.createElement('div');
        card.classList.add('card', 'h-100');
        cardDiv.appendChild(card);

        // 상품 이미지
        let img = document.createElement('img');
        img.classList.add('card-img-top');
        img.src = imageUrl;
        card.appendChild(img);

        // 상품 정보
        let cardBody = document.createElement('div');
        cardBody.classList.add('card-body', 'p-4');
        card.appendChild(cardBody);

        let textCenter = document.createElement('div');
        textCenter.classList.add('text-center');
        cardBody.appendChild(textCenter);

        // 상품 이름
        let productNameHeader = document.createElement('h5');
        productNameHeader.classList.add('fw-bolder');
        productNameHeader.textContent = productName;
        textCenter.appendChild(productNameHeader);

        // 상품 가격
        let productPriceSpan = document.createElement('span');
        productPriceSpan.textContent = productPrice;
        textCenter.appendChild(productPriceSpan);

        // 상품 링크
        let cardFooter = document.createElement('div');
        cardFooter.classList.add('card-footer', 'p-4', 'pt-0', 'border-top-0', 'bg-transparent');
        card.appendChild(cardFooter);

        let textCenter2 = document.createElement('div');
        textCenter2.classList.add('text-center');
        cardFooter.appendChild(textCenter2);

        let productLink = document.createElement('a');
        productLink.classList.add('btn', 'btn-outline-dark', 'mt-auto');
        productLink.href = '/product/productdetail/' + productId;
        productLink.textContent = '상품 상세보기';
        textCenter2.appendChild(productLink);

        return cardDiv;
    }
</script>
</body>
</html>
