<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
	<c:when test="${empty list || fn:length(list) == 0 }">
		<script>
			alert("리뷰가 삭제되거나 없습니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>

		<c:set var="dto" value="${list[0] }" />

		<!DOCTYPE html>
		<html lang="ko">
		<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>review - ${dto.subject }</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<style>

/*profile*/
.profile-section {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: flex-start;
	margin: 100px 100px;
}

#profile-picture {
	width: 200px;
	margin-right: 60px;
}

#info-edit {
	margin-bottom: 10px;
}

#auth {
	float: right;
	margin: 0 120px 0 0;
	word-spacing: 5px;
}

.introduction>h1 {
	margin: 0px;
	font-family: 'Montserrat', sans-serif;
	font-size: 20px;
}

.introduction>p {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 300;
	font-size: 16px;
	margin-bottom: 40px;
}

.frame {
	margin: 30px 150px;
	display: flex;
	flex-direction: column;
}
</style>
<body>
	<header class="py-3 mb-4 border-bottom">
		<div class="row">
			<div class="col-lg-12">
				<div class="container">
					<div class="row">
						<div class="col-lg-1">
							<h1 class="fw-normal">Cajari</h1>
						</div>

						<div class="col-lg-8">
							<ul class="nav nav-tabs align-itmes-center">
								<li class="nav-item"><a class="nav-link active"
									aria-current="page"
									href="${pageContext.request.contextPath }/home">Home</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
									href="${pageContext.request.contextPath }/search/search_main"
									role="button" aria-expanded="false">Search</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath }/search/search_basic">Basic
												Search</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath }/search/search_map">Map
												Search</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath }/review/review_list">Review</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath }/qna/qna_list">Q &
										A</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath }/aboutus">About us</a>
								</li>
							</ul>
						</div>

						<div class="col-lg-3">
							<div>
								<span class="align-items-center badge bg-light"><p
										class="text-dark">${sessionScope.PRINCIPAL.authorities }</p>
									<h6 class="text-dark">
										<span class="text-dark">${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })</span>님
										환영합니다
									</h6>
									<div class="text-center">
										<form action="${pageContext.request.contextPath }/mypage"
											method="GET">
											<button type="submit" class="btn btn-outline-dark"
												style="float: right;">mypage</button>
										</form>
										<form action="${pageContext.request.contextPath }/cover"
											method="POST">
											<button type="submit" class="btn btn-outline-dark"
												style="float: right;">logout</button>
										</form>
									</div> </span>
							</div>
						</div>
						<!-- end col -->

					</div>
					<!-- end row -->
				</div>
				<!-- end container -->
			</div>
		</div>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="frame">
					<div class="container mt-3">
						<h2>수정</h2>
						<hr>
						<div class="mb-3 mt-3 clearfix">
							<span class="float-start me-2">id: ${dto.id }</span> <span
								class="float-end ms-4">작성일: ${dto.regDateTime }</span> <span
								class="float-end">조회수: ${dto.viewcnt }</span>
						</div>

						<form action="review_update" method="POST">
							<input type="hidden" name="id" value="${dto.id }" />

							<div class="mb-3">
								<label for="name">작성자:</label> <span class="form-control"
									readonly>${PRINCIPAL.username }(${PRINCIPAL.name })</span>
							</div>
							<div class="mb-3 mt-3">
								<label for="subject">제목:</label> <input type="text"
									class="form-control" id="subject" name="subject"
									value="${dto.subject }" required>
							</div>
							<div class="mb-3 mt-3">
								<label for="subject">주차장:</label> <input type="text"
									class="form-control" id="parkingName" name="parkingName"
									value="${dto.parking_write }" required>
							</div>
							<div class="mb-3 mt-3">
								<label for="content">내용:</label>
								<textarea class="form-control" rows="5" id="content"
									name="content">${dto.content }</textarea>
							</div>

							<!-- 하단 링크 -->
							<button type="submit" class="btn btn-outline-dark">수정완료</button>
							<button type="button" class="btn btn-outline-dark"
								onclick="history.back()">이전으로</button>
							<a class="btn btn-outline-dark" href="review_list?page=${page != null ? page : '' }">목록</a>
							<!-- 하단 링크 -->

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
		</html>
	</c:otherwise>
</c:choose>