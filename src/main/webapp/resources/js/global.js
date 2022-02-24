var mqWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
var mqWidth_mobile = 320;
var mqWidth_tablet = 768;
var mqWidth_pc = 1024;

//검색 keyword 불가 입력시 조치
$(window).load(function(){
	$("form[name=srchFrm]").bind("submit",	function() {
		// keydown 주석처리 - 모바일에서 중복 alert 나옴
		$("#searchText").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#searchText").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});

		$("#txtSrchKeyword1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#txtSrchKeyword1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#txtSrchKeyword1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});
	
	$("form[name=srchFrm2]").bind("submit",	function() {
		$("#searchText2").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#searchText2").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText2").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});

	$("form[name=searchForm1]").bind("submit",	function() {
		$("#searchText1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
	    });
//		$("#searchText1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});

	$("form[name=mainSrchFrm]").bind("submit",	function() {
		$("#searchText1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
	    });
//		$("#searchText1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
		
	});


	$("#searchText").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});

	$("#searchText1").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText1").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText1").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});
	
	$("#searchText2").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText2").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText2").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});

	$("#txtSrchKeyword1").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#txtSrchKeyword1").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#txtSrchKeyword1").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});
	
	// 17.09.04 성민화주무관 요청
	fn_forceKorModeInputV2();
});

//강제 입력창 한글입력(IE 빼고 안됨, 외국어사이트 제외)
function fn_forceKorModeInput(obj) {
	if ($('html').attr('lang') == "ko" || $('html').attr('lang') == "" || $('html').attr('lang') == undefined) {
		obj.css('ime-mode', 'active');
	}
}

//게시판, 프로그램 등 입력부분 공통으로 한글입력 우선 적용(IE 빼고 안됨, 외국어사이트 제외)
function fn_forceKorModeInputV2() {
	if ($('html').attr('lang') == "ko" || $('html').attr('lang') == "" || $('html').attr('lang') == undefined) {
		$('input[name="srchText"]').each(function(){
			$(this).css('ime-mode', 'active');
		});
	}
}

function searchKeywordCheck(obj){
	 var arr_char = new Array();
	 arr_char.push("<");
	 arr_char.push(">");
	 arr_char.push("onstop");
	 arr_char.push("layer");
	 arr_char.push("javascript");
	 arr_char.push("eval");
	 arr_char.push("onactivae");
	 arr_char.push("onfocusin");
	 arr_char.push("applet");
	 arr_char.push("document");
	 arr_char.push("onclick");
	 arr_char.push("onkeydown");
	 arr_char.push("create");
	 arr_char.push("onbeforecut");
	 arr_char.push("onkeyup");
	 arr_char.push("link");
	 arr_char.push("binding");
	 arr_char.push("ondeactivate");
	 arr_char.push("onload");
	 arr_char.push("script");
	 arr_char.push("msgbox");
	 arr_char.push("ondragend");
	 arr_char.push("onbounce");
	 arr_char.push("object");
	 arr_char.push("embed");
	 arr_char.push("ondragleave");
	 arr_char.push("onmovestart");
	 arr_char.push("frame");
	 arr_char.push("ondragstart");
	 arr_char.push("onmouseout");
	 arr_char.push("ilayer");
	 arr_char.push("onerror");
	 arr_char.push("onmouseup");
	 arr_char.push("bgsound");
	 arr_char.push("href");
	 arr_char.push("onabort");
	 arr_char.push("base");
	 arr_char.push("onstart");
	 arr_char.push("onfocus");
	 arr_char.push("onmove");
	 arr_char.push("innerHTML");
	 arr_char.push("onpaste");
	 arr_char.push("ondblclick");
	 arr_char.push("vbscript");
	 arr_char.push("charset");
	 arr_char.push("onresize");
	 arr_char.push("ondrag");
	 arr_char.push("expression");
	 arr_char.push("string");
	 arr_char.push("onselect");
	 arr_char.push("ondragenter");
	 arr_char.push("onchange");
	 arr_char.push("append");
	 arr_char.push("onscroll");
	 arr_char.push("ondragover");
	 arr_char.push("meta");
	 arr_char.push("alert");
	 arr_char.push("title");
	 arr_char.push("ondrop");
	 arr_char.push("void");
	 arr_char.push("refresh");
	 arr_char.push("iframe");
	 arr_char.push("oncopy");
	 arr_char.push("oncut");
	 arr_char.push("blink");
	 arr_char.push("onfinish");
	 arr_char.push("frameset");
	 arr_char.push("cookie");
	 arr_char.push("style");
	 arr_char.push("onreset");
	 arr_char.push("onselectstart");

	 var host = $(location).attr('host');
	 var rtn = true;

	 if(obj.val().length != 0 )
	 {
		for(var i =0; i<arr_char.length; i++)
		{
			if(obj.val().indexOf(arr_char[i]) != -1)
			{
				
				if ($('#Lang').val() == 'en'){
					alert("Your keywords include restricted words.");
				} else if ($('#Lang').val() == 'jp'){
					alert("入力することができない単語が含まれています。");
				} else if ($('#Lang').val() == 'cn'){
					alert("包括无输入单词。");
				} else if ($('#Lang').val() == 'tc'){
					alert("包括無輸入單詞。");
				} else {
					alert("입력할 수 없는 단어를 포함하고 있습니다.");
				}
				
				rtn = false;
				obj.val("");
				break;
			}
		}
	 }

	 return rtn;
}

