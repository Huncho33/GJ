<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="article" value="${articleMap.article}" />
<c:set var="removeCompleted" value="${articleMap.removeCompleted}" />
<c:set var="imageFileList" value="${articleMap.imageFileList}" />


<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath }/resources/css/admin/notice/viewArticle.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css"
	type="text/css">
<script src="${contextPath}/resources/js/sidemenu.js"></script>
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
       obj.action="${contextPath}/adminNotice/listArticles.do";
       obj.submit();
     }
     
    function fn_enable(){
       document.getElementById("noti_title").disabled=false;
       document.getElementById("noti_context").disabled=false;
       document.getElementById("tr_btn_modify").style.display="block";
       document.getElementById("notiView_mainBtn").style.display="none";
       fn_tr_modEnable();
    }
    
    function fn_tr_modEnable(){
       x=document.getElementsByClassName("tr_modEnable");
       for(var i =0; i < x.length; i++){
          x[i].style.display="table-row";
       }
    }
    
    
    function fn_modify_article(obj){
       obj.action="${contextPath}/adminNotice/modArticle.do";
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
    

    function readURL(input, index) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview'+index).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    

      
      function fn_removeModImage(_up_fileNO, _noti_NO, _up_fileName, rowNum){
          alert(rowNum);
             $.ajax({
                type:"post",
                async:false,  
                url:"http://localhost:8090/GJ/adminNotice/removeModImage.do",
                dataType:"text",
                data: {up_fileNO : _up_fileNO,  noti_NO : _noti_NO, up_fileName : _up_fileName},
                success:function (result, textStatus){
                   if(result == 'success'){
                	      
                       alert("파일을 삭제했습니다.");
                     
                      location.href="http://localhost:8090/GJ/adminNotice/viewArticle.do?removeCompleted=true&noti_NO=" + _noti_NO;
                             
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
	<div id="notiView_bground">
		<div id="notiView_cnt">
		<div id="khs_sideMenu_tot">
				<div id="khs_leftTitle">
					<p>관리페이지</p>
				</div>
				<div id="khs_subMenu">
					<ul>
						<li><a href="${contextPath}/admin/member/listMembers.do" id="khs_left khs_left1" class="khs_lnb"><p>사용자
									관리</p></a>
						</li>
						<li><a href="${contextPath}/admin/adminApply/adminMonthApply.do" id="khs_left khs_left2" class="khs_lnb"><p>신청
									관리</p></a>
							</li>
						<li><a id="khs_left khs_left3" class="khs_lnb"><p>게시판
									관리</p></a>
							<ul class="khs_depth2">
								<li><a href="${contextPath}/adminNotice/listArticles.do">-
										공지사항 관리</a></li>
								<li><a href="${contextPath}/adminData/listArticles.do">-
										기타자료실 관리</a></li>
								<li><a href="${contextPath}/adminQna/listQnas.do">-
										상담게시판 관리</a></li>
								<li><a href="${contextPath}/adminFree/listArticles.do">-
										자유게시판 관리</a></li>
							</ul></li>
						<li><a href="${contextPath}/admin/stats/stats.do"
							id="khs_left khs_left3" class="khs_lnb"><p>통계</p></a></li>
					</ul>
				</div>
			</div>
			<div id="adm_noti_tot">
			<div id="notiView_tit">
				<h3 class="notiView_titName">공지사항</h3>
			</div>
			<div id="notiView_table">
				<form name="frmArticle" method="post" accept-charset="UTF-8"
					enctype="multipart/form-data">
					<table id="noti_select_view">
						<tr id="table_mainCnt" class="table_title">
							<td class="table_category">제목</td>
							<td class="table_container" colspan=3><input type=text
								value="${article.noti_title}" name="noti_title" id="noti_title"
								disabled /></td>
						</tr>
						<tr id="table_mainCnt">
							<td class="table_category" style="width:100px;">글번호</td>
							<td class="table_container" colspan=3><input type="text"
								value="${article.noti_NO }" disabled /> <input type="hidden"
								name="noti_NO" value="${article.noti_NO}" /></td>
						</tr>
						<tr id="table_mainCnt">
							<td class="table_category">등록일자</td>
							<td class="table_container"><input type=text
								value="<fmt:formatDate value="${article.noti_date}"/>" disabled /></td>

							<td class="table_category">조회수</td>
							<td class="table_container"><input type=text
								value="${article.noti_hits }" name="noti_hits" id="noti_hits"
								disabled /></td>
						</tr>

						<tr id="table_mainCnt">
							<td class="table_category">내용</td>
							<td class="table_container" colspan=3><textarea rows="20"
									cols="110" name="noti_context" id="noti_context" disabled>${article.noti_context }</textarea></td>
						</tr>
						<!--  파일 업로드 -->
						<c:set var="img_index" />
						<c:choose>
							<c:when
								test="${not empty imageFileList && imageFileList!='null' }">
								<c:forEach var="item" items="${imageFileList}"
									varStatus="status">
									<tr id="tr_${status.count }" class="table_fileTr">
									
									
										<td class="table_category" rowspan="1">첨부파일${status.count }</td>
										<td class="table_container table_container1" colspan=2><input
											type="hidden" name="oldFileName" value="${item.up_fileName }" />
											<input type="hidden" name="up_fileNO"
											value="${item.up_fileNO }" /> <!-- <img src="${contextPath}/upload.do?noti_NO=${article.noti_NO}&up_fileName=${item.up_fileName}" id="preview${status.index }" />-->
											<div id="filedown">
												<a
													href="${contextPath}/noticeDownload.do?noti_NO=${article.noti_NO}&up_fileName=${item.up_fileName}">${item.up_fileName}</a><br>
											</div></td>

									</tr>

									<tr class="tr_modEnable" id="tr_sub${status.count }" style="display:none;">
										<td></td>
										<td colspan="3">
											<input type="file" name="up_fileName${status.index }"
												id="up_fileName${status.index }"
												onchange="readURL(this, ${status.index });" />
											<input type="button" value="기존파일 삭제" onClick="fn_removeModImage(${item.up_fileNO },  ${item.noti_NO }, '${item.up_fileName }', ${status.count })" />
										</td>
									</tr>

									<c:if test="${status.last eq true }">
										<c:set var="img_index" value="${status.count}" />
										<input type="hidden" name="pre_img_num"
											value="${status.count}" />
										<!-- 기존의 이미지수 -->
										<input type="hidden" id="added_img_num" name="added_img_num"
											value="${status.count}" />
										<!--   수정시 새로 추가된 이미지 수  -->
									</c:if>


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
							<td colspan="2">
								<table id="tb_addImage" align="center">
								</table>
							</td>
						</tr>
						
						<tr>
							<td colspan="3"></td>
							<td align="right">
								<div id="tr_btn_modify">
									<input type="button" value="파일 추가" onclick="fn_addModImage(${img_index})" />
									<input type="button" value="수정반영하기" onClick="fn_modify_article(frmArticle)">
								</div>
							</td>
						</tr>
						
					</table>
					<c:if test="${member.member_id == article.member_id }">
						<div class="notiView_btn_list notiView_btn_list1">
							<div id="notiView_mainBtn">
								<div id="notiView_mainBtn" class="notiView_btn notiView_btn3">
										<input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/adminNotice/removeArticle.do', ${article.noti_NO})">
								</div>
								<div id="notiView_mainBtn" class="notiView_btn notiView_btn1">
									<input type=button value="목록" onClick="backToList(this.form)">
								</div>
								<div id="notiView_mainBtn" class="notiView_btn notiView_btn2">
									<input type=button value="수정하기" onClick="fn_enable(this.form)">
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${member.member_id != article.member_id }">
						<div class="notiView_btn_list notiView_btn_list2">
							<div id="notiView_mainBtn">
								<div class="notiView_btn notiView_btn1">
								<input type=button value="목록" onClick="backToList(this.form)">
								</div>
								<div class="notiView_btn notiView_btn2">
									<input type=button value="수정하기" onClick="fn_enable()">
								</div>
							</div>
						</div>
					</c:if>
				</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>


