<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="membersList" value="${membersMap.membersList}" />
<c:set var="section" value="${membersMap.section}" />
<c:set var="pageNum" value="${membersMap.pageNum}" />
<c:set var="totMembers" value="${membersMap.totMembers}" />
<c:set var="searchMember" value="${membersMap.searchMember}" />
<c:set var="searchTotMembers" value="${membersMap.searchTotMembers}" />

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
						<li><a id="khs_left khs_left1" class="khs_lnb"><p>사용자
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/admin/member/listMembers.do">-
										사용자 관리</a></li>
								<li><a href="#">- 관리자 관리</a></li>
							</ul></li>
						<li><a id="khs_left khs_left2" class="khs_lnb"><p>신청
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/admin/adminApply/adminMonthApply.do">- 신청자 관리</a></li>
								<li><a href="">- 신청 통계</a></li>
							</ul></li>
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
								<li><a href="${contextPath}/adminAlarm/listArticles.do">-
										알림게시판 관리</a></li>
							</ul></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>통계</p></a></li>
					</ul>
				</div>
			</div>
			<div id="adm_memberManage_tot">
				<div id="adm_memberManage_tit1">
					<h3 class="adm_memberManage_tit">사용자 관리</h3>
				</div>
				<!-- 검색 창 -->
				<div id="adm_memberManage_search">
					<span>[검색 회원: ${searchTotMembers }명]</span>
					<form name="frmSearch"
						action="${contextPath}/admin/member/searchMemberList.do">

						<input type="submit" name="search" value="검 색"><input
							name="searchMember" type="text"> <select id="searchType"
							name="searchType">
							<option value="member_id"
								<c:if test="${searchType eq 'member_id' }">selected</c:if>>ID</option>
							<option value="member_name"
								<c:if test="${searchType eq 'member_name' }">selected</c:if>>이름</option>
							<option value="member_right"
								<c:if test="${searchType eq 'member_right' }">selected</c:if>>권한</option>
						</select>
					</form>
				</div>
				<table id=adm_memberManage_list_tab align="center" width="100%">
					<tr height="40" align="center" bgcolor="#abd1f6">
						<td><b>아이디</b></td>
						<td><b>이름</b></td>
						<td><b>이메일</b></td>
						<td><b>가입일</b></td>
						<td><b>권한</b></td>
						<td><b>최종접속일</b></td>
					</tr>
					<c:choose>
						<c:when test="${empty membersList }">
							<tr height="35">
								<td colspan="5">
									<p align="center">
										<b><span style="font-size: 12pt;">등록된 회원이 없습니다.</span></b>
									</p>
								</td>
							</tr>
						</c:when>
						<c:when test="${not empty membersList }">
							<c:forEach var="member" items="${membersList}"
								varStatus="memberNum">
								<tr align="center">
									<td width="18%"><a class='memberInfo'
										href="${contextPath}/admin/member/viewMember.do?member_id=${member.member_id }">
											${member.member_id}</a></td>
									<td width="15%">${member.member_name}</td>
									<td width="25%">${member.member_email1}@${member.member_email2}</td>
									<td width="15%">${member.member_joinDate}</td>
									<td width="12%">${member.member_joinDate}</td>
									<td width="15%">${member.member_last_log}</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>
				<div style="margin-bottom: 50px;">
					<a id="adm_memberManage_write" class="cls2"
						href="${contextPath}/member/memberForm.do">회원등록</a>
				</div>
				<div class="cls2">
					<c:if test="${searchTotMembers != null }">

						<c:choose>

							<c:when test="${searchTotMembers >100 }">
								<!-- 글 개수가 100 초과인경우 -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<c:if test="${section >1 && page==1 }">
										<a class="no-uline"
											href="${contextPath }/admin/member/searchMemberList.do?searchMember=${searchMember }&search=검+색section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
											< </a>
									</c:if>
									<a class="no-uline"
										href="${contextPath }/admin/member/searchMemberList.do?searchMember=${searchMember }&search=검+색section=${section}&pageNum=${page}">${(section-1)*10 +page }
									</a>
									<c:if test="${page ==10 }">
										<a class="no-uline"
											href="${contextPath }/admin/member/searchMemberList.do?searchMember=${searchMember }&search=검+색section=${section+1}&pageNum=${section*10+1}">&nbsp;
											></a>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${searchTotMembers ==100 }">
								<!--등록된 글 개수가 100개인경우  -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<a class="no-uline" href="#">${page } </a>
								</c:forEach>
							</c:when>

							<c:when test="${searchTotMembers< 100 }">
								<!--등록된 글 개수가 100개 미만인 경우  -->
								<c:forEach var="page" begin="1" end="${searchTotMembers/10 +1}"
									step="1">
									<c:choose>
										<c:when test="${page==pageNum }">
											<a class="sel-page"
												href="${contextPath }/admin/member/searchMemberList.do?searchMember=${searchMember }&search=검+색section=${section}&pageNum=${page}">${page }
											</a>
										</c:when>
										<c:otherwise>
											<a class="no-uline"
												href="${contextPath }/admin/member/searchMemberList.do?searchMember=${searchMember }&search=검+색section=${section}&pageNum=${page}">${page }
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