//내고장알리미 추가 180807
function winOpen0807() {
    window.open("http://laiis.go.kr/jsp/cmm/main/MainIndex_02.jsp",'lips1');
	var laiisTimer = setTimeout("window.open('http://laiis.go.kr/pegasusIndex.do?athena.pegasus.menuid=AHlbAAAAr3bzgQAg$$__system&AgsSelPos=left&AgsSelCont=AHlbAAAAyl1xPwAE$$__system&AgsSelPosRoot=MiddleTopMenu_Lips&dynamic=false&fromtop=true&menuIdToBeExtended=','lips2')",3000);
}

// tab
$(function() {
	$('[data-function=tab] a').on('click.tab', function(e) {
		e.preventDefault();
		
		var $this = $(this);
		
		var isTab = $this.is("[href^=#]");
		
		if(!isTab){
			isBlank = $this.is("[target=_blank]");
			if(isBlank) window.open($this.attr("href"),"_blank");
			else location.href = $this.attr("href");
		} else {
			if ($this.attr('href').indexOf('#') == 0 && $($this.attr('href')).length > 0) {
				if ($this.parent().is('li')) {
					$this.parents('[data-function=tab]').find('li').removeClass('active');
					$this.parent().addClass('active');
				} else {
					$this.parents('[data-function=tab]').find('a').removeClass('active');
					$this.addClass('active');
				}
				$('.tabContWrap').filter(function() {
					return $(this).data('tabname') == $this.parents('[data-function=tab]').data('tabname');
				}).addClass('hide');
				$($this.attr('href')).removeClass('hide');
				
				if (window.location.href.indexOf('www.busan.go.kr/index') > -1 && $this.parent().find('a').attr('href').indexOf('tabNews2') > -1){
					// 공지사항, 보도자료 탭만 적용
					$('.bodo_more').addClass('more_hide');
					$this.parent().find('.bodo_more').removeClass('more_hide');
				}
			}
		}
	});
});

//tabNavContent
/*
$(function() {
	var $tabNav = $('.tabStyle02');
	$tabNav.each(function (i) {
		$tabNav.eq(i).find('> li:first-child').addClass('selected');
		$tabNav.eq(i).find('> li.selected > a + div').css('padding-top', $tabNav.eq(i).find('> li:last-child').position().top + 46 + 20);
		$tabNav.eq(i).css('height', $tabNav.eq(i).find('> li.selected > a + div').outerHeight());
		$tabNav.eq(i).find('> li > a').on('click', function() {
			$tabNav.eq(i).find('> li').removeClass('selected');
			$(this).parent('li').addClass('selected');
			$(this).parent().find(' > a + div').css('padding-top', $(this).parent().parent('.tabStyle02').find('> li:last-child').position().top + 46 + 20);
			$(this).parent().parent('.tabStyle02').css('height', $(this).parent().find(' > a + div').outerHeight());
			return false;
		});
	});
});
*/
//btnMenuDropDownSub
$(function() {
	var $topNavSub = $('.topNavSub');
	$topNavSub.find('.btnMenuDropDown').on('click', function() {
		if ($(this).parent('li').is('.active')) {
			$(this).siblings('ul').stop().slideUp(100, function() {
				$(this).parent('li').removeClass('active');
			});
			$(this).html("하위메뉴 열기");
		} else {
			$(this).closest('ul').find('li').removeClass('active').find('>ul').hide();
			$(this).siblings('ul').hide().stop().slideDown(100, function() {
				$(this).parent('li').addClass('active');
			});
			$(this).html("하위메뉴 닫기");
		}
	});
});

// select
$(function() {
	$('.select').each(function() {
		var $select = $(this);
		$select.find('.selected-text').text($select.find('option:selected').text());
		$select.find('select').on({
			'click focus' : function() {
				$(this).parent('.select').addClass('active');
			},
			'focusout' : function() {
				$(this).parent('.select').removeClass('active');
			},
			'change' : function() {
				$(this).parent('.select').find('.selected-text').text($(this).find('option:selected').text());
			}
		});
	});
});


// focus outline
$(function() {
	$('input[type=file].hidden, input[type=radio].hidden, input[type=checkbox].hidden').each(function(i, self) {
		var thisId = $(self).attr('id');
		$(self).focusin(function() {
			$('label[for='+thisId+']').addClass('focusOutline');
		}).focusout(function() {
			$('label[for='+thisId+']').removeClass('focusOutline');
		});
	});

	$('.textFormFile input[type=file]').each(function(i, self) {
		$(self).change(function() {
			$(this).prev('.textForm').val($(this).val());
		});
	});
});


