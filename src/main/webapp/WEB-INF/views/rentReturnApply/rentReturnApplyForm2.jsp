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
	href="${contextPath}/resources/css/monthApplyForm/monthApplyForm2.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script language="javascript">
	
	
	
	function fn_modApplyFormData(attribute) {
		var value;
		var myInfo_frm = document.myInfo_frm;
		if(attribute == 'member'){
			/* var member_pw = document.getElementById("member_pw").value; */
			var member_phoneno = document.getElementById("member_phoneno").value;
			var member_email1 = document.getElementById("member_email1").value;
			var member_email2 = document.getElementById("member_email2").value;
			var member_zipcode = document.getElementById("member_zipcode").value;
			var member_roadAddress = document.getElementById("member_roadAddress").value;
            if(document.getElementById("member_jibunAddress").value == ""){
                var member_jibunAddress = " ";
             } else{
                var member_jibunAddress = document.getElementById("member_jibunAddress").value;
             }
             if(document.getElementById("member_namujiAddress").value == ""){
                var member_namujiAddress = " ";
             }else{
                var member_namujiAddress = document.getElementById("member_namujiAddress").value;
             }
			
			value = member_phoneno +","+ member_email1 +","+ member_email2 +","+ member_zipcode +","+ member_roadAddress +","+ member_jibunAddress +","+ member_namujiAddress;
		}
		console.log(value);
		
		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/ret/rentReturnApplyForm2.do",
			data : {
				attribute:attribute,
				value:value,
			},
			success : function(data, textStatus) {
				if(data.trim()=='mod_success'){
					 
					 location.href = "../ret/rentReturnApplyForm3.do";
					 alert("다음단계로 이동합니다."); 
					return 
				}else if(data.trim()=='failed'){
					alert("다시 시도해 주세요.");	
				}
				
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다." + data);
			},
			complete : function(data, textStatus) {
				//alert("작업을 완료 했습니다");
				
			}
		}); //end ajax
	}
	
	function cancel() {

		if (confirm("정말 취소하시겠습니까?") == true) { //확인

			window.location.href = '../main.do';

		} else { //취소

			return false;

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
					<li>
						<div style="margin-top: -20px;">약관동의</div>
					</li>
					<li>
						<div style="margin-top: -20px;">서류등록</div>
					</li>
				</ul>
			</div>
			<div id="monthApply_title">
				<p class="monthApply_tit" style="font-weight: bold;">
					<i class="fa-solid fa-pen"></i>&nbsp;개인정보 등록(<span
						style="font-weight: bold; color: red;">*</span>는 필수입력사항입니다.)
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
			<div id="monthApply_table">
				<form>
					<div id="detail_table">
						<table id="join_detail_table">
							<tbody>
								<tr class="dot_line">
									<td class="fixed_join">:: 이름<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input name="member_name" type="text"
										size="20" value="${member.member_name}"
										style="border: 0; background: #fff;" disabled /> <input
										name="member_id" type="hidden" value="${member.member_id}" /></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join">:: 생년월일<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input type="text" name="member_birth"
										id="member_birth" value="${member.member_birth}" size="20"
										style="border: 0; background: #fff;" disabled /></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join">:: 휴대전화번호<span
										style="font-weight: bold; color: red;">*</span></td>
									<td colspan="2"><input type="number" name="member_phoneno"
										id="member_phoneno" value="${member.member_phoneno}" size="20" />
									</td>
								</tr>


								<tr class="dot_line">
									<td class="fixed_join">:: 이메일(E-Mail)<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input size="10px" type="text"
										id="member_email1" name="member_email1"
										value="${member.member_email1}" /> @ <input size="10px"
										type="text" id="member_email2" name="member_email2"
										value="${member.member_email2}" /> <select
										id="_member_email2" name="_member_email2"
										onChange=emailSelect() title="직접입력">
											<option value="non">직접입력</option>
											<option value="hanmail.net">hanmail.net</option>
											<option value="daum.net">daum.net</option>
											<option value="naver.com">naver.com</option>
											<option value="nate.com">nate.com</option>
											<option value="google.com">google.com</option>
											<option value="gmail.com">gmail.com</option>
									</select></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 주소</td>
									<td class="fixed_join fixed_join1">우편번호<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td><input type="text" id="member_zipcode"
										name="member_zipcode" size="10"
										value="${member.member_zipcode}">
										<button>
											<a href="javascript:execDaumPostcode()" class="postcode">우편번호검색</a>
										</button></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">도로명 주소<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td><input type="text" id="member_roadAddress"
										name="member_roadAddress" size="50"
										value="${member.member_roadAddress}"></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">지번 주소</td>
									<td><input type="text" id="member_jibunAddress"
										name="member_jibunAddress" size="50"
										value="${member.member_jibunAddress}"></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">상세 주소</td>
									<td><input type="text" id="member_namujiAddress"
										name="member_namujiAddress" size="50"
										value="${member.member_namujiAddress}"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="join_btn_list">
						<div class="join_btn join_btn1">
							<input type="reset" value="취소" onclick="cancel()">
						</div>
						<div class="join_btn join_btn2">
							<input type="submit" value="다음"
								onClick="fn_modApplyFormData('member');">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>