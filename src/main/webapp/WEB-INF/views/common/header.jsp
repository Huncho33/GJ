<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<c:set var="result" value="${param.result }" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>헤더</title>

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<script src="${contextPath}/resources/js/jquery.scrollTo-min.js"></script>
<script src="https://kit.fontawesome.com/fc92373f81.js" crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/header.css" rel="stylesheet"
   type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
   type="text/css">
<script>
	function shareFacebook() {
		var sendUrl = "https://www.naver.com/"; 
		window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
	}
	 
	//월세지원 신청눌렀을때 로그인 먼저 하게 한 후  신청페이지 보여주는 기능
	function monApply_form(isLogOn,applyForm,loginForm){
         if(isLogOn != '' && isLogOn != 'false'){
            location.href = applyForm;
        } else{
           alert("로그인 후 이용해주세요.");
           location.href = loginForm + '?action=/month/monthApplyForm0.do';
        
        }
     }
	//월세지원 신청결과 로그인후 이용
	function monSelResult_form(isLogOn,resultForm,loginForm){
        if(isLogOn != '' && isLogOn != 'false'){
           location.href = resultForm;
       } else{
          alert("로그인 후 이용해주세요.");
          location.href = loginForm + '?action=/month/monthSelectedResult.do';
       
       }
    }
	//전월세보증금이자지원 
	function rentApply_form(isLogOn,applyForm,loginForm){
         if(isLogOn != '' && isLogOn != 'false'){
            location.href = applyForm;
        } else{
           alert("로그인 후 이용해주세요.");
           location.href = loginForm + '?action=/rent/rentApplyForm0.do';
        
        }
     }
	//전월세보증금이자지원 신청결과 로그인후 이용
	function rentSelResult_form(isLogOn,resultForm,loginForm){
        if(isLogOn != '' && isLogOn != 'false'){
           location.href = resultForm;
       } else{
          alert("로그인 후 이용해주세요.");
          location.href = loginForm + '?action=/rent/rentSelectedResult.do';
       
       }
    }
	//전세반환보증금이자지원 
	function rentReturnApply_form(isLogOn,applyForm,loginForm){
         if(isLogOn != '' && isLogOn != 'false'){
            location.href = applyForm;
        } else{
           alert("로그인 후 이용해주세요.");
           location.href = loginForm + '?action=/ret/rentReturnApplyForm0.do';
        
        }
     }
	//전세반환보증금이자지원 신청결과 로그인후 이용
	function returnSelResult_form(isLogOn,resultForm,loginForm){
        if(isLogOn != '' && isLogOn != 'false'){
           location.href = resultForm;
       } else{
          alert("로그인 후 이용해주세요.");
          location.href = loginForm + '?action=/ret/rentReturnSelectedResult.do';
       
       }
    }
	//귀환신혼부부전세지원
	function backApply_form(isLogOn,applyForm,loginForm){
         if(isLogOn != '' && isLogOn != 'false'){
            location.href = applyForm;
        } else{
           alert("로그인 후 이용해주세요.");
           location.href = loginForm + '?action=/back/backApplyForm0.do';
        }
     }
	//귀환신혼부부전세지원 신청결과 로그인후 이용
	function backSelResult_form(isLogOn,resultForm,loginForm){
        if(isLogOn != '' && isLogOn != 'false'){
           location.href = resultForm;
       } else{
          alert("로그인 후 이용해주세요.");
          location.href = loginForm + '?action=/back/backSelectedResult.do';
       
       }
    }
	
	//청년희망주택이자지원
	function shareApply_form(isLogOn,applyForm,loginForm){
         if(isLogOn != '' && isLogOn != 'false'){
            location.href = applyForm;
        } else{
           alert("로그인 후 이용해주세요.");
           location.href = loginForm + '?action=/share/shareApplyForm0.do';
        }
     }
	//청년희망주택이자지원 신청결과 로그인후 이용
	function shareSelResult_form(isLogOn,resultForm,loginForm){
        if(isLogOn != '' && isLogOn != 'false'){
           location.href = resultForm;
       } else{
          alert("로그인 후 이용해주세요.");
          location.href = loginForm + '?action=/share/shareSelectedResult.do';
       
       }
    }
	
