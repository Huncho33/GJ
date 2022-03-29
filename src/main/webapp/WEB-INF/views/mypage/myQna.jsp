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
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/mypage/myQna.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

<script language="javascript">
//글 삭제하기
function fn_remove_qna(url,qna_no){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
    var QnaNOInput = document.createElement("input");
    QnaNOInput.setAttribute("type","hidden");
    QnaNOInput.setAttribute("name","qna_no");
    QnaNOInput.setAttribute("value", qna_no);
	 
    form.appendChild(QnaNOInput);
    document.body.appendChild(form);
    form.submit();
}

//모달창 보이기
function fn_modalOpen(no) {
	var modal = document.getElementById('myQna_modal');
	modal.style.display = 'block';
	qna_no.value = Number(no);
}

// 모달창 감추기
function fn_modalClose() {
	var modal = document.getElementById('myQna_modal');
	modal.style.display = 'none';
}


</script>

</head>

<body>
	<div id="myQna_wrapper">
		<div id="myQna_total">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>마이페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a id="khs_left khs_left1" class="khs_lnb"><p>회원정보
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/myInfo.do">- 내 정보 수정</a></li>
								<li><a href="${contextPath}/mypage/memDeleteForm.do">-
										회원탈퇴</a></li>
							</ul></li>
						<li><a id="khs_left khs_left2" class="khs_lnb"><p>나의
									신청 현황</p></a>
							<ul class="khs_depth2">
								<li><a href="">- 월세지원 신청 현황</a></li>
								<li><a href="">- 전세지원 신청 현황</a></li>
								<li><a href="">- 행복주택지원 신청 현황</a></li>
							</ul></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>나의
									게시글 및 상담</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/myBoardList.do">- 나의
										게시글 목록</a></li>
								<li><a href="${contextPath}/mypage/myQna.do">- 나의 상담 목록</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			<div id="myQna_tot">
				<div id="myQna_tit1">
					<h3 class="myQna_tit">나의 상담 목록</h3>
				</div>
				<div id="myQna_tit2">
					<p class="myQna_subtit" style="font-weight: bold;">
						<strong>｜</strong> 2023청년월세지원 상담 현황
					</p>
				</div>
				<div id="myQna_cnt1">
					<table id="myQna_qnaList" align="center" width="100%" border=0>
						<tr class="myQna_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="10%">답변상태</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty monthQnaList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty monthQnaList }">
								<c:forEach var="qna" items="${monthQnaList }" varStatus="qnaNum">
									<tr align="center" height="35">
										<td width="10%">${qnaNum.count}</td>
										<td width="40%"><c:choose>
												<c:when test="${qna.qna_pw != '' }">
													<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
														onclick="fn_modalOpen(${qna.qna_no}); return false;">
														비밀글입니다.</a>
												</c:when>
												<c:otherwise>
													<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
												</c:otherwise>
											</c:choose></td>
										<td width="10%" style="color: blue;"><c:if
												test="${qna.qna_reply == 'true'}">
												답변완료
											</c:if> <c:if test="${qna.qna_reply == 'false'}">
												미답변
											</c:if></td>
										<td width="25%">${qna.qna_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_qna('${contextPath}/mypage/removeQna.do', ${qna.qna_no})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myQna_tit2">
					<p class="myQna_subtit" style="font-weight: bold;">
						<strong>｜</strong> 전월세보증금이자지원 상담 현황
					</p>
				</div>
				<div id="myQna_cnt1">
					<table id="myQna_qnaList" align="center" width="100%">
						<tr class="myQna_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="10%">답변상태</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty rentQnaList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty rentQnaList }">
								<c:forEach var="qna" items="${rentQnaList }" varStatus="qnaNum">
									<tr align="center" height="35">
										<td width="10%">${qnaNum.count}</td>
										<td width="40%"><c:choose>
												<c:when test="${qna.qna_pw != null }">
													<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
														onclick="fn_modalOpen(${qna.qna_no}); return false;">
														비밀글입니다.</a>
												</c:when>
												<c:otherwise>
													<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
												</c:otherwise>
											</c:choose></td>
										<td width="10%" style="color: blue;"><c:if
												test="${qna.qna_reply == 'true'}">
												답변완료
											</c:if> <c:if test="${qna.qna_reply == 'false'}">
												미답변
											</c:if></td>
										<td width="25%">${qna.qna_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_qna('${contextPath}/mypage/removeQna.do', ${qna.qna_no})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myQna_tit2">
					<p class="myQna_subtit" style="font-weight: bold;">
						<strong>｜</strong> 전세반환보증금보증료지원 상담 현황
					</p>
				</div>
				<div id="myQna_cnt1">
					<table id="myQna_qnaList" align="center" width="100%">
						<tr class="myQna_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="10%">답변상태</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty returnQnaList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty returnQnaList }">
								<c:forEach var="qna" items="${returnQnaList }"
									varStatus="qnaNum">
									<tr align="center" height="35">
										<td width="10%">${qnaNum.count}</td>
										<td width="40%"><c:choose>
												<c:when test="${qna.qna_pw != '' }">
													<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
														onclick="fn_modalOpen(${qna.qna_no}); return false;">
														비밀글입니다.</a>
												</c:when>
												<c:otherwise>
													<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
												</c:otherwise>
											</c:choose></td>
										<td width="10%" style="color: blue;"><c:if
												test="${qna.qna_reply == 'true'}">
												답변완료
											</c:if> <c:if test="${qna.qna_reply == 'false'}">
												미답변
											</c:if></td>
										<td width="25%">${qna.qna_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_qna('${contextPath}/mypage/removeQna.do', ${qna.qna_no})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myQna_tit2">
					<p class="myQna_subtit" style="font-weight: bold;">
						<strong>｜</strong> 신혼부부전세자금이자지원 상담 현황
					</p>
				</div>
				<div id="myQna_cnt1">
					<table id="myQna_qnaList" align="center" width="100%">
						<tr class="myQna_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="10%">답변상태</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty weddingQnaList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty weddingQnaList }">
								<c:forEach var="qna" items="${weddingQnaList }"
									varStatus="qnaNum">
									<tr align="center" height="35">
										<td width="10%">${qnaNum.count}</td>
										<td width="40%"><c:choose>
												<c:when test="${qna.qna_pw != '' }">
													<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
														onclick="fn_modalOpen(${qna.qna_no}); return false;">
														비밀글입니다.</a>
												</c:when>
												<c:otherwise>
													<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
												</c:otherwise>
											</c:choose></td>
										<td width="10%" style="color: blue;"><<c:if
												test="${qna.qna_reply == 'true'}">
												답변완료
											</c:if> <c:if test="${qna.qna_reply == 'false'}">
												미답변
											</c:if>
										</td>
										<td width="25%">${qna.qna_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_qna('${contextPath}/mypage/removeQna.do', ${qna.qna_no})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myQna_tit2">
					<p class="myQna_subtit" style="font-weight: bold;">
						<strong>｜</strong> 청년희망주택이자지원 상담 현황
					</p>
				</div>
				<div id="myQna_cnt1">
					<table id="myQna_qnaList" align="center" width="100%">
						<tr class="myQna_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="10%">답변상태</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty shareQnaList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty shareQnaList }">
								<c:forEach var="qna" items="${shareQnaList }" varStatus="qnaNum">
									<tr align="center" height="35">
										<td width="10%">${qnaNum.count}</td>
										<td width="40%"><c:choose>
												<c:when test="${qna.qna_pw != '' }">
													<i class="fa-solid fa-lock"></i>&nbsp;<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}"
														onclick="fn_modalOpen(${qna.qna_no}); return false;">
														비밀글입니다.</a>
												</c:when>
												<c:otherwise>
													<a class='cls1'
														href="${contextPath}/qna/viewQna.do?qna_no=${qna.qna_no}">${qna.qna_title }</a>
												</c:otherwise>
											</c:choose></td>
										<td width="10%" style="color: blue;"><c:if
												test="${qna.qna_reply == 'true'}">
												답변완료
											</c:if> <c:if test="${qna.qna_reply == 'false'}">
												미답변
											</c:if></td>
										<td width="25%">${qna.qna_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_qna('${contextPath}/mypage/removeQna.do', ${qna.qna_no})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 모달창 -->
	<div id="myQna_modal">
		<div id="myQna_modalCnt">
			<form id="myQna_modalFrm"
				action="${contextPath}/mypage/modalPwdCheck.do" method="post">
				<input type="button" value="✕" class="myQna_modalClose"
					onclick="fn_modalClose();" /> <label class="myQna_modalLabel">
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