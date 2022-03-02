

var xpos = 0;
$(function() {
    $('#khs_prev').click(function() {
        $(this).removeClass('on');
        xpos -= 200;
        if (xpos <= -1200) {
            xpos = -1200;
            $(this).addClass('on');
        } 
        $('#khs_box').stop().animate({left: xpos + "px"},1000);
    });
    $('#khs_next').click(function() {
        xpos += 200;
        if (xpos >= 0) {
            xpos = 0;
            $(this).addClass('on');
        } 
        $('#khs_box').stop().animate({left: xpos + "px"}, 1000);
    });
});