<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script language="javascript">
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
						document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('roadAddress').value = fullRoadAddr;
						document.getElementById('jibunAddress').value = data.jibunAddress;

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

	function fn_overlapped() {
		var _id = $("#_member_id").val();
		if (_id == '') {
			alert("ID를 입력하세요");
			return;
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
					$('#btnOverlapped').prop("disabled", true);
					$('#_member_id').prop("disabled", true);
					$('#member_id').val(_id);
				} else {
					alert("사용할 수 없는 ID입니다.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
			}
		}); //end ajax	 
	}

	//유효성검사
	function validate() {
		var checkIP = /^[a-zA-Z0-9]{0,12}$/; //ID와 PASSWORD 유효성 검사 정규식
		var checkEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/; //Email 유효성 검사 정규식
		var checkPN = /^[0-9]{13}$/;

		var member_id = document.getElementById("member_id"); //ID
		var member_pw = document.getElementById("member_pw"); //PASSWORD
		/* var userPassC = document.getElementById("userPassC"); //PASSWORD CHECKING */ 
		var member_email = document.getElementById("member_email"); //EMAIL
		var member_name = document.getElementById("member_name"); //NAME
		/* var personalN = document.getElementById("personalN"); //PERSONAL NUMBER */


		//ID 유효성검사
		if (member_id.value == '') {
			alert("아이디를 입력하세요");
			return false;
		}
		 if (!checkIP.test(member_id.value)) {
		alert("영문과 숫자로 이루어진 4~12자의 아이디를 입력하세요");
		return false;
		}  

		//PASSWORD 유효성검사
		if (member_pw.value == '') {
			alert("비밀번호를 입력하세요");
			return false;
		}
		if (!checkIP.test(member_pw.value)) {
			alert("영문과 숫자로 이루어진 4~12자의 비밀번호를 입력하세요.");
			return false;
		}
		/*  if (userPass.value != userPassC.value) {
			alert("Wrong: The Password and Password_Check are not same, Try again");
			return false;
		}  */

		//name 유효성검사
		if (member_name.value == '') {
			alert("이름을 입력하세요");
			return false;
		}
		
		//생년월일 유효성검사
		if (member_birth.value ==''){
			alert("생년월일을 입력하세요")
			return false;
		}
		
		if (member_phoneno.value == ''){
			alert("휴대폰번호를 입력하세요")
			return false;
		}
		
		//EMAIL 유효성검사
		if (email1.value == '') {
			alert("이메일을 입력하세요");
			return false;
		}
		if (!checkEmail.test(email1.value)) {
			alert("이메일 형식이 맞지 않습니다. 다시입력하세요");
			return false;
		}

		 //PERSONAL NUMBER 유효성 검사
		/* if (personalN.value == '') {
			alert("Input Personal No");
			return false;
		}
 
		 var personal = checkPN.exec(personalN.value);

		if (personal) {// 숫자만 들어왔을때 true로 돌아감
			var personalN = document.getElementById("personalN");

			for (var i = 0; i < personalN.value.length; i++) { // 주민번호 입력한거 배열에 담아주기
				arrayPN[i] = personalN.value.charAt(i);
			}

			var tempSum = 0;// 주민번호 유효성 계산해주기
			for (var i = 0; i < 6; i++) {
				tempSum += arrayPN[i] * (2 + i);
			}

			for (var i = 0; i < 6; i++) {
				if (i >= 2) {
					tempSum += arrayPN[i + 6] * i;
				} else {
					tempSum += arrayPN[i + 6] * (8 + i);
				}
			}
			if ((11 - (tempSum % 11)) % 10 != arrayPN[12]) {
				alert("Wrong: It's not correct Personal Number");
				arrayPN = "";
				return false;
			} else {
				alert("Correct Personal Number");
				// 생일에 주민번호 입력한 앞 6자리 넣어주기
				var birthYear = document.getElementById("years");
				var birthMonth = document.getElementById("month");
				var birthDay = document.getElementById("day");

				// years 설정
				if (arrayPN[0] > 2) {
					years.value = "19" + arrayPN[0] + arrayPN[1];
				} else {
					years.value = "20" + arrayPN[0] + arrayPN[1];
				}
				// month 설정
				if (arrayPN[2] == 0) {
					month.value = arrayPN[3];
				} else {
					month.value = arrayPN[2] + arrayPN[3];
				}
				// day 설정
				if (arrayPN[4] == 0) {
					day.value = arrayPN[5];
				} else {
					day.value = arrayPN[4] + arrayPN[5];
				}
				return true;
			}
		} else {
			alert("Wrong: Input type");
			return false;
		}  */
	}  
