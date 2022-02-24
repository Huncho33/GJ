$(function(){
	
	$(".mainBanner").slick({
		arrows: true,
		dots: false
	});
	
	$(".mainEvent").slick({
		arrows:false,
		dots: true,
		autoplay: true,
		autoplaySpeed: 5000,
	});
	
	$(".mainOrg").slick({
		arrows: false,
		dots: false,
		slidesToShow: 5,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 5000,
		responsive: [{
			breakpoint: 1024,
			settings: {
				slidesToShow: 3
			}
		},{
			breakpoint: 768,
			settings:{
				slidesToShow: 2
			}
		},{
			breakpoint: 440,
			settings:{
				slidesToShow: 1
			}
		}]
	});
	
	$(".mainTab").find(">li>button").on("click focus", function(){
		if(!$(this).parent().find(">ul").is(":visible")){
			$(".mainTab").find(">li>ul:visible").parent().removeClass("active");
			$(this).parent().addClass("active");
		}
	});
	
	$(".slick-controls").find("button").on('click', function(){
		var $slider = $(this).closest('div[class*=Wrap]').find(".slick-slider");
		switch($(this).attr('class')){
			case 'next' : $slider.slick('slickNext'); break;
			case 'prev' : $slider.slick('slickPrev'); break;
			case 'play' : 
				if($(this).attr('data-status') == 'play'){
					$(this).attr('data-status','pause');
					$(this).text("자동재생 시작");
					$slider.slick('pause');
				} else {
					$(this).attr('data-status','play');
					$(this).text("자동재생 정지");
					$slider.slick('play');
				}
		}
	});
	
});