let offset = 0;
let limit = 8;
let loading = false;

$(document).ready(function() {
    loadProductList(0, 8); // 초기 페이지 로딩 시 첫 번째 페이지 데이터를 받아옵니다.
    loadStoreList()
});

$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
        loadMore();
    }
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
                        '                <a class="btn btn-outline-dark mt-auto" onclick="addLikeList(\'' + product.productId + '\')">상품 상세보기</a>' +
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
                        '                <a class="btn btn-outline-dark mt-auto" onclick="addLikeList(\'' + product.productId + '\')" >상품 상세보기</a>' +
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

function loadStoreList() {
    $.ajax({
        type: "GET",
        url: "/api/admin/store/findAll", // 비동기 요청을 보낼 URL
        dataType: "json",
        success: function(response) {
            let storeList = response;
            let storeHtml = '';
            for (let i = 0; i < storeList.length; i++) {
                let store = storeList[i];
                if (i % 3 == 0) {
                    storeHtml += '<div class="carousel-item' + (i == 0 ? ' active' : '') + ' align-content-center text-center"><div class="row p-5 text-center mx-auto" style="max-width: 2000px">';
                }
                storeHtml += '<div class="col-md-4" ><div class="card mb-4 shadow-sm text-center mx-auto" >' +
                    '<img class="card-img-top" src="/upload/' + store.storeImg.storeImgSavedName + '"/>' +
                    '<div class="card-body"><p class="card-text">' + store.adminStoreName + '</p></div></div></div>';
                if ((i + 1) % 3 == 0 || i == storeList.length - 1) {
                    storeHtml += '</div></div>';
                }
            }
            $('#store-list').html(storeHtml);
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
}

function addLikeList(productId) {
    let memberId = localStorage.getItem("token")

    if (!memberId) {
        alert("로그인이 필요한 서비스입니다.")
        return
    }

    $.ajax({
        url: "/api/like/add/" + memberId + "/" + productId,
        type: "POST",
        success: function (data) {
            window.location.href = "/product-detail/" + productId
        },
        error: function (error) {
            alert(error.reaponse.data.message)
        }
    })
}

function searchByKeyword() {
    let offset = 0;
    let limit = 8;
    let loading = false;
    let keyword = document.getElementById("search-keyword").value

    if (keyword.trim() == '') {
        alert("검색어를 입력해주세요!")
        return
    }

    $("#product-list").empty()
    $('#store-list').empty()

    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMoreResult();
        }
    });

    $.ajax({
        url: "/product/searchproduct?keyword=" + keyword+ "&offset=" + offset + "&limit=" + limit,
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
                        '                <a class="btn btn-outline-dark mt-auto" onclick="addLikeList(\'' + product.productId + '\')">상품 상세보기</a>' +
                        '            </div>' +
                        '        </div>' +
                        '    </div>' +
                        '</div>';
                    productList.append(card); // 상품 리스트에 추가합니다.
                });
            }
        }
    });

    if (loading) {
        return;
    }

    loading = true;

    offset++;

    $.ajax({
        url: "/product/searchproduct?keyword=" + keyword+ "&offset=" + offset + "&limit=" + limit,
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
                        '                <a class="btn btn-outline-dark mt-auto" onclick="addLikeList(\'' + product.productId + '\')" >상품 상세보기</a>' +
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
