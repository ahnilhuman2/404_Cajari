<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Qna_WritePage</title>

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
    
</head>

<script>
$(document).ready(function(){
	$('#content').summernote({
		height: 300,
	});
});
</script>  

<body class="d-flex h-100 text-center text-bg-dark">
    
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  	<header class="mb-auto">
	    <div>
	      <h3 class="float-md-start mb-0">Cajari</h3>
	      <nav class="nav nav-masthead justify-content-center float-md-end">
	        <a class="nav-link fw-bold py-1 px-0 active" aria-current="page" href="${pageContext.request.contextPath }/home">Home</a>
	        <a class="nav-link fw-bold py-1 px-0" href="#">Service</a>
	        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/aboutus/aboutus">About us</a>
	        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/review/review_list">Review</a>
	        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/qna/qna_list">Q & A</a>
	      </nav>
	    </div>
  	</header>
  
  	<main class="px-3">
  	<h1>Cajari</h1>
    
		  <div class="container mt-3">
		        <h2>작성</h2>
		        <hr>
		        <form name="frm" action="qna_write" method="POST"
		        	enctype="Multipart/form-data">
		            <div class="mb-3">
		                <label for="name">작성자:</label>
		                <input type="text" class="form-control" id="name" placeholder="작성자를 입력하세요" name="name" required>
		            </div>
		            <div class="mb-3 mt-3">
		                <label for="subject">제목:</label>
		                <input type="text" class="form-control" id="subject" placeholder="제목을 입력하세요" name="subject" required>
		            </div>
		            <div class="mb-3 mt-3">
		                <label for="content">내용:</label>
		                <textarea class="form-control" rows="5" id="content" placeholder="내용을 입력하세요" name="content"></textarea>
		            </div>
		
		            <button type="submit" class="btn btn-outline-light">작성완료</button>
		            <a class="btn btn-outline-light" href="qna_list">목록</a>
		        </form>
		    </div>
  	</main>

	  <footer class="mt-auto text-white-50">
	    <p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
	  </footer>
  
	</div>

  </body>
  
</html>