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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <style>
        .email {
            width: 300px;
            height: 37px;
            margin-top: 21px;
            margin-bottom: 10px;

        }

       
        .ex {
            text-decoration: none;
            font-size: 15px;
            color: dimgray;
            position: absolute;
        }

        .code {
            width: 200px;
            height: 37px;
            margin-bottom: 10px;
        }

        .form {
            width: 100px;
            margin: 0 auto;
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
   <form class="form" method="post" action="${contextPath}/email/mail.do"
      onsubmit="return formCheck(this)">
      <div>
         <input type="text" class="email" id="email" name="email" placeholder="이메일을 입력해주세요 " /> 
         <input type="submit" class="btn btn-primary" id="_email" value="메일 전송" /> 
         <a id="ex" class="ex"> ex) 이메일@xxxx.com</a>
      </div>
   </form>
   <br>
        <form method="post" action="${contextPath}/email/verifyCode.do">
        <div>
            <input type="text" name="code" class="code" placeholder="인증번호를 입력해주세요">
            <input type="submit" class="btn btn-primary" name="confirm" value="인증확인"> </div>
       </form>
</body>

</html>