</script>
</head>
<body>
	<h3>회원가입</h3>
	<form action="${contextPath}/member/addMember.do" method="post"
		enctype="text/plain" onsubmit="return validate()">
		<div id="detail_table">
			<table>
				<tbody>
					<tr class="dot_line">
						<td class="fixed_join">아이디</td>
						<td><input type="text" name="_member_id" id="_member_id"
							size="20" /> <input type="hidden" name="member_id"
							id="member_id" /> <input type="button" id="btnOverlapped"
							value="중복체크" onClick="fn_overlapped()" /></td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">비밀번호</td>
						<td><input name="member_pw" id="member_pw" type="password"
							size="20" /> <!-- <div class="valid">비밀번호를 입력하세요. (영문 대/소문자, 숫자를 모두 포함)</div> -->
						</td>
					</tr>

					<tr class="dot_line">
						<td class="fixed_join">이름</td>
						<td><input name="member_name" id="member_name" type="text"
							size="20" /></td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">성별</td>
						<td><input type="radio" name="member_gender" value="F" /> 여<span
							style="padding-left: 120px"></span> <input type="radio"
							name="member_gender" value="M" checked />남</td>
					</tr>

					<tr>
						<td width="200"><p align="right">생년월일</p></td>
						<td width="400"><input type="text" name="member_birth"
							id="member_birth"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">휴대번호</p></td>
						<td width="400"><input type="text" name="member_phoneno"
							id="member_phoneno"></td>
					</tr>

					<tr class="dot_line">
						<td class="fixed_join">이메일<br>(e-mail)
						</td>
						<td><input size="10px" type="text" name="email1" id="email1" />
							@ <input size="10px" type="text" name="email2" id="email2" /> <select
							name="email2" onChange="" title="직접입력">
								<option value="non">직접입력</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="naver.com">naver.com</option>
								<option value="yahoo.co.kr">yahoo.co.kr</option>
								<option value="hotmail.com">hotmail.com</option>
								<option value="paran.com">paran.com</option>
								<option value="nate.com">nate.com</option>
								<option value="google.com">google.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="empal.com">empal.com</option>
								<option value="korea.com">korea.com</option>
								<option value="freechal.com">freechal.com</option>
						</select><br> <br> <!-- <input type="checkbox" name="emailsts_yn"> -->
						</td>
					</tr>

					<tr class="dot_line">
						<td class="fixed_join">주소</td>


						<td><input type="text" id="zipcode" name="zipcode" size="10">
							<a href="javascript:execDaumPostcode()">우편번호검색</a> <br>
							<p>
								지번 주소:<br> <input type="text" id="roadAddress"
									name="roadAddress" size="50"><br> <br> 도로명
								주소: <input type="text" id="jibunAddress" name="jibunAddress"
									size="50"><br> <br> 나머지 주소: <input
									type="text" name="namujiAddress" size="50" />
								<!--   <span id="guide" style="color:#999"></span> -->
							</p></td>
					</tr>

					<!-- <td width="200"><p>&nbsp;</p></td>
					<td width="400"><input type="submit" value="가입하기"> <input
						type="reset" value="다시입력"></td>
					</tr> -->

				</tbody>
			</table>
		</div>
		<div class="clear">
			<br> <br>
			<table align=center>
				<tr>
					<td><input type="submit" value="회원 가입"> <input
						type="reset" value="다시입력"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>