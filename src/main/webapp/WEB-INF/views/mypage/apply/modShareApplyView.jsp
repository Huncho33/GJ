<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<c:set var="removeCompleted" value="${ViewShareMap.removeCompleted}" />
<c:set var="share" value="${ViewShareMap.applyShare}" />
<c:set var="shareFileList" value="${ViewShareMap.shareFileList}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link href="${contextPath}/resources/css/mypage/myApplyView.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

<c:choose>
	<c:when test="${removeCompleted eq true }">
		<script type="text/javascript">
   $(window).load(function(){
      fn_enable()
   }); 
   </script>
	</c:when>
</c:choose>

<script type="text/javascript">
//우편번호 검색 기능
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
						document.getElementById('member_zipcode').value = data.zonecode;
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

	// 신청취소
	function fn_remove_apply(url,sh_no){
		var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", url);
        var sh_noInput = document.createElement("input");
        sh_noInput.setAttribute("type","hidden");
        sh_noInput.setAttribute("name","sh_no");
        sh_noInput.setAttribute("value", sh_no);
        
        form.appendChild(sh_noInput);
        document.body.appendChild(form);
        form.submit();
    }
	
	
	function fn_modify_apply(form){
		form.action="${contextPath}/mypage/modShareApply.do";
		form.submit();
    }
	

</script>


