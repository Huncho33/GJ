
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>



<!DOCTYPE html >
<html>
<head>
<meta charset=utf-8>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link
	href="${contextPath}/resources/css/monthApplyForm/monthApplyForm4.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">


<script>

	function cancel() {

		if (confirm("정말 취소하시겠습니까?") == true) { //확인

			window.location.href = '../main.do';

		} else { //취소

			return false;

		}

	}
	
	function attach(form){
		if($("#attach1").val() ==""){
			alert("필수 서류를 등록해주세요.");
			return false;
		}
		if($("#attach2").val() ==""){
			alert("필수 서류를 등록해주세요.");
			return false;
		}
		if($("#attach3").val() ==""){
			alert("필수 서류를 등록해주세요.");
			return false;
		}
		if($("#attach4").val() ==""){
			alert("필수 서류를 등록해주세요.");
			return false;
		}
		if($("#attach5").val() ==""){
			alert("필수 서류를 등록해주세요.");
			return false;
		}
		else{
			form.submit();
		}
	}
</script>
</head>
<body>
	<div id="monthApply_bground">
		<div id="monthApply_total">
			<div id="kkhs_sub" class="kkhs_joinCommon">
				<ul class="kkhs_processTab">
					<li class="kkhs_on">
						<div style="margin-top: -20px;">신청자격</div>
					</li>
					<li class="kkhs_on">
						<div style="margin-top: -20px;">정보등록</div>
					</li>
					<li class="kkhs_on">
						<div style="margin-top: -20px;">약관동의</div>
					</li>
					<li class="kkhs_on">
						<div style="margin-top: -20px;">서류등록</div>
					</li>
				</ul>
			</div>

			<div id="monthApply_fileTot">
				<div id="monthApply_title">
					<p class="monthApply_tit" style="font-weight: bold;">
						<i class="fa-solid fa-pen"></i>&nbsp;신청서류 등록
					</p>
				</div>
				<div id="monthApply_title2">
					<p class="monthApply_tit">
					<table id="join_detail_table">
						<tr class="dot_line">
							<td class="fixed_join">:: 신청 분야</td>
							<td><input type="text" name="Apply_PolicyName"
								id="Apply_PolicyName" value="전세반환보증금이자"
								style="border: 0; background: #fff;" disabled></td>
						</tr>
					</table>
					</p>
				</div>

				<div id="monthApply_title3">
					<form method="post"
						action="${contextPath}/ret/rentReturnApplyForm4.do"
						enctype="multipart/form-data">
						<div id="detail_table">
							<table id="join_detail_table2">
								<tbody>

									<tr>
										<td class="fixed_join2">:: 요청서류 1</td>
										<td class="table_Input"><input type="file" name="up_fileName1" id="attach1" value="첨부파일 추가" /></td>
									</tr>
									<tr>
										<td class="fixed_join2">:: 요청서류 2</td>
										<td class="table_Input"><input type="file" name="up_fileName2" id="attach2" value="첨부파일 추가" /></td>
									</tr>
									<tr>
										<td class="fixed_join2">:: 요청서류 3</td>
										<td class="table_Input"><input type="file" name="up_fileName3" id="attach3" value="첨부파일 추가" /></td>
									</tr>
									<tr>
										<td class="fixed_join2">:: 요청서류 4</td>
										<td class="table_Input"><input type="file" name="up_fileName4" id="attach4"value="첨부파일 추가" /></td>
									</tr>
									<tr>
										<td class="fixed_join2">:: 요청서류 5</td>
										<td class="table_Input"><input type="file" name="up_fileName5" id="attach5" value="첨부파일 추가" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="join_btn_list">
							<div class="join_btn join_btn1">
								<input type="reset" value="취소" onclick="cancel()">
							</div>
							<div class="join_btn join_btn2">
								<input type="button" id="nextBtn" value="다음단계" onclick="attach(form)" />
							</div>
						</div>
						
						<div id="d_file">
							<input type="hidden" name="ret_reason" value="null" /> <input
								type="hidden" name="ret_policy" value="null" /> <input
								type="hidden" name="ret_date" value="null" />
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>

