<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>qna_list</title>
    <link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>

    /*profile*/

.profile-section{
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: flex-start;

    margin: 100px 100px;
}


#profile-picture{
    width: 200px;
    margin-right: 60px;
}

#info-edit{
    margin-bottom:10px;
}

#auth{
    float:right;
    margin: 0 120px 0 0;
    word-spacing: 5px;
    
}

.introduction>h1{
    margin: 0px;
    font-family: 'Montserrat', sans-serif;
    font-size: 20px;
}

.introduction>p{
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 300;
    font-size: 16px;

    margin-bottom: 40px;
}



.frame{
    margin: 30px 150px;
    display: flex;
    flex-direction: column;
}

    </style>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <div class="container">
                <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                    <h2 class="fw-normal">Cajari</h2>
            
                  <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="${pageContext.request.contextPath }/home" class="nav-link px-2 link-dark">Home</a></li>
                    <li><a href="#" class="nav-link px-2 link-dark">Service</a></li>
                    <li><a href="${pageContext.request.contextPath }/aboutUs/aboutUs" class="nav-link px-2 link-dark">About us</a></li>
                    <li><a href="${pageContext.request.contextPath }/review/review_list" class="nav-link px-2 link-dark">Review</a></li>
                    <li><a href="#" class="nav-link px-2 link-dark">Q & A</a></li>
                  </ul>
            
                  <div class="col-md-3 text-end">
                    <form action="${pageContext.request.contextPath }/mypage/my_information" method="POST">
                        <button type="submit" class="btn btn-outline-primary me-2 ">mypage</button>
                  	</form>
                  	<form action="${pageContext.request.contextPath }/cover" method="POST">                	
                        <button type="submit" class="btn btn-outline-primary me-2 ">logout</button>
                  	</form>
                  </div>
                </header>
              </div>
        </div>
        <div class="col-lg-12">
            <div id = "auth" style="padding-left:50px">
                <span class="badge bg-primary">${sessionScope.PRINCIPAL.authorities }</span>
                <h6><span class="TODO">${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })</span>님 환영합니다</h6>
            </div>
        </div>
        <div class="col-lg-12">
            <div style="text-align:center">
                <h2>Cajari</h2>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="frame">
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
	                            <td>${dto.q_id }</td>
	                            <td><a href="detail?id=${dto.q_id }">${dto.subject }</a></td>
	                            <td>${dto.user.username }</td>
	                            <td>${dto.viewcnt }</td>
	                            <td>${dto.regDateTime }</td>
	                        </tr>            
                    	</c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col-12">
                        <a class="btn btn-outline-dark" href="qna_write">작성</a>
                    </div>
                </div>
                
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
                </div> <!-- end frame -->
            </div> <!-- end forth grid -->
        </div> <!-- end row -->
        
        
</body> 
</html>