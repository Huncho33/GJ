

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="viewMember" value="${membersMap.member}" />
<c:set var="viewShareApply" value="${membersMap.applyShare}" />
<c:set var="shareFileList" value="${membersMap.shareFileList}" />
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
<style>
input {
	border: 0px;
	background: none;
}
</style>
<script language="javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/admin/adminApply/adminShareApply.do";
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
	function fn_adminShareApply(attribute) {
		var value;
		var adminShareApply_frm = document.adminShareApply_frm;
		var sh_no = document.getElementById("sh_no").value;
		if (attribute == 'adminShare') {

			if (document.getElementById("_sh_result").value == "") {
				if(document.getElementById("sh_result").value != ""){
					var _sh_result = document.getElementById("sh_result").value;
				} else {
					var _sh_result = "null";
				}
			} else {
				var _sh_result = document.getElementById("_sh_result").value;
			}
			

			if (document.getElementById("_sh_reason").value == "") {
				var _sh_reason = "null";
			} else {
				var _sh_reason = document.getElementById("_sh_reason").value;

			}

			if (document.getElementById("_sh_startpay").value == "") {
				var _sh_startpay = "null";
			} else {
				var _sh_startpay = document.getElementById("_sh_startpay").value;

			}

			if (document.getElementById("_sh_endpay").value == "") {
				var _sh_endpay = "null";
			} else {
				var _sh_endpay = document.getElementById("_sh_endpay").value;

			}

			/*    var _mo_reason = document.getElementById("_mo_reason").value; */
			/*    var _mo_startpay = document.getElementById("_mo_startpay").value;
			   var _mo_endpay = document.getElementById("_mo_endpay").value; */

			value = _sh_result + "," + _sh_reason + "," + _sh_startpay + ","
					+ _sh_endpay;

		}


		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/admin/adminApply/modifyAdminShare.do",
			data : {
				attribute : attribute, 
				value : value,
				sh_no : sh_no,
			},
			success : function(data, textStatus) {
				if (data.trim() == 'mod_success') {
					alert("신청 상태를 적용하였습니다.");
					return;
				} else if (data.trim() == 'not_correct') {
					alert("승인일 경우에만 지급날짜를 입력해주세요.");
					return false;
				} else if (data.trim() == 'failed') {
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
	function fn_remove_mem(url, member_id) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var member_idInput = document.createElement("input");
		member_idInput.setAttribute("type", "hidden");
		member_idInput.setAttribute("name", "member_id");
		member_idInput.setAttribute("value", member_id);

		form.appendChild(member_idInput);
		document.body.appendChild(form);
		form.submit();
	}
</script>

</head>

<body>
	<div id="memberInfo_wrapper">
		<div id="memberInfo_total">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>관리페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a href="${contextPath}/admin/member/listMembers.do" id="khs_left khs_left1" class="khs_lnb"><p>사용자
									관리</p></a>
						</li>
						<li><a href="${contextPath}/admin/adminApply/adminMonthApply.do" id="khs_left khs_left2" class="khs_lnb"><p>신청
									관리</p></a>
							</li>
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
			<div id="memberInfo_tot">
				<form id="adminMonApply_frm" name="adminMonApply_frm">
					<div id="memberInfo_tit2">
						<p class="memberInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 신청자 상세 정보
						</p>
					</div>
					<div id="memberInfo_container">
						<div class="memberInfo_cnt1">

							<table>
								<tr height="50">
									<td width="150">:: 신청번호</td>
									<td><input type="text" name="sh_no" id="sh_no"
										value="${viewShareApply.sh_no}" size="30" disabled><input
										type="hidden" name="sh_no" id="sh_no"
										value="${viewShareApply.sh_no}"></td>
								</tr>
								<tr height="50">
									<td width="150">:: 아이디</td>
									<td><input type="text" name="member_id" id="member_id"
										value="${viewMember.member_id}" size="30" disabled><input
										type="hidden" name="member_id" id="member_id"
										value="${viewMember.member_id}"></td>
								</tr>
								<tr height="50">
									<td width="150">:: 이름</td>
									<td><input type="text" value="${viewMember.member_name}"
										size="30" disabled></td>
								</tr>

								<tr height="50">
									<td width="150">:: 휴대전화번호</td>
									<td colspan="2"><input type="text" name="member_phoneno"
										id="member_phoneno" value="${viewMember.member_phoneno}"
										size="30" disabled /></td>
								</tr>

								<tr height="50">
									<td width="150">:: 이메일(E-Mail)</td>
									<td colspan="2"><input size="15" type="text"
										id="member_email1" name="member_email1"
										value="${viewMember.member_email1}" disabled /> @ <input
										size="15" type="text" id="member_email2" name="member_email2"
										value="${viewMember.member_email2}" disabled /> <select
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
										value="${viewMember.member_zipcode}" disabled>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">도로명 주소<span
										style="font-weight: bold; color: red;" disabled>*</span>
									</td>
									<td><input type="text" id="member_roadAddress"
										name="member_roadAddress" size="50"
										value="${viewMember.member_roadAddress}" disabled></td>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">지번 주소</td>
									<td><input type="text" id="member_jibunAddress"
										name="member_jibunAddress" size="50"
										value="${viewMember.member_jibunAddress}" disabled></td>
								</tr>
								<tr height="30">
									<td class="fixed_join"></td>
									<td class="fixed_join fixed_join1">상세 주소</td>
									<td><input type="text" id="member_namujiAddress"
										name="member_namujiAddress" size="50"
										value="${viewMember.member_namujiAddress}" disabled></td>
								</tr>

								<tr height="50">
									<td width="150">:: 신청 정책</td>
									<td colspan="2"><input type="text" name="sh_policy"
										id="sh_policy" value="${viewShareApply.sh_policy}" size="30"
										disabled /></td>
								</tr>

								<tr height="50">
									<td width="150">:: 지원 상태</td>
									<td colspan="2"><input type="text" name="sh_result"
										id="sh_result" value="${viewShareApply.sh_result}" size="30"
										disabled /></td>
								</tr>

								<tr height="50">
									<td width="150">:: 지급 이력</td>
									<td colspan="2"><c:choose>
											<c:when test="${empty viewShareApply.sh_startpay}">
				                              -
				                           </c:when>
											<c:otherwise>
												<input type="text" name="sh_pay" id="sh_pay"
													value="${viewShareApply.sh_startpay} ~ ${viewShareApply.sh_endpay}"
													size="30" disabled />
											</c:otherwise>
										</c:choose></td>
								</tr>

								<tr height="50">
									<td width="150">:: 처리사유</td>
									<td colspan=2><c:choose>
											<c:when
												test="${viewShareApply.sh_reason == 'null' || empty viewShareApply.sh_reason}">
                                    -
                                 </c:when>
											<c:otherwise>
												<input type="text" name="sh_reason"
													value="${viewShareApply.sh_reason}" size="30">
											</c:otherwise>
										</c:choose></td>
								</tr>

								<tr height="50">
									<td width="150">:: 휴대전화번호</td>
									<td colspan="2"><input type="text" name="member_phoneno"
										id="member_phoneno" value="${viewMember.member_phoneno}"
										size="30" disabled /></td>
								</tr>



								<c:choose>
									<c:when
										test="${not empty shareFileList && shareFileList!='null' }">
										<c:forEach var="shareFile" items="${shareFileList}"
											varStatus="status">
											<tr id="tr_${status.count }" class="table_fileTr"
												align="center">
												<td width="25%" style="background: #efefef;">등록서류${status.count }</td>
												<td colspan="3" width="75%"><input type="hidden"
													name="oldFileName" value="${shareFile.up_filename }" /> <input
													type="hidden" name="up_fileno"
													value="${shareFile.up_fileno }" />
													<div id="filedown">
														<a
															href="${contextPath}/adminShareDownload.do?sh_no=${viewShareApply.sh_no}&up_filename=${shareFile.up_filename}">${shareFile.up_filename}</a>
													</div></td>
											</tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</table>
						</div>
					</div>
					<div id="memberInfo_tit2">
						<p class="memberInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 신청 관리
						</p>
					</div>
					<div id="memberInfo_container">
						<div class="memberInfo_cnt1">
							<table>
								<tr height="50">
									<td width="150">:: 진행현황</td>
									<td><select id="_sh_result" name="_sh_result">
											<option value="">---신청현황---</option>
											<option value="심사대기">심사대기</option>
											<option value="심사중">심사중</option>
											<option value="승인">승인</option>
											<option value="미승인">미승인</option>
											<option value="재검토">재검토</option>
									</select></td>
								</tr>

								<tr height="50">
									<td width="150">:: 처리사유</td>
									<td colspan=2>
									<c:choose>
										<c:when test="${viewShareApply.sh_reason != 'null'}">
											<textarea name="_sh_reason" id="_sh_reason"
												rows="6" style="resize: none;" cols="90" maxlength="2000"
												>${viewShareApply.sh_reason}</textarea>
										</c:when>
										<c:otherwise>
											<textarea name="_sh_reason" id="_sh_reason"
												rows="6" style="resize: none;" cols="90" maxlength="2000"
												placeholder="처리사유를 작성해주세요." ></textarea>
										</c:otherwise>
									</c:choose>
									
									</td>
											
								</tr>
								
								<tr height="50">
									<td width="150">:: 지급기간설정</td>
									<td width="150">지급시작날짜</td>
									<td><input type="date" id="_sh_startpay"
										name="_sh_startpay" value=""></td>
								</tr>
								<tr>
									<td></td>
									<td width="150">지급종료날짜</td>
									<td><input type="date" id="_sh_endpay" name="_sh_endpay"
										value =""></td>
								</tr>


							</table>
						</div>
					</div>
					<div class="memberInfo_cnt2">
						<div class="join_btn join_btn2">
							<input type="submit" value="적용" id="memberInfo_mod"
								name="memberInfo_mod" onClick="fn_adminShareApply('adminShare');">
						</div>
						<div class="join_btn join_btn2">
							<input type="button" value="삭제" id="memberInfo_del"
								name="memberInfo_del"
								onClick="fn_remove_mem('${contextPath}/admin/member/removeMember.do', '${viewMember.member_id}')">

						</div>
						<div class="join_btn join_btn1">
							<input type=button value="목록" onClick="backToList(this.form)">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>