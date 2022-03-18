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
<title>비밀번호 확인</title>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/mypage/confirmPwdView.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">
<script>
// vo에서 불러온 아이디 값 읽기 위해 히든으로 값 옮겨주는 기능
function fn_idToHidden() {
	var h_id = document.getElementById("pwdCfm_id").value;
	var h_id2 = document.getElementById("pwdCfm_id2").value = h_id;
	console.log(h_id);
	return true;
}
</script>


</head>

<body>
	<div id="pwdCfm_wrapper">
		<div id="pwdCfm_total">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>마이페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li>
							<a id="khs_left khs_left1" class="khs_lnb"><p>회원정보 관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 내 정보 수정</a></li>
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 회원탈퇴</a></li>
							</ul>
						</li>
						<li>
							<a id="khs_left khs_left2" class="khs_lnb"><p>나의 신청 현황</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 월세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 전세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 행복주택지원 신청 현황</a></li>
							</ul>
						</li>
						<li>
							<a id="khs_left khs_left3" class="khs_lnb"><p>나의 게시글 및 상담</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 나의 게시글 목록</a></li>
								<li><a href="${contextPath}/mypage/confirmPwdView.do">- 나의 상담 목록</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div id="pwdCfm_tot">

				<div id="pwdCfm_tit1">
					<h3 class="pwdCfm_tit">내 정보 수정</h3>
				</div>
				<div id="pwdCfm_tit2">
					<p class="pwdCfm_subtit" style="font-weight: bold;">
						<strong>｜</strong> 비밀번호 재확인
					</p>
				</div>
				<div id="pwdCfm_container">
					<form id="pwdCfm_frm" name="pwdCfm_frm"
						action="${contextPath}/mypage/myInfoView.do" method="post">
						<div class="pwdCfm_cnt1">
							<p class="pwdCfm_info">
								<strong>${member.member_name}</strong> 님의 회원정보를 안전하게 보호하기 위해 비밀번호를 한번 더
								확인해 주세요.
							</p>
							<br>
							<table>
								<tr align="center">
									<td width="300">:: 아이디</td>
									<td><input type="text" name="pwdCfm_id" id="pwdCfm_id" value="${member.member_id}" size="30" disabled></td>
									<td><input type="hidden" name="pwdCfm_id2" id="pwdCfm_id2" ></td>
								</tr>
								<tr align="center">
									<td width="300">:: 비밀번호 입력</td>
									<td><input type="password" name="pwdCfm_pwd" id="pwdCfm_pwd" size="30"></td>
								</tr>
							</table>
						</div>
						<div class="pwdCfm_cnt2">
							<div class="join_btn join_btn2">
								<input id="pwdCfm_subm" type="submit" value="확인" onClick="fn_idToHidden()">
							</div>
							<div class="join_btn join_btn1">
								<input type="reset" value="취소">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>