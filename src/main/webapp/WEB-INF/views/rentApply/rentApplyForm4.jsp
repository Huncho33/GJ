 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>



<!DOCTYPE html >
<html>
<head>
<meta "charset=utf-8">
<script src="https://kit.fontawesome.com/fc92373f81.js"
		crossorigin="anonymous"></script>
	<link href="${contextPath}/resources/css/monthApplyForm/monthApplyForm2.css"
		rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
		type="text/css">


<script>


/* var cnt=1;

function fn_addFile() {
	$("#d_file").append(
			"<br>" + "<input type='file' name='up_fileName"+cnt+"' />");
	cnt++;
} */


function cancel() {

	if (confirm("정말 취소하시겠습니까?") == true) { //확인

		window.location.href = '../main.do';

	} else { //취소

		return false;

	}

}


</script>
</head>
<body>
<div id="monthApply_bground">
	 <div id="monthApply_total">
			<div id="kkhs_sub" class="kkhs_joinCommon">
                <ul class="kkhs_processTab">
                    <li class="kkhs_on">
                        <div style="margin-top:-20px;">신청자격</div>
                    </li>
                    <li class="kkhs_on">
                        <div style="margin-top:-20px;">정보등록</div>
                    </li>
                    <li class="kkhs_on">
                        <div style="margin-top:-20px;">약관동의</div>
                    </li>
                    <li class="kkhs_on">
                        <div style="margin-top:-20px;">서류등록</div>
                    </li>
                </ul>
            </div>


<form method="post" action="${contextPath}/rent/rentApplyForm4.do" enctype="multipart/form-data" >
	<div id="monthApply_title">
      <p class="monthApply_tit" style="font-weight: bold;"><i class="fa-solid fa-pen"></i>&nbsp;신청서류 등록</p>
    </div>
	 <div id="monthApply_title2">
       <p class="monthApply_tit">
         <table id="join_detail_table">
           <tr class="dot_line">
             <td class="fixed_join">:: 신청 분야</td>
             <td><input type="text" name="Apply_PolicyName" id="Apply_PolicyName" value="월세지원" style="border: 0; background: #fff;" disabled > </td></tr>
          		
          </table>    
       </p>
      </div>
	
	<div id="monthApply_table">
                <form>
                    <div id="detail_table">
                        <table id="join_detail_table">
                            <tbody>
                                <tr class="dot_line">
                                <p>첨부파일을 등록해주시기 바랍니다.</p><br>
                                <label>첨부파일 등록 : </label>
								<input type="file" name ="up_fileName"value="첨부파일 추가" />
								</tr>
                            </tbody>
                        </table>
                    </div>
            </div>


	<div id="d_file">
	<input type="hidden" name="rent_reason" value="null"/>
    <input type="hidden" name="rent_policy" value="null"/>
    <input type="hidden" name="rent_date" value="null"/>
	</div>
	<div class="join_btn_list">
		<div class="join_btn join_btn1">
         <input type="reset" value="취소" onclick="cancel()">
    </div>
	   <div class="join_btn join_btn2">
		<input type="submit" id="nextBtn" value="다음단계" onclick="next()" />
	   </div>
	 </div>     
</form>
</div>
</div>
</body>
</html>

