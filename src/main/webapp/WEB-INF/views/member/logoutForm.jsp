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
<title>로그인창</title>
<c:choose>
	<c:when test="${result=='logOut' }">
		<script>
			window.onload = function() {
				alert("로그아웃되었습니다.");
			}
		</script>
	</c:when>
	
</c:choose>
</head>

<body>
<form name="frmLogin" method="post"  action="${contextPath}/member/logout.do">
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
			<td><b>수정</b></td>
			<td><b>삭제</b></td>
		</tr>

		<c:forEach var="member" items="${membersList}">
			<tr align="center">
				<td>${member.id}</td>
				<td>${member.pwd}</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.joinDate}</td>
				<td><a
					href="${contextPath }/member/modMemberForm.do?id=${member.id}">정보수정</a></td>
				<td><a
					href="${contextPath }/member/removeMember.do?id=${member.id}">삭제하기</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="${contextPath}/member/memberForm.do"><h1
			style="text-align: center">회원가입</h1></a>
	<c:choose>
		<c:when test="${isLogOn == true  && member!= null}">
			<h3>환영합니다. ${member.name }님!</h3>
			<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>


		</c:when>
		<c:otherwise>
			<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
		</c:otherwise>
	</c:choose>
</body>

</html>
