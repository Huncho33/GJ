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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/memberForm/memberForm3.css"
	rel="stylesheet" type="text/css">
<script src="${contextPath}/resources/js/memberForm/memberForm3.js"></script>
<script language="javascript">
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
	var overlapped = false;
	// 아이디 중복체크 기능
	function fn_overlapped() {

		var checkID = /^[a-zA-Z0-9]{4,12}$/; //ID 유효성 검사 정규식  변수선언  
		var _id = $("#_member_id").val();
		if (_id == '') {
			alert("ID를 입력하세요");
			return;
		}
		//id 정규식 유효성검사(4~12자리 입력하게끔) 
		if (!checkID.test(_member_id.value)) {
			alert("영문과 숫자로 이루어진 4~12자의 아이디를 입력하세요");
			return false;
		}

		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/member/overlapped.do",
			dataType : "text",
			data : {
				id : _id
			},
			success : function(data, textStatus) {
				if (data == 'false') {

					alert("사용할 수 있는 ID입니다.");
					
					$('#member_id').val(_id); 
				} else {
					alert("이미 사용 중인 ID입니다.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");

			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
			}
		}); //end ajax
		overlapped = true;
	}

	// 이메일 주소 선택 - 자동입력 기능
	function emailSelect() {
		$('#member_email2').val($('#_member_email2').val())
	}
	function check1() {
		if (overlapped) {
			return true;
		} else {
			alert("중복체크하세요.");
			return false;
		}
	}
	//
	// 빈칸 유효성 검사
	function formCheck(form) {

		var checkPw = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/; //PASSWORD 유효성 검사 정규식
		var checkBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/; // 생년월일 정규식. 숫자만 입력 가능하게 하는 정규식 ex)[0-9] 0~9까지.
		var checkName = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|\*]+$/;//한글+영어 정규식
		var checkPhone = /^\d{3}\d{3,4}\d{4}$/; // 휴대폰번호정규식 

		if (form.member_id.value == "") {
			alert('영문과 숫자로 이루어진 4~12자의 아이디를 입력하세요.')
			form.member_id.focus();
			return false;
		}
		//비밀번호	
		if (form.member_pw.value == "") {
			alert('비밀번호를 입력하세요.')
			form.member_pw.focus();
			return false;
		}
		//비밀번호 정규식 검사.  
		if (!checkPw.test(member_pw.value)) {
			alert("영문 대소문자, 숫자, 특수기호로 이루어진 8~25 자의 비밀번호로 입력하세요.");
			return false;
		}
		//이름 
		if (form.member_name.value == "") {
			alert('이름을 입력하세요.')
			form.member_name.focus();
			return false;
		}
		//이름 정규식 검사
		if (!checkName.test(member_name.value)) {
			alert("올바르지 않은 형식입니다.");
			return false;
		}
		// 생년월일    
		if (form.member_birth.value == "") {
			alert('생년월일을 입력하세요.')
			form.member_birth.focus();
			return false;
		}
		// 생년월일 정규식 검사(숫자만 입력가능) 
		if (!checkBirth.test(member_birth.value)) {
			alert("8자리 생년월일을 숫자만 입력하세요. EX)yyyyMMdd");
			return false;
		}

		// 휴대폰번호	
		if (form.member_phoneno.value == "") {
			alert('휴대전화번호를 입력하세요.')
			form.member_phoneno.focus();
			return false;
		}
		// 휴대폰번호 정규식 검사
		if (!checkPhone.test(member_phoneno.value)) {
			alert("11자리 숫자로 입력하세요.(예)01011111234");
			return false;
		}
		if (form.member_email1.value == "" || form.member_email2.value == "") {
			alert('이메일을 입력하세요.')
			form.member_email1.focus();
			return false;
		}
		if (form.member_zipcode.value == ""
				|| form.member_roadAddress.value == "") {
			alert('우편번호와 도로명 주소를 입력하세요.')
			form.member_roadAddress.focus();
			return false;
		}

	}

	/*  */
	/* 비밀번호 확인 일치 유효성 검사 */
	$(function() {

		$('#member_pwck').keyup(function() {

			var _member_pwck = $("#member_pwck").val();
			var _member_pw = $("#member_pw").val();
			console.log(_member_pwck);
			if (_member_pwck == _member_pw) {
				$('#pwck1').css('display', 'block');
				$('#pwck2').css('display', 'none');

			} else {
				$('#pwck1').css('display', 'none');
				$('#pwck2').css('display', 'block');

			}

		});
	});

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
	<div id="Memjoin_bground">
		<div id="Memjoin_total">
			<div id="kkhs_sub" class="kkhs_joinCommon">
				<div id="kkhs_subVisual">
					<h3 class="kkhs_subTit">회원가입</h3>
				</div>
				<ul class="kkhs_processTab">
					<li class="kkhs_on">
						<div style="margin-top: -20px;">약관동의</div>
					</li>
					<li class="kkhs_on">
						<div style="margin-top: -20px;">본인인증</div>
					</li>
					<li class="kkhs_on">
						<div style="margin-top: -20px;">정보입력</div>
					</li>
					<li>
						<div style="margin-top: -20px;">가입완료</div>
					</li>
				</ul>
			</div>
			<div id="Memjoin_title">
				<p class="Memjoin_tit" style="font-weight: bold;">
					<i class="fa-solid fa-pen"></i>&nbsp;정보입력(<span
						style="font-weight: bold; color: red;">*</span>는 필수입력사항입니다.)
				</p>
			</div>
			<div id="Memjoin_table">
				<form action="${contextPath}/member/addMember.do" method="post" onsubmit="return formCheck(this)">
					<div id="detail_table">
						<table id="join_detail_table">
							<tbody>
								<tr class="dot_line">
									<td class="fixed_join">:: 아이디<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input type="text" name="_member_id"
										id="_member_id" size="20" minlength="4" maxlength="12" /> <input
										type="hidden" name="member_id" id="member_id" /> <input
										type="button" id="btnOverlapped" value="중복체크"
										onClick="fn_overlapped()" /></td>
								</tr>
								<tr class="dot_line">
									<td></td>
									<td colspan="2"><a class="join_rule">&nbsp; * 영문과 숫자로
											이루어진 4~12자의 아이디를 입력하세요</a></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 비밀번호<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input name="member_pw" id="member_pw"
										type="password" size="20" minlength="8" maxlength="25" />
								</tr>

								<tr class="dot_line">
									<td class="fixed_join">:: 비밀번호확인<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input name="member_pwck" id="member_pwck"
										type="password" size="20" minlength="8" maxlength="25" />
								</tr>
								<span id="pwck1"
									style="color: lime; display: none; font-size: 14px;">비밀번호가
									일치합니다.</span>
								<span id="pwck2"
									style="color: red; display: none; font-size: 14px;">비밀번호가
									일치하지않습니다.</span>

								<tr class="dot_line">
									<td></td>
									<td colspan="2"><a class="join_rule">&nbsp; * 영문 대소문자,
											숫자, 특수기호로 이루어진 8~25 자의 비밀번호를 입력하세요.</a></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 이름<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input name="member_name" id="member_name"
										type="text" size="20" /></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 성별<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input type="radio" name="member_gender"
										value="F" /> 여성<span style="padding-left: 120px"></span> <input
										type="radio" name="member_gender" value="M" checked />남성</td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 생년월일<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input type="text" name="member_birth"
										id="member_birth" minlength="8" maxlength="8" size="20" /></td>
								</tr>
								<tr class="dot_line">
									<td></td>
									<td colspan="2"><a class="join_rule">&nbsp; * 8자리
											생년월일을 숫자만 입력하세요. EX)yyyyMMdd</a></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 휴대전화번호<span
										style="font-weight: bold; color: red;">*</span></td>
									<td colspan="2"><input type="text" name="member_phoneno"
										id="member_phoneno" minlength="11" maxlength="11" size="20" />
									</td>
								</tr>
								<tr class="dot_line">
									<td></td>
									<td colspan="2"><a class="join_rule">&nbsp; * 특수문자 없이
											숫자만 입력하세요. EX)01011111234 </a></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join">:: 이메일(E-Mail)<span
										style="font-weight: bold; color: red;">*</span>
									</td>
									<td colspan="2"><input size="10px" type="text"
										id="member_email1" name="member_email1" /> @ <input
										size="10px" type="text" id="member_email2"
										name="member_email2" /> <select id="_member_email2"
										name="_member_email2" onChange=emailSelect() title="직접입력">
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
										name="member_zipcode" size="10">
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
										name="member_roadAddress" size="50"></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">지번 주소</td>
									<td><input type="text" id="member_jibunAddress"
										name="member_jibunAddress" size="50"></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">상세 주소</td>
									<td><input type="text" id="member_namujiAddress"
										name="member_namujiAddress" size="50"></td>
								</tr>
							</tbody>
						</table>
					</div>
			</div>
			<div class="join_btn_list">
				 <div class="join_btn join_btn1">
					<input type="button" value="취소" button onclick="cancel()">
				  </div>
				   <div class="join_btn join_btn2">
					 <input type="submit" value="회원가입" onclick="check1()">
			    	</div>
			 </div>
		  </form>
		</div>
	</div>
</body>

</html>