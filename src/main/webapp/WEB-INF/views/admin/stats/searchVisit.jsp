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
var ctx8 = $('#ageChart').get(0).getContext("2d"); 
window.theChart8 = new Chart(ctx8, { 
     type: 'pie', 
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

}
/* 구별 방문자수 막대 그래프 데이터 셋 */
var barChartData = {
        labels : [ "중구", "동구", "서구", "남구", "북구", "달성구", "달성군", "수성구" ],
        datasets : [
              {
                 label : '방문자 수',
                 backgroundColor : "#1E90FF",
                 data : [
                    ${searchAddrList[0]},
                    ${searchAddrList[1]},
                    ${searchAddrList[2]},
                    ${searchAddrList[3]},
                    ${searchAddrList[4]},
                    ${searchAddrList[5]},
                    ${searchAddrList[6]},
                    ${searchAddrList[7]}
],
              datalabels: { 
                    display: false
                    },
              }
            ]
     };
     
/* 남녀 비율 파이 차트 데이터 셋 */
var data = { 
      labels: ["남"," 여"], 
      datasets: [ 
         {   
            label: 'Pie Chart Count',
            data: [ 
               ${searchMap.searchGenderCnt[0] },
               ${searchMap.searchGenderCnt[1] } 
               ], 
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
      labels: ["20"," 30", "40"], 
      datasets: [ 
         {   
            label: 'Pie Chart Age Count',
            data: [ 
                ${searchAgeTotVisit[0]},
                ${searchAgeTotVisit[1]},
                ${searchAgeTotVisit[2]}
               ], 
            backgroundColor: [ 
               "Mediumpurple", 
               "orangered",
               "lightgreen"],
             
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
            <span>[총 방문: ${searchVisitTotCnt}명]</span>

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
            <canvas id="visitChart"></canvas>


            <canvas id="cityChart"></canvas>

            <table border="1" align="center" width="100%">
               <tr align="center" bgcolor="lightgreen">
                  <td width="11$"><b>구별</b></td>
                  <td width="11$"><b>수성구</b></td>
                  <td width="11$"><b>북구</b></td>
                  <td width="11$"><b>달성군</b></td>
                  <td width="11$"><b>중구</b></td>
                  <td width="11$"><b>동구</b></td>
                  <td width="11$"><b>서구</b></td>
                  <td width="11$"><b>남구</b></td>
                  <td width="11$"><b>달성구</b></td>
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