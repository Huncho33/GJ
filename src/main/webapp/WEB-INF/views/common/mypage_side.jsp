<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>메인 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

</head>
<body>



	<div id="khs_wrap">

		<div id="khs_contents">

			<div id="khs_leftMenu">
				<div id="khs_leftTitle">마이 페이지</div>
				<div id="khs_subMenu">
					<ul>

						<li><a href="#" id="khs_left1" class="khs_lnb">회원정보 관리</a>
						<ul class="khs_depth2">
								<li><a href="">내 정보 수정</a></li>
								<li><a href="">비밀번호 변경</a></li>
								<li><a href="">회원탈퇴</a></li>
							</ul></li>
						<li><a href="#" id="khs_left2" class="khs_lnb">나의 신청 현황</a>
						<ul class="khs_depth2">
								<li><a href="">월세지원 신청 현형</a></li>
								<li><a href="">전세지원 신청 현황</a></li>
								<li><a href="">행복주택지원 신청 현황</a></li>
							</ul></li>
						<li><a href="#" id="khs_left3" class="khs_lnb">나의 게시글 및 상담</a>
						<ul class="khs_depth2">
								<li><a href="">나의 게시글 목록</a></li>
								<li><a href="">나의 상담 목록</a></li>
							</ul></li>

						

					</ul>
				</div>
			</div>

		</div>
	</div>





</body>
</html>