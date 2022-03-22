<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="section" value="${articlesMap.section}" />
<c:set var="pageNum" value="${articlesMap.pageNum}" />
<c:set var="searchWord" value="${articlesMap.searchWord}" />
<c:set var="totArticles" value="${articlesMap.totArticles}" />
<c:set var="searchTotArticles" value="${articlesMap.searchTotArticles}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}

#data_bground {
	width: 100%;
	position: relative;
}

#data_container {
	position: relative;
	margin: 0 auto;
	align: center;
	width: 1200px;
}

#data_title {
	padding: 50px 0px 30px 0px;
	border-bottom: 1px solid #cfcfcf;
	margin-bottom: 30px;
}

#data_list_tab {
	border-collapse: collapse;
}

#data_list_tab td {
	text-indent: 10px;
	font-size: 15px;
	border: solid 1px #e5e5e5;
}

#data_list_tab tr {
	border-left: none;
	border-right: none;
}

.data_subnavi {
	margin-bottom: 20px;
	float: right;
}

.txt_center {
	text-align: center;
	font-size: 14pt;
	margin: 20 auto;
}

.no-uline {
	text-decoration: none;
}

.sel-page {
	text-decoration: none;
	color: red;
}
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script>
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/boardData/articleForm.do';
		}
	}
	

</script>	
<body>
	<div id="data_bground">
		<div id="data_container">
			<div id="data_title">
				<h1 align="center">공지사항</h1>
			</div>
			<span>[검색 게시물: ${searchTotArticles }건]</span>
			<span class="data_subnavi" >청년패키지>공지사항></span>
			
			<!-- 검색 창 -->
			<div id="search" >
		<form name="frmSearch" action="${contextPath}/boardData/searchBoardList.do" >
			<input name="searchWord"  type="text" > 
			<input type="submit" name="search" value="검 색" >
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
									<b><span style="font-size: 12pt;" >등록된 글이 없습니다.</span></b>
								</p>
							</td>
						</tr>
					</c:when>
					<c:when test="${not empty articlesList }">
						<c:forEach var="article" items="${articlesList }"
							varStatus="articleNum">
							<tr align="center" height="35">

								<td width="10%">${article.etc_NO}</td>
								<td width="55%"><a class='cls1'
									href="${contextPath}/boardData/viewArticle.do?etc_NO=${article.etc_NO }">
										${article.etc_title }</a>
									<td width="10%">${article.member_id }</td>
							<td width="15%">${article.etc_date}</td>
							<td width="10%">${article.etc_hits}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		
		<div class="txt_center">
		<c:if test="${searchTotArticles != null }">
		
         <c:choose>

            <c:when test="${searchTotArticles >100 }">
               <!-- 글 개수가 100 초과인경우 -->
               <c:forEach var="page" begin="1" end="10" step="1">
                  <c:if test="${section >1 && page==1 }">
                     <a class="no-uline"
										href="${contextPath }/boardData/searchBoardList.do?searchWord=${searchWord }&search=검+색section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
                        < </a>
                  </c:if>
                  <a class="no-uline"
									href="${contextPath }/boardData/searchBoardList.do?searchWord=${searchWord }&search=검+색section=${section}&pageNum=${page}">${(section-1)*10 +page }
                  </a>
                  <c:if test="${page ==10 }">
                     <a class="no-uline"
										href="${contextPath }/boardData/searchBoardList.do?searchWord=${searchWord }&search=검+색section=${section+1}&pageNum=${section*10+1}">&nbsp;
                        ></a>
                  </c:if>
               </c:forEach>
            </c:when>
            <c:when test="${searchTotArticles ==100 }">
               <!--등록된 글 개수가 100개인경우  -->
               <c:forEach var="page" begin="1" end="10" step="1">
                  <a class="no-uline" href="#">${page } </a>
               </c:forEach>
            </c:when>

            <c:when test="${searchTotArticles< 100 }">
               <!--등록된 글 개수가 100개 미만인 경우  -->
               <c:forEach var="page" begin="1"
								end="${searchTotArticles/10 +1}" step="1">
                  <c:choose>
                     <c:when test="${page==pageNum }">
                        <a class="sel-page"
											href="${contextPath }/boardData/searchBoardList.do?searchWord=${searchWord }&search=검+색section=${section}&pageNum=${page}">${page }
                        </a>
                     </c:when>
                     <c:otherwise>
                        <a class="no-uline"
											href="${contextPath }/boardData/searchBoardList.do?searchWord=${searchWord }&search=검+색section=${section}&pageNum=${page}">${page }
                        </a>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </c:when>

         </c:choose>
      </c:if>
      </div>


<a class="cls1"
				href="javascript:fn_articleForm('${isLogOn}','${contextPath}/boardData/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p
					class="cls2">글쓰기</p></a>
	</div>
							</div>


</body>
</html>
