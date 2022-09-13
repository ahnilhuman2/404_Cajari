<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cover</title>
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

#carouselExampleControls>.carousel-inner>.carousel-item active>img {
	top: 0;
	left: 0;
	min-width: 100%;
	min-height: 400px;
}

.container {
	margin-bottom: 20px;
}

.frame {
	margin: 30px 150px;
	display: flex;
	flex-direction: column;
}
</style>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="container">
				<header
					class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
					<h2 class="fw-normal">Cajari</h2>
				</header>
			</div>
		</div>

		<div class="col-lg-12">
			<div style="text-align: center">
				<h1>Welcome</h1>
				<h3>Cajari</h3>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-lg-4"></div>

				<div class="col-lg-4">
					<div id="carouselExampleControls" class="carousel slide"
						data-bs-ride="carousel">

						<div class="carousel-inner">

							<div class="carousel-item active">
								<img src="./Image/Jari1.png" class="d-block w-100" alt="Cajari1">
							</div>

							<div class="carousel-item">
								<img src="./Image/Jari2.png" class="d-block w-100" alt="Cajari2">
							</div>

							<div class="carousel-item">
								<img src="./Image/Jari3.png" class="d-block w-100" alt="Cajari3">
							</div>

							<button class="carousel-control-prev" type="button"
								data-bs-target="#carouselExampleControls" data-bs-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Previous</span>
							</button>
							<button class="carousel-control-next" type="button"
								data-bs-target="#carouselExampleControls" data-bs-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Next</span>
							</button>

						</div>
					</div>
				</div>

				<div class="col-lg-4"></div>

			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div style="text-align: center">
						<a href="${pageContext.request.contextPath }/user/login"
							class="btn btn-lg btn-secondary fw-bold border-white bg-black">Login</a>
						<a href="${pageContext.request.contextPath }/user/signin"
							class="btn btn-lg btn-secondary fw-bold border-white bg-black">Sign
							in</a>
					</div>
				</div>
			</div>
		</div>


		<div class="col-lg-12">
			<div class="frame"></div>
		</div>
	</div>
</body>
</html>