<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="article" value="${articleMap.article}" />
<c:set var="removeCompleted" value="${articleMap.removeCompleted}" />
<c:set var="imageFileList" value="${articleMap.imageFileList}" />


<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="utf-8">
<title>글보기</title>

<style>
#tr_btn_modify {
	display: none;
}

.tr_modEnable {
	display: none;
}

#tr_btn_addFile {
	display: none;
}

#tr_up_fileName {
	display: none;
	border: none;
}

#noti_view_bground {
	width: 100%;
	position: relative;
}

#noti_view_container {
	position: relative;
	margin: 0 auto;
	align: center;
	width: 1200px;
	font-size: 15px
}

tr input {
	border: none;
	background: white;
	font-size: 15px;
}

#noti_select_view td {
	text-indent: 10px;
	font-size: 15px;
	border: solid 1px #e5e5e5;
	border-left: none;
	border-right: none;
}

#noti_select_view {
	border-collapse: collapse;
	width: 1200px;
	align: center;
}

#noti_view_btn {
	width: 1200px;
	align: center;
	margin: 40 auto;
}
</style>

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
     function backToList(obj){
	    obj.action="${contextPath}/boardNotice/listArticles.do";
	    obj.submit();
     }
     
	 function fn_enable(obj){
		 document.getElementById("noti_title").disabled=false;
		 document.getElementById("noti_context").disabled=false;
		 document.getElementById("up_fileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("noti_view_btn").style.display="none";
		 fn_tr_modEnable();
	 }
	 
	 function fn_tr_modEnable(){
		 x=document.getElementsByClassName("tr_modEnable");
		 for(var i =0; i < x.length; i++){
			 x[i].style.display="block";
		 }
	 }
	 
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/boardNotice/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,noti_NO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var noti_NOInput = document.createElement("input");
	     noti_NOInput.setAttribute("type","hidden");
	     noti_NOInput.setAttribute("name","noti_NO");
	     noti_NOInput.setAttribute("value", noti_NO);
		 
	     form.appendChild(noti_NOInput);
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
	 
	 var cnt = 1;
		function fn_addFile() {
			$("#d_file").append(
					"<br>" + "<input type='file' name='up_fileName"+cnt+"' />");
			cnt++;
		}
		
		function fn_removeModImage(_up_fileNO, _noti_NO, _up_fileName, rowNum){
			 alert(rowNum);
				 $.ajax({
			       type:"post",
			       async:false,  
			       url:"http://localhost:8090/GJ/boardNotice/removeModImage.do",
			       dataType:"text",
			       data: {up_fileNO : _up_fileNO,  noti_NO : _noti_NO, up_fileName : _up_fileName},
			       success:function (result, textStatus){
			          if(result == 'success'){
			       	    alert("이미지를 삭제했습니다.");
		//	     	 	location.href="http://localhost:8090/pro30/board/viewArticle.do?removeCompleted=true&articleNO=" + _articleNO;
			  
			  			$('#tr_'+rowNum).remove();
			 			$('#tr_sub'+rowNum).remove();
					  			
			          }else{
			        	  alert("다시 시도해 주세요");
			          }
			       },
		       error:function(data,textStatus){
		          alert("에러가 발생했습니다.");ㅣ
		       },
		       complete:function(data,textStatus){
		       }
		    });  //end ajax	
		 }
		 
		 var pre_img_num = 0;  //수정 이전의 이미지 수
		 var img_index = 0;     //수정 후 이미지 수 
		 
		 var isFirstAddImage = true;
		 function fn_addModImage(_img_index){
			 
			 if(isFirstAddImage == true){
				 pre_img_num = _img_index;
				 img_index = ++_img_index;
				 isFirstAddImage = false;
			 }else{
				 ++img_index;	 
			 }
			 
			 var innerHtml = "";
			 innerHtml +='<tr  width=200px  align=center>';
			 innerHtml +='<td >'+
			 						"<input  type=file  name=up_fileName" + img_index + "  onchange='readURL(this,"+ img_index+")'   />"+
			 					'</td>';
			 innerHtml +='<td>'+
								"<img  id=preview"+img_index+"  />"+
			                   	'</td>';
			 innerHtml +='</tr>';
			 $("#tb_addImage").append(innerHtml);
		
			 $("#added_img_num").val(img_index);  // 추가된 이미지수를 히든 태그에 저장해서 컨트롤러로 보낸다.
		 }
	
	 

 </script>
<c:choose>
	<c:when test="${removeCompleted eq true }">
		<script type="text/javascript">
   $(window).load(function(){
	   fn_enable()
	}); 
   </script>
	</c:when>
</c:choose>

</head>
<body>

	<div id="noti_view_bground">
		<div id="noti_view_container">
			<form name="frmArticle" method="post" action="${contextPath}"
				enctype="multipart/form-data">
				<table id=noti_select_view border=0 align="center">
					<tr height="40px">
						<td width="200px" align="center" bgcolor="#f2f8ff">제목</td>
						<td colspan=3><input type=text value="${article.noti_title}"
							name="noti_title" id="noti_title" disabled /></td>
					</tr>

					<tr height="40px">
						<td width="200px" align="center" bgcolor="#f2f8ff">등록일자</td>
						<td><input type=text
							value="<fmt:formatDate value="${article.noti_date}"/>" disabled /></td>

						<td width="200px" align="center" bgcolor="#f2f8ff">조회수</td>
						<td><input type=text value="${article.noti_hits }"
							name="noti_hits" id="noti_hits" disabled /></td>
					</tr>



					<!--  파일 업로드 -->
					<c:set var="img_index" />
					<c:choose>
						<c:when
							test="${not empty imageFileList && imageFileList!='null' }">
							<c:forEach var="item" items="${imageFileList}" varStatus="status">
								<tr id="tr_${status.count }">
									<td width="150" align="center" bgcolor="#f2f8ff" rowspan="1">
										첨부파일${status.count }</td>

									<td colspan=2><input type="hidden" name="originalFileName"
										value="${item.up_fileName }" /> <input type="hidden"
										name="up_fileNO" value="${item.up_fileNO }" /> <img
										src="${contextPath}/download.do?noti_NO=${article.noti_NO}&up_fileName=${item.up_fileName}"
										id="preview${status.index }" /><br></td>

								</tr>

								<tr class="tr_modEnable" id="tr_sub${status.count }">
									<td></td>
									<td><input type="file" name="up_fileName${status.index }"
										id="up_fileName${status.index }"
										onchange="readURL(this, ${status.index });" /> <input
										type="button" value="이미지 삭제하기"
										onclick="fn_removeModImage(${item.up_fileNO },  ${item.noti_NO }, '${item.up_fileName }', ${status.count })" />
									</td>
								</tr>

								<c:if test="${status.last eq true }">
									<c:set var="img_index" value="${status.count}" />
									<input type="hidden" name="pre_img_num" value="${status.count}" />
									<!-- 기존의 이미지수 -->
									<input type="hidden" id="added_img_num" name="added_img_num"
										value="${status.count}" />
									<!--   수정시 새로 추가된 이미지 수  -->
								</c:if>

								<td id="tr_up_fileName"><input type="file"
									name="up_fileName " id="up_fileName" disabled
									onchange="readURL(this);" /></td>
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


					<tr>
						<td width="150" align="center" bgcolor="#f2f8ff">내용</td>
						<td><textarea rows="20" cols="60" name="noti_context"
								id="noti_context" disabled />${article.noti_context }</textarea></td>
						</td>
					</tr>
					
					<tr>
				<td colspan="2">
					<table id="tb_addImage" align="center">
					</table>
				</td>
			</tr>

			<tr class="tr_modEnable">
				<td colspan="2"><input type="button" value="이미지 추가"
					onclick="fn_addModImage(${img_index})" /></td>
			</tr>


				</table>


		


				<div id="tr_btn_modify" align="center">
					<input type=button value="수정반영하기"
						onClick="fn_modify_article(frmArticle)"> <input
						type=button value="취소" onClick="backToList(frmArticle)">
				</div>

				<div id="noti_view_btn">
					<div>
						<c:if test="${member.member_id == article.member_id }">
							<input type=button value="수정하기" onClick="fn_enable(this.form)">
							<input type=button value="삭제하기"
								onClick="fn_remove_article('${contextPath}/boardNotice/removeArticle.do', ${article.noti_NO})">
						</c:if>
						<input type=button value="리스트로 돌아가기"
							onClick="backToList(this.form)">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>



