
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청페이지</title>


<script type="text/javascript">

	$("#nextBtn").click(function() {
		if ($("input[type=radio]:checked").size()>0){
			alert('ok')}
		});
	
	
	function div_OnOff(v, id) {
		const nextClick = document.getElementById("nextBtn");

		if (v == "1") {
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
		if (v == "3") {
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
		if (v == "7") {
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
</script>

</head>
<body>



	<form id="applyCond_frm" name="applyCond_frm" method="get"
		action="${contextPath}/apply/applyInfo.do"
		onsubmit="return formCheck(this)">



		<span>세부정책에 관련한 신청 자격 조건1 입니다.</span><br> <input type="radio"
			name="check1" value="1" onClick="div_OnOff(this.value,'checkcon')" />1번
		<input type="radio" name="check1" value="2"
			onClick="div_OnOff(this.value,'checkcon')" />2번
		<div id="checkcon" style="display: none; color: red; font-size: 12px;">
			신청 자격에 준하지 않습니다.</div>


		<br>
		<span>세부정책에 관련한 신청 자격 조건2 입니다.</span><br> <input type="radio"
			name="check2" value="3" onClick="div_OnOff2(this.value,'checkcon2')" />3번
		<input type="radio" name="check2" value="4"
			onClick="div_OnOff2(this.value,'checkcon2')" />4번
		<div id="checkcon2"
			style="display: none; color: red; font-size: 12px;">신청 자격에 준하지
			않습니다.</div>


		<br>
		<span>세부정책에 관련한 신청 자격 조건3 입니다.</span><br> <input type="radio"
			name="check3" value="5" onClick="div_OnOff3(this.value,'checkcon3')" />5번
		<input type="radio" name="check3" value="6"
			onClick="div_OnOff3(this.value,'checkcon3')" />6번
		<div id="checkcon3"
			style="display: none; color: red; font-size: 12px;">신청 자격에 준하지
			않습니다.</div>


		<br>
		<span>세부정책에 관련한 신청 자격 조건4 입니다.</span><br> <input type="radio"
			name="check4" value="7" onClick="div_OnOff4(this.value,'checkcon4')" />7번
		<input type="radio" name="check4" value="8"
			onClick="div_OnOff4(this.value,'checkcon4')" />8번
		<div id="checkcon4"
			style="display: none; color: red; font-size: 12px;">신청 자격에 준하지
			않습니다.</div>



		<button id='apply_cancel'>
			<a href='${contextPath}/main.do'> 취소</a>
		</button>
		<input type="submit" id="nextBtn" value="다음단계">


	</form>

</body>
</html>

