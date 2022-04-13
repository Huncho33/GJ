<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="getVisitTotCnt" value="${countMap.getVisitTotCnt}" />
<c:set var="searchList" value="${searchMap.searchList}" />
<c:set var="searchVisitTotCnt" value="${searchMap.searchVisitTotCnt}" />
<c:set var="searchAddrList" value="${searchMap.searchAddrList}" />
<c:set var="searchAgeTotVisit" value="${searchMap.searchAgeTotVisit}" />
<c:set var="fromDate" value="${searchMap.fromDate}" />
<c:set var="toDate" value="${searchMap.toDate}" />

<c:set var="section" value="${searchMap.section}" />
<c:set var="pageNum" value="${searchMap.pageNum}" />




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
<script type="text/javascript" charset="utf-8"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/Chart.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>


<script type="text/javascript">
window.onload = function() {
	
	   
    /* 총 방문자 수 line 그래프 function */
    var ctx = $('#visitTotChart').get(0).getContext("2d");
window.theChart = new Chart(ctx, {
   type : 'line',
   data : lineChartData,
   options : {
      title: {
          display: true,
          text: '<기간별 방문자 수>',
          fontSize:18
      },
     /* x축 y축 설정 */
         scales : { 
             xAxes : [{
                barThickness : 50,
             
             gridLines : {
                display : false
             },
             offset:true
             
          }],
          yAxes: [{
             ticks: {
                min:0
             }
             
          }]
       }
   }
});

/*기간 검색 후 신청 통계 막대 그래프 function  */
var ctx = $('#applyChart').get(0).getContext("2d");
window.theChart = new Chart(ctx, {
   type : 'bar',
   data : applyBarChartData,
   options : {
  	 title: {
	            display: true,
	            text: '< 신청자 수>',
	            fontSize:18
	        },
	       /* x축 y축 설정 */
          scales : { 
              xAxes : [{
                 barThickness : 50,
              
              gridLines : {
                 display : false
              },
              offset:true
           }],
           yAxes: [{
              ticks: {
                 min:0
              }
              
           }]
        }
   }
});
	
   /*기간별 검색 후 막대 그래프 function  */
var ctx = $('#cityChart').get(0).getContext("2d");
window.theChart = new Chart(ctx, {
   type : 'bar',
   data : barChartData,
   options : {
      title: {
            display: true,
            text: '<구별 방문자 수>',
            fontSize:18
        },
     /* x축 y축 설정 */
           scales : { 
               xAxes : [{
                  barThickness : 50,
               
               gridLines : {
                  display : false
               },
               offset:true
            }],
            yAxes: [{
               ticks: {
                  min:0
               }
            }]
         }
   }
});

/* 남녀 비율 파이 차트 function*/
var ctx8 = $('#genderChart').get(0).getContext("2d"); 
window.theChart8 = new Chart(ctx8, { 
     type: 'pie', 
     data: data, 
     options: { 
        responsive: true, 
        legend: {
           position: 'top',
        },
        title: {
               display: true,
               text: '<방문 남/녀 비율>',
               fontSize:18
           },
} 
});
/* 연령 비율 파이 차트 function*/
var ctx3 = $('#ageChart').get(0).getContext("2d"); 
window.theChart3 = new Chart(ctx3, { 
     type: 'doughnut', 
     data: ageData, 
     options: { 
        responsive: true, 
        legend: {
           position: 'top',
        },
        title: {
               display: true,
               text: '<방문 연령 비율>',
               fontSize:18
           },
} 
});

}//window.onload 끝

//총 방문자수 line 그래프 데이터 셋
var lineChartData = {
     labels : ${searchMap.searchTotVisitDate},
      datasets : [
            {
               label : '방문자 수',
               borderColor: "rgba(255, 201, 14, 1)",
               backgroundColor: "white",
               data :
                 ${searchMap.searchTotVisit},
              fill: false,
           datalabels: { 
             display: false
             
             },
           }
         ]
  };
  
  