</script>



</head>

<body>
   <div id="header">
      <div id="header_top">
         <div id="header_container">
            <div class="logo1">
               <a href="https://www.daegu.go.kr/index.do"><img
                  src="${contextPath}/resources/image/대구광역시로고.png" height="40px">
            </div>


            <div class="sns">
               <ul class="sns_link">
                  <li><a href="#"> <img
                        src="${contextPath}/resources/image/kakaotalk.png" height="25px">
                  </a></li>
                  <li><a id="btnFacebook" href="javascript:shareFacebook();">
								<img src="${contextPath}/resources/image/facebook.png"
								height="25px">
						</a></li>
                  <li><a href="#"> <img
                        src="${contextPath}/resources/image/insta.png" height="25px">
                  </a></li>
               </ul>

            </div>
         </div>
      </div>
      <hr>
      <div id="header_mid">
         <div id="header_mid1">
            <div id="header_inner">
               <div class="logo2">
                  <h1>
                     <a href="${contextPath}/main.do"> <img
                        src="${contextPath}/resources/image/원스톱청년주거 로고.png">
                  </h1>
               </div>
            </div>
         </div>
      </div>
      <div id="header_bottom">
         <div id="header_menu">
            <div id="header_nav">
               <div id="header_navWrap">
                  <ul>
                     <li class="menu" style="width: 230px;"><a href="#">청년패키지</a>
                        <ul class="submenu submenu1">
                           <li><a href="${contextPath}/boardIntro/yIntro.do">청년패키지란?</a></li>
                           <li><a href="${contextPath}/boardNotice/listArticles.do">공지사항</a></li>
                           <li><a href="${contextPath}/qna/listQnas.do">문의사항</a></li>
                           <li><a href="${contextPath}/qna/freqQna.do"> - 자주 묻는 질문</a></li>
                           <li><a href="${contextPath}/qna/listQnas.do"> - 상담게시판</a></li>
                           <li><a href="${contextPath}/boardData/listArticles.do">기타자료실</a></li>
                           <li><a href="${contextPath}/boardFree/listArticles.do">자유게시판</a></li>
                        </ul></li>
                     <li class="menu" style="width: 230px;"><a href="#">월세지원</a>
                        <ul class="submenu submenu4">
                           <li><a href="${contextPath}/month/monthApplyInfo.do">청년월세지원 안내</a></li>
                           <li><a href="https://www.bokjiro.go.kr/ssis-teu/index.do">청년월세지원신청(2022)</a></li>
                           <li><a href="javascript:monApply_form('${isLogOn}','${contextPath}/month/monthApplyForm0.do',
								'${contextPath}/member/loginForm.do')">청년월세지원신청(2023)</a></li>
                           <li><a href="javascript:monSelResult_form('${isLogOn}','${contextPath}/month/monthSelectedResult.do',
								'${contextPath}/member/loginForm.do')">선정 결과 확인</a></li >
                        </ul></li>
                     <li class="menu" style="width: 250px;"><a href="#">전세지원</a>
                        <ul class="submenu submenu2">
                           <li><a href="${contextPath}/rent/rentApplyInfo.do">전월세보증금이자지원 안내</a></li>
                           <li><a href="javascript:rentApply_form('${isLogOn}','${contextPath}/rent/rentApplyForm0.do',
								'${contextPath}/member/loginForm.do')"> - 전월세보증금이자지원 신청</a></li>
                           <li><a href="javascript:rentSelResult_form('${isLogOn}','${contextPath}/rent/rentSelectedResult.do',
								'${contextPath}/member/loginForm.do')"> - 전월세보증금이자지원 결과</a></li>
                           
                           <li><a href="${contextPath}/ret/rentReturnApplyInfo.do">전세반환보증금보증료지원 안내</a></li>
                           <li><a href="javascript:rentReturnApply_form('${isLogOn}','${contextPath}/ret/rentReturnApplyForm0.do',
								'${contextPath}/member/loginForm.do')"> - 전세반환보증금보증료지원 신청</a></li>
                           <li><a href="javascript:returnSelResult_form('${isLogOn}','${contextPath}/ret/rentReturnSelectedResult.do',
								'${contextPath}/member/loginForm.do')"> - 전세반환보증금보증료지원 결과</a></li>
                           
                           <li><a href="${contextPath}/back/backApplyInfo.do">신혼부부전세자금이자지원 안내</a></li>
                           <li><a href="javascript:backApply_form('${isLogOn}','${contextPath }/back/backApplyForm0.do','${contextPath}/member/loginForm.do')"> - 귀환신혼부부전세이자지원 신청</a></li>
                           <li><a href="javascript:backSelResult_form('${isLogOn}','${contextPath}/back/backSelectedResult.do',
								'${contextPath}/member/loginForm.do')"> - 귀환신혼부부전세이자지원 결과</a></li>
                        </ul></li>
                     <li class="menu" style="width: 250px;"><a href="#">공공임대주택</a>
                        <ul class="submenu submenu3">
                           <li><a href="https://www.duco.or.kr/">청년공공임대주택 안내</a></li>
                           <li><a href="https://www.duco.or.kr/">청년희망주택공급 안내</a></li>
                           <li><a href="${contextPath}/share/shareApplyInfo.do">청년희망주택이자지원 안내</a></li>
                           <li><a href="javascript:shareApply_form('${isLogOn}','${contextPath}/share/shareApplyForm0.do',
								'${contextPath}/member/loginForm.do')"> - 청년희망주택이자지원 신청</a></li>
                           <li><a href="javascript:shareSelResult_form('${isLogOn}','${contextPath}/share/shareSelectedResult.do',
								'${contextPath}/member/loginForm.do')"> - 청년희망주택이자지원 결과</a></li>
                        </ul></li>
                  </ul>
               </div>
            </div>
         </div>
         <div class="info">

            <c:choose>
            	<c:when test="${isLogOn == true  &&  member!= null && member.member_right != 'MEMBER'}">
                  <a href="${contextPath}/member/logout.do"><i class="fa-solid fa-arrow-right-from-bracket"></i>&nbsp;로그아웃</a>
                  <a href="${contextPath}/admin/member/listMembers.do"><i class="fa-solid fa-gear"></i>&nbsp;관리페이지</a>
               </c:when>
               <c:when test="${isLogOn == true  && member!= null && member.member_right == 'MEMBER'}">
                  <a href="${contextPath}/member/logout.do"><i class="fa-solid fa-arrow-right-from-bracket"></i>&nbsp;로그아웃</a>
                  <a href="${contextPath}/mypage/confirmPwdView.do"><i class="fa-solid fa-house"></i>&nbsp;마이페이지</a>

                  




               </c:when>
               <c:otherwise>
                  <a href="${contextPath}/member/loginForm.do"><i class="fa-solid fa-user"></i>&nbsp;로그인</a>
                  <a href="${contextPath}/member/memberForm.do"><i class="fa-regular fa-file"></i>&nbsp;회원가입</a>
               </c:otherwise>
            </c:choose>

            <c:choose>
               <c:when test="${result == 'logOut'}">
                  <script>
                     window.onload = function() {
                        alert("로그아웃 되었습니다.");
                     }
                  </script>
               </c:when>
            </c:choose>

         </div>
      </div>
      <script src="${contextPath}/resources/js/header.js"></script>
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
         integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
         crossorigin="anonymous"></script>
      <script
         src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
         integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
         crossorigin="anonymous"></script>
      <script
         src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
         integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
         crossorigin="anonymous"></script>
</body>

</html>