<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html lang="ko" class="ko">
<head>





    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Home : 청년정책플랫폼</title>
    <!-- Common CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/global.css">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css?v=202110121007">
    <!-- 2019년 개편전 소스 -->
    <!-- Contents CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/slick.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
    <!-- Common Scripts -->
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.validate.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.validate.rule.js"></script>
    <script src="${contextPath}/resources/js/jquery.cookie.js"></script>
    <script src="${contextPath}/resources/js/jquery.fancybox.js"></script>
    <script src="${contextPath}/resources/js/netfunnel.js"></script>
    <script src="${contextPath}/resources/js/script/global.js"></script>
    <script src="${contextPath}/resources/js/common.js"></script>

    <script src="${contextPath}/resources/js/slick.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/messages_ko.js"></script>
</head>

<body>







    <section class="mainSection">
        <div class="mainInner">
            <div class="mainOrgWrap">
                <div class="mainOrgTit">
                    <h3 class="mainTit">관련기관</h3>
                    <div class="mainOrgControl slick-controls">
                        <button class="prev" img src="${contextPath}/resources/image/ico_slide_control2.png">이전 슬라이드</button>
                        &nbsp; &nbsp;
                        <button class="next">다음 슬라이드</button>
                    </div>
                </div>
                <div class="mainOrg">
                    <a class="slide" href="http://www.busan.go.kr/Main.bs" target="_blank" title="부산광역시"><img src="${contextPath}/resources/image/duke1.png" alt="새창"></a>
                    <a class="slide" href="https://www.busanjob.net/" target="_blank" title="부산일자리정보망"><img src="${contextPath}/resources/image/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.bepa.kr" target="_blank" title="부산경제진흥원"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.work.go.kr" target="_blank" title="워크넷"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.moel.go.kr/" target="_blank" title="부산지방고용노동청"><img src="${contextPath}/resources/css/duke1.png" alt="새창-"></a>
                    <a class="slide" href="http://www.smba.go.kr/" target="_blank" title="부산지방중소기업청"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.pen.go.kr/" target="_blank" title="부산광역시교육청"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="https://ccei.creativekorea.or.kr/busan/info/info.do" target="_blank" title="부산창조경제혁신센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.bcci.or.kr/html/main.php" target="_blank" title="부산상공회의소"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.bsef.or.kr/" target="_blank" title="부산경영자총협회"><img src="${contextPath}/resources/css/duke1.png" alt="새창-"></a>
                    <a class="slide" href="http://www.busanhrd.or.kr/kor/" target="_blank" title="부산지역인적자원개발위원회"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.bschangup.kr/" target="_blank" title="부산창업지원센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창-"></a>
                    <a class="slide" href="http://www.4060job.or.kr" target="_blank" title="중장년일자리희망센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.wlb.or.kr" target="_blank" title="일.가정양립포털 울브"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://bici.bepa.kr" target="_blank" title="부산시 산업용지 관리시스템"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://busanjobproject.or.kr/" target="_blank" title="부산 일자리 르네상스 프로젝트"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://gsjsoop.com/" target="_blank" title="기술자숲-기술인력 구직"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.mom2dream.com/" target="_blank" title="엄마들을 위한 일자리 어플"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.shoenet.org/index.do" target="_blank" title="신발산업진흥센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창-"></a>
                    <a class="slide" href="http://www.busan50plus.or.kr/" target="_blank" title="부산시 장노년일자리지원센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://jobablebusan.or.kr/" target="_blank" title="부산장애인일자리통합지원센터"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.mois.go.kr/frt/a01/frtMain.do" target="_blank" title="행정안전부"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.molit.go.kr/portal.do" target="_blank" title="국토교통부"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>
                    <a class="slide" href="http://www.mogef.go.kr/" target="_blank" title="여성가족부"><img src="${contextPath}/resources/css/duke1.png" alt="새창"></a>


                </div>
            </div>
        </div>
    </section>
 



    <!-- start:footer -->
    <footer id="footer">
        <div class="footerTop">
            <div class="footerInner">
                <ul class="footerBtn">
                    <li>
                        <a href="http://www.busan.go.kr/vprivacy1" target="_blank">개인정보 처리방침</a>
                    </li>
                    <li>
                        <a href="http://www.busan.go.kr/bhintro02" target="_blank">고객센터</a>
                    </li>
                    
                    <!-- <li>
               <a href="http://www.busan.go.kr/cright" target="_blank">홈페이지 저작권 보호정책</a>
            </li>
            <li>
               <a href="http://www.busan.go.kr/viewdn" target="_blank">뷰어다운로드</a>
            </li>
            <li>
               <a href="http://www.busan.go.kr/bhtelinfo03" target="_blank">실국별전화번호</a>
            </li>
            <li>
               <a href="/young/sitemap">사이트맵</a>
            </li> -->
                </ul>

                <button class="scrollTop"><span>Top</span></button>
            </div>
        </div>
        <div class="footerBody">
            <div class="footerInner">
                <div class="footerLogo">
                    <img src="${contextPath}/resources/image/logo.png" alt="대구가 청년에게 부산청년플랫폼" />
                </div>
                <div class="footerTxt">
                    <p>(우 47545) 부산광역시 연제구 중앙대로 1001(연산동) ☎(051)120 (평일 08:30-18:30, ※야간/공휴일 등 근무시간외는 당직실 전환) </p>
                    <p>본 홈페이지에 게시된 이메일 주소가 자동 수집되는 것을 거부하며, 이를 위반시 개인정보 보호법에 의해 처벌됨을 유념하시기 바랍니다.</p>
                    <p class="copyright">
                        Copyright &copy; Daegue Metropolitan City. All rights reserved.
                    </p>
                </div>
            </div>

        </div>
    </footer><!-- end:footer -->

    <!-- Google Analytics -->
   
    <!-- LOGGER TRACKING SCRIPT V.31 FOR www.busan.go.kr/job/young / 1082 : FAIL-SAFE TYPE / DO NOT ALTER THIS SCRIPT. -->
    <!-- COPYRIGHT (C) 2002-2018 BIZSPRING INC. LOGGER(TM) ALL RIGHTS RESERVED. -->
   
 

</body></html>