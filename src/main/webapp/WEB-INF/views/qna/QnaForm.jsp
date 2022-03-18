<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/qna/QnaForm.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/qna/listQnas.do";
		obj.submit();
	}
</script>
</head>
<body>
	<div id="qnaFrm_bground">
		<div id="qnaFrm_cnt">
			<div id="qnaFrm_tit">
				<h3 class="qna_titName">상담게시판</h3>
			</div>
			<div id="qnaFrm_table">
				<div id="qnaFrm_title">
					<p class="qnaFrm_tit" style="font-weight: bold;">
						<i class="fa-solid fa-pen"></i>&nbsp;상담게시글 작성(<span
							style="font-weight: bold; color: red;">*</span>는 필수입력사항입니다.)
					</p>
				</div>
				<div id="qnaFrm_title2">
					<p class="qnaFrm_tit">
					<table id="qnaFrm_detail_table">
						<tr class="dot_line">
							<td class="fixed_join">:: 신청 분야</td>
							<td><select id="qnaFrm_PolicyName" name="qnaFrm_PolicyName"
								onChange=policySelect() title="qnaFrm_PolicyName">
									<option value="#" selected>-----상담할 정책 분야 선택-----</option>
									<option value="청년월세지원">청년월세지원</option>
									<option value="전월세보증금이자지원">전월세보증금이자지원</option>
									<option value="전세반환보증금보증료지원">전세반환보증금보증료지원</option>
									<option value="귀환신혼부부전세이자지원">귀환신혼부부전세이자지원</option>
									<option value="청년희망주택이자지원">청년희망주택이자지원</option>
							</select></td>
						</tr>
					</table>
					</p>
				</div>
				<form name="QnaForm" method="post" accept-charset="utf-8"
					action="${contextPath}/qna/addNewQna.do">
					<div id="qnaFrm_table">
						<tbody>
							<table id="qnaFrm_detail_table">
								<tr class="dot_line">
									<td class="fixed_join">:: 질문자</td>
									<td colspan=2 align="left"><input type="text" size="20"
										id="member_id" name="member_id" maxlength="100"
										value="${member.member_id }" readonly /></td>
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
									<td colspan="2"><input type="text" size="67"
										maxlength="500" id="qna_title" name="qna_title" /></td>
								</tr>

								<tr class="dot_line">
									<td class="fixed_join"><br>:: 글내용</td>
									<td colspan=2><textarea id="qna_content"
											name="qna_content" rows="10" cols="65" maxlength="4000"></textarea></td>
								</tr>
								</tbody>
							</table>
					</div>
					<div class="qnaFrm_btn_list">
						<div class="qnaFrm_btn qnaFrm_btn1">
							<input type="button" value="목록" onClick="backToList(this.form)">
						</div>
						<div class="qnaFrm_btn qnaFrm_btn2">
							<input type="submit" value="등록">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
