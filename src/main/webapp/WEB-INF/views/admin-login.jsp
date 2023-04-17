<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- bootstrap 적용 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>
<body>
<div class="container">
    <div class="col-6 mx-auto">
        <form class="form-control" >
            <label for="email" class="form-label">아이디</label>
            <input type="text" name="email" id="email" class="form-control">
            <label for="password" class="form-label">비밀번호</label>
            <input type="text" name="password" id="password" class="form-control">
            <div class="d-grid">
                <input type="button" class="btn btn-primary btn-block my-3" id="login" value="로그인"/>
                <button type="reset" class="btn btn-danger">취소</button>
                <input type="button" class="btn btn-success btn-block my-3" id="join" value="회원가입"/>
            </div>

        </form>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>

    document.getElementById("join").onclick = function login() {
        window.location.href = "/admin/join"
    },



    document.getElementById("login").onclick = function login() {

        let email = document.getElementById("email").value
        let password = document.getElementById("password").value

        axios.post("/api/admin/login", {
            email : email,
            password : password
        })
            .then((response) => {
                localStorage.clear()
                localStorage.setItem("email",response.data)
                alert("로그인 성공 ")
            })
            .catch((error) => {
                console.log(error)
            })
    }
</script>
</html>