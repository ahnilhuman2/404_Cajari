<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Home</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


	<link href="./css/bootstrap.min.css" rel="stylesheet">

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
    <link href="./css/home.css" rel="stylesheet">
    
</head>
 
<body class="d-flex h-100 text-center text-bg-dark">
    
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	  <header class="mb-auto">
	    <div>
	      <h3 class="float-left-start mb-100">Cajari</h3>
	      <nav class="nav nav-masthead justify-content-center float-center-end">
	      
	      <!-- 누구에게나 보이는 메뉴 로그인여부 x -->
	        <a class="nav-link fw-bold py-1 px-0 active" aria-current="page" href="${pageContext.request.contextPath }/home">Home</a>
	        <a class="nav-link fw-bold py-1 px-0 " href="${pageContext.request.contextPath }/search/search_main">Service</a>
	        <a class="nav-link fw-bold py-1 px-0 " href="${pageContext.request.contextPath }/aboutus/aboutus">About us</a>

	      <!-- 로그인한사람에게만 보이는 메뉴 -->
	      <c:if test="${not empty sessionScope.PRINCIPAL }">
	        <a class="nav-link fw-bold py-1 px-0 " href="${pageContext.request.contextPath }/review/review_list">Review</a>
	        <a class="nav-link fw-bold py-1 px-0 " href="${pageContext.request.contextPath }/qna/qna_list">Q & A</a>
	      </c:if>  
	       
	        <c:if test="${not empty sessionScope.PRINCIPAL }">
	        <span class="d-flex">
	        	<span class="text-light p-2"><span>${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })</span> 님 환영합니다</span>
	        	<form action="${pageContext.request.contextPath }/user/logout" method="POST">
		        	<span><button class="btn btn-danger" type="submit">Logout</button></span>
	        	</form>
	        	<form action="${pageContext.request.contextPath }/mypage/my_information" method="POST">
	        		<span><button class="btn btn-primary" type="submit">MyPage</button></span>
	        	</form>
	        </span>
	        </c:if> 
	      </nav>
	    </div>
	  </header>
	  	<br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	
	  <main class="px-3">
	    <h1>Cajari</h1>
	    <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
	    <p class="lead">
	      <button type="button" class="btn btn-warning">검색으로 찾기</button>
	      <button type="button" class="btn btn-warning">지도로 찾기</button>
	    </p>
	    
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	    <br>
	  </main>
	
	  <footer class="mt-auto text-white-50">
	    <p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
	  </footer>
	  
	</div>
    
  </body>
</html>