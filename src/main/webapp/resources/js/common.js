$(function(){
	var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	var $header = $("#header");
	var $navMenu = $("#topNavMenu").find(">li>a");
	var $navBtn = $("#topNavBtn");
	var $back = $("#headerBack");
	var $skip = $("#skipNav > a");
	var speed = 300;
	
	responsiveHeader();
	function responsiveHeader(){
		width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

		if(width > 1024){ //PC ver
			$navMenu.off("mouseover").on("mouseover", function(){
				if(!$navMenu.parent().find(">ul").is(":visible")){
					$navMenu.parent().find(">ul").show();
					var heightArray = new Array();
					for(var i = 0; i < $navMenu.parent().find(">ul").length; i++){
						heightArray.push($navMenu.parent().find(">ul").eq(i).outerHeight());
					}
					heightArray.sort(function(a,b){
						return b - a;
					});
					$navMenu.parent().find(">ul").hide();
					$navMenu.parent().find(">ul").height(heightArray[0] - 20).stop().slideDown(speed);
					$back.height(heightArray[0]).slideDown(speed);
				}
			});
			
			$header.find(".headerBody").off("mouseleave").on("mouseleave", function(){
				$navMenu.parent().find(">ul").stop().slideUp(speed);
				$back.stop().slideUp(speed);
			});
		} else {
			$navBtn.off("click").on("click", function(){
				if(!$header.is('.opened')){
					$(this).find("span").text("메뉴닫기");
					$header.addClass("opened");
				} else {
					$(this).find("span").text("메뉴열기");
					$header.removeClass("opened");
				}
				
			});
			$navMenu.off("click").on("click", function(e){
				e.preventDefault();
			});
		}
	}
	
	function resetHeader(){
		$header.removeClass('opened').find(".headerBody").off("mouseleave");
		$navBtn.off("click").find("span").text("메뉴열기");
		$navMenu.off("mouseover click");
		$navMenu.parent().find(">ul").removeAttr("style");
		$back.removeAttr("style");
	}
	
	$skip.on('click', function(e){
		e.preventDefault();
		
		var cont = $(this).attr("href");
		
		$(cont).attr("tabindex","0").focus().removeAttr("tabindex");
		
	});
	
	$(".subMenuBtn").on("click", function(){
		if(!$(this).parent().find(">ul").is(":visible")){
			$(this).text("하위메뉴 닫기");
			$(this).parent().find(">ul").stop().slideDown(speed);
		} else {
			$(this).text("하위메뉴 열기");
			$(this).parent().find(">ul").stop().slideUp(speed);
		}
	});
	
	$(".scrollTop").on("click", function(){
		$("body, html").animate({scrollTop:0},300);
	});
	
	$('#snb_box dt a').on('click', function() {
		return false;
	});
	$('#snb_box dt a').on('mousedown', function(e) {
		is_mousedown = true;
		$(this).closest('dl').siblings().removeClass('on');
		$(this).closest('dl').toggleClass('on');
		return false;
	});
	$('#snb_box dt a').on('focus', function(e) {
		if (!is_mousedown) {
			$(this).closest('dl').siblings().removeClass('on');
			$(this).closest('dl').toggleClass('on');
		}
	});
	$('#snb_box dl.last a').last().on('focusout', function(e) {
		if (!is_mousedown) {
			$('#snb_box dl').removeClass('on');
		}
	});
	
	$(".footerFamilySite").find("button").on("click", function(){
		if(!$(this).next('ul').is(":visible")){
			$(this).next('ul').slideDown(300);
			$(this).attr("title","닫기");
		} else {
			$(this).next('ul').slideUp(300);
			$(this).attr("title","펼치기");
		}
	});
	
	$(window).on('resize', function(){
		if(width != (window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth)){
			resetHeader();
			responsiveHeader();
		}
	});
});