// flexslider
$(function() {
	// flexslider default
	if ($.flexslider !== undefined) {
		$('.flexslider').addClass('not-load').prepend('<div class="flex-control-container" />');

		$.extend($.flexslider.defaults, {
			start: function(s) {
				try {
					var $sliderWrap = $(s.slides).parents('.flexslider');
					var sliderTitle;
					if ($sliderWrap.data('flexslider-title') !== undefined) {
						sliderTitle = $sliderWrap.data('flexslider-title');
					} else {
						sliderTitle = '';
					}

					$sliderWrap.removeClass('not-load');
					$sliderWrap.find('.flex-viewport').wrap('<div class="flex-viewport-wrap" />');
					$sliderWrap.find('.flex-control-paging li a').prepend('<span class="hidden">'+sliderTitle+' 목록</span>');
					$sliderWrap.find('.flex-direction-nav .flex-prev, .flex-direction-nav .flex-next').prepend('<span class="hidden">'+sliderTitle+'</span>');

					//pause/play
					$sliderWrap.find('.flex-control-nav').wrap('<div class="flex-control-nav-wrap" />');
					$sliderWrap.find('.flex-pauseplay').appendTo($sliderWrap.find('.flex-control-nav-wrap'));

					//paging number
					if ($sliderWrap.data('flexslider-controlpaging') == 'number') {
						$sliderWrap.prepend('<div class="flex-control-paging-number"><span class="flex-paging-current"><span class="hidden">현재</span><b>'+(s.currentSlide+1)+'</b><span class="hidden">번째 목록</span></span> / <span class="hidden">전체</span>'+s.count+'<span class="hidden">개 목록</span></div>');
					}

					//tabkey focus
					if (s.vars.direction == 'vertical') {
						$(s.slides).find('a').focus(function() {
							$(s.viewport).scrollTop(0);
							var focusIdx = $(this).parents(s.vars.selector).index();
							if (s.cloneOffset == 1) {
								s.flexAnimate(focusIdx-s.cloneOffset, true);
							} else {
								s.flexAnimate(focusIdx, true);
							}
						});
					} else {
						$(s.slides).find('a').focus(function() {
							$(s.viewport).scrollLeft(0);
							var focusIdx = $(this).parents(s.vars.selector).index();
							if (s.cloneOffset == 1) {
								s.flexAnimate(focusIdx-s.cloneOffset, true);
							} else {
								s.flexAnimate(Math.floor(focusIdx/s.visible), true);
							}
						});
					}
				} catch (e) {
					// TODO: handle exception
				}
			}
		});
	}

	$(window).on('load', function(){

		$('[data-function=flexslider]').each(function() {
			var $thisSlider = $(this);

			var silderItemWidth;
			if ($thisSlider.data('flexslider-itemswidth') !== undefined && typeof $thisSlider.data('flexslider-itemswidth') == 'number') {
				silderItemWidth = $thisSlider.data('flexslider-itemswidth');
			} else {
				silderItemWidth = 100;
			}
			//console.log(silderItemWidth);

			var silderItemLen;
			if ($thisSlider.data('flexslider-items') !== undefined && typeof $thisSlider.data('flexslider-items') == 'number') {
				if (mqWidth < mqWidth_tablet) {
					silderItemLen = $thisSlider.data('flexslider-minitems');
				} else if (mqWidth >= mqWidth_tablet && mqWidth < mqWidth_pc) {
					silderItemLen = $thisSlider.data('flexslider-items');
				} else if (mqWidth >= mqWidth_pc) {
					silderItemLen = $thisSlider.data('flexslider-maxitems');
				}
			} else {
				silderItemLen = 1;
			}
			//console.log(silderItemLen);

			var silderAnimate;
			var silderAnimateSpeed;
			if ($thisSlider.data('flexslider-animation') == 'fade') {
				silderAnimate = 'fade';
				silderAnimateSpeed = 800;
			} else {
				silderAnimate = 'slide';
				silderAnimateSpeed = 600;
			}

			var silderSlideshow;
			var silderPausePlay = false;
			if ($thisSlider.data('flexslider-slideshow') == true) {
				silderSlideshow = true;
				silderPausePlay = true;
			} else {
				silderSlideshow = false;
			}

			var silderDirectionNav;
			if ($thisSlider.data('flexslider-directionnav') == false) {
				silderDirectionNav = false;
			} else {
				silderDirectionNav = true;
			}

			var silderControlNav;
			if ($thisSlider.data('flexslider-controlnav') == false) {
				silderControlNav = false;
			} else {
				silderControlNav = true;
			}

			var silderSelector;
			if ($thisSlider.data('flexslider-selector') == 'slides-item') {
				silderSelector = '.slides-item';
			} else {
				silderSelector = 'li';
			}

			$thisSlider.flexslider({
				selector: '.slides >'+silderSelector,
				animation: silderAnimate,
				animationSpeed: silderAnimateSpeed,
				slideshow: silderSlideshow,
				pausePlay: silderPausePlay,
				itemWidth: silderItemWidth,
				minItems: silderItemLen,
				maxItems: silderItemLen,
				prevText: '이전 '+silderItemLen+'개 목록',
				nextText: '다음 '+silderItemLen+'개 목록',
				pauseText: '슬라이드쇼 정지',
				playText: '슬라이드쇼 재생',
				directionNav: silderDirectionNav,
				controlNav: silderControlNav,
				controlsContainer: $thisSlider.find('.flex-control-container'),
				after: function(s) {
					if ($thisSlider.data('flexslider-controlpaging') == 'number') {
						$thisSlider.find('.flex-control-paging-number .flex-paging-current >b').text(s.currentSlide+1);
					}
				}
			});
		});

		$('[data-function=flexsliderTicker]').each(function() {
			var $thisSliderTicker = $(this);

			$thisSliderTicker.flexslider({
				animation: 'slide',
				slideshow: true,
				slideshowSpeed: 5000,
				direction: 'horizontal',
				touch: false,
				pausePlay: true,
				prevText: '이전 1개 목록',
				nextText: '다음 1개 목록',
				pauseText: '슬라이드쇼 정지',
				playText: '슬라이드쇼 재생',
				controlNav: false,
				controlsContainer: $thisSliderTicker.find('.flex-control-container')
			});
		});
	});
});

