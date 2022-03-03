<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<c:set var="result" value="${param.result }" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>헤더</title>

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<script src="${contextPath}/resources/js/jquery.scrollTo-min.js"></script>
<link href="${contextPath}/resources/css/header.css" rel="stylesheet"
   type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
   type="text/css">


</head>

<body>
   <div id="header">
      <div id="header_top">
         <div id="header_container">
            <div class="logo1">
               <a href="https://www.daegu.go.kr/index.do"><img
                  src="${contextPath}/resources/image/대구광역시로고.png" height="40px">
            </div>


            <div class="sns">
               <ul class="sns_link">
                  <li><a href="#"> <img
                        src="${contextPath}/resources/image/kakaotalk.png" height="25px">
                  </a></li>
                  <li><a href="#"> <img
                        src="${contextPath}/resources/image/facebook.png" height="25px">
                  </a></li>
                  <li><a href="#"> <img
                        src="${contextPath}/resources/image/insta.png" height="25px">
                  </a></li>
               </ul>

            </div>
         </div>
      </div>
      <hr>
      <div id="header_mid">
         <div id="header_mid1">
            <div id="header_inner">
               <div class="logo2">
                  <h1>
                     <a href="${contextPath}/main.do"> <img
                        src="${contextPath}/resources/image/원스톱청년주거 로고.png">
                  </h1>
               </div>
            </div>
         </div>
         <!-- 
         <div id="header_mid2">
            <div class="searchBox">
               <form class="d-flex">
                  <input class="form-control me-2" type="search"
                     placeholder="Search" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">
                     <p>검색</p>
                  </button>
               </form>
            </div>
         </div>
          -->
      </div>
      <div id="header_bottom">
         <div id="header_menu">
            <div id="header_nav">
               <div id="header_navWrap">
                  <ul>
                     <li class="menu" style="width: 200px;"><a href="#">청년패키지</a>
                        <ul class="submenu submenu1">
                           <li><a href="${contextPath}/boardIntro/yIntro.do">청년패키지란?</a></li>
                           <li><a href="${contextPath}/boardNotice/listArticles.do">공지사항</a></li>
                           <li><a href="#">문의사항</a></li>
                           <li><a href="${contextPath}/qna/freqQna.do"> - 자주 묻는 질문</a></li>
                           <li><a href="#"> - 상담게시판</a></li>
                           <li><a href="#">기타자료실</a></li>
                           <li><a href="#">자유게시판</a></li>
                        </ul></li>
                     <li class="menu" style="width: 200px;"><a href="#">월세지원</a>
                        <ul class="submenu submenu1">
                           <li><a href="${contextPath}/month/monthApplyInfo.do">청년월세지원 안내</a></li>
                           <li><a href="#">청년월세지원(2022)</a></li>
                           <li><a href="#">청년월세지원(2023)</a></li>
                           <li><a href="#">선정 결과 확인</a></li>

                        </ul></li>
                     <li class="menu" style="width: 250px;"><a href="#">전세지원</a>
                        <ul class="submenu submenu2">
                           <li><a href="#">전월세보증금이자지원 안내</a></li>
                           <li><a href="#"> - 전월세보증금이자지원 신청</a></li>
                           <li><a href="#"> - 전월세보증금이자지원 결과</a></li>
                           <li><a href="#">전세반환보증금보증료지원 안내</a></li>
                           <li><a href="#"> - 전세반환보증금보증료지원 신청</a></li>
                           <li><a href="#"> - 전세반환보증금보증료지원 결과</a></li>
                           <li><a href="#">신혼부부전세자금이자지원 안내</a></li>
                           <li><a href="#"> - 귀환신혼부부전세이자지원 신청</a></li>
                           <li><a href="#"> - 귀환신혼부부전세이자지원 결과</a></li>
                        </ul></li>
                     <li class="menu" style="width: 230px;"><a href="#">공공임대주택</a>
                        <ul class="submenu submenu3">
                           <li><a href="#">청년공공임대주택 안내</a></li>
                           <li><a href="#">청년희망주택공급 안내</a></li>
                           <li><a href="#">청년희망주택이자지원 안내</a></li>
                           <li><a href="#"> - 청년희망주택이자지원 신청</a></li>
                           <li><a href="#"> - 청년희망주택이자지원 결과</a></li>
                        </ul></li>
                  </ul>
               </div>
            </div>
         </div>
         <div class="info">

            <c:choose>
               <c:when test="${isLogOn == true  && member!= null}">

                  <a href="${contextPath}/member/logout.do"><img
                     src="${contextPath}/resources/image/user.png">로그아웃</a>
                  <a href="${contextPath}/member/logout.do"><img
                     src="${contextPath}/resources/image/file.png">마이페이지</a>
                  <script>
                     window.onload = function() {
                        alert("로그인 되었습니다.");
                     }
                  </script>


               </c:when>


               <c:otherwise>
                  <a href="${contextPath}/member/loginForm.do"><img
                     src="${contextPath}/resources/image/user.png">로그인</a>
                  <a href="${contextPath}/member/memberForm.do"><img
                     src="${contextPath}/resources/image/file.png">회원가입</a>
               </c:otherwise>
            </c:choose>

            <c:choose>
               <c:when test="${result == 'logOut'}">
                  <script>
                     window.onload = function() {
                        alert("로그아웃 되었습니다.");
                     }
                  </script>
               </c:when>
            </c:choose>

         </div>
      </div>
      <script src="${contextPath}/resources/js/header.js"></script>
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
         integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
         crossorigin="anonymous"></script>
      <script
         src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
         integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
         crossorigin="anonymous"></script>
      <script
         src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
         integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
         crossorigin="anonymous"></script>
</body>

</html>