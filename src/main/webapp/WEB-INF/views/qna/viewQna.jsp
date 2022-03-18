<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="utf-8">
<title>글보기</title>
<style>
#tr_file_upload {
	display: none;
}

#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
	function fn_enable(obj) {
		document.getElementById("i_title").disabled = false;
		document.getElementById("i_content").disabled = false;
		document.getElementById("i_imageFileName").disabled = false;
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("tr_btn").style.display = "none";
	}

	function fn_modify_article(obj) {
		obj.action = "${contextPath}/board/modArticle.do";
		obj.submit();
	}

	function fn_remove_article(url, articleNO) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type", "hidden");
		articleNOInput.setAttribute("name", "articleNO");
		articleNOInput.setAttribute("value", articleNO);

		form.appendChild(articleNOInput);
		document.body.appendChild(form);
		form.submit();

	}

	function fn_reply_form(url, parentNO) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var parentNOInput = document.createElement("input");
		parentNOInput.setAttribute("type", "hidden");
		parentNOInput.setAttribute("name", "parentNO");
		parentNOInput.setAttribute("value", parentNO);

		form.appendChild(parentNOInput);
		document.body.appendChild(form);
		form.submit();
	}
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</head>
<body>
	<div id="qnaView_bground">
		<div id="qnaFrm_cnt">
			<div id="qnaFrm_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="qnaFrm_table">
				<div id="qnaFrm_title2">
					<p class="qnaFrm_tit">
					<table id="qnaFrm_detail_table">
						<tr class="dot_line">
							<td class="fixed_join">:: 신청 분야</td>
							<td><input type="text" value="{qna.policyName}"></td>
						</tr>
					</table>
					</p>
				</div>
				<form name="QnaForm" method="post">
					<div id="qnaFrm_table">
						<tbody>
							<table id="qnaFrm_detail_table">
								<tr class="dot_line">
									<td class="fixed_join">:: 글번호</td>
									<td colspan=2><input type="text" size="20" id="qna_no"
										name="qna_no" value="${qna.qna_no }" readonly /></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 작성자</td>
									<td colspan=2><input type="text" size="20" id="member_id"
										name="member_id" value="${qna.member_id }" readonly /></td>
								</tr>
								<!-- 
								<tr class="dot_line">
									<td align="right">비밀번호:</td>
									<td colspan="2"><input type="text" size="67" maxlength="500"
										id="qna_password" name="qna_password" /></td>
								</tr>
								 -->
								<tr class="dot_line">
									<td class="fixed_join">:: 글제목</td>
									<td colspan="2"><input type="text" id="qna_title"
										name="qna_title" value="${qna.qna_title }" /></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join"><br>:: 글내용</td>
									<td colspan=2><textarea id="qna_content"
											name="qna_content" rows="10" cols="65" maxlength="4000"
											value="${qna.qna_content }"></textarea></td>
								</tr>
								<tr>
									<td class="fixed_join">:: 등록일자</td>
									<td colspan=2>"${qna.qna_date }"</td>
								</tr>
								</tbody>
							</table>
					</div>
					<c:if test="${member.member_id} == ${qna.member_id}">
						<div class="qnaFrm_btn_list">
							<div class="qnaFrm_btn qnaFrm_btn1">
								<input type="button" value="삭제" onClick="fn_enable()">
							</div>
							<div class="qnaFrm_btn qnaFrm_btn1">
								<input type="button" value="목록" onClick="backToList(this.form)">
							</div>
							<div class="qnaFrm_btn qnaFrm_btn2">
								<input type="submit" value="수정"
									onClick="fn_remove_qnaForm('${contextPath}/qna/removeQna.do', ${qna.qna_no})">
							</div>
						</div>
					</c:if>
					<!-- 
					<c:if test="${member.member_id} == 어드민">
						<div class="qnaFrm_btn_list">
							<div class="qnaFrm_btn qnaFrm_btn1">
								<input type="button" value="삭제하기" onClick="fn_enable()">
							</div>
							<div class="qnaFrm_btn qnaFrm_btn2">
								<input type="submit" value="답글쓰기"
									onClick="fn_reply_form('${isLogOn}', '${contextPath}/qna/replyQnaForm.do', ${qna.qna_no})">
							</div>
						</div>
					</c:if>
					 -->
					<div class="qnaFrm_btn qnaFrm_btn1">
						<input type="button" value="목록" onClick="backToList(this.form)">
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>



