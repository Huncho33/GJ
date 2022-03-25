<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="membersList" value="${membersMap.membersList}" />
<c:set var="section" value="${membersMap.section}" />
<c:set var="pageNum" value="${membersMap.pageNum}" />
<c:set var="totMembers" value="${membersMap.totMembers}" />

<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset=UTF-8">
<title>회원 정보 출력창</title>

<style>

.memberInfo {
	text-decoration: none;
}
#adm_memberManage_bground {
	width: 100%;
	position: relative;
}

#adm_memberManage_contatiner {
	position: relative;
	margin: 0 auto;
	align: center;
	width: 1200px;
}

#adm_memberManage_title {
	padding: 50px 0px 30px 0px;
	border-bottom: 1px solid #cfcfcf;
	margin-bottom: 30px;
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
</head>
<body>
	<div id="adm_memberManage_bground">
		<div id="adm_memberManage_contatiner">
			<div id="adm_memberManage_title">
				<h1 align="center">사용자 관리</h1>
			</div>
			<span>[총 회원: ${totMembers }명]</span>
			
			<!-- 검색 창 -->
			<div id="searchMember" >
		<form name="frmSearch" action="${contextPath}/admin/member/searchMemberList.do" >
			<input name="searchMember"  type="text" > 
			<input type="submit" name="search" value="검 색" >
		</form>
	</div>
			
			<table border="1" align="center" width="100%">
				<tr align="center" bgcolor="#abd1f6">
					<td><b>아이디</b></td>
					<td><b>이름</b></td>
					<td><b>이메일</b></td>
					<td><b>가입일</b></td>
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
						<c:forEach var="member" items="${membersList}" varStatus="memberNum">
							<tr align="center">
								<td width="20%"><a class='memberInfo'
									href="${contextPath}/admin/member/viewMember.do?member_id=${member.member_id }">
									${member.member_id}</a>
								<td width="20%">${member.member_name}</td>
								<td width="30%">${member.member_email1}@${member.member_email2}</td>
								<td width="15%">${member.member_joinDate}</td>
								<td width="15%">${member.member_last_log}</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			
			<div class="txt_center">
		<c:if test="${totMembers != null }">
		
         <c:choose>

            <c:when test="${totMembers >100 }">
               <!-- 글 개수가 100 초과인경우 -->
               <c:forEach var="page" begin="1" end="10" step="1">
                  <c:if test="${section >1 && page==1 }">
                     <a class="no-uline"
										href="${contextPath }/admin/member/listMembers.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
                        < </a>
                  </c:if>
                  <a class="no-uline"
									href="${contextPath }/admin/member/listMembers.do?section=${section}&pageNum=${page}">${(section-1)*10 +page }
                  </a>
                  <c:if test="${page ==10 }">
                     <a class="no-uline"
										href="${contextPath }/admin/member/listMembers.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;
                        ></a>
                  </c:if>
               </c:forEach>
            </c:when>
            <c:when test="${totMembers ==100 }">
               <!--등록된 글 개수가 100개인경우  -->
               <c:forEach var="page" begin="1" end="10" step="1">
                  <a class="no-uline" href="#">${page } </a>
               </c:forEach>
            </c:when>

            <c:when test="${totMembers< 100 }">
               <!--등록된 글 개수가 100개 미만인 경우  -->
               <c:forEach var="page" begin="1"
								end="${totMembers/10 +1}" step="1">
                  <c:choose>
                     <c:when test="${page==pageNum }">
                        <a class="sel-page"
											href="${contextPath }/admin/member/listMembers.do?section=${section}&pageNum=${page}">${page }
                        </a>
                     </c:when>
                     <c:otherwise>
                        <a class="no-uline"
											href="${contextPath }/admin/member/listMembers.do?section=${section}&pageNum=${page}">${page }
                        </a>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </c:when>

         </c:choose>
      </c:if>
      </div>

			<a href="${contextPath}/member/memberForm.do"><button>회원등록</button></a>
		</div>
	</div>
</body>
</html>
