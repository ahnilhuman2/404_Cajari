<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<!DOCTYPE html>
<html>

<head>
  <title>NavBar</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

  <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="javascript:void(0)">Security</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="navbar-nav me-auto">
          <!-- 누구에게나 보이는 메뉴 -->
          <li class="nav-item" TODO="">
            <a class="nav-link" href="javascript:void(0)">Anonymous</a>
          </li>
         
          <!-- 로그인한 사람에게만 보이는 메뉴 -->
          <c:if test="${not empty sessionScope.PRINCIPAL }">
          <li class="nav-item" TODO="">
            <a class="nav-link" href="javascript:void(0)">Authenticated</a>
          </li>
          </c:if>
         
          <!-- 로그인한 사람중 ROLE_MEMBER 권한 있는 사람에게만 보이는 메뉴 -->
          <c:if test="${fn:contains(sessionScope.PRINCIPAL.authorities, 'ROLE_MEMBER' )}">
          <li class="nav-item" TODO="">
            <a class="nav-link" href="javascript:void(0)">Member</a>
          </li>
          </c:if>  
         
          <!-- 로그인한 사람중 ROLE_ADMIN 권한 있는 사람에게만 보이는 메뉴 -->
          <c:if test="${fn:contains(sessionScope.PRINCIPAL.authorities, 'ROLE_ADMIN' )}">
          <li class="nav-item" TODO="">
            <a class="nav-link" href="javascript:void(0)">Admin</a>
          </li>
          </c:if>
         
        </ul>
       
       	<c:choose>
       		<c:when test="${empty sessionScope.PRINCIPAL }">       		
	            <!-- 로그인 안했을때는 로그인 form 보여주기 -->
	            <a class="btn btn-primary" type="submit" href="${pageContext.request.contextPath}/user/login">Login</a>       		
       		</c:when>
       		<c:otherwise>
	            <!-- 로그인 했을때는 username 과 로그아웃 form 보여주기 -->
	            <form action="${pageContext.request.contextPath}/user/logout" method="POST" TODO="">
	              <!--TODO : 로그아웃후 다시 돌아오기 -->
	              <span class="d-flex">
	                <span class="text-light p-2"><span TODO="">${sessionScope.PRINCIPAL.username }(${sessionScope.PRINCIPAL.name })</span> 님 환영합니다</span>
	                <span><button class="btn btn-danger" type="submit">Logout</button></span>
	              </span>
	            </form>       		
       		</c:otherwise>
       	</c:choose>
       

       
      </div>
    </div>
  </nav>

  <div class="container-fluid mt-3">
  	<c:choose>
  		<c:when test="${empty sessionScope.PRINCIPAL }">  		
            <!-- 로그인 하지 않았을때 보여주는 화면 -->
            <div TODO="" class="alert alert-warning alert-dismissible">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                로그인하지 않은 상태입니다 <br>
            </div>          
  		</c:when>
  		<c:otherwise>
            <!-- 로그인 했을때 보여주는 화면 -->            
            <div TODO="" class="alert alert-info alert-dismissible">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <c:out value="${sessionScope.PRINCIPAL }"/><br>              
                로그인 하셨습니다 <br>
            </div>  		
  		</c:otherwise>
  	</c:choose>
           
  </div>
</body>

</html>



