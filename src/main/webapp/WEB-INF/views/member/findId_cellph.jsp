<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!-- 아이디찾기 -->
<!DOCTYPE html>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <style>
         .joinForm2_Title {
            float: left;
            width: 40%;
            height: 80px;
            margin: 10px 0px 20px 0px;
            text-align: center;
            padding-top: 30px;
            border: 2px solid #dddcdc;
            color: dimgray;
            font-weight: bold;
        }
        .form-group{
            width: 40%;
            margin-top: 25px;
        }
        #inYourInfo{
            width: 40%;
            margin-top: 20px;
        }
    </style>
<script>

	function fn_findID() {
		var _name = $("#member_name").val();
		var _hp = $("#member_phoneno").val();
		var _email = $("#member_email1").val();
		var _email2 = $("#member_email2").val();

		if (_name == '' || _hp == '' || _email == '' || _email2 == ''){
			alert("모든 정보를 입력하여 주세요.");
		
		}else{
			document.frmfindID_Hp.submit();
		
			
		}

	}
</script>
<meta charset="UTF-8">
<title>아이디 찾기</title>

</head>
<body>
	 <div class="container">
    <form name="frmfindID_Hp" method="POST" action="${contextPath}/member/findYourId.do">
        
        <h4 class="joinForm2_Title">정보를 입력하여 주십시오.</h4>
        <tr align="findIdcontainer">
            <div class="form-group">
                <label for="exampleInputEmail1">이름</label>
                <td><input type="text" class="form-control" id="member_name" name="member_name" placeholder="이름 입력하세요" value="" size="20"></td>
            </div>
            
            <div class="form-group">
                <label for="exampleInputEmail1">연락처</label>
                <td><input type="text" class="form-control" id="member_phoneno" name="member_phoneno" placeholder=" '-'를 제외한 숫자 11자리" value="" size="20"></td>
            </div>
            
            <div class="form-group">
                <label for="exampleInputEmail1">이메일</label>
                <td><input type="text" class="form-control" id="member_email1" name="member_email1" value="" 
                           placeholder="email ID" size="20"></td> 
               
               
                <label for="exampleInputEmail1">@</label>
                <td><input type="text" class="form-control" id="member_email2" name="member_email2" placeholder="도메인 주소 ex) gmail.com" value="" size="20"></td>
            </div>
        </tr>

        <tr align="center">
            <td colspan="2"><input class="btn btn-primary btn-lg" type="button" id="inYourInfo" value="아이디 찾기" onClick="fn_findID()"> 
        </tr>
    </form>
        </div>
</body>
</html>