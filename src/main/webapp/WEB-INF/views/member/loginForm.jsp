<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
   request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
   crossorigin="anonymous">
<link href="${contextPath}/resources/css/loginForm.css" rel="stylesheet">

<title>로그인창</title>
<c:choose>
   <c:when test="${result=='loginFailed' }">
      <script>
         window.onload = function() {
            alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
         }
      </script>
   </c:when>
</c:choose>
</head>

<body>
   <div id="login_bground">
      <div id="login_tot">
         <form name="frmLogin" method="post"
            action="${contextPath}/member/login.do">

            <div class="form1">
               <div class="login_wrapper">
                  <div class="container-fluid">
                     <h2 class="fw-bold">로그인</h2>
                     <br>
                     <p align='center'>
                        원스톱 청년주거 서비스를 이용해주셔서 감사합니다.<br> 신청 및 다양한 서비스를 사용하시려면
                        로그인해주세요.
                     </p>
                     <br>
                     <div class="col">
                        <input type="text" class="form-control" placeholder="아이디"
                           aria-label="member_id" name="member_id" value=""> <input
                           type="password" class="form-control" placeholder="비밀번호"
                           aria-label="member_pw" name="member_pw" value="">
                     </div>

                     <div class="row">
                        <input type="submit" class="btn btn-primary" value="로그인">
                     </div>
                     <div class="login_btnList">
                        <button id='loginmainbutton'>
                           <a href='${contextPath}/member/findId.do'>ID 찾기</a>
                        </button>
                        <button id='loginmainbutton'>
                           <a href='${contextPath}/member/find_pw_form.do'>PW 찾기</a>
                        </button>
                        <button id='loginmainbutton'>
                           <a href='${contextPath}/member/memberForm.do'>회원가입</a>
                        </button>
                     </div>
                  </div>
               </div>
            </div>

         </form>

      </div>
   </div>
</body>
</html>