// slick slider
$(function(){
	
	if($.fn.slick !== undefined) {
		
		$("[data-function=slick]").on("init", function(event, slick){
			$slick = $(event.currentTarget);
			
			// 자동재생 시 재생/정지 컨트롤 생성
			if(slick.options.autoplay){
				var play = document.createElement("button");
				play.classList.add("slick-play");
				play.setAttribute("data-paused","false");
				play.textContent = "자동재생 정지하기";
				
				play.addEventListener("click", function(){
					if(JSON.parse(play.dataset.paused)){
						$slick.slick("slickPlay");
						play.dataset.paused = "false";
						play.textContent = "자동재생 정지하기";
					} else {
						$slick.slick("slickPause");
						play.dataset.paused = "true";
						play.textContent = "자동재생 시작하기";
					}
				});
				
				$slick.append(play);
			}
		});
		
		$("[data-function=slick]").each(function(){
			$slick = $(this);
			datas = $(this).data();
			
			// 일괄 옵션
			options = datas.slickOptions !== undefined ? datas.slickOptions : new Object();
			
			Object.keys(datas).map(function(opt,idx){
				switch(opt){
					case "dots" : {
						options[opt] = datas[opt];
						break;
					}
					case "arrows" : {
						options[opt] = datas[opt];
						break;
					}
					case "autoplay" : {
						options[opt] = datas[opt];
						break;
					}
					case "autoplaySpeed" : {
						options[opt] = datas[opt];
						break;
					}
					case "fade" : {
						options[opt] = datas[opt];
						break;
					}
					case "infinite" : {
						options[opt] = datas[opt];
						break;
					}
					case "slidesToShow" : {
						options[opt] = datas[opt];
						break;
					}
					case "slidesToScroll" : {
						options[opt] = datas[opt];
						break;
					}
					case "speed" : {
						options[opt] = datas[opt];
						break;
					}
					case "vertical" : {
						options[opt] = datas[opt];
						break;
					}
					case "variableWidth" : {
						options[opt] = datas[opt];
						break;
					}
					case "adaptiveHeight" : {
						options[opt] = datas[opt];
						break;
					}
					case "responsive" : {
						options[opt] = datas[opt];
						break;
					}
					default : {
						// 기본적인 옵션 추가 - 필요시 다른 옵션 추가 가능
						break;
					}
				}
			});
			
			$slick.slick(options);
			
		});
	}
});

// alert
$(function(){
	$("[data-function=alert]").each(function(){
		$a = $(this);
		message = $a.data("message");
		
		$a.on("click", function(e){
			e.preventDefault();
			alert(message);
		});
	});
});

// datepicker
$(function() {
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
        prevText: '이전달',
        nextText: '다음달',
		changeYear: true,
		changeMonth: true,
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		yearRange: 'c-100:c+10',
		/* s :접근성 추가 */
		closeText: '닫기',
		currentText: '오늘',
		buttonImageOnly: false,
		showButtonPanel: true
		/* e :접근성 추가 */
	});
	$('.jqdate').datepicker();
	$('.jqdate-img').datepicker({
		showOn: 'button',
		buttonImage: 'https://www.busan.go.kr/humanframe/global/assets/img/ico_calender.png',
		buttonText: '클릭하시면 좌측의 텍스트박스에 달력이 열립니다. 달력은 CTRL/COMMAND+방향키로 컨트롤이 가능합니다.'
		/* buttonImageOnly: true,
		 * buttonText: 'Select date'*/
	});
	 //$('.jqdate-img').attr("readonly", false);

});


// boardTop search mobile ver
/*$(function(){
	var $boardHead = $('.boardHead');
	$boardHead.each(function(i, self) {
		$(self).find('.btnSchDate').click(function() {
			$(self).find('.boardSearchDate').addClass('opened');
			$(self).find('.boardSearch').removeClass('opened').prev('.btnSchOpen').removeClass('hidden');
			$(self).addClass('toggleOn');
		}).end().find('.btnSchOpen').click(function() {
			$(self).find('.boardSearchDate').removeClass('opened');
			$(self).find('.boardSearch').addClass('opened').prev('.btnSchOpen').addClass('hidden');
			$(self).addClass('toggleOn');
		}).end().find('.btnSchClose').click(function() {
			$(self).find('.boardSearchDate').removeClass('opened');
			$(self).find('.boardSearch').removeClass('opened').prev('.btnSchOpen').removeClass('hidden');
			$(self).removeClass('toggleOn');
		});
	});

});*/


