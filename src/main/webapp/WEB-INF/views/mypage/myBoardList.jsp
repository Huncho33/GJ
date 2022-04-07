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
<link href="${contextPath}/resources/css/mypage/myBoardList.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

<script language="javascript">
//글 삭제하기
function fn_remove_board(url,fr_NO){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
    var FreeNOInput = document.createElement("input");
    FreeNOInput.setAttribute("type","hidden");
    FreeNOInput.setAttribute("name","fr_NO");
    FreeNOInput.setAttribute("value", fr_NO);
	 
    form.appendChild(FreeNOInput);
    document.body.appendChild(form);
    form.submit();
}

</script>

</head>

<body>
	<div id="myBoard_wrapper">
		<div id="myBoard_total">
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
								<li><a href="${contextPath}/mypage/monthApplyList.do">-
										월세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/rentApplyList.do">- 전세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/shareApplyList.do">- 행복주택지원 신청 현황</a></li>
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
			<div id="myBoard_tot">
				<div id="myBoard_tit1">
					<h3 class="myBoard_tit">나의 게시글 목록</h3>
				</div>
				<div id="myBoard_tit2">
					<p class="myBoard_subtit" style="font-weight: bold;">
						<strong>｜</strong> 내가 작성한 글목록
					</p>
				</div>
				<div id="myBoard_cnt1">
					<table id="myBoard_qnaList" align="center" width="100%" border=0>
						<tr class="myBoard_qnaTitle" align="center">
							<td width="10%">글번호</td>
							<td width="40%">제목</td>
							<td width="25%">작성일</td>
							<td width="15%">삭제</td>
						</tr>
						<c:choose>
							<c:when test="${empty myBoardList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty myBoardList }">
								<c:forEach var="article" items="${myBoardList }"
									varStatus="myBoardNum">
									<tr align="center" height="35">
										<td width="10%">${myBoardNum.count}</td>
										<td width="40%"><a class='cls1'
											href="${contextPath}/boardFree/viewArticle.do?fr_NO=${article.fr_NO }">
												${article.fr_title }</a></td>
										<td width="25%">${article.fr_date}</td>
										<td width="15%"><button type="button"
												onClick="fn_remove_board('${contextPath}/mypage/removeArticle.do', ${article.fr_NO})">삭제</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>