<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/monthApplyInfo.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"
	type="text/css">
</head>


<body>
	<div id="monthApply_bground">
		<div id="monthApply_tot">
			<div id="pl_introduce">
				<img src="${contextPath}/resources/image/정책소개인덱스.png">
			</div>
			<div id="month_intro_info">
				<div class="month_intro_title">
					<h2>
						<i class="fa-solid fa-caret-right"></i> &nbsp;&nbsp;전세반환보증금이자 소개
					</h2>
				</div>
				<div class="month_intro_cnt">
					<div class="intro_cnt1">
						<h3>○ 지원 대상</h3>
						<div class="intro_cnt1_tg">
							<ul>
								<li>만 19-34세 이하 부모님과 별도 거주 중인 무주택 청년</li>
								<li>원가구 소득 기준중위소득 100% 이하 및 청년독립기구 소득 기준중위소득 60% 이하</li>
								<li>청년독립가구가 중위소득 50% 이상의 경제활동 등으로 원가구와 분리되어 기초보장제도상
									(별도)보장가구로 인정되는 경우 청년독립기구의 소득(60% 이하)만 확인</li>
								<li>혼인(사실혼 포함) 또는 형제·자매 관계의 청년 2인 이상이 동일한 주택에 함께 거주하는 경우
									가구당 1명에게만 지원</li>
								<li>임차보증금 5천만원 및 월세 60만원 이하 주택 거주 등</li>
							</ul>
						</div>
						<h3>○ 지원 제외 대상</h3>
						<div class="intro_cnt1_tg">
							<ul>
								<li>주택소유자(분양권, 입주권 포함)</li>
								<li>직계존속·형제·자매 등 2촌 이내 명의로 임차한 주택의 거주자(단, 청년의 거주사실
									입증-전입신고·월세지급-을 통해 월세지원 가능</li>
								<li>공공주택특별법에 따른 공공임대주택 거주자</li>
								<li>보증금 5천만원 초과 주택 거주자</li>
								<li>방 1개에 다수가 거주하는 방식의 전대차인 경우</li>
								<li>지자체 자체 귀환신혼부부전세지원사업 12개월 이상 참여자(지원받은 기간이 12개월에 미치지 못한
									경우 미달된 기간만큼 추가지원 가능)</li>
								<li>대구 청년주거관련 정책(청년 주거안정 패키지) 중 귀환신혼부부전세지원사업과 중복참여를 제한하는
									정책의 참여자</li>
								<li>공공임대주택(대구형 청년희망주택 포함) 등</li>
							</ul>
							<p>※주거급여 수급자의 경우 월세지원액에서 주거급여액(청년 주거급여 분리지급액 포함)을 차감한 금액만 지원</p>
						</div>
					</div>
					<div class="intro_cnt2">
						<h3>○ 지원내용</h3>
						<p class="intro_cnt1_tg">월 임대료 중 최대 20만원 지원(최대 12개월, 생애 1회)</p>
						<h3>○ 지원기간</h3>
						<p class="intro_cnt1_tg">최대 12개월 간 지원</p>
						<h3>○ 신청기간</h3>
						<p class="intro_cnt1_tg">2022년 6월 ~ 2023년 6월(1년간)</p>
						<h3>○ 지급기간</h3>
						<p class="intro_cnt1_tg">2022년 8월 ~ 2024년 11월</p>
					</div>
					<div class="intro_cnt3">
						<h3>○ 신규신청</h3>
						<p class="intro_cnt1_tg">귀환신혼부부전세지원신청은 신청기간동안 청년이 거주하는 주민등록상 주소지 관할
							읍면동에 신청</p>
						<h3>○ 변경신청</h3>
						<div class="intro_cnt1_tg">
							<p>지급기간동안 주소지 변경 등으로 임대차계약이 변경되는 경우 새로운 주소지의 관할 읍면동에 변경 계약서
								제출</p>
							<p>(※동일 주소지에서 계약내용(금액·기간 등)만 변경된 경우에도 변경신청 하여야 함)</p>
						</div>
					</div>
					<div class="intro_cnt4">
						<h3>○ 제출서류양식 다운로드</h3>
						<div class="intro_cnt1_tg" align="center">
							<table id="intro_fileList" border="1" width="800px"
								height="200px">
								<tr align="center">
									<td>1</td>
									<td>제출서류양식1</td>
									<td><a class="btn"
										href="${contextPath}/resources/pdf/test.pdf" download><button>다운로드</button></a></td>
								</tr>
								<tr align="center">
									<td>2</td>
									<td>제출서류양식2</td>
									<td><a class="btn"
										href="${contextPath}/resources/pdf/test.pdf" download><button>다운로드</button></a></td>
								</tr>
								<tr align="center">
									<td>3</td>
									<td>제출서류양식3</td>
									<td><a class="btn"
										href="${contextPath}/resources/pdf/test.pdf" download><button>다운로드</button></a></td>
								</tr>
								<tr align="center">
									<td>4</td>
									<td>제출서류양식4</td>
									<td><a class="btn"
										href="${contextPath}/resources/pdf/test.pdf" download><button>다운로드</button></a></td>
								</tr>
								<tr align="center">
									<td>5</td>
									<td>제출서류양식5</td>
									<td><a class="btn"
										href="${contextPath}/resources/pdf/test.pdf" download><button>다운로드</button></a></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="intro_cnt5">
						<h3>○ 신청 바로가기</h3>
						<div class="intro_applys">
							<div class="intro_Button intro_Button2">
								<a
									href="javascript:apply_form('${isLogOn}','${contextPath}/ret/rentReturnApplyForm0.do',
								'${contextPath}/member/loginForm.do')">
									<div class="intro_apply_btn2">
										<p>전세반환보증금이자신청</p>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function apply_form(isLogOn, applyForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = applyForm;
		} else {
			alert("로그인 후 이용해주세요.");
			location.href = loginForm + '?action=/ret/rentReturnApplyForm1.do';
		}
	}
</script>
</html>

