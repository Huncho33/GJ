<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.scrollTo-min.js"></script>
<script src="https://kit.fontawesome.com/fc92373f81.js"
	crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/main_view.css" rel="stylesheet"
	type="text/css">
<script src="${contextPath}/resources/js/main_tap.js"></script>

</head> 

<body>
	<div id="main_bground">
		<div id="main_tot">
			<div id="main_container1">
				<div id="main_banner">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-bs-ride="carousel">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="0" class="active" aria-current="true"
								aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="${contextPath}/resources/image/배너3.png"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="${contextPath}/resources/image/배너1.png"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="${contextPath}/resources/image/배너2.png"
									class="d-block w-100" alt="...">
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
				</div>
			</div>
			<div id="main_container2">
				<div class="find_bar my-3">
					<div class="find_bar_ele">
						<a>맞춤형 정책찾기</a> <select>
							<option selected>대상 선택</option>
							<option>신혼부부</option>
							<option>무주택청년</option>
						</select> <select>
							<option selected>연령 선택</option>
							<option>만 20-29세</option>
							<option>만 30-39세</option>
						</select>
						<button type="button" class="btn btn-outline-primary">정책찾기</button>
					</div>
				</div>
			</div>
			<div id="main_container3">
				<div class="main_cnt1">
					<div class="main_tabs">
						<ul class="nav nav-tabs">
							<li class="nav-item1"><a class="nav-link active"
								aria-current="page">공지사항</a></li>
							<li class="nav-item2"><a class="nav-link active">자료실</a></li>
							<li class="nav-item3"><a
								href="${contextPath }/boardNotice/listArticles.do"><i
									class="fa-solid fa-plus"></i></a></li>
						</ul>
						<div class="main_tablist1">

							<c:choose>
								<c:when test="${empty notiList }">
									<div id="main_notiNon">
										<p>등록된 글이 없습니다.</p>
									</div>
								</c:when>
								<c:when test="${not empty notiList }">
									<table id="main_notiList">
										<c:forEach var="article" items="${notiList }">
											<tr>
												<td width="5%"><span style="font-style: bold;">-</span></td>
												<td width="80%"><a class='cls1'
													href="${contextPath}/boardNotice/viewArticle.do?noti_NO=${article.noti_NO }">
														${article.noti_title }</a></td>
											</tr>
										</c:forEach>
									</table>
								</c:when>
							</c:choose>
						</div>
						<div class="main_tablist2">

							<c:choose>
								<c:when test="${empty dataList }">
									<div id="main_dataNon">
										<p>등록된 글이 없습니다.</p>
									</div>
								</c:when>
								<c:when test="${not empty dataList }">
									<table id="main_dataList">
										<c:forEach var="article" items="${dataList }">
											<tr>
												<td width="5%"><span style="font-style: bold;">-</span></td>
												<td width="80%"><a class='cls1'
													href="${contextPath}/boardData/viewArticle.do?etc_NO=${article.etc_NO }">${article.etc_title }</a></td>
											</tr>
										</c:forEach>
									</table>
								</c:when>
							</c:choose>

						</div>
					</div>
				</div>
				<div class="main_cnt2">
					<div class="main_shortcut">
						<div class="main_shortcut_tab">
							<p>
								<i class="fa-solid fa-pipe"></i>&nbsp;&nbsp;바로가기
							</p>
						</div>
						<div class="main_shortcuts">
							<figure class="main_long main_long1">
								<a href="${contextPath}/month/monthApplyInfo.do">
									<p></p>
									<figcaption>
										<div class="main_line mn_shortcut1">
											<span>
												<p>월세지원</p>
											</span>
										</div>
									</figcaption>
								</a>
							</figure>
							<figure class="main_long main_long2">
								<a href="${contextPath}/rent/rentApplyInfo.do">
									<p></p>
									<figcaption>
										<div class="main_line mn_shortcut2">
											<span>
												<p>전세지원</p>
											</span>
										</div>
									</figcaption>
								</a>
							</figure>
							<figure class="main_long main_long3">
								<a href="${contextPath}/share/shareApplyInfo.do">
									<p></p>
									<figcaption>
										<div class="main_line mn_shortcut3">
											<span>
												<p>공공주택지원</p>
											</span>
										</div>
									</figcaption>
								</a>
							</figure>
						</div>
					</div>
				</div>
			</div>
			<script
				src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
				crossorigin="anonymous"></script>
		</div>
</body>

</html>