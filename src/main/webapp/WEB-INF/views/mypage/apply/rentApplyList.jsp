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
<link href="${contextPath}/resources/css/mypage/myApply.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

</head>
<body>
	<div id="myApply_wrapper">
		<div id="myApply_total">
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
			<div id="myApply_tot">
				<div id="myApply_tit1">
					<h3 class="myApply_tit">전세지원 신청 현황</h3>
				</div>
				<div id="myApply_tit2">
					<p class="myApply_subtit" style="font-weight: bold;">
						<strong>｜</strong> 전월세보증금이자지원 신청 현황
					</p>
				</div>
				<div id="myApply_cnt1">
					<table id="myApply_qnaList" align="center" width="100%" border=0>
						<tr class="myApply_qnaTitle" align="center">
							<td width="25%">정책명</td>
							<td width="40%">지급기간</td>
							<td width="10%">심사상태</td>
							<td width="15%">신청일</td>
							<td width="10%">상세</td>
						</tr>
						<c:choose>
							<c:when test="${empty rentApplyList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">신청내역이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty rentApplyList }">
								<c:forEach var="rent" items="${rentApplyList }">
									<tr align="center" height="35">
										<td width="25%">${rent.rent_policy}</td>
										<td width="40%">
										<c:choose>
											<c:when test="${not empty rent.rent_startpay}">
												${rent.rent_startpay} ~ ${rent.rent_endpay}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
										</td>
										<td width="10%"><span style="color: blue;">${rent.rent_result}</span></td>
										<td width="15%">${rent.rent_date}</td>
										<td width="10%"><input type="hidden" name="rent_no" id="rent_no" value="${rent.rent_no }"><button type="button"
												onclick="location.href='${contextPath}/mypage/viewRentApply.do?rent_no=${rent.rent_no }'"
												class='cls1' id="apply_sltBtn">조회</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myApply_tit2">
					<p class="myApply_subtit" style="font-weight: bold;">
						<strong>｜</strong> 전세반환보증금보증료지원 신청 현황
					</p>
				</div>
				<div id="myApply_cnt1">
					<table id="myApply_qnaList" align="center" width="100%" border=0>
						<tr class="myApply_qnaTitle" align="center">
							<td width="25%">정책명</td>
							<td width="40%">지급기간</td>
							<td width="10%">심사상태</td>
							<td width="15%">신청일</td>
							<td width="10%">상세</td>
						</tr>
						<c:choose>
							<c:when test="${empty rentReturnApplyList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">신청내역이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty rentReturnApplyList }">
								<c:forEach var="ret" items="${rentReturnApplyList }">
									<tr align="center" height="35">
										<td width="25%">${ret.ret_policy}</td>
										<td width="40%">
										<c:choose>
											<c:when test="${not empty ret.ret_startpay}">
												${ret.ret_startpay} ~ ${ret.ret_endpay}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
										</td>
										<td width="10%"><span style="color: blue;">${ret.ret_result}</span></td>
										<td width="15%">${ret.ret_date}</td>
										<td width="10%"><input type="hidden" name="ret_no" id="ret_no" value="${ret.ret_no }"><button type="button"
												onclick="location.href='${contextPath}/mypage/viewRetApply.do?ret_no=${ret.ret_no }'"
												class='cls1' id="apply_sltBtn">조회</button></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</div>
				<div id="myApply_tit2">
					<p class="myApply_subtit" style="font-weight: bold;">
						<strong>｜</strong> 귀환신혼부부전세자금이자지원 신청 현황
					</p>
				</div>
				<div id="myApply_cnt1">
					<table id="myApply_qnaList" align="center" width="100%" border=0>
						<tr class="myApply_qnaTitle" align="center">
							<td width="25%">정책명</td>
							<td width="40%">지급기간</td>
							<td width="10%">심사상태</td>
							<td width="15%">신청일</td>
							<td width="10%">상세</td>
						</tr>
						<c:choose>
							<c:when test="${empty backApplyList }">
								<tr height="35">
									<td colspan="5">
										<p align="center">
											<b><span style="font-size: 9pt;">신청내역이 없습니다.</span></b>
										</p>
									</td>
								</tr>
							</c:when>
							<c:when test="${not empty backApplyList }">
								<c:forEach var="back" items="${backApplyList }">
									<tr align="center" height="35">
										<td width="25%">${back.ba_policy}</td>
										<td width="40%">
											<c:choose>
											<c:when test="${not empty back.ba_startpay}">
												${back.ba_startpay} ~ ${back.ba_endpay}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
										</td>
										<td width="10%"><span style="color: blue;">${back.ba_result}</span></td>
										<td width="15%">${back.ba_date}</td>
										<td width="10%"><input type="hidden" name="ba_no" id="ba_no" value="${back.ba_no }"><button type="button"
												onclick="location.href='${contextPath}/mypage/viewBackApply.do?ba_no=${back.ba_no }'"
												class='cls1' id="apply_sltBtn">조회</button></td>
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