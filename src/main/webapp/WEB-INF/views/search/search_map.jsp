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
    <title>Map Search</title>
    <link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e157a116310babfe805b8aca0d0245c8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- 제이쿼리 임포트 -->

    <!-- services와 clusterer, drawing 라이브러리 불러오기 -->
	  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e157a116310babfe805b8aca0d0245c8&libraries=services,clusterer,drawing"></script>
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


html, body {width:100%;height:100%;margin:0;padding:0;} 
.map_wrap {position:relative;overflow:hidden;width:100%;height:350px;}
.radius_border{border:1px solid #919191;border-radius:5px;}     
.custom_typecontrol {position:absolute;top:10px;right:10px;overflow:hidden;width:130px;height:30px;margin:0;padding:0;z-index:1;font-size:12px;font-family:'Malgun Gothic', '맑은 고딕', sans-serif;}
.custom_typecontrol span {display:block;width:65px;height:30px;float:left;text-align:center;line-height:30px;cursor:pointer;}
.custom_typecontrol .btn {background:#fff;background:linear-gradient(#fff,  #e6e6e6);}       
.custom_typecontrol .btn:hover {background:#f5f5f5;background:linear-gradient(#f5f5f5,#e3e3e3);}
.custom_typecontrol .btn:active {background:#e6e6e6;background:linear-gradient(#e6e6e6, #fff);}    
.custom_typecontrol .selected_btn {color:#fff;background:#425470;background:linear-gradient(#425470, #5b6d8a);}
.custom_typecontrol .selected_btn:hover {color:#fff;}   
.custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;} 
.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}     
.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}             
.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}

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
							    <li class="nav-item">
							    	<a  class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/home">Home</a>
							    </li>
							    <li class="nav-item dropdown">
							  		<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="${pageContext.request.contextPath }/search/search_main" role="button" aria-expanded="false">Search</a>
								    <ul class="dropdown-menu">
										<li><a class="dropdown-item" href="${pageContext.request.contextPath }/search/search_basic">Basic Search</a></li>
										<li><a class="dropdown-item" href="${pageContext.request.contextPath }/search/search_map">Map Search</a></li>
									</ul>
							    </li>
							    <li class="nav-item">
							    	<a class="nav-link" href="${pageContext.request.contextPath }/review/review_list">Review</a>
							    </li>
							    <li class="nav-item">
							    	<a class="nav-link" href="${pageContext.request.contextPath }/qna/qna_list">Q & A</a>
							    </li>
							    <li class="nav-item">
							    	<a class="nav-link" href="${pageContext.request.contextPath }/aboutus/aboutus">About us</a>
							    </li>
						    </ul>
						</div>
						
						<div class="col-lg-3">
							<div>
		               			<span class="align-items-center badge bg-light"><p class="text-dark">${sessionScope.PRINCIPAL.authorities }</p>
			                		<h6 class="text-dark"><span class="text-dark">${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })</span>님 환영합니다</h6>	
		                		<div class="text-center">
			                  		<form action="${pageContext.request.contextPath }/mypage/my_information" method="POST">
			                        	<button type="submit" class="btn btn-outline-dark" style="float: right;">mypage</button>
			                  		</form>
			                  		<form action="${pageContext.request.contextPath }/cover" method="POST">                	
			                       		<button type="submit" class="btn btn-outline-dark" style="float: right;">logout</button>
			                  		</form>
		              			</div>
		               			</span>
	            			</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</header>
	
	<main>
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-3"></div>
			
			<div class="col-lg-6">
        	<div class="map_wrap">
          		<div id="map" style="width:800px;height:1000px; margin-right: 100px"></div>
          			<div class="custom_typecontrol radius_border">
              			<span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span>
              <span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
          			</div>
          <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
          <div class="custom_zoomcontrol radius_border"> 
              	<span onclick="zoomIn()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span>  
              	<span onclick="zoomOut()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
          	</div>
          	</div>
			
			</div>
			
			<div class="col-lg-3"></div>
		</div>
	</div>
        <div class="col-lg-12">
            <div style="text-align:center">
          		<div>
            		<input type='text' id="text1" placeholder="지역입력">
            		<input type='button' value='검색' onclick="search()">
          		</div>
            </div>
        </div>
	
	</main>

<script type="text/javascript">
          var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
          var options = { //지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
            level: 3 //지도의 레벨(확대, 축소 정도)
          };
      
          var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
          //여기까지가 맵을 로딩하는 부분
          
       // 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
          function setMapType(maptype) { 
              var roadmapControl = document.getElementById('btnRoadmap');
              var skyviewControl = document.getElementById('btnSkyview'); 
              if (maptype === 'roadmap') {
                  map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
                  roadmapControl.className = 'selected_btn';
                  skyviewControl.className = 'btn';
              } else {
                  map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
                  skyviewControl.className = 'selected_btn';
                  roadmapControl.className = 'btn';
              }
          }
</script>
 
<script type="text/javascript">
function search() {
	var searchQuery = $("#text1").val();
	
	$.ajax({
		url : "http://openapi.seoul.go.kr:8088/41766e4d5461686e35336e4a557477/json/GetParkInfo/1/5/" + searchQuery, // 어디로 갈거니? // 갈 때 데이터
		type : "get", // 타입은 뭘 쓸거니?
		datatype : "json",
		success : function(data) { // 갔다온 다음 결과값
                   //console.log(data.GetParkInfo.row);
                   data.GetParkInfo.row.forEach(r => {
                     var lat = r.LAT;
                     var lng = r.LNG;
                     console.log(r);
                     var points = [];
                     points.push(new kakao.maps.LatLng(lat, lng));
                     
                   // 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
                       var bounds = new kakao.maps.LatLngBounds();    //위치 중앙 다시 보기
      
                       var i, marker;
                       for (i = 0; i < points.length; i++) {
                           // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
                           marker = new kakao.maps.Marker({ position : points[i] });
                           marker.setMap(map);
                           
                           // LatLngBounds 객체에 좌표를 추가합니다
                           bounds.extend(points[i]);
                       }
      
                           // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
                           // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
                           
                       map.setBounds(bounds);
                   })
                 },
                 error : function() {
                   alert('error');			
                 }
               });
            }
         
      
          // 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
          function zoomIn() {
              map.setLevel(map.getLevel() - 1);
          }
      
          // 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
          function zoomOut() {
              map.setLevel(map.getLevel() + 1);
          }
          //컨트롤러
          </script>

</body>
</html>