// table scroll (As-is)
$(function() {
	$('table[class*="tableCol"], table[class*="tableRow"], table[class*="tableMt"]').each(function(){
		if(!$(this).is(".no-scroll")){
			if($(this).parents("table").length === 0){
				$(this).wrap('<div class="srcoll-table"><div></div></div>');
			}
		}
	});
});


//문서변환 스크립트
window.f_filePreivew = function(srvcId, upperNo, fileTy, fileNo){
	$.ajax({
		type : "POST",
		url : "/comm/docPreview",
		data : {
			"srvcId" : srvcId,
			"upperNo" : upperNo,
			"fileTy" : fileTy,
			"fileNo" : fileNo
		},
		success: function(resultMap) {
			if(resultMap){

				// 문서 변환 성공
				if(resultMap.resultCode == 0){
					window.open(resultMap.resultPage);

				// 파일이 없을 경우
				}else if(resultMap.resultCode == 999){
					alert("첨부파일이 존재하지 않습니다.");
				}else{
					alert("지원하지 않는 파일입니다.");
				}


			}else{
				alert("문서변환중 오류가 발생하였습니다.");
			}
		}
	});
}

//문서변환
function fn_Preivewbudget(savename){

	$.ajax({
     		dataType : "json",
     	    type : "POST",
     	 	 url: "/humanframe-cms/bbs/docPreview",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data : "bbsNo=716&savename="+savename,
		success: function(resultMap) {
			if(resultMap){

				// 문서 변환 성공
				if(resultMap.resultCode == 0){
					window.open(resultMap.resultPage);

				// 파일이 없을 경우
				}else if(resultMap.resultCode == 999){
					alert("첨부파일이 존재하지 않습니다.");
				}else{
					alert("지원하지 않는 파일입니다.");
				}


			}else{
				alert("문서변환중 오류가 발생하였습니다.");
			}
		}
	});
}


//문서변환 스크립트
window.f_gosiFilePreivew = function(fileId, seq){
	$.ajax({
		type : "POST",
		url : "/nbgosi/docPreview",
		dataType:"json",
		data : {
			"fileId" : fileId,
			"seq" : seq
		},
		success: function(resultMap) {
			
			if(resultMap){
				
				// 문서 변환 성공
				if(resultMap.resultCode == 0){
					window.open(resultMap.resultPage);

				// 파일이 없을 경우
				}else if(resultMap.resultCode == 999){
					alert("첨부파일이 존재하지 않습니다.");
				}else{
					alert("지원하지 않는 파일입니다.");
				}


			}else{
				alert("문서변환중 오류가 발생하였습니다.");
			}
		}
	});
}

//sns 공유
window.f_share = function(sns, tit){
	var linkUrl = document.location.href;
	var title = encodeURI(tit);
	snsShare(sns, title, linkUrl);
}
//북마크 등록
window.addBookMark = function(path,siteNm){
	var nowUrl = location.href;
	var tmpNm = path;
	var urlNm = tmpNm.replace('>Home', siteNm);

	$.ajax({
		url:"/comm/bookmark/add",
		type: "POST",
		data: {
				"url" : nowUrl
				,"urlName" : urlNm
			  },
		success : function(data){
			if(data.reCode == -1){
				if(confirm(data.reMsg)){
					location.href = "/member/login?referUrl="+nowUrl;
				}
			} else if(data.reCode == 9999) {
				if(confirm(data.reMsg)){
					location.href = nowUrl;
				}
			} else {
				alert(data.reMsg);
			}
		}
	});
}


