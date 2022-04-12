
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
<meta charset="utf-8">
<style>
</style>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link
	href="${contextPath}/resources/css/monthApplyForm/monthApplyResult.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script language="javascript">
	
</script>
</head>

<body>
	<div id="monthApply_bground">
		<div id="monthApply_total">
			<form name="frmResult" method="post" action="">
				<div id="result_tit">
					<p>
						<input id="member_name" type="text" name="member_name"
							value="${member.member_name}" disabled>님의 전월세보증금이자지원 신청
						결과는<span><input id="result_title" type="text"
							name="member_id" value="${no.rent_result}" disabled></span>입니다.
					</p>
				</div>
			</form>
			<div id="monthApply_title">
				<p class="monthApply_tit" style="font-weight: bold;">
					<i class="fa-solid fa-pen"></i>&nbsp;지원결과상세
				</p>
			</div>
			<div id="monthApply_title2">
				<p class="monthApply_tit">
				<table id="join_detail_table">
					<tbody>
						<tr class="dot_line">
							<td class="fixed_join">:: 신청날짜</td>
							<td><input type="text" name="member_id"
								id="Apply_PolicyName" value="${no.rent_date}"
								style="border: 0; background: #fff;" disabled></td>
						</tr>

						<tr class="dot_line">
							<td class="fixed_join">:: 신청자</td>
							<td><input type="text" name="member_name"
								id="Apply_PolicyName" value="${member.member_name}"
								style="border: 0; background: #fff;" disabled></td>
						</tr>

						<tr class="dot_line">
							<td class="fixed_join">:: 선정 여부</td>
							<td><input type="text" name="Apply_PolicyName"
								id="Apply_PolicyName" value="${no.rent_result}"
								style="border: 0; background: #fff;" disabled></td>
						</tr>

						<tr class="dot_line">
							<td class="fixed_join">:: 상세 사유</td>
							<td><input type="text" name="Apply_PolicyName"
								id="Apply_PolicyName" value=""
								style="border: 0; background: #fff;" disabled></td>
						</tr>
					</tbody>
				</table>
				</p>
			</div>

			<div id="monthApply_table">
					<div class="join_btn1">
						<button onclick ="location.href='${contextPath}/mypage/rentApplyList.do'">나의신청현황확인</button>
					</div>
			</div>

		</div>
	</div>
	
	
	
</body>

</html>
