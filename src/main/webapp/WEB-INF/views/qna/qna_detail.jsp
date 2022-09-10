<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${empty list || fn:length(list) == 0 }">
		<script>
			alert("QnA가 삭제되거나 없습니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
	
	<c:set var="dto" value="${list[0] }"/>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>조회 - ${dto.subject }</title>
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

<script>
function chkDelete() {
	let answer = confirm("삭제하시겠습니까?");
	if(answer) {
		document.forms['frmDelete'].submit();
	}
}
</script>


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
                <h2>조회 - ${dto.subject }</h2>
                <hr>
                <div class="mb-3 mt-3 clearfix">
                    <span class="float-start me-2">id: ${dto.q_id }</span>
                    <span class="float-end ms-4">작성일: ${dto.regDateTime }</span>
                    <span class="float-end">조회수: ${dto.viewcnt }</span>
                </div>
        
                <section>
                  <form name="frmDelete" action="qna_delete" method="POST">
                    <input type="hidden" name="id" value="${dto.q_id }">
                  </form>
                    <div class="mb-3">
                        <label for="name">작성자:</label>
                        <span class="form-control" >${dto.user.username } (${dto.user.name })</span>
                    </div>    
                    <div class="mb-3 mt-3">
                        <label for="subject">제목:</label>
                        <span class="form-control" >${dto.subject }</span>
                    </div>      
                    <div class="mb-3 mt-3">
                        <label for="content">내용:</label>
                        <span class="form-control" >${dto.content }</span>
                    </div>    
        
                    <!-- 하단 링크 -->
                    <c:if test="${(PRINCIPAL.id == dto.user.id)}">
                    <a class="btn btn-outline-dark" href="qna_update?id=${dto.q_id }">수정</a>
                    </c:if>
                    
                    <a class="btn btn-outline-dark" href="qna_list?page=${page != null ? page : '' }">목록</a>
                    
                    <c:if test="${(PRINCIPAL.id == dto.user.id)}">
                    <button type="button" class="btn btn-outline-dark" onclick="chkDelete()">삭제</button>
                    </c:if>
                    
                    <c:if test="${(PRINCIPAL.id == dto.user.id)}">
                    <a class="btn btn-outline-dark" href="qna_write">작성</a>
                    </c:if>
                    <!-- 하단 링크 -->        
        
                </section>
            </div>
            </div><!-- end frame -->
                </div><!-- end forth grid -->
            </div><!-- end row -->
        
        
		</body><!-- end body -->
	</html>

	</c:otherwise>
</c:choose>