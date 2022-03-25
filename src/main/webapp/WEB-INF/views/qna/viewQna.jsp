<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="qna" value="${QnaMap.Qna}" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="utf-8">

<link href="${contextPath}/resources/css/qna/viewQna.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	// 제목, 내용 필수 alert
	function formCheck(obj) {
		var pw_chkBox = document.getElementById('qna_Lock').checked;

		if (obj.qna_title.value == "") {
			alert("제목 입력은 필수입니다.")
			form.qna_title.focus();
			return false;
		}
		
		if (obj.qna_content.value == "") {
			alert("내용 입력은 필수입니다.")
			form.qna_content.focus();
			return false;
		}
	}
	
	// 체크박스 체크 여부로 비밀번호란 활성화-비활성화
	$(function (){
		$("#qna_secret").change(function(){
			if(this.checked){
				$("input#qna_pw").prop("readonly", false);
			} else {
				$("input#qna_pw").prop("readonly", true);
			}
		});
	});
		
	// 목록으로 돌아가기
	function backToList(obj) {
		obj.action = "${contextPath}/qna/listQnas.do";
		obj.submit();
	}
	
	
	
	// 수정폼으로 변환
	function fn_enable(obj){
		 document.getElementById("qna_title").disabled=false;
		 document.getElementById("qna_content").disabled=false;
		 document.getElementById("qna_secretTr").style.display="table-row";
		 document.getElementById("qnaView_btn_modify").style.display="block";
		 document.getElementById("qnaView_btn_default").style.display="none";
	 }
	 
	 
	// 글 수정하기
	 function fn_modify_qna(obj){
		obj.action="${contextPath}/qna/modQna.do";
		obj.submit();
	}
	 
	 
	 // 글 삭제하기
	 function fn_remove_qna(url,qna_no){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var QnaNOInput = document.createElement("input");
	     QnaNOInput.setAttribute("type","hidden");
	     QnaNOInput.setAttribute("name","qna_no");
	     QnaNOInput.setAttribute("value", qna_no);
		 
	     form.appendChild(QnaNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
	 
	 
	 // 글 답변하기
	 function fn_reply_qna(url, qnaparent_no){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var parentNOInput = document.createElement("input");
	     parentNOInput.setAttribute("type","hidden");
	     parentNOInput.setAttribute("name","qnaparent_no");
	     parentNOInput.setAttribute("value", qnaparent_no);
		 
	     form.appendChild(parentNOInput);
	     document.body.appendChild(form);
		 form.submit();
	  } 
	
</script>
</head>
<body>
	<div id="qnaView_bground">
		<div id="qnaView_cnt">
			<div id="qnaView_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="qnaView_table">
				<form name="QnaForm" method="post">
					<div id="qnaView_table">
						<table id="qnaView_detail_table">
							<tbody>
								<tr id="table_mainCnt" class="table_title">
									<td class="table_category" >:: 신청 분야</td>
									<td class="table_container"><input type="text" value="${qna.qna_policy}" disabled></td>
								</tr>
								<tr id="table_mainCnt">
									<td class="table_category">:: 글번호</td>
									<td class="table_container" colspan=2><input type="text" size="20" id="qna_no"
										name="qna_no" value="${qna.qna_no }" disabled /> <input
										type="hidden" size="20" id="qna_no" name="qna_no"
										value="${qna.qna_no }" /></td>
								</tr>
								<tr id="table_mainCnt">
									<td class="table_category">:: 작성자</td>
									<td class="table_container" colspan=2><input type="text" size="20" id="member_id"
										name="member_id" value="${qna.member_id }" disabled /></td>
								</tr>
								<tr id="qna_secretTr" >
									<td class="table_category">:: 비밀글여부</td>
									<td class="table_container"><input type="checkbox" value="1" id="qna_secret"
										name="qna_secret" />&nbsp;&nbsp;해당 상담글을 잠급니다.</td>
								</tr>
								<tr id="table_mainCnt">
									<td class="table_category">:: 비밀번호</td>
									<c:choose>
										<c:when test="${member.member_right == 'ADMIN'}">
											<td class="table_container" colspan=2><input type="text" size="20"
										maxlength="4" id="qna_pw" name="qna_pw"
										value="${qna.qna_pw }" readonly /></td>
										</c:when>
										<c:otherwise>
											<td class="table_container" colspan=2><input type="password" size="20"
										maxlength="4" id="qna_pw" name="qna_pw"
										value="${qna.qna_pw }" readonly /></td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr id="table_mainCnt" class="table_title">
									<td class="table_category">:: 상담 제목</td>
									<td class="table_container" colspan="2"><input type="text" id="qna_title"
										name="qna_title" value="${qna.qna_title }" disabled /></td>
								</tr>
								<tr id="table_mainCnt">
									<td class="table_category" style="vertical-align: top;"><br>::
										상담 내용</td>
									<td class="table_container" colspan=2><textarea id="qna_content"
											name="qna_content" rows="20" cols="125" maxlength="4000"
											disabled>${qna.qna_content }</textarea></td>
								</tr>
								<tr id="table_mainCnt">
									<td class="table_category">:: 등록일자</td>
									<td class="table_container" colspan=2><p class="qna_sysDate">${qna.qna_date }</p></td>
								</tr>
								</tbody>
							</table>
					</div>
					<div id="qnaView_btn_modify">
						<div class="qnaView_btn qnaView_btn1">
							<input type="button" value="취소" onClick="backToList(QnaForm)">
						</div>
						<div class="qnaView_btn qnaView_btn2">
							<input type="button" value="수정반영하기"
								onClick="fn_modify_qna(QnaForm)">
						</div>
					</div>

					<div id="qnaView_btn_default">
						<c:if test="${member.member_id == qna.member_id && member.member_right eq 'MEMBER'}">
							<div class="qnaView_btn_list">
								<div class="qnaView_btn qnaView_btn1">
									<input type="button" value="삭제하기" onClick="fn_remove_qna('${contextPath}/qna/removeQna.do', ${qna.qna_no})">
								</div>
								<div class="qnaView_btn qnaView_btn2">
									<input type="button" value="수정하기" onClick="fn_enable()">
								</div>

							</div>
						</c:if>
						<c:if test="${member.member_right eq 'ADMIN' }">
							<div class="qnaView_btn_list qnaView_btn_list2">
								<div class="qnaView_btn qnaView_btn1">
									<input type="button" value="삭제하기"
										onClick="fn_remove_qna('${contextPath}/qna/removeQna.do', ${qna.qna_no})">
								</div>
								<div class="qnaView_btn qnaView_btn1">
									<input type="button" value="수정하기" onClick="fn_enable()">
								</div>
								<div class="qnaView_btn qnaView_btn2">
									<input type="button" value="답글쓰기"
										onClick="fn_reply_qna('${contextPath}/qna/replyForm.do', ${qna.qna_no})">
								</div>
							</div>
						</c:if>
						<div class="qnaView_btn qnaView_btn4">
							<input type="button" value="목록" onClick="backToList(this.form)">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>