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
    <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
</head>
<body class="text-center">

<%@include file="nav-bar.jsp"%>

<section class="form-signin">
        <form>
            <h1 class="h3 mb-3 fw-normal">Codeum Shopping</h1>
            <h5 class="h5 mb-5 fw-normal">Please sign in</h5>

            <div class="form-floating">
                <input type="email" class="form-control" id="signin-email" placeholder="name@example.com">
                <label for="signin-email">Email address</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="signin-password" placeholder="Password">
                <label for="signin-password">Password</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit" onclick="singin()" >Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
        </form>
</section>

<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/js/member.js" type="text/javascript"></script>
</body>
</html>