<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>헤더</title>
    <link href="./bootstrap4/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="./js/jquery-ui.js"></script>
    <script src="./js/jquery.scrollTo-min.js"></script>
    <link href="${contextPath}/resources/css/header.css" rel="stylesheet" type="text/css">

</head>

<body>
    <div id="header_top">
        <div id="container">
            <div class="logo1">
                <a href="https://www.daegu.go.kr/index.do"><img src="img/대구광역시로고.png" height="40px">
            </div>
            
            <div class="sns">
                <ul class="sns_link">
                    <li><a href="#">
                            <img src="img/kakaotalk.png" height="25px">
                        </a></li>
                    <li><a href="#">
                            <img src="img/facebook.png" height="25px">
                        </a></li>
                    <li><a href="#">
                            <img src="img/insta.png" height="25px">
                        </a></li>
                </ul>
                
            </div>
        </div>
    </div>
    <hr>
    <div id="header_mid">
        <div id="header_mid1">
            <div id="inner">
                <div class="logo2">
                    <h1><a href="#"><img src="img/원스톱청년주거 로고.png"></h1>
                </div>
            </div>
        </div>
        <div id="header_mid2">
            <div class="searchBox">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit"><p>검색</p></button>
                </form>
            </div>
        </div>
    </div>
    <div id="header_bottom">
        <div id="menu">
            <div id="nav">
                <div id="navWrap">
                    <ul>
                        <li class="menu" style="width: 180px;">
                            <a href="#">청년패키지</a>
                            <ul class="submenu1">
                                <li><a href="#">청년패키지란?</a></li>
                                <li><a href="#">공지사항</a></li>
                                <li><a href="#">문의사항</a></li>
                                <li><a href="#"> - 자주 묻는 질문</a></li>
                                <li><a href="#"> - 상담게시판</a></li>
                                <li><a href="#">기타자료실</a></li>
                                <li><a href="#">자유게시판</a></li>
                            </ul>
                        </li>
                        <li class="menu" style="width: 180px;">
                            <a href="#">월세지원</a>
                            <ul class="submenu1">
                                <li><a href="#">청년월세지원 안내</a></li>
                                <li><a href="#">청년월세지원(2022)</a></li>
                                <li><a href="#">청년월세지원(2023)</a></li>
                                <li><a href="#">선정 결과 확인</a></li>

                            </ul>
                        </li>
                        <li class="menu" style="width: 245px;">
                            <a href="#">전세지원</a>
                            <ul class="submenu2">
                                <li><a href="#">전월세보증금이자지원 안내</a></li>
                                <li><a href="#"> - 전월세보증금이자지원 신청</a></li>
                                <li><a href="#"> - 전월세보증금이자지원 결과</a></li>
                                <li><a href="#">전세반환보증금보증료지원 안내</a></li>
                                <li><a href="#"> - 전세반환보증금보증료지원 신청</a></li>
                                <li><a href="#"> - 전세반환보증금보증료지원 결과</a></li>
                                <li><a href="#">신혼부부전세자금이자지원 안내</a></li>
                                <li><a href="#"> - 귀환신혼부부전세이자지원 신청</a></li>
                                <li><a href="#"> - 귀환신혼부부전세이자지원 결과</a></li>
                            </ul>
                        </li>
                        <li class="menu" style="width: 219px;">
                            <a href="#">공공임대주택</a>
                            <ul class="submenu3">
                                <li><a href="#">청년공공임대주택 안내</a></li>
                                <li><a href="#">청년희망주택공급 안내</a></li>
                                <li><a href="#">청년희망주택이자지원 안내</a></li>
                                <li><a href="#"> - 청년희망주택이자지원 신청</a></li>
                                <li><a href="#"> - 청년희망주택이자지원 결과</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="info">
            <a href="#"><img src="img/user.png">
                <p>로그인</p>
            </a>
            <a href="#"><img src="img/file.png">
                <p>회원가입</p>
            </a>
        </div>
    </div>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
        integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
        crossorigin="anonymous"></script>
    <script src="./header.js"></script>
</body>

</html>