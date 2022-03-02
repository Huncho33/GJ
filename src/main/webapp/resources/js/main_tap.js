$(function () {
    $('.main_tablist2').hide();

    $('.nav-item1').click(function (e) {
        $('.main_tablist1').show();
        $('.main_tablist2').hide();
    });
    $('.nav-item2').click(function (e) {
        $('.main_tablist2').show();
        $('.main_tablist1').hide();
    });
});