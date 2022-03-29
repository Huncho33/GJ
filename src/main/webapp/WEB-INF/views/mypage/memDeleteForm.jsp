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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="${contextPath}/resources/css/mypage/memDeleteForm.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css">

<script>
/* 로그인 중인 아이디 조회하여 지정하는 기능  */
function fn_idToHidden() {
	var h_id = document.getElementById("memDel_id").value;
	var h_id2 = document.getElementById("memDel_id2").value = h_id;
	console.log(h_id);
	return true;
}

/* 회원탈퇴 미체크 시 alert창 기능  */
$(document).ready(function(){
    $("#memDel_subm").click(function(){    
        if($("#memDel_chBx").is(":checked") == false){
            alert("탈퇴 사항 숙지란에 체크하셔야 회원탈퇴가 가능합니다.");
            return false ;
        }else{
            $("memDel_frm").submit();
            alert("탈퇴가 완료되었습니다. 감사합니다.");
        }
    });    
});



</script>


</head>

<body>
	<div id="memDel_wrapper">
		<div id="memDel_total">
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
								<li><a href="${contextPath}/mypage/memDeleteForm.do">- 회원탈퇴</a></li>
							</ul></li>
						<li><a id="khs_left khs_left2" class="khs_lnb"><p>나의
									신청 현황</p></a>
							<ul class="khs_depth2">
								<li><a href="">- 월세지원 신청 현황</a></li>
								<li><a href="">- 전세지원 신청 현황</a></li>
								<li><a href="">- 행복주택지원 신청 현황</a></li>
							</ul></li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>나의
									게시글 및 상담</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/mypage/myBoardList.do">- 나의 게시글 목록</a></li>
								<li><a href="${contextPath}/mypage/myQna.do">- 나의 상담 목록</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			<div id="memDel_tot">

				<div id="memDel_tit1">
					<h3 class="memDel_tit">회원 탈퇴</h3>
				</div>
				<div id="memDel_tit2">
					<p class="memDel_subtit" style="font-weight: bold;">
						<strong>｜</strong> 회원 탈퇴하기
					</p>
				</div>
				<div id="memDel_container">
					<form id="memDel_frm" name="memDel_frm"
						action="${contextPath}/mypage/deleteMember.do" method="get">
						<div class="memDel_cnt1">
							<p class="memDel_info">
								<strong>원활한 회원 탈퇴를 위해서 아래 내용을 확인하고 진행해 주세요.</strong> <br> <br>
								1. 탈퇴를 희망하는 아이디로 정책을 신청한 경우, 신청한 정책이 자동으로 취소 처리됩니다.<br> 2.
								원스톱청년주거에 해당하는 서비스를 먼저 탈퇴/해지한 후 탈퇴해 주세요.<br> 3. 게시판형
								서비스(자유게시판, 상담게시판 등)에 등록한 게시물은 탈퇴 후에도 남아 있습니다. 글이 남아 있는 것을 원치
								않으신다면 삭제 후 탈퇴해 주세요.<br> 4. 위 사항을 숙지하지 않고 일어나는 불이익은 신청자에게
								책임이 있습니다.<br><br><br>
							</p>
							<table>
								<tr align="center">
									<td width="300">:: 탈퇴를 진행할 아이디</td>
									<td><input type="text" name="memDel_id" id="memDel_id" value="${member.member_id}" size="30" disabled></td>
									<td><input type="hidden" name="memDel_id2" id="memDel_id2" ></td>
								</tr>
							</table>
						</div>
						<div class="memDel_ckBox">
							<input type="checkbox" class="memDel_checkBox" id="memDel_chBx"
								name="memDel_chBx"> 탈퇴와 관련된 위 확인사항을 숙지하였으며 탈퇴를 진행하겠습니다.
						</div>
						<div class="memDel_cnt2">
							<div class="join_btn join_btn2">
								<input id="memDel_subm" type="submit" value="회원탈퇴" onClick="fn_idToHidden()">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>

</html>