/* 기간 검색 후 신청 통계 막대 그래프 데이터 셋 */
var applyBarChartData = {
	     labels : [ "월세지원","전월세보증금","전세반환", "신혼부부", "공공임대" ],
         datasets : [
               {
                  label : '신청자수',
                  backgroundColor: [ 
                 	 'rgba(255, 99, 132, 0.5)', 
                 	 'rgba(255, 206, 86, 0.5)', 
                 	 'rgba(75, 192, 192, 0.5)', 
                 	 'rgba(153, 102, 255, 0.5)', 
                 	 'rgba(255, 159, 64, 0.5)'
                 	 ], 
                 	 borderColor: [
                 		 'rgb(255, 99, 132,1.5)', 
                 		 'rgba(255, 206, 86, 1.5)', 
                 		 'rgba(75, 192, 192, 1.5)', 
                 		 'rgba(153, 102, 255, 1.5)',
                 		 'rgba(255, 159, 64, 1.5)'
                 		 ],
                  data : [
                     ${searchMap.searchMonApply},
                     ${searchMap.searchRentApply},
                     ${searchMap.searchReturnApply},
                     ${searchMap.searchBackApply},
                     ${searchMap.searchShareApply},
                     ],
	               datalabels: { 
	   	        	display: false
	   	        	},
	               }
	             ]
	      };

/* 구별 방문자수 막대 그래프 데이터 셋 */
var barChartData = {
        labels : [ "남구", "달성구", "달성군", "동구", "북구", "서구", "수성구", "중구" ],
        datasets : [
              {
                 label : '방문자 수',
                 backgroundColor:  
                 	 'rgba(54, 162, 235, 0.5)'
                 	 , 
                 	 borderColor: 
                 		 'rgba(54, 162, 235, 1.5)'
                 		 ,
                 data : 
                    ${searchAddrList},
                   

              datalabels: { 
                    display: false
                    },
              }
            ]
     };
     
/* 남녀 비율 파이 차트 데이터 셋 */
 var GenderCnt = ${searchMap.searchGenderCnt};
if(isNaN(GenderCnt[0])){
   GenderCnt = [0];
  console.log(GenderCnt[0])
}

var data = { 
      labels: ["남"," 여"], 
      datasets: [ 
         {   
            label: 'Pie Chart Count',
            data:  
               ${searchMap.searchGenderCnt },
      
            backgroundColor: [ 
               "steelblue", 
               "lightcoral", ],
             
            borderWidth: 0,
            datalabels: { 
               labels: { 
               value: { 
                  borderWidth: 2, borderRadius: 4, font: {size: 15}, 
                  formatter: function(value, ctx) { 
                     var value = ctx.dataset.data[ctx.dataIndex]; 
                     return value > 0 ? Math.round(value / (ctx.dataset.data[0] + ctx.dataset.data[1] ) * 100) + ' %' : null; 
                     }, 
                     color: function(ctx) { 
                        var value = ctx.dataset.data[ctx.dataIndex]; return value > 0 ? 'white' : null; 
                        }, 
                        
                           padding: 4
                     }
                  }
               }
         }] 
};
     
/* 연령 비율 파이 차트 데이터 셋 */
var ageData = { 
      labels: ["20대"," 30대", "40대"], 
      datasets: [ 
         {   
            label: 'Pie Chart Age Count',
            data:  
                ${searchAgeTotVisit},
             
            backgroundColor: [ 
            	 "Mediumpurple", 
                 "orangered",
                 "green"],
             
            borderWidth: 0,
            datalabels: { 
               labels: { 
               value: { 
                  borderWidth: 2, borderRadius: 4, font: {size: 15}, 
                  formatter: function(value, ctx) { 
                     var value = ctx.dataset.data[ctx.dataIndex]; 
                     return value > 0 ? Math.round(value / (ctx.dataset.data[0] + ctx.dataset.data[1]+ctx.dataset.data[2] ) * 100) + ' %' : null; 
                     }, 
                     color: function(ctx) { 
                        var value = ctx.dataset.data[ctx.dataIndex]; return value > 0 ? 'white' : null; 
                        }, 
                        
                           padding: 4
                     }
                  }
               }
         }] 
};
</script>

<style>
#cityChart {
	margin-top: 50;
	margin-bottom: 20;
}

#genderChart {
	margin-top: 50;
	margin-bottom: 50;
}

#ageChart {
	margin-top: 50;
	margin-bottom: 50;
}