window.snsShare = function(shareTy, title, linkUrl){
	var url = "";
	if(shareTy == "facebook") {
		// 공유하기 캐시 동기화
		url="https://www.facebook.com/sharer.php?u="+encodeURIComponent(linkUrl);
		$.ajax({
			url:"https://graph.facebook.com",
			data: {
					id: url,
			        scrape: true
				  },
			success : function(data){
			}, error : function (err) {
			}
		});
		window.open(url,"facebook",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}else if(shareTy == "twitter") {
		url="https://twitter.com/intent/tweet?text="+title+"&url="+linkUrl+"&original_referer="+linkUrl;
		window.open(url,"twitter",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}else if(shareTy == "googleplus") {
		url="https://plus.google.com/share?url="+linkUrl;
		window.open(url,"google",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}else if(shareTy == "kakao"){
		url = "https://story.kakao.com/share?url="+linkUrl+"&text="+title;
		window.open(url,"kakao",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}else if(shareTy == "line"){
		url = "http://line.me/R/msg/text/?="+linkUrl;
		window.open(url,"kakao",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}else if(shareTy == "band"){
		url = "http://band.us/plugin/share?body="+ title +""+encodeURIComponent("\r\n")+""+linkUrl+"&route="+linkUrl;
		window.open(url.replace("https", "http"),"band",'location=1,scrollbars=auto,resizable=no,top=25,left=100,width=600,height=500');
	}
}




$(function() {
	if ($("#pageSatisfy").length > 0) {
		loadStsfdy();
	}

	$('.shareArea .share a').on('click.share', function(e){
		e.preventDefault();
		var $target = $(this).parent('.share');
		$target.toggleClass('on');
		if($target.hasClass('on')){
			$(this).text('공유하기 닫기');
		} else {
			$(this).text('공유하기 열기');
		}
	});
	
	$(".shareArea .print a").on("click", function(e){
		e.preventDefault();
		window.print();
	});

	//select
	$("#selcSiteLanguage").on("click", function(e){
		e.preventDefault();
		if($("#selcLanguage").val() != "") {
			window.open($("#selcLanguage").val(), "_blank", "");
		}
	});

});

function loadStsfdy(){
	$.ajax({
		url: "/comm/stsfdg/list", // 평균 및 의견목록 가져오기
		type: "post",
		data: {
			menuNo:$("#satisfactionForm input[name='menuNo']").val()
			, listDoNot:"Y"					//listDoNot값을 Y를 넣어주면 리스트객제를 가져오지 않는다. 아무값도 없거나 N일경우는 리스트객체를 가져온다.
		},
		success: function(data){
			if (data.listVO != null) {
				$("#stsfdg_avgScore").text(data.avgScore);
				$("#stsfdg_totCnt").text(data.listVO.totalCount);
				/*$("#satisfactionList tr").remove();
				var list = data.listVO.listObject;
				for(var i=0; i<list.length; i++) {
					var btnDel = "";
					if (list[i].crtrUniqueId == "${sessMber.uniqueId}"){
						btnDel = ' <input type="button" class="btnTypeS btnLineType1" onclick="javascript:saveStsfdy("DELETE","'+list[i].satsfcNo+'");" value="삭제">';
					}
					var tr = "<tr><td>"+list[i].opinion+btnDel+"</td><td>"+list[i].crtrNm+" "+list[i].creatDttm+"</td></tr>";
					$("#satisfactionList").append(tr);
				}*/
			}
		},
		error: function(xhr, status, err){
			alert("[error] " + ":" + err + "(" + status + ")");
		}
	});
}
function saveStsfdy(actionTy,satsfcNo){

	$("#satisfactionForm input[name='crud']").val(actionTy);
	if (actionTy == "DELETE") {
		$("#satisfactionForm input[name='satsfcNo']").val(Number(satsfcNo));
	}
	$.ajax({
		url: "/comm/stsfdg/action", // 의견 추가,삭제
		type: "post",
		data: $("#satisfactionForm").serialize(),
		success: function(data){
			alert(data.msg);
			if (data.success > 0) {
				loadStsfdy();
			} else if (data.redirectUrl != null) {
				window.location = data.redirectUrl;
			}
		},
		error: function(xhr, status, err){
			alert("[error] " + ":" + err + "(" + status + ")");
		}
	});
}


$(function(){

	/** fn_isEmpty **/
	window.fn_isEmpty = function(obj) {
		if (obj == undefined || obj == null || obj === "") return true;
		return false;
	};

	/** 이미지 크게보기(일반팝업) **/
	window.fn_imageBigPopView = function(url) {
		var src = url;
		if(!fn_isEmpty(src)) {
			var win = window.open('', 'imagePop', 'width=0, height=0, menubar=0, toolbar=0, directories=0, scrollbars=1, status=0, location=0, resizable=1');

			var html = new Array();
			html.push("<!doctype html><head><meta charset='utf-8'><title>:: 크게 보기 ::</title></head>");
			html.push("<body leftmargin='0' topmargin='0'>");
			html.push("<img src='"+ src +"' border='0' style='display:block;cursor:pointer;position:absolute;top:0;left:0;' alt='이미지 크게 보기' onclick='javascript:window.close();'");
			html.push("onload='window.resizeTo(this.width+20, this.height+70);");
			html.push("window.moveTo( (screen.width-this.width)/2 ,  (screen.height-this.height)/2-50 )'>");
			html.push("</body></html>");
			win.document.write(html.join(""));
		}
	};

	/** 이미지 크게보기(fancyBox) **/
	var prev_click_object = null;
	$('a').on('click', function() {
		prev_click_object = $(this);
	});
	window.fn_imageBigFancyBoxView = function(url) {
		var src = url;
		var click_obj = '';
		if(prev_click_object != null) {
			click_obj = prev_click_object;
		}
		if(!fn_isEmpty(src)) {
			$.fancybox({
				content     : "<img src='"+ src +"' style='cursor:pointer' alt='이미지 크게 보기' onclick='javascript:$.fancybox.close();'/>",
				fitToView	: false,
				autoSize	: true,
				closeClick	: false,
				openEffect	: 'none',
				closeEffect	: 'none',
				afterShow: function(){
					$('.fancybox-overlay-fixed').css({'position':'fixed','top':'0','left':'0','bottom':'0','right':'0','z-index':'9999999','background':'rgba(0,0,0,.6)'});
					$('body').css("overflow","hidden");
					$('*[tabIndex = -1]').attr('tabIndex', '');
					$('.fancybox-outer').attr('tabIndex', -1);
					$('.fancybox-outer').focus();
					$('.fancybox-wrap').css('z-index','9999999999');
					
				},
				afterClose : function(){
					$('body').css("overflow","");
					$('*[tabIndex = -1]').attr('tabIndex', '');
					prev_click_object.focus();
				},
				helpers : {
					title : {
						type : 'inside'
					}
				}
			});
		}
	};

	/** 컨텐츠 팝업처리 for fancyBox **/
	window.fn_contentsFancyBox = function(url, mWidth, mHeight) {
		$.fancybox.open({
			href 		: url,
			type 		: 'iframe',
			padding 	: 0,
			maxWidth	: mWidth,
			maxHeight	: mHeight,
			fitToView	: false,
			width		: '95%',
			height		: '95%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterShow: function(){
				$('.fancybox-iframe').focus();
			},
			afterClose : function(){
				prev_click_object.focus();
			},
		});
	};

	/** 이미지 팝업처리 for fancyBox **/
	window.fn_imageFancyBox = function(url, mWidth, mHeight) {
		$.fancybox({
			content		: "<img src='"+ url +"' style='cursor:pointer' alt='이미지 크게 보기' onclick='javascript:$.fancybox.close();'/>",
			maxWidth	: mWidth,
			maxHeight	: mHeight,
			fitToView	: false,
			width		: '95%',
			height		: '95%',
			autoSize	: true,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterShow: function(){
					$('*[tabIndex = -1]').attr('tabIndex', '');
					$('.fancybox-outer').attr('tabIndex', -1);
					$('.fancybox-outer').focus();
			},
			afterClose : function(){
				$('*[tabIndex = -1]').attr('tabIndex', '');
				prev_click_object.focus();
			},
		});
	};

});

jQuery(function($){
	set_Tabbox(true);
	window.onresize = function() {
		set_Tabbox(false);
	};

	function set_Tabbox(set_event) {
		var tab = $('.tabCont');
		var client_width = window.innerWidth;

		tab.find('> ul > li').removeClass('on');
		tab.each(function(){
			var tabBox = $(this);
			tabBox.removeClass('js_off');

			if(client_width > 640){
				tabBox.removeClass('mobile');
				onSelectTab(tabBox.find('> ul > li.selected > a'), tabBox);
			} else {
				if(!tabBox.hasClass('mobile')){
					tabBox.addClass('mobile');
					tabBox.find('> ul > li.on').removeClass('on')
				}
			}

			if(set_event) {
				tabBox.find('> ul > li > a').click(function(){
					onSelectTab(this, tabBox);
					return false;
				});
			}
		});
	};

	// Click Event :: Tab a element = obj
	function onSelectTab(obj, box){
		var t = $(obj);
		var myClass = 'on';

		if(!box.hasClass('mobile'))	{
			// PC, Tblete Mode Event
			t.parents('li').siblings().removeClass(myClass);
			t.parents('li').addClass(myClass);
		} else {
			// Mobile Event
			if(!t.parents('li').hasClass(myClass)){
				t.parents('li').addClass(myClass);
			} else {
				t.parents('li').removeClass(myClass);
			}
			t.parents('li').siblings().removeClass(myClass);
		}
		box.css('padding-bottom', t.next('div').height());
	};

	window.fn_bookMark = function(url, name, sMsg, fMsg, iMsg, e) {
        var triggerDefault = false;

        if (window.sidebar && window.sidebar.addPanel) {
            // Firefox version < 23
            window.sidebar.addPanel(name, url, '');
            alert(sMsg);
            var triggerDefault = true;
        } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) || (window.opera && window.print)) {
            // Firefox version >= 23 and Opera Hotlist
            var $this = $(this);
            $this.attr('href', url);
            $this.attr('title', name);
            $this.attr('rel', 'sidebar');
            $this.off(e);
            triggerDefault = true;
            alert(sMsg);
        } else if (window.external && ('AddFavorite' in window.external)) {
            // IE Favorite
            window.external.AddFavorite(url, name);
            triggerDefault = true;
        } else {
            // WebKit - Safari/Chrome
            //Alert("Information", (navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.');
       	 alert(iMsg);
       	 triggerDefault = true;
        }

	};
});

$(function(){
	//20190501 || (공통) 동영상/텍스트 레이어 팝업
	$("a[data-function='popup'], button[data-function='popup']").on({
		"click" : function(e){
			e.preventDefault();
			
			var $this = $(this);
			
//			var src = $this.find(".src").val();
//			var title = $this.find(".title").val();
//			var caption = $this.find(".caption").val();
			var src = $this.data().src;
			var title = $this.data().title;
			var caption = $this.data().caption;
			
			//caption = caption.replace(/\n/gi,"<br>");
			
			if(src){
				var layout = "<div class='layerPopup' data-type='video'>";
			} else {
				var layout = "<div class='layerPopup' data-type='text'>";
			}
			layout += "<div class='backspace'/>";
			layout += "<div class='layerWrap'>";
			layout += "<h4 class='layerTitle'>"+title+"</h4>";
			if(src){
				layout += "<div class='videoWrap'>";
				layout += "<video src='"+src+"' controls />";
				layout += "</div>";
			}
			layout += "<div class='captionWrap'><p>"+caption+"</p></div>";
			layout += "<button class='layerClose'>닫기</button>";
			layout += "</div>";
			layout += "</div>";
			
			$this.parent().append(layout);
			
			var $lp = $this.parent().find(".layerPopup");
			
			$lp.fadeIn(300);
			
			$lp.find(".layerClose").on("click", function(){
				$lp.fadeOut(300);
				setTimeout(function(){
					$lp.remove();
				},300);
			});
			
		}
	});
	
	$("a.popup-btn").on("click", function(e){
		e.preventDefault();
		
		var id = this.getAttribute("href");
		
		if(id.indexOf("#") == 0){
			var pop = $(id);
			var title = e.target.textContent;
			
			if(!pop.is(":visible")){
				pop.show();
				pop.attr("tabindex","0");
				pop.focus();
				pop.removeAttr("tabindex");
			}
			
			if(pop.has(".popup-close").length == 0){
				var close = document.createElement("a");
				close.classList.add("popup-close");
				close.href = id;
				close.textContent = title+" 닫기";
				
				close.addEventListener("click", function(k){
					k.preventDefault();
					
					pop.hide();
					e.target.focus();
				});
				
				pop.append(close);
			}
			
			
		}
	});
	
	ieYoutube();
});

// 유튜브에서 ie지원 중단에 의해 ie사용자에게 embed된 유튜브 시청시 안내문구를 표출하도록함. | 2020-02-19 | 정보화담당관의 요청
function ieYoutube(){
	// IE인지 구분 - ie10이하 : MSIE, ie11 이상 : Trident
	if(navigator.userAgent.indexOf("MSIE") !== -1 || navigator.userAgent.indexOf("Trident") !== -1) {
		var isYoutube = document.querySelector("iframe[src*=youtube]"); //유튜브 임베디드 유무
		
		if(isYoutube){
			var wrap = document.createElement("div");
			var youtube = document.querySelector("iframe[src*=youtube]");
			var pop = document.createElement("div");
			var txt = document.createElement("p");
			var close = document.createElement("button");
			
			close.innerHTML = "닫기";
			close.addEventListener("click", function(){
				wrap.removeChild(pop);
			});
			
			txt.innerHTML = "2020.3.1일부터 유튜브는 인터넷 익스플로러를 지원하지 않습니다.<br>유튜브 동영상을 시청하시려면 크롬, 엣지, 파이어폭스, 오페라 등의 브라우저를 이용하시길 바랍니다.";
			
			pop.appendChild(txt);
			pop.appendChild(close);
			
			wrap.innerHTML = youtube.outerHTML;
			wrap.insertBefore(pop, wrap.firstChild);
			
			youtube.parentNode.insertBefore(wrap, youtube);
			youtube.parentNode.removeChild(youtube);
			
			//CSS style
			wrap.style.display = "inline-block";
			wrap.style.position = "relative";
			pop.style.display = "table";
			pop.style.boxSizing = "border-box";
			pop.style.position = "absolute";
			pop.style.top = "50%";
			pop.style.left = "50%";
			pop.style.transform = "translate(-50%, -50%)";
			pop.style.width = "calc(100% - 20px)";
			pop.style.height = "calc(100% - 20px);";
			pop.style.background = "rgba(0,0,0,.8)";
			pop.style.zIndex = "1";
			pop.style.padding = "30px";
			pop.style.wordBreak = "keep-all";
			txt.style.display = "table-cell";
			txt.style.verticalAlign = "middle";
			txt.style.color = "#fff";
			txt.style.fontSize = "0.9em";
			txt.style.lineHeight = "1.4";
			close.style.position = "absolute";
			close.style.color = "inherit";
			close.style.top = "10px";
			close.style.right = "10px";
			close.style.width = "36px";
			close.style.height = "36px";
			close.style.overflow = "hidden";
			close.style.textIndent = "-9999px";
			close.style.background = "url(/humanframe/global/assets/img/common/ico_close_w.png) no-repeat center center";
			
		}
		
	}
}

//반응형 링크제공 (rel속성 noopener, noreferrer 등 속성있을시 동작하지 않습니다.)
function linkByResponsive(pc,mo,blank){
	var url;
	
	if(window.innerWidth > 768){
		url = pc;
	} else {
		url = mo;
	}
	
	if(blank){
		window.open(url,'_blank');
	} else {
		location.href = url;
	}
}

function skipNavHandler(id){
	var el = document.querySelector(id);
	
	el.setAttribute("tabindex","0");
	el.focus();
	el.removeAttribute("tabindex");
}

//javascript 파라미터 접근
function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

// 동적 url
function f_dynamicLinks() {
	var lurl = document.location.href;
	
	if (document.location.protocol == 'http:') {
		lurl = document.location.href.replace('http:', 'https:');
	}
	
	$.ajax({
		url : "/fnct/dynamicLinks/check",
		type: "post",
		data: {"lurl" : lurl, "type" : "A"},
		success: function(data) {
			$("#dynamicLinksDiv").css("display", "inline-block");
			$("#surl").val(data);	
		},
		error: function(data, status, err) {
			console.log('error forward : ' + data);
		}
	});
}

function f_dynamicLinksCopy() {
	var text = document.getElementById("surl");
	text.select();
	document.execCommand("copy");		
}

function f_dynamicLinksClose() {
	$("#dynamicLinksDiv").css("display", "none");	
}

function createStylesheet(css){
	var style = document.createElement("style");
	style.textContent = css;
	document.head.appendChild(style);
}
