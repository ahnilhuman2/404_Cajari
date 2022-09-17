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
<title>Profile</title>
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
	width: 150px;
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
								<li class="nav-item"><a class="nav-link active"
									aria-current="page" href="/404_Cajari/home">Home</a></li>
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

	<div class="frame" style="text-align: center;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<img class="img-fluid rounded-circle" id="profile-picture"
						src="../Image/gv70.png">
					<h1>My Profile</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="frame" style="text-align: center;">
					<main class="form-signin w-100 m-auto">
						<form>

							<div class="row mt-1">
								<div class="col-12 text-info">${REDIRECT_ATTR.error }</div>
							</div>

							<div class="form-floating" style="background-color: light">
								<span class="form-control" id="id"
									aria-label="readonly input example" readonly>닉네임 : ${sessionScope.PRINCIPAL.username }</span>
							</div>
							<br>

							<div class="form-floating">
								<span type="text" class="form-control" id="name" readonly>이름 : ${sessionScope.PRINCIPAL.name }</span>
							</div>
							<br>

							<div class="form-floating">
								<span type="text" class="form-control" id="phone_number"
									readonly>핸드폰 :  ${sessionScope.PRINCIPAL.phone_number } </span>
							</div>
							<br>

							<div class="form-floating">
								<span type="text" class="form-control" id="car_name" readonly>
									차종 : ${sessionScope.PRINCIPAL.car_name } </span>
							</div>
							<br>

							<div class="form-floating">
								<span type="text" class="form-control" id="car_number" readonly>
									차번호 : ${sessionScope.PRINCIPAL.car_number } </span>
							</div>
							<br> <a
								class="btn btn-lg btn-secondary fw-bold border-white bg-black"
								href="${pageContext.request.contextPath }/mypage">마이페이지로</a>

						</form>
					</main>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>

</body>
</html>