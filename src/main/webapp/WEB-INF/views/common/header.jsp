<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
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
<link href="${contextPath}/resources/css/header.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet" type="text/css">

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
							<a href="#"><img
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
								<ul class="submenu1">
									<li><a href="#">청년패키지란?</a></li>
									<li><a href="#">공지사항</a></li>
									<li><a href="#">문의사항</a></li>
									<li><a href="#"> - 자주 묻는 질문</a></li>
									<li><a href="#"> - 상담게시판</a></li>
									<li><a href="#">기타자료실</a></li>
									<li><a href="#">자유게시판</a></li>
								</ul></li>
							<li class="menu" style="width: 200px;"><a href="#">월세지원</a>
								<ul class="submenu1">
									<li><a href="#">청년월세지원 안내</a></li>
									<li><a href="#">청년월세지원(2022)</a></li>
									<li><a href="#">청년월세지원(2023)</a></li>
									<li><a href="#">선정 결과 확인</a></li>

								</ul></li>
							<li class="menu" style="width: 250px;"><a href="#">전세지원</a>
								<ul class="submenu2">
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
								<ul class="submenu3">
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
				<a href="#"><img src="${contextPath}/resources/image/user.png">
					<p>로그인</p> </a> <a href="#"><img
					src="${contextPath}/resources/image/file.png">
					<p>회원가입</p> </a>
			</div>
		</div>
	</div>
	<script src="${contextPath}/resources/js/header.js"></script>
</body>

</html>