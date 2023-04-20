<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">Codeum Shopping</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                <li class="nav-item" id="signin-nav"><a class="nav-link" href="#!" data-bs-toggle="modal" data-bs-target="#signin">Sign in</a></li>
                <li class="nav-item" id="logout-nav" style="display: none"><a class="nav-link" href="#" onclick="logOut()">Log out</a></li>
            </ul>
            <form class="d-flex">
                <button class="btn btn-outline-dark" type="button" onclick="toLikeList()">
                    <i class="bi-cart-fill me-1"></i>
                    관심상품
                </button>
            </form>
        </div>
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" data-bs-backdrop="static" id="signin" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">로그인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-floating">
                        <input id="signin-email" type="text" class="form-control"  placeholder="name@example.com">
                        <label for="signin-email">Email</label>
                    </div>
                    <div class="form-floating" style="margin-bottom: 15px;">
                        <input id="signin-password" type="password" class="form-control" placeholder="Password">
                        <label for="signin-password">Password</label>
                    </div>

                    <button class="w-100 btn btn-lg btn-secondary" onclick="singin()" type="button" id="login">로그인</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">나가기</button>
                <button class="btn btn-primary" data-bs-target="#signup" data-bs-toggle="modal" data-bs-dismiss="modal">회원가입 화면으로</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" data-bs-backdrop="static" id="signup" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel2">회원가입</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div >
                        <div class="input-group mb-3">
                            <input type="text" id="email" name="email" autocomplete="off"  class="form-control" placeholder="email" aria-label="email" aria-describedby="button-addon2" oninput="checkActivate()">
                            <button class="btn btn-outline-secondary" type="button" id="duplicate-check-btn" onclick="duplicateCheck()">중복체크</button>
                            <input type="checkbox" id="user-check" autocomplete="off" class="form-control" aria-label="user-check" aria-describedby="basic-addon1" style="display: none">
                        </div>

                        <div class="input-group mb-3">
                            <input type="password" id="password" name="password" autocomplete="off" class="form-control" placeholder="password" aria-label="password" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3">
                            <input type="text" id="name" name="name" autocomplete="off" class="form-control" placeholder="name" aria-label="name" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3">
                            <input type="text" id="address" onclick="searchAddress()" name="address" autocomplete="off" class="form-control" placeholder="address" aria-label="address" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3">
                            <input type="text" id="phoneNumber" oninput="oninputPhone(this)" name="phoneNumber" autocomplete="off" class="form-control" placeholder="000-0000-0000" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13" aria-label="phoneNumber" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3" style="display: none">
                            <input type="checkbox" id="smsAuth" name="smsAuth" autocomplete="off" class="form-control" aria-label="sms auth" aria-describedby="basic-addon1">
                        </div>

                        <button class="w-100 btn btn-lg btn-secondary" id="go-to-sms-auth" onclick="sendAuthSms()" type="button" style="margin-bottom: 15px">SMS 인증하기</button>
                        <button class="w-100 btn btn-lg btn-secondary" onclick="signUp()" type="button" disabled id="sign-up-btn">회원가입</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">나가기</button>
                <button type="button" data-bs-target="#signin" data-bs-toggle="modal" class="btn btn-primary" >로그인 화면으로</button>
            </div>
        </div>
    </div>
</div>
<div class="modal in" data-bs-backdrop="static" id="sms-auth" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">SMS 인증</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-floating">
                        <input id="auth-code" type="text" class="form-control"  placeholder="인증번호를 입력하세요.">
                        <label for="signin-email">인증번호</label>
                    </div>
                    <button class="w-100 btn btn-lg btn-secondary" onclick="checkAuthKey()" type="button" id="auth-button">인증 완료</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-target="#sms-auth" data-bs-toggle="modal" data-bs-dismiss="modal">나가기</button>
            </div>
        </div>
    </div>
</div>
