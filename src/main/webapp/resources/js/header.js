$(function () {
    $("#nav").mouseover(function () {
        $(this).css({ height: "350px" });
        $("#navWrap").children("ul").children("li").find("ul").addClass('show');
    });
    $("#nav").mouseout(function () {
        $(this).css({ height: "52" });
        $("#navWrap").children("ul").children("li").find("ul").removeClass('show');
    });
});