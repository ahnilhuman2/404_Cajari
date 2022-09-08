<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
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

<body>
    <div class="row">
        <div class="col-lg-12">
            <div class="container">
                <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                    <h2 class="fw-normal">Cajari</h2>
            
       
            
                  <div class="col-md-3 text-end">
                        <button type="button" class="btn btn-outline-primary me-2 ">mypage</button>
                        <button type="button" class="btn btn-outline-primary me-2 ">logout</button>
                    </div>
                </header>
              </div>
        </div>
        
        <div class="col-lg-12">
            <div style="text-align:center">
                <br>
                <br>
                <h1>Cajari</h1>
                <br>
                <br>
                <p class="lead">
                  <a href="${pageContext.request.contextPath }/user/login" class="btn btn-lg btn-secondary fw-bold border-white bg-black">Login</a>
                  <a href="${pageContext.request.contextPath }/user/signin" class="btn btn-lg btn-secondary fw-bold border-white bg-black">Sign in</a>
                </p>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="frame">
              
            </div>
        </body>
</html>