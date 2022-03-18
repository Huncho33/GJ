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
<title>FAQ</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<style>
* {
	padding: 0;
	margin: 0;
}

#faq_wrapper {
	width: 100%;
	position: relative;
	margin: 0 auto;
}

#faq_top {
	padding: 30px 0px 50px 0px;

}

.panel-group {
}
</style>
</head>

<body>
	<div id="faq_wrapper">
		<div id="faq_top"  style="text-align : center;">
			<img src="${contextPath}/resources/image/FAQ인덱스.png">
		</div>
		<div id="faq_container">
			<div class="container" style="min-height: calc(100vh - 136px);">
				<!-- 그룹 태그로 role과 aria-multiselectable를 설정한다. -->
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<!-- 하나의 item입니다. data-parent 설청과 href 설정만 제대로 하면 문제없이 작동합니다. -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" width="1000px">
							<a role="button" data-toggle="collapse" href="#collapse1"
								aria-expanded="false"> <i class="fa-regular fa-q"></i>&nbsp;&nbsp;
								:: 자주 묻는 질문1 입니다.
							</a>
						</div>
						<div id="collapse1" class="panel-collapse collapse"
							role="tabpanel">
							<div class="panel-body">
								&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-regular fa-a"></i>
								&nbsp;&nbsp;자주 묻는 질문1 에 대한 답변1 입니다.
							</div>
						</div>
					</div>
					<!-- -->
					<!-- 하나의 item입니다. -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab">
							<a role="button" data-toggle="collapse" href="#collapse2"
								aria-expanded="false"> <i class="fa-regular fa-q"></i>&nbsp;&nbsp;
								:: 자주 묻는 질문2 입니다.
							</a>
						</div>
						<div id="collapse2" class="panel-collapse collapse"
							role="tabpanel">
							<div class="panel-body">
								&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-regular fa-a"></i>
								&nbsp;&nbsp;자주 묻는 질문2 에 대한 답변2 입니다.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab">
							<a role="button" data-toggle="collapse" href="#collapse3"
								aria-expanded="false"> <i class="fa-regular fa-q"></i>&nbsp;&nbsp;
								:: 자주 묻는 질문3 입니다.
							</a>
						</div>
						<div id="collapse3" class="panel-collapse collapse"
							role="tabpanel">
							<div class="panel-body">
								&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-regular fa-a"></i>
								&nbsp;&nbsp;자주 묻는 질문3 에 대한 답변3 입니다.
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>