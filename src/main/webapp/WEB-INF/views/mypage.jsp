<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
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
	width: 350px;
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
	margin: 1px 150px;
	display: flex;
	flex-direction: column;
}
</style>
</head>
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
								<li class="nav-item"><a class="nav-link"
									href="/404_Cajari/home">Home</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
									href="/404_Cajari/search/search_main" role="button"
									aria-expanded="false">Search</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item"
											href="/404_Cajari/search/search_basic">Basic Search</a></li>
										<li><a class="dropdown-item"
											href="/404_Cajari/search/search_map">Map Search</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-link"
									href="/404_Cajari/review/review_list">Review</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/404_Cajari/qna/qna_list">Q & A</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/404_Cajari/aboutus">About us</a></li>
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

					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="col-lg-12">
		<div style="text-align: center">
			<h1>My Page</h1>
		</div>
	</div>

	<div class="col-lg-12">
		<div class="frame">
			<div class="profile-section">
				<img class="img-fluid rounded-circle" id="profile-picture"
					src="./Image/gv70.png">
				<div class="introduction">
					<br>
					<p>${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })<br>${sessionScope.PRINCIPAL.authorities }</p>
					<a class="btn btn-outline-dark" style="margin-bottom: 10px;"
						href="${pageContext.request.contextPath }/user/infoUpdate">내
						정보</a> <br> <a class="btn btn-outline-dark"
						href="${pageContext.request.contextPath }/reserve/reserve_list">예약내역</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>