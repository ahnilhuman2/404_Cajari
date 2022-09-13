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
<title>Login</title>
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
	margin: 30px 800px;
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
			<div class="frame" style="text-align: center;">
				<br> <br>
				<main class="form-signin w-100 m-auto">
					<form action="${pageContext.request.contextPath }/user/login"
						method="POST">
						<h1 class="h3 mb-3 fw-normal" style="font-size: 50px">Cajari</h1>
						<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

						<div class="row mt-1">
							<div class="col-12 text-info">${REDIRECT_ATTR.error }</div>
						</div>

						<div class="form-floating" style="">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="id"
								value="${REDIRECT_ATTR.username }" required> <label
								for="floatingInput">Id</label>
						</div>

						<div class="form-floating">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="password" required> <label
								for="floatingPassword">Password</label>
						</div>
						<br>
						<div class="checkbox mb-3">
							<label> <input type="checkbox" value="remember-me">
								Remember me
							</label>
						</div>
						<br>
						<button
							class="btn btn-lg btn-secondary fw-bold border-white bg-black"
							type="submit">Login</button>

					</form>
				</main>
			</div>
		</div>
	</div>
</body>
</html>