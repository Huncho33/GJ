<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style>
.findPw {
	width: 500px;
	margin: 0 auto;
	border: 2px solid gray;
	margin-top: 220px;
}

#_id {
	width: 400px;
	height: 37px;
	margin-top: 21px;
	margin-bottom: 10px;
	margin-left: auto;
	margin-right: auto;
}

#_email {
	width: 400px;
	height: 37px;
	margin-top: 21px;
	margin-bottom: 10px;
	margin-left: auto;
	margin-right: auto;
}

h3 {
	width: 300px;
	display: table;
	margin-left: auto;
	margin-right: auto;
}

#findBtn {
	/*    position: absolute;*/
	
}

.btn btn-primary {
	/*   position: relative;*/
	
}
</style>



<script language="javascript">
	$(function() {
		$("#findBtn").click(function() {
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
	<form name="find_pw_form" class="findPw">
		<div class="w3-content w3-container w3-margin-top">
			<div class="w3-container w3-card-4">
				<div class="w3-center w3-large w3-margin-top">
					<h3>비밀번호 찾기</h3>
				</div>
				<div>
					<p>
						<label>ID</label> <input class="w3-input" type="text" id="_id"
							name="id" placeholder="아이디를 입력해주세요 ">
					</p>
					<p>
						<label>Email</label> <input class="w3-input" type="text"
							id="_email" name="email" placeholder="이메일을 입력해주세요 ">
					</p>
					<p class="w3-center">
						<button type="button" id=findBtn class="btn btn-primary">찾기</button>
						<button type="button" onclick="history.go(-1);"
							class="btn btn-primary">취소</button>
					</p>
				</div>
			</div>
		</div>
	</form>
</body>

</html>
