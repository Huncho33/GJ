<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
   request.setCharacterEncoding("UTF-8");
%>

<!-- 회원가입 인증 선택창 -->
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/memberForm/memberForm2.css"
   rel="stylesheet" type="text/css">

</head>

<body>
   <div id="joinForm2_bground">
      <div id="joinForm2_total">
         <div id="joinForm2_tot">
            <div id="joinForm2_innerTop">
               <div id="kkhs_subVisual">
                  <h3 class="kkhs_subTit">회원가입</h3>
               </div>
               <ul class="kkhs_processTab">
                  <li class="kkhs_on">
                     <div style="margin-top: -20px;">약관동의</div>
                  </li>
                  <li class="kkhs_on">
                     <div style="margin-top: -20px;">본인인증</div>
                  </li>
                  <li>
                     <div style="margin-top: -20px;">정보입력</div>
                  </li>
                  <li>
                     <div style="margin-top: -20px;">가입완료</div>
                  </li>
               </ul>
            </div>
            <form id="joinForm2_frm" name="joinForm2_frm" method="get"
               action="${contextPath}/member/memberForm3.do">
               <div class="joinForm2_innerCnt">
                  <div class="joinForm2_cnt1">
                     <ul>
                        <p>- 키보드보안 프로그램 설치 없이도 서비스를 이용할 수 있습니다.</p>
                        <p>- 귀하의 정보는 동의 없이 공개되지 않으며, 개인정보보호정책에 의해 보호받고 있습니다.</p>
                        <p>
                           - 대구시는 사용자의 개인정보보호를 위해 사용자 여러분이 대구시 누리집의 서비스를 이용함에 있어 온라인상에서
                           대구시에서 제공한 개인정보가 보호 받을 수<br> &nbsp;&nbsp;있도록 최선을 다하고 있습니다.
                        </p>
                     </ul>
                  </div>
                  <div class="joinForm2_cnt2">
                     <div class="joinForm2_certiList">
                        <p>
                           <span>'이메일, 휴대폰' </span>사용자 인증 중, 선택하여 본인 인증을 완료해주세요.
                        </p>
                        <ul class="joinForm2_certi">
                           <div class="joinForm2_cerTitle">
                              <p>이메일 인증</p>
                              <a class="joinForm2_cerBtn" href="${contextPath}/member/emailSend.do">인증하기</a>
                           </div>
                           <div class="joinForm2_cerTitle">
                              <p>휴대폰 인증</p>
                              <a class="joinForm2_cerBtn" href="#">인증하기</a>
                           </div>
                        </ul>
                     </div>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>

</body>

</html>