<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="getVisitTotCnt" value="${countMap.getVisitTotCnt}" />
<c:set var="searchList" value="${searchMap.searchList}" />
<c:set var="searchTotVisit" value="${searchMap.searchTotVisit}" />
<c:set var="fromDate" value="${searchMap.fromDate}" />
<c:set var="toDate" value="${searchMap.toDate}" />



<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset="UTF-8">

<link href="${contextPath}/resources/css/admin/stats/stats.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<!--chart.js  -->
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
	
	
<script type="text/javascript">
window.onload = function() {
var ctx = $('#chart').get(0).getContext("2d");0
window.theChart = new Chart(ctx, {
   type : 'bar',
   data : barChartData,
   options : {}
});
}

var barChartData = {
        labels : [ "중구", "서구", "동구", "남구", "북구", "수성구", "달성구", "달성군" ],
        datasets : [
              {
                 label : '방문자 수',
                 backgroundColor : "#1E90FF",
                 data : [${searchMap.searchTotJungguVisit },
                	 ${searchMap.searchTotDongguVisit },
                	 ${searchMap.searchTotSuguVisit }, 
                	 ${searchMap.searchTotNamguVisit },
                	 ${searchMap.searchTotBukguVisit }, 
                	 ${searchMap.searchTotDalsungVisit },
                	 ${searchMap.searchTotDalsunggunVisit },
                	 ${searchMap.searchTotSusungVisit } ]
              }
            ]
     };
     

</script>


</head>
<body>
	<div id="adm_stats_bground">
		<div id="adm_stats_contatiner">
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
						<li><a href="${contextPath}/admin/stats/stats.do"
							id="khs_left khs_left3" class="khs_lnb"><p>통계</p></a></li>
					</ul>
				</div>
			</div>

			<div id="adm_stats_tot">
				<div id="adm_stats_tit1">
					<h3 class="adm_stats_tit">통계</h3>
				</div>
				<span>[총 방문: ${searchTotVisit}명]</span>

				<!-- 기간별 검색 -->
				<form name="v_search"
					action="${contextPath}/admin/stats/searchVisit.do">
					<p>
						조회기간 <input type="date" id="fromDate" name="fromDate"
							value="${fromDate}"> <input type="date" id="toDate"
							name="toDate" value="${toDate}"> <input type="submit"
							name="search" value="검 색">
					</p>
				</form>

				<table border="1" align="center" width="100%">
					<tr align="center" bgcolor="lightgreen">
						<td><b>접속일자</b></td>
						<td><b>아이디</b></td>
						<td><b>성별</b></td>
						<td><b>주소</b></td>
						<td><b>생년월일</b></td>
					</tr>

					<c:forEach var="search" items="${searchList}">
						<tr align="center">
							<td width="30%">${search.v_date}</td>
							<td width="10%">${search.member_id}</td>
							<td width="10%">${search.v_gender}</td>
							<td width="35%">${search.v_roadAddress}</td>
							<td width="15%">${search.v_birth}</td>

						</tr>
					</c:forEach>
				</table>

				<table border="1" align="center" width="100%">
					<tr align="center" bgcolor="lightgreen">
						<td><b>구별</b></td>
						<td><b>중구</b></td>
						<td><b>동구</b></td>
						<td><b>서구</b></td>
						<td><b>남구</b></td>
						<td><b>북구</b></td>
						<td><b>수성구</b></td>
						<td><b>달성구</b></td>
						<td><b>달성군</b></td>
					</tr>


					<tr align="center">
						<td width="11%">인원</td>
						<td width="11%">${searchMap.searchTotJungguVisit }</td>
						<td width="11%">${searchMap.searchTotDongguVisit }</td>
						<td width="11%">${searchMap.searchTotSuguVisit }</td>
						<td width="11%">${searchMap.searchTotNamguVisit }</td>
						<td width="11%">${searchMap.searchTotBukguVisit }</td>
						<td width="11%">${searchMap.searchTotDalsungVisit }</td>
						<td width="11%">${searchMap.searchTotDalsunggunVisit }</td>
						<td width="11%">${searchMap.searchTotSusungVisit }</td>

					</tr>
				</table>
				
				<canvas id="chart"></canvas>
			</div>

		</div>
	</div>


</body>
</html>