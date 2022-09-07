<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>회원가입</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<link href="../css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="../css/cover.css" rel="stylesheet">
    <link href="../css/signin.css" rel="stylesheet">
    
</head>
  
<body class="d-flex h-100 text-center text-bg-dark">
    
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<header class="mb-auto">
		<div>
			<h3 class="float-md-start mb-0">Cajari</h3>
			<nav class="nav nav-masthead justify-content-center float-md-end"></nav>
		</div>
	</header>

	<main class="form-signin w-100 m-auto">
		<form method="POST" action="${pageContext.request.contextPath }/user/signin">   
			<h1 class="h3 mb-3 fw-normal" style="font-size:50px">Cajari</h1>
			<h1 class="h3 mb-3 fw-normal">Sign In</h1>
			
			<div class="row mt-1">
				<div class="col-12 text-info">
					${REDIRECT_ATTR.error }
				</div>
			</div>
				
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingId" name="username" placeholder="id" value="${REDIRECT_ATTR.username }" required>
				<label for="floatingPassword">* 아이디를 입력하세요</label>
			</div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingName" name="name" placeholder="name" value="${REDIRECT_ATTR.name }" required>
				<label for="floatingPassword">* 이름</label>
			</div>
			
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="password" placeholder="password" required>
				<label for="floatingPassword">* 비밀번호</label>
			</div>
			
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="re_password" placeholder="re_password" required>
				<label for="floatingPassword">* 비밀번호확인</label>
			</div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingPassword" name="phone_number" placeholder="phoneNum" value="${REDIRECT_ATTR.phone_number }" required>
				<label for="floatingPassword">* 핸드폰</label>
			</div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingPassword" name="car_name" placeholder="carName">
				<label for="floatingPassword">차종</label>
			</div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingPassword" name="car_number" placeholder="carNum">
				<label for="floatingPassword">차량번호</label>
			</div>
			
			<p style="font-size:15px">이미 Cajari 계정이 있으신가요?
				<a href="${pageContext.request.contextPath }/user/login">로그인하기</a>
			</p>	
			<button class="w-30 btn btn-lg btn-light" type="submit">Sign in</button>	
				
				
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
			
		</form>
	</main>
	
	<footer class="mt-auto text-white-50">
	<p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
	</footer>
	
	</div>
    
</body>

</html>