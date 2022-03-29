<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="member" value="${membersMap.member}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link href="${contextPath}/resources/css/admin/memberInfo.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

<script language="javascript">

function backToList(obj){
    obj.action="${contextPath}/admin/member/listMembers.do";
    obj.submit();
  }
	// 우편번호 검색 기능
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수
						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}
						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('member_zipcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('member_roadAddress').value = fullRoadAddr;
						document.getElementById('member_jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	}

	// 이메일 주소 선택 - 자동입력 기능
	function emailSelect() {
		$('#member_email2').val($('#_member_email2').val())
	}
	
	// 권한 선택 - 자동입력 기능
	function rightSelect() {
		$('#member_right').val($('#_member_right').val())
	}

	// ajax 입력값 전송 기능
	function fn_modFormData(attribute) {
		var value;
		var memberInfo_frm = document.memberInfo_frm;
		var member_id = document.getElementById("member_id").value;
		if(attribute == 'member'){
			var member_pw = document.getElementById("member_pw").value;
			var member_phoneno = document.getElementById("member_phoneno").value;
			var member_email1 = document.getElementById("member_email1").value;
			var member_email2 = document.getElementById("member_email2").value;
			var member_zipcode = document.getElementById("member_zipcode").value;
			var member_roadAddress = document.getElementById("member_roadAddress").value;
			var member_jibunAddress = document.getElementById("member_jibunAddress").value;
			var member_namujiAddress = document.getElementById("member_namujiAddress").value;
			var member_right = document.getElementById("member_right").value;
			
			value = member_pw +","+ member_phoneno +","+ member_email1 +","+ member_email2 +","+ member_zipcode +","+ member_roadAddress +","+ member_jibunAddress +","+ member_namujiAddress +","+ member_right;
		}
		console.log(value);
		
		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/admin/member/modMemberInfo.do",
			data : {
				attribute:attribute,
				value:value,
				member_id:member_id,
			},
			success : function(data, textStatus) {
				if(data.trim()=='mod_success'){
					alert("회원 정보를 수정했습니다.");
					return;
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
	
	 // 회원 정보 삭제하기
	 function fn_remove_mem(url,member_id){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var member_idInput = document.createElement("input");
	     member_idInput.setAttribute("type","hidden");
	     member_idInput.setAttribute("name","member_id");
	     member_idInput.setAttribute("value", member_id);
		 
	     form.appendChild(member_idInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
	 
	 //관리자 권한
		function adm_reg(member_right) {
			console.log(member_right);
			if (member_right == "SuperAdmin") {
				var a = document.getElementById("_member_right");
				a.style.display = "block";
			}
		}
	
</script>

</head>

<body onload="adm_reg('${member.member_right}')">
	<div id="memberInfo_wrapper">
		<div id="memberInfo_total">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>관리페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a id="khs_left khs_left1" class="khs_lnb"><p>사용자
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/admin/member/listMembers.do">- 사용자 관리</a></li>
								<li><a href="#">-
										관리자 관리</a></li>
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
								<li><a href="">- 공지사항 관리</a></li>
								<li><a href="">- 기타자료실 관리</a></li>
								<li><a href="">- 상담게시판 관리</a></li>
								<li><a href="">- 자유게시판 관리</a></li>
								<li><a href="">- 알림게시판 관리</a></li>
							</ul></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>통계</p></a></li>
					</ul>
				</div>
			</div>
			<div id="memberInfo_tot">
				<div id="memberInfo_tit1">
					<h3 class="memberInfo_tit">내 정보 수정</h3>
				</div>
				<form id="memberInfo_frm" name="memberInfo_frm">
					<div id="memberInfo_tit2">
						<p class="memberInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 로그인 정보
						</p>
					</div>
					<div id="memberInfo_container">
						<div class="memberInfo_cnt1">
						
							<table>
								<tr height="50">
									<td width="150">:: 아이디</td>
									<td><input type="text" name="member_id" id="member_id" value="${member.member_id}" size="30" disabled><input
										type="hidden" name="member_id" id="member_id"  value="${member.member_id}"></td>
								</tr>
								<tr height="50">
									<td width="150">:: 이름</td>
									<td><input type="text" value="${member.member_name}" size="30" disabled></td>
								</tr>
								<tr height="50">
									<td width="150">:: 비밀번호</td>
									<td><input type="text" name="member_pw" id="member_pw" value="${member.member_pw}"
										size="30"></td>
								</tr>
								
							  	<tr height="50">
									<td width="150">:: 회원가입일</td>
									<td><input type="text" name="member_joinDate" id="member_joinDate" value="${member.member_joinDate}"
										size="30" disabled></td>
								</tr>
								
								<tr height="50">
									<td width="150">:: 권한</td>
									<td><input  type="text" name="member_right" id="member_right" value="${member.member_right}"
										size="30" disabled >
										<td><select
										id="_member_right" name="_member_right"
										onChange=rightSelect() title="선택" style='display:none'>
											<option value="NONE">선택</option>
											<option value="MEMBER">MEMBER</option>
											<option value="ADMIN">ADMIN</option>
											<option value="SuperAdmin">SuperAdmin</option>
									</select></td>
									
								</tr>
								
								<tr height="50">
									<td width="150">:: 최종접속일</td>
									<td><input type="text" name="member_last_log" id="member_last_log" value="${member.member_last_log}"
										size="30"></td>
								</tr>
								
							</table>
						</div>
					</div>
					<div id="memberInfo_tit2">
						<p class="memberInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 개인정보 변경
						</p>
					</div>
					<div id="memberInfo_container">
						<div class="memberInfo_cnt1">
							<table>
							<tr height="50">
									<td width="150">:: 성별</td>
									<td colspan="2"><input type="text" name="member_gender"
										id="member_gender" value="${member.member_gender}" size="30" disabled/></td>
								</tr>
								
								<tr height="50">
									<td width="150">:: 휴대전화번호</td>
									<td colspan="2"><input type="text" name="member_phoneno"
										id="member_phoneno" value="${member.member_phoneno}" size="30" /></td>
								</tr>
								
								<tr height="50">
									<td width="150">:: 이메일(E-Mail)</td>
									<td colspan="2"><input size="15" type="text"
										id="member_email1" name="member_email1"
										value="${member.member_email1}" /> @ <input size="15"
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
								<tr height="30">
									<td width="150">:: 주소</td>
									<td width="150">우편번호</td>
									<td><input type="text" id="member_zipcode"
										name="member_zipcode" size="10"
										value="${member.member_zipcode}">
										<button>
											<a href="javascript:execDaumPostcode()" class="postcode">우편번호검색</a>
										</button></td>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">도로명 주소<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td><input type="text" id="member_roadAddress"
										name="member_roadAddress" size="50"
										value="${member.member_roadAddress}"></td>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">지번 주소</td>
									<td><input type="text" id="member_jibunAddress"
										name="member_jibunAddress" size="50"
										value="${member.member_jibunAddress}"></td>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">상세 주소</td>
									<td><input type="text" id="member_namujiAddress"
										name="member_namujiAddress" size="50"
										value="${member.member_namujiAddress}"></td>
								</tr>
							</table>
						</div>

					</div>
					<div id="memberInfo_tit2">
						<p class="memberInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 신청 정책 / 지급 이력
						</p>
					</div>
					<div id="memberInfo_container">
						<div class="memberInfo_cnt1">
							<table>
								<tr height="50">
									<td width="150">:: 신청 정책 </td>
									<td colspan="2"><input type="text" name="#"
										id="#" value="#" size="30" /></td>
								</tr>
								
								<tr height="50">
									<td width="150">:: 지급 이력</td>
									<td colspan="2"><input type="text" name="#"
										id="#" value="#" size="30" /></td>
								</tr>
								
							</table>
						</div>

						<div class="memberInfo_cnt2">
							<div class="join_btn join_btn2">
								<input type="submit" value="수정" id="memberInfo_mod"
									name="memberInfo_mod"
									onClick="fn_modFormData('member');">
							</div>
							<div class="join_btn join_btn2">
								<input type="button" value="삭제" id="memberInfo_del"
									name="memberInfo_del"
									onClick="fn_remove_mem('${contextPath}/admin/member/removeMember.do', '${member.member_id}')">
									
							</div>
							<div class="join_btn join_btn1">
								<input type="reset" value="취소">
								
								<input type=button value="돌아가기"
                     onClick="backToList(this.form)">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>