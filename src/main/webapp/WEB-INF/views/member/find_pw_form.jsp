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

<!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style>
.findPw {
	width: 500px;
    height: 350px;
	margin: 0 auto;
	border: 2px solid gray;
	margin-top: 80px;
    
}

#_id {
	width: 400px;
	height: 37px;
	margin-top: 5px;
	margin-bottom: 10px;
	margin-left: auto;
	margin-right: auto;
}

#_email {
	width: 400px;
	height: 37px;
	margin-top: 5px;
	margin-bottom: 30px;
	margin-left: auto;
	margin-right: auto;
}

h3 {
    text-align: center;
	width: 300px;
	display: table;
	margin-left: auto;
	margin-right: auto;
}
    
    #lab{
        margin-top: 20px;
        margin-left: 50px;
        
    }
    #findBtn{
        margin-left: 65px;
        width: 150px;
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
					location.href = "../member/loginForm.do";
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
					
						<label id="lab">ID</label> <input class="form-control" type="text" id="_id"
							name="id" placeholder="아이디를 입력해주세요 ">
					
						<label id="lab">Email</label> <input class="form-control" type="text"
							id="_email" name="email" placeholder="이메일을 입력해주세요 ">
					
					
						<button type="button" id=findBtn class="btn btn-primary">찾기</button>
						<button type="button" id=findBtn onclick="history.go(-1);"
							class="btn btn-primary">취소</button>
					
				</div>
			</div>
		</div>
	</form>
</body>

</html>
