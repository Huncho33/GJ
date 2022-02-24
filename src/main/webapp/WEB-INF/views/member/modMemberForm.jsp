<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		.cls1{
			font-size:40px;
			text-align:center;
		}
	</style>
</head>
<body>
	<form method="post"  action="${contextPath}/member/mod.do">
	<h1 style="text-align:center">회원 정보 수정창 </h1>
	<table align="center">
		<tr>
			<td width="200"><p align="right">아이디</p></td>
			<td width="400"><input type="text" name="id" value="${member.id}" readOnly></td>
		</tr>
		<tr>
			<td width="200"><p align="right">비밀번호</p></td>
			<td width="400"><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">이름</p></td>
			<td width="400"><input type="text" name="name"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">이메일</p></td>
			<td width="400"><input type="text" name="email"></td>
		</tr>	
		<tr align="center">
			<td colspan="2" width="400">
			<input type="submit" value="수정하기">
			<input type="reset" value="다시입력">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>