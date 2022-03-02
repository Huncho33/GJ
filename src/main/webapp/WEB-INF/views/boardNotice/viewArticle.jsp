<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="article" value="${articleMap.article}" />
<c:set var="imageFileList" value="${articleMap.imageFileList}" />


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
     function backToList(obj){
	    obj.action="${contextPath}/boardNotice/listArticles.do";
	    obj.submit();
     }
     
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_up_fileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/boardNotice/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","articleNO");
	     articleNOInput.setAttribute("value", articleNO);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 
	 }
	 

	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }
	 


 </script>
</head>
<body>
<form name="frmArticle" method="post" action="${contextPath}"
		enctype="multipart/form-data">

		<table border=0 align="center">
			<tr>
			<td width="150" align="center" bgcolor="#FF9933">제목</td>
			<td><input type=text value="${article.noti_title }" name="title"
				id="i_title" disabled /></td>
		</tr>
		
			<tr>
			<td width="150" align="center" bgcolor="#FF9933">등록일자</td>
			<td><input type=text
				value="<fmt:formatDate value="${article.noti_date}" />" disabled />
			</td>

		</tr>
		
			<tr>
			<td width="150" align="center" bgcolor="#FF9933">조회수</td>
			<td><input type=text value="${article.noti_hits }" name="hits"
				id="noti_hits" disabled /></td>
		</tr>
		
			<tr>
			<td width="150" align="center" bgcolor="#FF9933">내용</td>
			<td><textarea rows="20" cols="60" name="content" id="i_content"
					disabled />${article.noti_context }</textarea></td>
		</tr>
		
	
	
 <!--  파일 업로드 --> 
	<c:if test="${not empty imageFileList && imageFileList!='null' }">
				<c:forEach var="item" items="${imageFileList}" varStatus="status">
					<tr>
						<td width="150" align="center" bgcolor="#FF9933" rowspan="2">
							이미지${status.count }</td>
						<td><input type="hidden" name="originalFileName"
							value="${item.imageFileName }" /> <img
							src="${contextPath}/download.do?noti_NO=${article.noti_NO}&up_fileName=${item.up_fileName}"
							id="preview" /><br></td>
					</tr>
					<tr>
						<td><input type="file" name="up_fileName "
							id="i_up_fileName" disabled onchange="readURL(this);" /></td>
					</tr>
				</c:forEach>
			</c:if>

			<c:choose>
				<c:when
					test="${not empty article.up_fileName && article.up_fileName!='null' }">
					<tr>
						<td width="150" align="center" bgcolor="#FF9933" rowspan="2">
							이미지</td>
						<td><input type="hidden" name="originalFileName"
							value="${article.up_fileName }" /> <img
							src="${contextPath}/download.do?noti_NO=${article.noti_NO}&imageFileName=${article.up_fileName}"
							id="preview" /><br></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" name="up_fileName "
							id="i_up_fileName" disabled onchange="readURL(this);" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr id="tr_file_upload">
						<td width="150" align="center" bgcolor="#FF9933" rowspan="2">
							이미지</td>
						<td><input type="hidden" name="originalFileName"
							value="${article.up_fileName }" /></td>
					</tr>
					<tr>
						<td></td>
						<td><img id="preview" /><br> <input type="file"
							name="up_fileName " id="i_up_fileName" disabled
							onchange="readURL(this);" /></td>
					</tr>
				</c:otherwise>
			</c:choose>

		</table>
</body>
</html>



