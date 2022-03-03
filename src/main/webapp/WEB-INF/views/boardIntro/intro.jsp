<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- <link rel="stylesheet" href="${contextPath}/resources/css/sidemenu.css"> --%>

	<!-- intro CSS -->
<link rel="stylesheet" href="${contextPath}/resources/css/intro.css">
<script>


</script>
<style>

</style>
</head>
<body>
	<div id="khs_container">
		<div id="khs_contents">	
			<div id="khs_intro_Image">
				<img src="${contextPath}/resources/image/intro.png">
			</div>
			<div id="khs_post-content">
				<h3 id="khs_paddingSpace"><img src="${contextPath}/resources/image/intro1.png">&nbsp;대구 청년주거안정패키지</h3>
				<ul class="khs_list_h3">
					<li>‘청년주거안정 패키지’는 사회진입 및 신규유입 청년의 초기 주거안정을 도모하고 유입 청년의 
					지속적인 정착을 지원하기 위해 단계별로 전략을 수립했다.
					먼저 저소득 청년의 원활한 사회진입과 주거비용 부담완화를 위해 1인 가구 청년(가구소득 기준 중위소득 120% 이하, 
					임차보증금 1억원 이하 및 월세 60만원 이하)에 대하여 내년부터 연간 2,500가구를 대상으로 
					2025년까지 최대 월 15만 원을 2025년까지 지원한다.
					다음은 사회진입 청년들이 지역 내에 지속적으로 정착할 수 있도록 전세금 융자이자 및 전세금 반환보증 보증료를 2025년까지 해당 대상가구 전체에
					 지원할 계획이다. 우선 임차보증금 2억 이하 무주택 청년가구에 대해 융자한도 5천만원까지 시중금리보다 2% 이상 저렴하게 지원하는 
					 방안을 한국주택금융공사 및 금융기관과 함께 마련하는 한편, 임차보증금을 떼이지 않도록 주택도시보증공사(HUG)와 한국주택금융공사(HF)에서 보증하는 임차보증금 3억원 이하 전세 반환 보증보험에 가입한 청년에 대하여는 보증료 전액을 지원한다.
					
					</li>
					
					<br>
					
					
				</ul>
			</div>
			<br>
			<div id="khs_contents">
				<h3><img src="${contextPath}/resources/image/intro1.png">&nbsp;신청안내</h3>
				<ul class="khs_list_h3">
					<li>지원기간 : 최대 12개월 간 지원<br></li>
					<li>신청기간 : 2022년 6월 ~ 2023년 6월(1년간)</li>
					<li>지급기간 : 2022년 8월 ~ 2024년 11월</li>
					<li>신청지역</li>
					<li>- (신규 신청) 청년월세의 신청은 신청기간 동안 청년이 거주하는 주민등록상 주소지 관할 읍면동에 신청</li>
					<li>- (변경 신청) 지급기간 동안 주소지 변경 등으로 임대차계약이 변경 되는 경우 새로운 주소지의 관할 읍면동에 변경 계약서 제출</li>
                   <li>※ 동일 주소지에서 계약내용(금액·기간 등)만 변경된 경우에도 변경신청 하여야 함</li>
					
				</ul>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		
			<div class="khs_line_box khs_cf">
			    <div class="khs_request">
			        <div class="khs_requst_info">
			            <img alt="신청픽토그램" src="${contextPath}/resources/image/intro2.png"><br>
			        </div>
			        <div class="khs_request_btn">
			            <a class="khs_requestBtn khs_btnColorType14" href="#">
			            	월세지원신청
			            </a>
			        </div>
			        <div class="khs_request_btn">
			            <a class="khs_requestBtn" href="#">전월세<br>보증금<br>이자지원<br>신청</a>
			        </div>
			        <div class="khs_request_btn">
			            <a class="khs_requestBtn khs_btnColorType14" href="#">전세반환<br>보증금<br>이자지원<br>신청</a>
			        </div>
			        <div class="khs_request_btn">
			            <a class="khs_requestBtn" href="#">공공<br>희망주택<br>이자지원<br>신청</a>
			        </div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>