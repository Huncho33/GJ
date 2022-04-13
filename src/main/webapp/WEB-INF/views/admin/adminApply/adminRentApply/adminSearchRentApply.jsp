
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="membersList" value="${membersMap.membersList}" />
<c:set var="section" value="${membersMap.section}" />
<c:set var="pageNum" value="${membersMap.pageNum}" />
<c:set var="totMembers" value="${membersMap.totMembers}" />
<c:set var="totApply" value="${applyMap.totApply}" />
<c:set var="applyList" value="${applyMap.applyList}" />
<c:set var="section" value="${applyMap.section}" />
<c:set var="pageNum" value="${applyMap.pageNum}" />
<c:set var="searchApply" value="${applyMap.searchApply}" />
<c:set var="searchType" value="${applyMap.searchType}" />
<c:set var="searchTotApply" value="${applyMap.searchTotApply}" />
<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/admin/searchMemberList.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/admin/policyKindBox.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css"
	type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
</head>
<body>
	<div id="adm_memberManage_bground">
		<div id="adm_memberManage_contatiner">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>관리페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a href="${contextPath}/admin/member/listMembers.do"
							id="khs_left khs_left1" class="khs_lnb"><p>사용자 관리</p></a></li>
						<li><a
							href="${contextPath}/admin/adminApply/adminMonthApply.do"
							id="khs_left khs_left2" class="khs_lnb"><p>신청 관리</p></a></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>게시판
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/adminNotice/listArticles.do">-
										공지사항 관리</a></li>
								<li><a href="${contextPath}/adminData/listArticles.do">-
										기타자료실 관리</a></li>
								<li><a href="${contextPath}/adminQna/listQnas.do">-
										상담게시판 관리</a></li>
								<li><a href="${contextPath}/adminFree/listArticles.do">-
										자유게시판 관리</a></li>
							</ul></li>
						<li><a href="${contextPath}/admin/stats/stats.do"
							id="khs_left khs_left3" class="khs_lnb"><p>통계</p></a></li>
					</ul>
				</div>
			</div>

			<div id="adm_memberManage_tot">
				<div id="adm_memberManage_tit1">
					<h3 class="adm_memberManage_tit">신청자 관리</h3>
				</div>
				<div id='kindApplycontainer'>
					<a id="kindApply"
						href="${contextPath}/admin/adminApply/adminMonthApply.do"><br>월세지원</a>
					<a id="kindApply"
						href="${contextPath}/admin/adminApply/adminRentApply.do"><br>전세지원</a>
					<a id="kindApply"
						href="${contextPath}/admin/adminApply/adminShareApply.do"><br>공공임대</a><br>
				</div>
				<!-- 검색 창 -->
				<div id="adm_memberManage_search">
					<span>[검색 회원: ${searchTotApply }명]</span>
					<form name="frmSearch"
						action="${contextPath}/admin/adminApply/adminSearchRentApply.do">

						<input type="submit" name="search" value="검 색"><input
							name="searchApply" type="text"> <select id="searchType"
							name="searchType">
							<option value="member_id"
								<c:if test="${searchType eq 'member_id' }">selected</c:if>>ID</option>
							<option value="rent_result"
								<c:if test="${searchType eq 'rent_result' }">selected</c:if>>진행사항</option>
						</select>
					</form>
				</div>
				<table id=adm_memberManage_list_tab align="center" width="100%">
					<tr height="40" align="center" bgcolor="#abd1f6">
						<td><b>순번</b></td>
						<td><b>아이디</b></td>
						<td><b>이름</b></td>
						<td><b>신청정책명</b></td>
						<td><b>신청일</b></td>
						<td><b>진행사항</b></td>
					</tr>
					<c:choose>
						<c:when test="${empty applyList }">
							<tr height="35">
								<td colspan="6">
									<p align="center">
										<b><span style="font-size: 11pt;">등록된 회원이 없습니다.</span></b>
									</p>
								</td>
							</tr>
						</c:when>
						<c:when test="${not empty applyList }">
							<c:forEach var="join" items="${applyList}" varStatus="status">
								<tr align="center">
									<td width="10%"><a class='memberInfo'
										href="${contextPath}/admin/adminApply/adminViewRentApply.do?rent_no=${join.rent_no }&member_id=${join.member_id}">
											${join.rent_no}</a></td>
									<td width="15%">${join.member_id}</td>
									<td width="10%">${join.membervo.member_name}</td>
									<td width="15%">${join.rent_policy}</td>
									<td width="12%">${join.rent_date}</td>
									<td width="15%">${join.rent_result}</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>

				<div class="cls2">
					<c:if test="${searchTotApply != null }">

						<c:choose>

							<c:when test="${searchTotApply >100 }">
								<!-- 글 개수가 100 초과인경우 -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<c:if test="${section >1 && page==1 }">
										<a class="no-uline"
											href="${contextPath}/admin/adminApply/adminSearchRentApply.do?searchApply=${searchApply }&searchType=${searchType }&search=검+색section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
											< </a>
									</c:if>
									<a class="no-uline"
										href="${contextPath }/admin/adminApply/adminSearchRentApply.do?searchApply=${searchApply }&searchType=${searchType }&search=검+색section=${section}&pageNum=${page}">${(section-1)*10 +page }
									</a>
									<c:if test="${page ==10 }">
										<a class="no-uline"
											href="${contextPath }/admin/adminApply/adminSearchRentApply.do?searchApply=${searchApply }&searchType=${searchType }&search=검+색section=${section+1}&pageNum=${section*10+1}">&nbsp;
											></a>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${searchTotApply ==100 }">
								<!--등록된 글 개수가 100개인경우  -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<a class="no-uline" href="#">${page } </a>
								</c:forEach>
							</c:when>

							<c:when test="${searchTotApply < 100 }">
								<!--등록된 글 개수가 100개 미만인 경우  -->
								<c:forEach var="page" begin="1" end="${searchTotApply/10 +1}"
									step="1">
									<c:choose>
										<c:when test="${page==pageNum }">
											<a class="sel-page"
												href="${contextPath }/admin/adminApply/adminSearchRentApply.do?searchApply=${searchApply }&searchType=${searchType }&search=검+색section=${section}&pageNum=${page}">${page }
											</a>
										</c:when>
										<c:otherwise>
											<a class="no-uline"
												href="${contextPath }/admin/adminApply/adminSearchRentApply.do?searchApply=${searchApply }&searchType=${searchType }&search=검+색section=${section}&pageNum=${page}">${page }
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
	</div>
</body>
</html>




