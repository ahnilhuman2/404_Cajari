<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<c:choose>
	<c:when test="${empty list || fn:length(list) == 0}">
		<script>
			alert("해당 정보가 삭제되거나 없습니다");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
    	<c:set var="dto" value="${list[0]}"/>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    	const conPath = "${pageContext.request.contextPath}";
    	const logged_id = ${PRINCIPAL.id};
    </script>
    <script src="${pageContext.request.contextPath }/js/detail.js"></script>    

    <title>조회 - ${dto.subject}</title>
</head>

<script>
function chkDelete(){
	let answer = confirm("삭제하시겠습니까?");
	if(answer){
		document.forms['frmDelete'].submit();
	}
}
</script>

<body>
    <%-- 인증 헤더 --%>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <div class="container mt-3">
        <h2>조회 - ${dto.subject }</h2>
        <hr>
        <div class="mb-3 mt-3 clearfix">
            <span class="float-start me-2">id: ${dto.id}</span>
            <span class="float-end ms-4">작성일: ${dto.regDateTime }</span>
            <span class="float-end">조회수: ${dto.viewCnt }</span>
        </div>

        <section>
        	<form name="frmDelete" action="delete" method="POST">
        		<input type="hidden" name="id" value="${dto.id }">
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

			<!-- 첨부파일목록 -->
			<div class="container mt-3 mb-3 border rounded">
			    <div class="mb-3 mt-3">
			        <label>첨부파일:</label>
			        <!--첨부파일 이름, 다운로드 링크 -->
			        <ul class="list-group mb-1">
			            <c:forEach var="fileDto" items="${fileList }">
			                <li class="list-group-item"><a href="download?id=${fileDto.id }">${fileDto.source }</a></li>
			            </c:forEach>
			        </ul>
			        <%-- 이미지인 경우 보여주기 --%>
			        <c:forEach var="fileDto" items="${fileList }">
			            <c:if test="${fileDto.image == true }">
			            <div>
			                <img src="${pageContext.request.contextPath}/upload/${fileDto.file }" class="img-thumbnail">
			            </div>
			            </c:if>
			        </c:forEach>
			    </div>
			</div>
			<!-- 첨부파일목록 -->

            <!-- 하단 링크 -->
            <c:if test="${fn:contains(PRINCIPAL.authorities, 'ROLE_MEMBER' ) && (PRINCIPAL.id == dto.user.id)}">
            <a class="btn btn-outline-dark" href="update?id=${dto.id }">수정</a>
            </c:if>
            
            <a class="btn btn-outline-dark" href="list?page=${page != null ? page : '' }">목록</a>
            
            <c:if test="${fn:contains(PRINCIPAL.authorities, 'ROLE_MEMBER' ) && (PRINCIPAL.id == dto.user.id)}">
            <button type="button" class="btn btn-outline-dark" onclick="chkDelete()">삭제</button>
            </c:if>
            
            <c:if test="${fn:contains(PRINCIPAL.authorities, 'ROLE_MEMBER' )}">
            <a class="btn btn-outline-dark" href="write">작성</a>
            </c:if>
            <!-- 하단 링크 -->        

			<!-- 댓글 -->
			<jsp:include page="qna_comment.jsp"/>
			<!-- 댓글 -->

        </section>
    </div>
</body>

</html>

	</c:otherwise>
</c:choose>   
    
    