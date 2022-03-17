 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<style>


       
        .joinForm2_Title {
            float: left;
            width: 820px;
            height: 80px;
            margin: 50px 10px 50px 10px;
            text-align: center;
            padding-top: 30px;
            border: 2px solid #dddcdc;
            color: dimgray;
            font-weight: bold;
        }

        .joinForm2_cerTitle {
            float: left;
            width: 400px;
            height: 230px;
            margin: 10px;
            text-align: center;
            padding-top: 30px;
            border: 2px solid #dddcdc;
            
        }

        .joinForm2_cerBtn {
            width: 150px;
            height: 50px;
            padding: 20px 40px;
            text-decoration: none;
            color: white;
            background-color: dodgerblue;
            border-radius: 1em;
        }
        #userfont{
            color: dodgerblue;
        }

        
    </style>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>

</head>
<div class="container">
<body>
   <h4 class="joinForm2_Title"><span id=userfont>'이메일, 휴대폰'</span> 사용자 인증 방법을 선택하여 주십시오.</h4>
         
            

                <span class="joinForm2_cerTitle" id='certHp'><h3>이메일 인증</h3><br>
                    <a class="btn btn-primary btn-lg" href='${contextPath}/member/findId_cellph.do'>인증하기</a>
                </span>
                <span class="joinForm2_cerTitle" id='certemai'><h3>휴대폰 인증</h3><br>
                    <a class="btn btn-primary btn-lg" href='${contextPath}/member/findId_email.do'>인증하기</a>
                </span>
</body>
</div>
</html> 