</head>
<body>
	<div id="myApplyView_wrapper">
		<div id="myApplyView_total">
			<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>마이페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a id="khs_left khs_left1" class="khs_lnb"><p>회원정보
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/myInfo.do">- 내 정보 수정</a></li>
								<li><a href="${contextPath}/mypage/memDeleteForm.do">-
										회원탈퇴</a></li>
							</ul></li>
						<li><a id="khs_left khs_left2" class="khs_lnb"><p>나의
									신청 현황</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/monthApplyList.do">-
										월세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/rentApplyList.do">-
										전세지원 신청 현황</a></li>
								<li><a href="${contextPath}/mypage/shareApplyList.do">-
										행복주택지원 신청 현황</a></li>
							</ul></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>나의
									게시글 및 상담</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/myBoardList.do">- 나의
										게시글 목록</a></li>
								<li><a href="${contextPath}/mypage/myQna.do">- 나의 상담 목록</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			<div id="myApplyView_tot">
				<div id="myApplyView_tit2">
					<p class="myApplyView_subtit" style="font-weight: bold;">
					<ul>
						<li>본 신청서는 원스톱청년주거 플랫폼을 통해 지원한 신청서입니다.</li>
						<li>주민등록상의 거주지 및 기타 조건 사항이 변경되었을 시엔 변경신청 해주시기 바랍니다.</li>
						<li>심사 상태가 심사로 변경된 후의 신청취소는 '☎ 000-0000(정책담당부서)'로 문의주시기 바랍니다.</li>
					</ul>
					</p>
				</div>
				<div id="myApplyView_cnt">
					<div id="myApplyView_tit1">
						<p class="myInfo_subtit" style="font-weight: bold;">
							<strong>｜</strong> 신청서 변경 신청
						</p>
					</div>
					<form name="applyFrm" id="applyFrm" method="post"
						accept-charset="UTF-8" enctype="multipart/form-data">
						<div id="myApplyView_cnt1">
							<table id="myApplyView_viewTable" align="center" width="100%"
								border=1>
								<tbody>

									<tr align="center">
										<td width="25%" style="background: #efefef;">지원사업명</td>
										<td width="25%">${share.sh_policy}</td>
										<td width="25%" style="background: #efefef;">신청일</td>
										<td width="25%">${share.sh_date}</td>
									</tr>
									<tr align="center">
										<td width="25%" style="background: #efefef;">대상</td>
										<td width="25%">대구시 거주 청년<input type="hidden"
											name="sh_no" value="${share.sh_no }" /></td>
										<td width="25%" style="background: #efefef;">지급기간</td>
										<td width="25%"><c:choose>
												<c:when test="${not empty share.sh_startpay}">
									${share.sh_startpay} ~ ${share.sh_endpay}
								</c:when>
												<c:otherwise>
									-
								</c:otherwise>
											</c:choose></td>
									</tr>
									<tr align="center">
										<td width="25%" style="background: #efefef;">신청자명</td>
										<td width="25%">${member.member_name}</td>
										<td width="25%" style="background: #efefef;">심사상태</td>
										<td width="25%"><a href="#">${share.sh_result}</a></td>
									</tr>
									<tr align="center">
										<td width="25%" style="background: #efefef;">생년월일</td>
										<td colspan="3" width="75%">${member.member_birth}</td>
									</tr>
									<tr align="center">
										<td width="25%" style="background: #efefef;">휴대전화번호</td>
										<td colspan="3" width="75%"><input type="text"
											id="member_phoneno" name="member_phoneno"
											value="${member.member_phoneno}"  ></td>
									</tr>
									<tr align="center">
										<td rowspan="2" width="25%" style="background: #efefef;">주소</td>
										<td colspan="3" width="75%"><input type="text"
											id="member_roadAddress" name="member_roadAddress" id="member_roadAddress"
											value="${member.member_roadAddress}"  >
											
											<button>
												<a href="javascript:execDaumPostcode()" class="postcode">우편번호검색</a>
											</button></td>
									</tr>
									<tr align="center">
										<td colspan="3" width="75%">
										<input type="hidden"
											id="member_zipcode" name="member_zipcode"
											value="${member.member_zipcode}">
										<input type="hidden"
											id="member_jibunAddress" name="member_jibunAddress"
											value="${member.member_jibunAddress}">
											<input type="text"
											id="member_namujiAddress" name="member_namujiAddress"
											value="${member.member_namujiAddress}"  ></td>
									</tr>
									<!-- 첨부파일란 -->
									<c:choose>
										<c:when
											test="${not empty shareFileList && shareFileList!='null' }">
											<c:forEach var="shareFile" items="${shareFileList}"
												varStatus="status">
												<tr id="tr_${status.count }" class="table_fileTr"
													align="center">
													<td width="25%" style="background: #efefef;">필수첨부파일${status.count }</td>
													<td colspan="3" width="75%"><input type="hidden"
														name="oldFileName" value="${shareFile.up_filename }" /> <input
														type="hidden" name="up_fileno"
														value="${shareFile.up_fileno }" />
														<div id="filedown">
															<a
																href="${contextPath}/noticeDownload.do?sh_no=${share.sh_no}&up_filename=${shareFile.up_filename}">${shareFile.up_filename}</a>
														</div></td>
												</tr>
												<tr id="tr_sub${status.count }">
													<td></td>
													<td colspan="3"><input type="file"
														name="up_fileName${status.count }"
														id="up_fileName${status.count }"
														onchange="readURL(this, ${status.count });" /></td>
												</tr>

												<c:if test="${status.last eq true }">
													<c:set var="img_index" value="${status.count}" />
													<input type="hidden" name="pre_img_num"
														value="${status.count}" />
													<!-- 기존의 이미지수 -->
													<input type="hidden" id="added_img_num"
														name="added_img_num" value="${status.count}" />
													<!--   수정시 새로 추가된 이미지 수  -->
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:set var="img_index" value="${0}" />
											<input type="hidden" name="pre_img_num" value="${0}" />
											<!-- 기존의 이미지수 -->
											<input type="hidden" id="added_img_num" name="added_img_num"
												value="${0}" />
											<!--   수정시 새로 추가된 이미지 수  -->
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<div class="myInfo_cnt2">
								<div class="join_btn join_btn1">
									<input type="button" value="취소"
										onClick="location.href='${contextPath}/mypage/shareApplyList.do'">
								</div>
								<div class="join_btn join_btn2">
									<input type="button" value="수정반영"
										onClick="fn_modify_apply(applyFrm)">
								</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>