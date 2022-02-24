$(function () {
    $("#header_nav").mouseover(function () {
        $(this).css({ height: "350px" });
        $("#header_navWrap").children("ul").children("li").find("ul").addClass('show');
    });
    $("#header_nav").mouseout(function () {
        $(this).css({ height: "52" });
        $("#header_navWrap").children("ul").children("li").find("ul").removeClass('show');
    });
});