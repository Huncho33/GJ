<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/login/findId_email.css"
	type="text/css">

<script>
	function fn_findID() {
		var _name = $("#member_name").val();
		var _hp = $("#member_phoneno").val();
		var _email = $("#member_email1").val();
		var _email2 = $("#member_email2").val();

		if (_name == '' || _hp == '' || _email == '' || _email2 == '') {
			alert("모든 정보를 입력하여 주세요.");

		} else {
			document.frmfindID_Hp.submit();

		}

	}
</script>
</head>
<body>
	<div id=findID_bground>
		<div id="findID_container">
			<div id="findID_tot">
				<div id="findID_titName">
					<p class="findID_Title"> <span>가입 시 작성했던 이메일 정보</span>를 입력하여 주십시오.</p>
				</div>
				<form name="frmfindID_Hp" method="POST"
					action="${contextPath}/member/findYourId.do">
					<div id="findIDfrm_list">
						<div class="form-group">
							<label for="exampleInputEmail1">이름</label> <input type="text"
								class="form-control" id="member_name" name="member_name"
								placeholder="이름을 입력하세요." value="" size="20">
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">연락처</label> <input type="text"
								class="form-control" id="member_phoneno" name="member_phoneno"
								placeholder=" '-'를 제외한 숫자 11자리를 입력해주세요." value="" size="20">
						</div>


						<div class="form-group">
							<label for="exampleInputEmail1">이메일</label><br> <input
								type="text" class="form-control emailInput" id="member_email1"
								name="member_email1" value="" placeholder="email ID" size="20">
							<p class="emailIcon">&nbsp;@&nbsp;</p>
							<input type="text" class="form-control emailInput1"
								id="member_email2" name="member_email2"
								placeholder="도메인 주소 ex)gmail.com" value="" size="20">
						</div>
					</div>
					<div id="findID_button">
						<input class="btn btn-primary btn-lg" type="button"
							id="inYourInfo" value="아이디 찾기" onClick="fn_findID()">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>