 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%-- <c:set var="apply" value="${articleMap.article}" />
<c:set var="imageFileList" value="${articleMap.imageFileList}" /> --%>
<%
	request.setCharacterEncoding("UTF-8");
%>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <style>
        
    </style>
	<script src="https://kit.fontawesome.com/fc92373f81.js"
		crossorigin="anonymous"></script>
	<link href="${contextPath}/resources/css/monthApplyForm/monthApplyForm2.css"
		rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
		type="text/css">
	<script language="javascript">
	</script>
</head>

<body>
    <div id="monthApply_bground">
        <div id="monthApply_total">
        <form name ="frmResult" method="post" action= "">
            <div id="kkhs_sub" class="kkhs_joinCommon">
                	<%-- <h1 >회원님의 월세지원 신청결과는</h1><input type="text" name="member_id" value="${no.mo_result}" readonly> <h1>입니다.</h1> --%>
  					<p>회원님의 월세지원 신청 결과는<input type="text" name="member_id" value="'${no.rent_result}'" style ="border: 0; background: #fff; font-size: 16px; color:red; width: 70px;">입니다.</p>	          
            </div>
        </form>    
            <div id="monthApply_title">
                <p class="monthApply_tit" style="font-weight: bold;"><i class="fa-solid fa-pen"></i>&nbsp;지원결과상세</p>
            </div>
            <div id="monthApply_title2">
                <p class="monthApply_tit">
                    <table id="join_detail_table">
                        <tr class="dot_line">
                            <td class="fixed_join">:: 신청날짜</td>
                            <td><input type="text" name="member_id" id="Apply_PolicyName" value="${no.rent_date}" style="border: 0; background: #fff;"> </td>
                        </tr>
                       
                        <tr class="dot_line">
                            <td class="fixed_join">:: 신청자</td>
                            <td><input type="text" name="member_id" id="Apply_PolicyName" value="${no.member_id}" style="border: 0; background: #fff;"> </td>
                        </tr>
                        
                        <tr class="dot_line">
                            <td class="fixed_join">:: 선정 여부</td>
                            <td><input type="text" name="Apply_PolicyName" id="Apply_PolicyName" value="${no.rent_result}" style="border: 0; background: #fff;"  > </td>
                        </tr>
                        
                        <tr class="dot_line">
                            <td class="fixed_join">:: 상세 사유</td>
                            <td><input type="text" name="Apply_PolicyName" id="Apply_PolicyName" value="null" style="border: 0; background: #fff;"  > </td>
                        </tr>

                    </table>    
                </p>
            </div>
            
            <div id="monthApply_table">
                <form>
                
                 <div class="join_btn join_btn2">
                     <input type="submit" value="나의신청현황확인" onClick="fn_modApplyFormData('member');" style="margin-top: 200px;"> 
                </div>
                 
            </div>
            </form>
        </div>
    </div>
</body>

</html>
