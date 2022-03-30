<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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

<link href="${contextPath}/resources/css/qna/listQnas.css" rel="stylesheet" type="text/css">
<script>
	function fn_QnaForm(isLogOn, QnaForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = qnaForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/qna/QnaForm.do';
		}
	}
</script>
</head>

<body>
	<div id="qna_bground">
		<div id="qna_cnt">
			<div id="qna_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="qna_smallNav">
			<p><span href="#">청년패키지</span>><span href="#">상담게시판</span></p>
			</div>
			<table id="qna_qnaList" align="center">
				<tr width="400px" height="20" align="center" bgcolor="#abd1f6">
					<td width="30px">글번호</td>
					<td width="250px">제목</td>
					<td width="50px">작성자</td>
					<td width="70px">작성일</td>
				</tr>
				<c:choose>
					<c:when test="${empty QnasList }">
						<tr height="10">
							<td colspan="4">
								<p align="center">
									<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
								</p>
							</td>
						</tr>
					</c:when>
					<c:when test="${not empty QnasList }">
						<c:forEach var="qna" items="${QnasList }" varStatus="qnaNum">
							<tr align="center">
								<td width="5%">${qnaNum.count}</td>

								<td align='center' width="35%"><c:choose>
										<c:when test='${qna.level > 1 }'>
											<c:forEach begin="1" end="${qna.level }" step="1">
												<span style="padding-left: 20px"></span>
											</c:forEach>
											<span style="font-size: 12px;">[답변완료]</span>
											<a class='cls1'
												href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title}</a>
										</c:when>
										<c:otherwise>
											<a class='cls1'
												href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
										</c:otherwise>
									</c:choose></td>
								<td width="10%">${qna.member_id }</td>
								<td width="10%">${qna.qna_date}</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
			<a class="cls1"
				href="javascript:fn_QnaForm('${isLogOn}','${contextPath}/qna/QnaForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><button class="cls2">글쓰기</button></a>
		</div>

	</div>

</body>
</html>
