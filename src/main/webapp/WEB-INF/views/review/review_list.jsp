<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Search_Main</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/51772bd9bd.js"></script>
	<script src="${pageContext.request.contextPath }/js/list.js"></script>
    

    

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
    <link href="../css/search_main.css" rel="stylesheet">
  </head>
  <body class="d-flex h-100 text-center text-bg-dark">
    
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="mb-auto">
    <div>
      <h3 class="float-md-start mb-0">Cajari</h3>
      <nav class="nav nav-masthead justify-content-center float-md-end">
        <a class="nav-link fw-bold py-1 px-0 active" aria-current="page" href="${pageContext.request.contextPath }/home">Home</a>
        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/search/search_main">Service</a>
        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/aboutus/aboutus">About us</a>
        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/review/review_list">Review</a>
        <a class="nav-link fw-bold py-1 px-0" href="${pageContext.request.contextPath }/qna/qna_list">Q & A</a>
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
    
    
    <div class="container mt-3">
        <h2>목록</h2>
        <div class="mb-3 mt-3 clearfix">
            <span class="float-start me-2">총 ${cnt }개</span>
            <span class="float-start">page ${page }/${totalPage }</span>
            <span class="float-end">
                <form name="frmPageRows">
                    <input type="hidden" name="page" value="${page }">
                    <select class="form-select" name="pageRows">
                        <option value="10" ${pageRows==10 ? 'selected' : '' }>10</option>
                        <option value="15" ${pageRows==15 ? 'selected' : '' }>15</option>
                        <option value="20" ${pageRows==20 ? 'selected' : '' }>20</option>
                        <option value="50" ${pageRows==50 ? 'selected' : '' }>50</option>
                    </select>
                </form>
            </span>
        </div>          
        
        <table class="table table-hover">
            <thead class="table-success">
                <tr>
                    <th>#</th>
                    <th>제목</th>
                    <th>주차장</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="dto" items="${list }">
                <tr>
                    <td>${dto.id }</td>
                    <td><a href="detail?id=${dto.id }">${dto.subject }</a></td>
                    <td>${dto.user_id.username }</td>
                    <td>${dto.parking_id.parking_name }</td>
                    <td>${dto.viewCnt }</td>
                    <td>${dto.regDateTime}</td>
                </tr>            
            </c:forEach>           
            </tbody>
        </table>
        
    <!-- pagination -->
    <div class="container mt-1">
        <ul class="pagination justify-content-center">
            <%-- << 표시 여부 --%>   
            <c:if test="${page > 1 }">
            <li class="page-item"><a class="page-link" href="${url }" title="처음"><i class='fas fa-angle-double-left'></i></a></li>
            </c:if>     
        
            <%-- < 표시 여부 --%>
            <c:if test="${startPage > 1 }">
            <li class="page-item"><a class="page-link" href="${url }?page=${startPage - 1 }"><i class='fas fa-angle-left'></i></a></li>
            </c:if>
            
            <%-- 페이징 안의 '숫자' 표시 --%> 
            <c:if test="${totalPage > 1 }">
                <c:forEach var="k" begin="${startPage }" end="${endPage }">
                <c:choose>
                    <c:when test="${k != page }">
                        <li class="page-item"><a class="page-link" href="${url }?page=${k }">${k }</a></li>        			
                    </c:when>
                    <c:otherwise>
                        <li class="page-item active"><a class="page-link" href="javascript:void(0);">${k }</a></li>
                    </c:otherwise>
                </c:choose>
                </c:forEach>    
            </c:if>
                        
            <%-- > 표시 여부 --%>
            <c:if test="${totalPage > endPage }">
            <li class="page-item"><a class="page-link" href="${url }?page=${endPage + 1 }"><i class='fas fa-angle-right'></i></a></li>
            </c:if>
            
            <%-- >> 표시 여부 --%>
            <c:if test="${page < totalPage }">
            <li class="page-item"><a class="page-link" href="${url }?page=${totalPage }"><i class='fas fa-angle-double-right'></i></a></li>
            </c:if>
            
        </ul>
    </div>
    <!-- pagination -->


        
        
    </div>
    
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
   <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script> -->

    
  </body>
</html>