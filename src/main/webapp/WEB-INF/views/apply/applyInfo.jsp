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
   <button id='apply_cancel'>
      <a href='#'> 취소</a>
   </button>
   <button id='apply_nextStep'>
      <a href="${contextPath}/apply/applyDocm.do"> 다음단계</a>
   </button>
</body>
</html> 

</body>
</html> 