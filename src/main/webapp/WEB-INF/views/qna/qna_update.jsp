<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${empty list || fn:length(list) == 0 }">
		<script>
			alert("리뷰가 삭제되거나 없습니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
	
	<c:set var="dto" value="${list[0] }"/>
	
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review - ${dto.subject }</title>
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
                <h2>수정</h2>
                <hr>
                <div class="mb-3 mt-3 clearfix">
                    <span class="float-start me-2">id: ${dto.q_id }</span>
                    <span class="float-end ms-4">작성일: ${dto.regDateTime }</span>
                    <span class="float-end">조회수: ${dto.viewcnt }</span>
                </div>
        
                <form action="qna_update" method="POST">
                    <input type="hidden" name="id" value="${dto.q_id }"/>
                    <div class="mb-3">
                        <label for="name">작성자:</label>
                        <span class="form-control" readonly>${dto.user.username } (${dto.user.name })</span>
                    </div>    
                    <div class="mb-3 mt-3">
                        <label for="subject">제목:</label>
                        <input type="text" class="form-control" id="subject" placeholder="제목을 입력하세요" name="subject" value="{dto.subject }" required>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="content">내용:</label>
                        <textarea class="form-control" rows="5" id="content" placeholder="내용을 입력하세요" name="content">${dto.content }</textarea>
                    </div>
        
                    <!-- 하단 링크 -->
                    <button type="submit" class="btn btn-outline-dark">수정완료</button>
                    <button type="button" class="btn btn-outline-dark" onclick="history.back()">이전으로</button>
                    <a class="btn btn-outline-dark" href="qna_list">목록</a>
                    <!-- 하단 링크 -->
        
                </form>
            </div>
             </div><!-- end frame -->
              </div><!-- end forth grid -->
        </body><!-- end body -->
        
	</html>
</c:otherwise>
</c:choose>