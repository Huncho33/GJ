<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="section" value="${articlesMap.section}" />
<c:set var="pageNum" value="${articlesMap.pageNum}" />
<c:set var="totArticles" value="${articlesMap.totArticles}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<link href="${contextPath }/resources/css/admin/data/listArticles.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script>
	function adm_reg(member_right) {
		console.log(member_right);
		if (member_right == "ADMIN") {
			var a = document.getElementById("data_write");
			a.style.display = "block";
		}
	}

	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/adminData/articleForm.do';
		}
	}
</script>
<body onload="adm_reg('${member.member_right}')">
	<div id="data_bground">
		<div id="data_container">
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
								<li><a href="">- 신청자 관리</a></li>
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
			<div id="adm_data_tot">
				<div id="data_title">
					<h3 class="data_titName">기타자료실</h3>
				</div>
				<div id="data_smallNav">
					<p>
						<span>[총 게시물: ${totArticles }건]</span> <span class="data_subnavi">청년패키지
							> 기타자료실 </span>
					</p>
					<br>
				</div>
				<!-- 검색 창 -->
				<div id="data_search">
					<form name="frmSearch"
						action="${contextPath}/adminData/searchBoardList.do">
						<select id="searchType_etc" name="searchType_etc">
							<option value="etc_title_context"
								<c:if test="${searchType_etc eq 'etc_title_context'  }">selected</c:if>>제목+내용</option>
							<option value="etc_title"
								<c:if test="${searchType_etc eq 'etc_title' }">selected</c:if>>제목</option>
							<option value="etc_context"
								<c:if test="${searchType_etc eq 'etc_context' }">selected</c:if>>내용</option>
							<option value="member_id"
								<c:if test="${searchType_etc eq 'member_id' }">selected</c:if>>작성자</option>
						</select> <input name="searchWord" type="text"> <input
							type="submit" name="search" value="검 색">
					</form>
				</div>

				<table id=data_list_tab align="center" width="100%">
					<tr height="40" align="center" bgcolor="#abd1f6">
						<td>글번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					<c:choose>
						<c:when test="${empty articlesList }">
							<tr height="35">
								<td colspan="5">
									<p align="center">
										<b><span style="font-size: 12pt;">등록된 글이 없습니다.</span></b>
									</p>
								</td>
							</tr>
						</c:when>
						<c:when test="${not empty articlesList }">
							<c:forEach var="article" items="${articlesList }"
								varStatus="articleNum">
								<tr align="center" height="35">
									<td width="10%">${article.etc_NO}</td>
									<td width="45%"><a class='cls1'
										href="${contextPath}/adminData/viewArticle.do?etc_NO=${article.etc_NO }">
											${article.etc_title }</a>
									<td width="15%">${article.member_id }</td>
									<td width="20%">${article.etc_date}</td>
									<td width="15%">${article.etc_hits}</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>
				<div style="margin-bottom: 50px;">
					<a class="cls2" id="data_write"
						href="javascript:fn_articleForm('${isLogOn}','${contextPath}/adminData/articleForm.do','${contextPath}/member/loginForm.do')"><p>글쓰기</p></a>
				</div>
				<div class="cls2">
					<c:if test="${totArticles != null }">
						<c:choose>
							<c:when test="${totArticles >100 }">
								<!-- 글 개수가 100 초과인경우 -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<c:if test="${section >1 && page==1 }">
										<a class="no-uline"
											href="${contextPath }/adminData/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
											< </a>
									</c:if>
									<a class="no-uline"
										href="${contextPath }/adminData/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 +page }
									</a>
									<c:if test="${page ==10 }">
										<a class="no-uline"
											href="${contextPath }/adminData/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;
											></a>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${totArticles ==100 }">
								<!--등록된 글 개수가 100개인경우  -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<a class="no-uline" href="#">${page } </a>
								</c:forEach>
							</c:when>

							<c:when test="${totArticles< 100 }">
								<!--등록된 글 개수가 100개 미만인 경우  -->
								<c:forEach var="page" begin="1" end="${totArticles/10 +1}"
									step="1">
									<c:choose>
										<c:when test="${page==pageNum }">
											<a class="sel-page"
												href="${contextPath }/adminData/listArticles.do?section=${section}&pageNum=${page}">${page }
											</a>
										</c:when>
										<c:otherwise>
											<a class="no-uline"
												href="${contextPath }/adminData/listArticles.do?section=${section}&pageNum=${page}">${page }
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
