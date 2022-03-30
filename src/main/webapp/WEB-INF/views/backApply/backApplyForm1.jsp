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
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link
	href="${contextPath}/resources/css/monthApplyForm/monthApplyForm1.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">


<script type="text/javascript">
	$("#nextBtn").click(function() {
		if ($("input[type=radio]:checked").size() > 0) {
			alert('ok')
		}
	});

	function div_OnOff(v, id) {
		const nextClick = document.getElementById("nextBtn");

		if (v == "2") {
			document.getElementById(id).style.display = "";
			$("input[name=check2]").attr("disabled", true);
			$("input[name=check3]").attr("disabled", true);
			$("input[name=check4]").attr("disabled", true);
			nextClick.disabled = true;
		} else {
			document.getElementById(id).style.display = "none";
			$("input[name=check2]").attr("disabled", false);
			$("input[name=check3]").attr("disabled", false);
			$("input[name=check4]").attr("disabled", false);
			nextClick.disabled = false;
		}
	}

	function div_OnOff2(v, id) {
		const nextClick = document.getElementById("nextBtn");
		if (v == "4") {
			document.getElementById(id).style.display = "";
			$("input[name=check1]").attr("disabled", true);
			$("input[name=check3]").attr("disabled", true);
			$("input[name=check4]").attr("disabled", true);
			nextClick.disabled = true;
		} else {
			document.getElementById(id).style.display = "none";
			$("input[name=check1]").attr("disabled", false);
			$("input[name=check3]").attr("disabled", false);
			$("input[name=check4]").attr("disabled", false);
			nextClick.disabled = false;
		}
	}

	function div_OnOff3(v, id) {
		const nextClick = document.getElementById("nextBtn");
		if (v == "6") {
			document.getElementById(id).style.display = "";
			$("input[name=check2]").attr("disabled", true);
			$("input[name=check1]").attr("disabled", true);
			$("input[name=check4]").attr("disabled", true);
			nextClick.disabled = true;
		} else {
			document.getElementById(id).style.display = "none";
			$("input[name=check2]").attr("disabled", false);
			$("input[name=check1]").attr("disabled", false);
			$("input[name=check4]").attr("disabled", false);
			nextClick.disabled = false;
		}
	}

	function div_OnOff4(v, id) {
		const nextClick = document.getElementById("nextBtn");
		if (v == "8") {
			document.getElementById(id).style.display = "";
			$("input[name=check2]").attr("disabled", true);
			$("input[name=check3]").attr("disabled", true);
			$("input[name=check1]").attr("disabled", true);
			nextClick.disabled = true;
		} else {
			document.getElementById(id).style.display = "none";
			$("input[name=check2]").attr("disabled", false);
			$("input[name=check3]").attr("disabled", false);
			$("input[name=check1]").attr("disabled", false);
			nextClick.disabled = false;
		}
	}
	function next() {
		alert("다음단계로 이동합니다.")
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
					<li>
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


			<form id="applyCond_frm" name="applyCond_frm" method="get"
				action="${contextPath}/back/backApplyForm2.do"
				onsubmit="return formCheck(this)">
				<div id="monthApply_title">
					<p class="monthApply_tit" style="font-weight: bold;">
						<i class="fa-solid fa-pen"></i>&nbsp;월세지원 신청 자격 확인
					</p>
				</div>


				<div id="applyCond_cnt1">
					<div id="applyCond_tit1">
						<p>
							<i class="fa-solid fa-circle-dot"></i>&nbsp;&nbsp;대구광역시 청년 월세 지원은
							만 19~34세 이하 부모님과 별도 거주 중인 무주택 청년인 지원자만 신청가능합니다. 본인은 해당 조건에 포함되나요?
							<span style="font-size: 10pt; color: gray;">(공고월 기준 만 나이)</span>
						</p>
					</div>
					<div id="checkcon"
						style="display: none; color: red; font-size: 11pt;">신청 자격에
						준하지 않습니다.</div>
					<div id="applyCond_selBtn1">
						<input style="margin-right:15px;" type="radio" name="check1" value="1"
							onClick="div_OnOff(this.value,'checkcon')" checked/>예 <input
							type="radio" name="check1" value="2"
							onClick="div_OnOff(this.value,'checkcon')" style="margin-left:20px; margin-right:15px;"/>아니요
					</div>
					


					<div id="applyCond_tit1">
						<p>
							<i class="fa-solid fa-circle-dot"></i>&nbsp;&nbsp;현재 신청일 기준 원가구
							소득 기준중위소득 100%이하 및 청년독립가구 소득 기준중의소득 60%이하에 해당되시나요?
						</p>
					</div>
					<div id="checkcon2"
						style="display: none; color: red; font-size: 11pt;">신청 자격에
						준하지 않습니다.</div>
					<div id="applyCond_selBtn1">
						<input style="margin-right:15px;" type="radio" name="check2" value="3"
							onClick="div_OnOff2(this.value,'checkcon2')" checked/>예 <input
							type="radio" name="check2" value="4"
							onClick="div_OnOff2(this.value,'checkcon2')" style="margin-left:20px; margin-right:15px;"/>아니요
					</div>
					


					<div id="applyCond_tit1">
						<p>
							<i class="fa-solid fa-circle-dot"></i>&nbsp;&nbsp;현재 신청일 기준
							청년독립가구가 중위소득 50% 이상의 경제활동 등으로 원가구와 분리되어 기초보장제도상 (별도)보장가구로 인정되는 경우
							청년독립가구의 소득(60% 이하)에 해당되시나요?
						</p>
					</div>
					<div id="checkcon3"
						style="display: none; color: red; font-size: 11pt;">신청 자격에
						준하지 않습니다.</div>
					<div id="applyCond_selBtn1">
						<P>
							<input style="margin-right:15px;" type="radio" name="check3" value="5"
								onClick="div_OnOff3(this.value,'checkcon3')" checked/>예 <input
								type="radio" name="check3" value="6"
								onClick="div_OnOff3(this.value,'checkcon3')" style="margin-left:20px; margin-right:15px;"/>아니요
						</P>
					</div>
					

					<div id="applyCond_tit1">
						<p>
							<i class="fa-solid fa-circle-dot"></i>&nbsp;&nbsp;현재 신청일 기준 임차보증금
							5천만원 및 월세 60만원 이하 주택에 거주하시나요?
						</p>
					</div>
					<div id="checkcon4"
						style="display: none; color: red; font-size: 11pt;">신청 자격에
						준하지 않습니다.</div>
					<div id="applyCond_selBtn1">
						<input style="margin-right:15px;" type="radio" name="check4" value="7"
							onClick="div_OnOff4(this.value,'checkcon4')" checked/>예 <input
							type="radio" name="check4" value="8"
							onClick="div_OnOff4(this.value,'checkcon4')" style="margin-left:20px; margin-right:15px;"/>아니요
					</div>
					
				</div>



				<div class="join_btn_list">
					<div class="join_btn join_btn1">
						<input type="button" value="취소" onclick="cancel()">
					</div>
					<div class="join_btn join_btn2">
						<input type="submit" id="nextBtn" value="다음단계" onclick="next()">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