#visitTotChart {
	margin-top: 50;
	margin-bottom: 50;
}
</style>


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

			<div id="adm_stats_tot">
				<div id="adm_stats_tit1">
					<h3 class="adm_stats_tit">통계</h3>
				</div>


				<!-- 기간별 검색 -->
				<div id="adm_visit_search">
					<form name="v_search"
						action="${contextPath}/admin/stats/searchVisit.do">
						<span>[검색 방문 수: ${searchVisitTotCnt}명]</span> <input type="submit"
							name="search" value="검 색"> <input type="date" id="toDate"
							name="toDate" value="${toDate}"> <input type="date"
							id="fromDate" name="fromDate" value="${fromDate}">
					</form>
				</div>

				<table id=adm_visitList_tab align="center" width="100%">
					<tr height="40" align="center" bgcolor="#abd1f6">
						<td><b>접속일자</b></td>
						<td><b>아이디</b></td>
						<td><b>성별</b></td>
						<td><b>주소</b></td>
						<td><b>생년월일</b></td>
					</tr>
					<c:choose>
						<c:when test="${empty searchList }">
							<tr>
								<td colspan="5">
									<p align="center">
										<b><span style="font-size: 12pt;">방문한 회원이 없습니다.</span></b>
									</p>
								</td>
							</tr>
						</c:when>
						<c:when test="${not empty searchList }">

							<c:forEach var="search" items="${searchList}">
								<tr align="center">
									<td width="30%">${search.v_date}</td>
									<td width="10%">${search.member_id}</td>
									<td width="10%">${search.v_gender}</td>
									<td width="35%">${search.v_roadAddress}</td>
									<td width="15%">${search.v_birth}</td>

								</tr>

							</c:forEach>
						</c:when>
					</c:choose>
				</table>

				<div class="cls2">
					<c:if test="${searchVisitTotCnt != null }">

						<c:choose>

							<c:when test="${searchVisitTotCnt >100 }">
								<!-- 글 개수가 100 초과인경우 -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<c:if test="${section >1 && page==1 }">
										<a class="no-uline"
											href="${contextPath }/admin/stats/searchVisit.do?search=검+색&toDate=${toDate }&fromDate=${fromDate }&section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
											< </a>
									</c:if>
									<a class="no-uline"
										href="${contextPath }/admin/stats/searchVisit.do?search=검+색&toDate=${toDate }&fromDate=${fromDate }&section=${section}&pageNum=${page}">${(section-1)*10 +page }
									</a>
									<c:if test="${page ==10 }">
										<a class="no-uline"
											href="${contextPath }/admin/stats/searchVisit.do?search=검+색&toDate=${toDate }&fromDate=${fromDate }&section=${section+1}&pageNum=${section*10+1}">&nbsp;
											></a>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${searchVisitTotCnt ==100 }">
								<!--등록된 글 개수가 100개인경우  -->
								<c:forEach var="page" begin="1" end="10" step="1">
									<a class="no-uline" href="#">${page } </a>
								</c:forEach>
							</c:when>

							<c:when test="${searchVisitTotCnt< 100 }">
								<!--등록된 글 개수가 100개 미만인 경우  -->
								<c:forEach var="page" begin="1" end="${searchVisitTotCnt/10 +1}"
									step="1">
									<c:choose>
										<c:when test="${page==pageNum }">
											<a class="sel-page"
												href="${contextPath }/admin/stats/searchVisit.do?search=검+색&toDate=${toDate }&fromDate=${fromDate }&section=${section}&pageNum=${page}">${page }
											</a>
										</c:when>
										<c:otherwise>
											<a class="no-uline"
												href="${contextPath }/admin/stats/searchVisit.do?search=검+색&toDate=${toDate }&fromDate=${fromDate }&section=${section}&pageNum=${page}">${page }
											</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:if>
				</div>

				<div>
					<canvas id="visitTotChart"></canvas>
				</div>

				<div>
					<canvas id="applyChart"></canvas>
				</div>

				<canvas id="cityChart"></canvas>

				<table id="adm_visitList_tab" border="1" align="center" width="100%">
					<tr height="40" align="center" bgcolor="#abd1f6">
						<td width="11$"><b>구별</b></td>
						<td width="11$"><b>남구</b></td>
						<td width="11$"><b>달성구</b></td>
						<td width="11$"><b>달성군</b></td>
						<td width="11$"><b>동구</b></td>
						<td width="11$"><b>북구</b></td>
						<td width="11$"><b>서구</b></td>
						<td width="11$"><b>수성구</b></td>
						<td width="11$"><b>중구</b></td>
					</tr>


					<tr align="center">
						<td width="11%">인원</td>
						<c:forEach var="addr" items="${searchAddrList }"
							varStatus="status">
							<td>${addr}</td>
						</c:forEach>
					</tr>
				</table>


				<div>
					<canvas id="genderChart"></canvas>
					<canvas id="ageChart"></canvas>
				</div>
			</div>

		</div>
	</div>


</body>
</html>