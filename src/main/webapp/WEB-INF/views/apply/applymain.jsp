 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청페이지</title>

</head>
<body>
   <button id='doc1'>
      <a href='#'> 필수서류1</a>
   </button>
   <button id='doc2'>
      <a href='#'> 필수서류2</a>
   </button>
   <button id='doc3'>
      <a href='#'> 필수서류3</a>
   </button>
   <button id='applymain'>
      <a href="${contextPath}/apply/applyCond.do"> 신청하기</a>
   </button>
</body>
</html> 

</body>
</html> 