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
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${contextPath}/resources/css/login/emailSend.css" type="text/css">
<script language="javascript">
	function formCheck(form) {
		var checkEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		if (form.email.value == "") {
			alert('이메일을 입력해주세요.')
			return false;
		}

		if (!checkEmail.test(email.value)) {
			alert('올바른 이메일 형식으로 입력해주세요.')
			return false;
		}
		if (checkEmail.test(email.value)) {
			alert('메일을 발송하였습니다.')
		}
	}
</script>
</head>

<body>
	<div id=findID_bground>
		<div id="findID_container">
			<div id="findID_tot">
				<div id="findID_titName">
					<p class="findID_Title">
						<span>이메일과 인증번호</span>를 입력하여 주십시오.
					</p>
				</div>
				<form class="emailSend_form" method="post"
					action="${contextPath}/email/mail.do"
					onsubmit="return formCheck(this)">
					<div id="findIDfrm_list">
						<div id="form-group">
							<label for="exampleInputEmail1">이메일 주소</label><br> <input type="text"
								class="form-control" id="email" name="email" placeholder="ex) test@gmail.com" />
						</div>
						<div id="findID_button">
							<input id="fpwbutton" type="submit" class="btn btn-primary"
								id="_email" value="메일 전송" />
						</div>
					</div>
				</form>
				<br>
				<form method="post" action="${contextPath}/email/verifyCode.do">
					<div id="findIDfrm_list">
						<div id="form-group">
							<label for="exampleInputEmail1">인증번호</label><br> <input type="text"
								name="code" class="form-control" placeholder="인증번호를 입력해주세요">
						</div>
						<div id="findID_button">
							<input id="fpwbutton" type="submit" class="btn btn-primary"
								name="confirm" value="인증확인">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>