<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="QnaVO" value="${QnaParentMap.QnaVO}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/qna/replyForm.css"
	rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	// 목록으로 돌아가기
	function backToList(obj) {
		if (confirm("답글 작성을 취소하고 목록으로 돌아가시겠습니까?") == true) {
			window.location.href = "${contextPath}/qna/listQnas.do";
		} else {
			return false;
		}
	}

	// 제목, 내용 필수 alert
	function QnaformCheck(form) {
		var pw_chkBox = document.getElementById('qna_secret').checked;

		if (form.qna_title.value == "") {
			alert("제목 입력은 필수입니다.")
			form.qna_title.focus();
			return false;
		}
		if (pw_chkBox == true && form.qna_pw.value == ""){
			alert("비밀글로 등록하려면 비밀번호를 입력하세요.")
			form.qna_pw.focus();
			return false;
		}
		if (pw_chkBox == false && form.qna_pw.value != ""){
			alert("비밀글로 등록하려면 비밀글 여부에 체크해주세요.")
			form.qna_secret.focus();
			return false;
		}
		if (form.qna_content.value == "") {
			alert("내용 입력은 필수입니다.")
			form.qna_content.focus();
			return false;
		}
		if (form.qna_policy.value == "none") {
			alert("상담 받을 정책 분야를 선택해주십시오.")
			form.qna_policy.focus();
			return false;
		}
	}
	
	
</script>
</head>
<body>
	<div id="replyFrm_bground">
		<div id="replyFrm_cnt">
			<div id="replyFrm_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="replyFrm_table">
				<div id="replyFrm_title">
					<p class="replyFrm_tit" style="font-weight: bold;">
						<i class="fa-solid fa-pen"></i>&nbsp;답변 작성하기(<span
							style="font-weight: bold; color: red;">*</span>는 필수입력사항입니다.)
					</p>
				</div>
				<form name="QnaForm" method="post" accept-charset="utf-8" action="${contextPath}/qna/addNewQna.do">
					<div id="replyFrm_title2">
						<p class="replyFrm_tit">
						<table id="replyFrm_detail_table">
							<tr class="dot_line">
								<td class="fixed_join">:: 신청 분야</td>
								<td><select id="qna_policy" name="qna_policy"
									onChange=policySelect() title="replyFrm_PolicyName">
										<option value="답변" selected>-----답변-----</option>
								</select></td>
							</tr>
						</table>
						</p>
					</div>
					<div id="replyFrm_table">
						<table id="replyFrm_detail_table">
							<tbody>
								<tr class="dot_line">
									<td class="fixed_join">:: 답변자</td>
									<td colspan=2 align="left"><input type="text" size="20"
										id="member_id" name="member_id" maxlength="100"
										value="${member.member_id }" disabled /></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 비밀글여부</td>
									<td><input type="checkbox" value="1" id="qna_secret"
										name="qna_secret" />&nbsp;&nbsp;해당 상담글을 잠급니다.</td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 비밀번호</td>
									<td colspan=2><input type="password" size="20"
										maxlength="4" id="qna_pw" name="qna_pw" value="${QnaVO.qna_pw }" /><input
										type="hidden" size="20" maxlength="4" id="qna_pw2"
										name="qna_pw2"></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 답글제목</td>
									<td colspan="2">
									<input type="text" size="67"
										maxlength="500" id="qna_title" name="qna_title"
										value="${QnaVO.qna_title }에 대한 답변입니다." /></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join"><br>:: 답글내용</td>
									<td colspan=2><textarea id="qna_content"
											name="qna_content" rows="10" cols="65" maxlength="4000"></textarea></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="replyFrm_btn_list">
						<div class="replyFrm_btn replyFrm_btn1">
							<input type="button" value="목록" onClick="backToList(this.form)">
						</div>
						<div class="replyFrm_btn replyFrm_btn2">
							<input type="submit" value="답글등록" onClick="return QnaformCheck(this.form)">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
