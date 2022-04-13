<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!-- 비밀번호 찾기 -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/login/find_pw_form.css"
	type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script language="javascript">
	$(function() {
		$("#find_pw_findBtn").click(function() {
			$.ajax({
				url : "${contextPath}/member/find_pw.do",
				type : "POST",
				data : {
					id : $("#_id").val(),
					email : $("#_email").val()
				},
				success : function(result) {
					alert(result);
				},
			})
		});
	})
</script>
<title>비밀번호 찾기</title>
</head>
<body>
	<div id=findID_bground>
		<div id="findID_container">
			<div id="findID_tot">
				<div id="findID_titName">
					<p class="findID_Title">
						<span>가입 시 작성했던 아이디와 이메일 정보</span>를 입력하여 주십시오.
					</p>
				</div>
				<form name="find_pw_form" class="findPw">
					<div class="w3-content w3-container w3-margin-top">
						<div class="w3-container w3-card-4">
							<div>
								<label id="find_pw_lab">ID</label> <input class="form-control"
									type="text" id="find_pw_id" name="id"
									placeholder="아이디를 입력해주세요."> <label id="find_pw_lab">Email</label>
								<input class="form-control" type="text" id="find_pw_email"
									name="email" placeholder="이메일을 입력해주세요. ">
								<button type="button" id=find_pw_findBtn class="btn btn-primary">찾기</button>
								<button type="button" id=find_pw_findBtn
									onclick="history.go(-1);" class="btn btn-primary">취소</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>