<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="QnasList" value="${qnasMap.QnasList}" />
<c:set var="section" value="${qnasMap.section}" />
<c:set var="pageNum" value="${qnasMap.pageNum}" />
<c:set var="totQnas" value="${qnasMap.totQnas}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${contextPath}/resources/css/qna/listQnas.css"
	rel="stylesheet" type="text/css">
<script src="${contextPath}/resources/js/qna/listQnas.js"></script>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
	
</head>

<body>
	<div id="qna_bground">
		<div id="qna_cnt">
			<div id="qna_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="qna_smallNav">
				<p>
					<span>[총 게시물: ${totQnas }건]</span> <span class="qna_subnavi">청년패키지
						> 상담게시판</span>
				</p>
			</div>
			<table id="qna_qnaList" align="center" width="100%">
				<tr height="40" align="center" bgcolor="#abd1f6">
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
				<c:choose>
					<c:when test="${empty QnasList }">
						<tr height="35">
							<td colspan="4">
								<p align="center">
									<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
								</p>
							</td>
						</tr>
					</c:when>
					<c:when test="${not empty QnasList }">
						<c:forEach var="qna" items="${QnasList }" varStatus="qnaNum">
							<tr align="center" height="35">
								<td width="15%">${qnaNum.count}</td>
								<td width="60%"><c:if
										test="${member.member_right == 'ADMIN'}">
										<c:choose>
											<c:when test="${(qna.qna_pw != '') && (qna.level > 1)}">
												<c:forEach begin="1" end="${qna.level }" step="1">
													<span style="padding-left: 20px"></span>
												</c:forEach>
												<span style="font-size: 12px;">└[답변완료]</span>
											&nbsp;<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
													href="${contextPath}/qna/viewQna.do?
=${qna.qna_no}">
													${qna.qna_title}.</a>
											</c:when>
											<c:when test="${qna.qna_pw != '' }">
												<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">
													비밀글입니다.</a>
											</c:when>
											<c:when test='${qna.level > 1 }'>
												<c:forEach begin="1" end="${qna.level }" step="1">
													<span style="padding-left: 20px"></span>
												</c:forEach>
												<span style="font-size: 12px;">└[답변완료]</span>
												<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title}</a>
											</c:when>
											<c:otherwise>
												<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
											</c:otherwise>
										</c:choose>
									</c:if> <c:if test="${member.member_right != 'ADMIN'}">
										<c:choose>
											<c:when test='${qna.qna_pw != "" && qna.level > 1}'>
												<c:forEach begin="1" end="${qna.level }" step="1">
													<span style="padding-left: 20px"></span>
												</c:forEach>
												<span style="font-size: 12px;">└[답변완료]</span>
												&nbsp;<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
													onclick="fn_modalOpen(${qna.qna_no}); return false;">
													${qna.qna_title}</a>
											</c:when>
											<c:when test="${qna.qna_pw != '' }">
												<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
													onclick="fn_modalOpen(${qna.qna_no}); return false;">
													비밀글입니다.</a>
											</c:when>
											<c:when test='${qna.level > 1 }'>
												<c:forEach begin="1" end="${qna.level }" step="1">
													<span style="padding-left: 20px"></span>
												</c:forEach>
												<span style="font-size: 12px;">└[답변완료]</span>
												<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title}</a>
											</c:when>
											<c:otherwise>
												<a class='cls1'
													href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
											</c:otherwise>
										</c:choose>
									</c:if></td>
								<td width="10%"><c:choose>
										<c:when test="${qna.level > 1 }">
										관리자
									</c:when>
										<c:otherwise>
										${qna.member_id }
									</c:otherwise>
									</c:choose></td>
								<td width="15%">${qna.qna_date}</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			<div style="margin-bottom: 50px;">
				<a class="cls1"
					href="javascript:fn_QnaForm('${isLogOn}','${contextPath}/qna/QnaForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p
						class="cls2">글쓰기</p></a>
			</div>
		</div>
	</div>

	<div id="qna_bground">
		<div id="qna_cnt">
			<div class="cls2">
				<c:if test="${not empty totQnas}">
					<c:choose>
						<c:when test="${totQnas >100 }">
							<!-- 글 개수가 100 초과인경우 -->
							<c:forEach var="page" begin="1" end="10" step="1">
								<c:if test="${section >1 && page==1 }">
									<a class="no-uline"
										href="${contextPath }/qna/listQnas.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
										pre </a>
								</c:if>
								<a class="no-uline"
									href="${contextPath }/qna/listQnas.do?section=${section}&pageNum=${page}">${(section-1)*10 +page }
								</a>
								<c:if test="${page ==10 }">
									<a class="no-uline"
										href="${contextPath }/qna/listQnas.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;
										next</a>
								</c:if>
							</c:forEach>
						</c:when>
						<c:when test="${totQnas ==100 }">
							<!--등록된 글 개수가 100개인경우  -->
							<c:forEach var="page" begin="1" end="10" step="1">
								<a class="no-uline" href="#">${page } </a>
							</c:forEach>
						</c:when>

						<c:when test="${totQnas< 100 }">
							<!--등록된 글 개수가 100개 미만인 경우  -->
							<c:forEach var="page" begin="1" end="${totQnas/10 +1}" step="1">
								<c:choose>
									<c:when test="${page==pageNum }">
										<a class="sel-page"
											href="${contextPath }/qna/listQnas.do?section=${section}&pageNum=${page}">${page }
										</a>
									</c:when>
									<c:otherwise>
										<a class="no-uline"
											href="${contextPath }/qna/listQnas.do?section=${section}&pageNum=${page}">${page }
										</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:if>
			</div>
		</div>
	</div>
	<!-- 모달창 -->
	<div id="qna_modal">
		<div id="qna_modalCnt">
			<form id="qna_modalFrm" action="${contextPath}/qna/modalPwdCheck.do"
				method="post">
				<input type="button" value="✕" class="qna_modalClose"
					onclick="fn_modalClose();" /> <label class="qna_modalLabel">
					해당글은 비밀글입니다.<br>작성 시 설정했던 비밀번호를 입력해주세요.
				</label> <input type="hidden" id="qna_no" name="qna_no"> <input
					type="password" id="qna_modalPwd" name="qna_modalPwd" /> <input
					type="submit" value="확인" id="qna_modalChk" name="qna_modalChk"
					onclick="fn_adminCheck()" />
			</form>
		</div>
	</div>
</body>
</html>
