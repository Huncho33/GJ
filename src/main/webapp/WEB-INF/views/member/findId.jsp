
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!-- 아이디찾기 선택창 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/login/findId.css" type="text/css">
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<div id="findID_bground">
		<div id="findID_container">
			<div id="findID_tot">
				<div id="findID_titName">
					<p class="findID_Title">
						'<span>이메일, 휴대폰</span>' 사용자 인증 방법을 선택하여 주십시오.
					</p>
				</div>
				<div id="findID_cerTap">
					<div class="findID_cerTitle" id='findID_cerPhone'>
						<i class="fa-solid fa-envelope fa-4x"></i>
						<p>이메일 인증</p>
						<br> <a class="btn btn-primary btn-lg"
							href='${contextPath}/member/findId_cellph.do'>인증하기</a>
					</div>
					<div class="findID_cerTitle" id='findID_cerEmail'>
						<i class="fa-solid fa-mobile-screen-button fa-4x"></i>
						<p>휴대폰 인증</p>
						<br> <a class="btn btn-primary btn-lg"
							href='#'>인증하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
