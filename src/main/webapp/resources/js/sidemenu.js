$(function() {
            $('ul.khs_depth2').hide();
            $('.khs_lnb').click(function() {
                $('ul.khs_depth2').hide();
                $('a.khs_lnb').each(function() {
                    if ($(this).hasClass('on')) {
                        $(this).removeClass('on');
                    }
                });

                $(this).addClass('on');
                $(this).next('.khs_depth2').slideDown();
            });

        });