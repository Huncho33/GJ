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

<link href="${contextPath }/resources/css/free/articleForm.css"
	rel="stylesheet" type="text/css">
<script src="${contextPath }/resources/js/free/articleForm.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="freeFrm_bground">
		<div id="freeFrm_cnt">
			<div id="freeFrm_tit">
				<h3 class="freeFrm_titName">자유게시판</h3>
			</div>
			<div id="freeFrm_table">
				<div id="freeFrm_title">
					<p class="freeFrm_tit" style="font-weight: bold;">
						<i class="fa-solid fa-pen"></i>&nbsp;자유게시판 게시글 작성
					</p>
				</div>
				<form name="articleForm" method="post" accept-charset="utf-8"
					action="${contextPath}/adminFree/addNewArticle.do"
					enctype="multipart/form-data">
					<div id="freeFrm_table">
						<tbody>
							<table id="freeFrm_detail_table">
								<tr class="dot_line">
									<td class="fixed_join">:: 작성자</td>
									<td colspan=2 align="left"><input type="text" size="20"
										maxlength="100" name="member_id" value="${member.member_id}"
										readonly /></td>

								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 글제목</td>
									<td colspan="2"><input type="text" size="67"
										maxlength="500" name="fr_title" /></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join" valign="top"><br>:: 글내용</td>
									<td colspan=2><textarea name="fr_context" rows="10"
											cols="65" maxlength="4000"></textarea></td>
								</tr>
								<tr class="dot_line">
									<td class="fixed_join">:: 이미지파일 첨부</td>
									<td align="left"><input type="button" value="파일 추가"
										onClick="fn_addFile()" /></td>
									<td colspan="4"><div id="d_file"></div></td>
								</tr>
							</table>
						</tbody>
					</div>
					<div class="freeFrm_btn_list">
						<div class="freeFrm_btn freeFrm_btn1">
							<input type="button" value="목록" onClick="backToList(this.form)">
						</div>
						<div class="freeFrm_btn freeFrm_btn2">
							<input type="submit" value="등록" >
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
