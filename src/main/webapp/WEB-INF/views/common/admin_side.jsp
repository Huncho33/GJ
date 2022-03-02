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
				<div id="khs_leftTitle">관리자 페이지</div>
				<div id="khs_subMenu">
					<ul>

						<li><a href="#" id="khs_left1" class="khs_lnb">사용자 관리</a>
						<ul class="khs_depth2">
								<li><a href="">일반 사용자 관리</a></li>
								<li><a href="">관리자 관리</a></li>
							</ul></li>
						<li><a href="#" id="khs_left2" class="khs_lnb">신청 관리</a>
						<ul class="khs_depth2">
								<li><a href="">일반 신청자 관리</a></li>
								<li><a href="">관리자 관리</a></li>
							</ul></li>
						<li><a href="#" id="khs_left3" class="khs_lnb">게시판관리</a>
						<ul class="khs_depth2">
								<li><a href="">공지사항 관리</a></li>
								<li><a href="">자유게시판 관리</a></li>
								<li><a href="">기타자료실 관리</a></li>
								<li><a href="">상담게시판 관리</a></li>
								<li><a href="">알림게시판 관리</a></li>
							</ul></li>

						<li><a href="#" id="khs_left4" class="khs_lnb">통계 관리</a>
							</li>

					
					</ul>
				</div>
			</div>

		</div>
	</div>





</body>
</html>