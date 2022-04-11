<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
   request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <style>
      .joinForm2_Title {
            float: left;
            width: 100%;
            height: 80px;
            margin: 10px 0px 50px 0px;
            text-align: center;
            padding-top: 30px;
            border: 2px solid #dddcdc;
            color: dimgray;
            font-weight: bold;
        }

        form {
            width: 96%;
            
        }

        .container{
           width: 20%;
            
        }
        input{
           width: 100%;
            margin: 5px;
        }
        #fpwbutton{
            width: 100%;
            font-size: 14px;
        }
        p{
            font-size: 12px;
            font-weight: bold;
        }
    </style>
   <script language="javascript">
   
   function formCheck(form) {
       var checkEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

       if(form.email.value == ""){
          alert('이메일을 입력해주세요.')
           return false;
       }
       
       if(!checkEmail.test(email.value)){
          alert('올바른 이메일 형식으로 입력해주세요.')
           return false;
       }
       if(checkEmail.test(email.value)){
          alert('메일을 발송하였습니다.')
       }
   }
   </script>
</head>

<body>
<div class="container">

    <h4 class="joinForm2_Title">정보를 입력하여 주십시오.</h4>

   <form class="form" method="post" action="${contextPath}/email/mail.do"
      onsubmit="return formCheck(this)">
      <div>
         <input type="text" class="email" id="email" name="email" placeholder="이메일을 입력해주세요 " /> 
         <input id="fpwbutton" type="submit" class="btn btn-primary" id="_email" value="메일 전송" /> 
       <p>&nbsp;&nbsp;&nbsp; ex) 이메일@xxxx.com</p>
      </div>
   </form>
   <br>
        <form method="post" action="${contextPath}/email/verifyCode.do">
        <div>
            <input type="text" name="code" class="code" placeholder="인증번호를 입력해주세요">
            <input id="fpwbutton" type="submit" class="btn btn-primary" name="confirm" value="인증확인"> </div>
       </form>
       </div>
</body>

</html>