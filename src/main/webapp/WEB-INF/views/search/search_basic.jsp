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
<title>Basic Search</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
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

.carousel-item {
	min-height: 200px;
	background: no-repeat center center scroll;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

img {
	max-width: 100%;
	height: 350px;
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
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath }/home">Home</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link active dropdown-toggle"
									data-bs-toggle="dropdown"
									href="${pageContext.request.contextPath }/search/search_main"
									aria-current="page" role="button" aria-expanded="false">Search</a>
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
									href="${pageContext.request.contextPath }/aboutus">About us</a></li>
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
										<form
											action="${pageContext.request.contextPath }/mypage/my_information"
											method="POST">
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

	<div class="col-lg-12">
		<div style="text-align: center">
			<h1>Cajari</h1>
			<h3>검색으로 찾기</h3>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-4"></div>

			<div class="col-lg-4">
				<div class="input-group mb-3">
					<input type="text" id="inputaddress" class="form-control"
						placeholder="지역을 입력하세요" aria-label="Recipient's username"
						aria-describedby="button-addon2">
					<button class="btn btn-dark" type="button" id="button-addon2"
						onclick="test1()">찾기</button>
				</div>
			</div>

			<div class="col-lg-4">
				<a class="btn btn-outline-dark" href="../reserve/reserve_write">예약하기</a>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>

			<div class="col-lg-8">
				<table id="table" class="table">
					<thead>
						<tr>
							<th>주차장</th>
							<th>주소</th>
							<th>야간개방</th>
							<th>종류</th>
							<th>유료/무료</th>
							<th>연락처</th>
						</tr>
					</thead>
					<tbody id="tbody">

					</tbody>
				</table>
			</div>

			<div class="col-lg-2"></div>

		</div>
	</div>
	<script>
	    function test1() {
	      let address = $("#inputaddress").val()
	      if (!address) {
	        return
	      }
	      $.ajax({
	        url: "http://openapi.seoul.go.kr:8088/41766e4d5461686e35336e4a557477/json/GetParkInfo/1/5/" + address + "/",
	        type: "get",
	        crossDomain: true,
	        success: function (json, status) {
	          tbody = $("#tbody")
	          tbody.empty();
	          console.log(json);
	          json.GetParkInfo.row.forEach(function (r) {
	            console.log(r)
	            markup = "<tr>";
	            markup += "<td>" + r.PARKING_NAME + "</td>";
	            markup += "<td>" + r.ADDR + "</td>";
	            markup += "<td>" + r.NIGHT_FREE_OPEN_NM + "</td>";
	            markup += "<td>" + r.OPERATION_RULE_NM + "</td>";
	            markup += "<td>" + r.PAY_NM + "</td>";
	            markup += "<td>" + r.TEL + "</td>";
	            markup += "</tr>";
	            tbody.append(markup)
	
	          })
	        }
	      });
	    }
  </script>
</body>
</html>