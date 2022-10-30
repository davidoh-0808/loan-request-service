$(function(){
    
    /*=============================================
    jquery ui modal
    =============================================*/
    var arr_pid = [];

    // var html_alert = '<div class="pop_wrap alert_msg"><div class="pop_contents"><p class="msg"></p></div></div>'
    // $( ".pop_btn" ).on( "click", function() {
    //     $("body").append(html_alert);
    // });

    $.widget("ui.dialog", $.ui.dialog, {
        options: {
            modal: false,
            resizable: false,
            draggable: false,
            maxHeight: $(window).height(),
            show: {
                effect: 'fade',
                duration: 100,
            },
            hide: {
                effect: 'fade',
                duration: 100
            },
            showAlertMessage: 'defaultMessage', // alert message 옵션 추가
            overlayBlock: true  // ui-widget-overlay block 옵션 추가
        },
        open: function() {
            $('body').addClass('ov_hidden');
            if (this.options.showAlertMessage) {
                $('.alert_msg').find('.msg').text(this.options.showAlertMessage);
            }
            var overlayBlock = this.options.overlayBlock;
            var thisId = this.element[0].id;
            $(document).on('click', '.ui-widget-overlay', function () {
                console.log(overlayBlock);
                if (overlayBlock) {
                    $("#" + thisId).dialog('close');
                }
            });
            return this._super();
        },
        close: function () {
            $('body').removeClass('ov_hidden');
            $(".ui-widget-overlay").off('click', function () {
            });
            $(document).off('click', '.ui-widget-overlay', function () {

            });
            // $('.alert_msg').remove();
            return this._super();
        },
       /*_create: function() {
            console.log(this.element);
           arr_pid.push(this.element[0].id);
           console.log(arr_pid[0]);
            // this.element.css( "background-color", this.options.color );

            // console.log(this.element, this.options);
            return this._super();
        }*/

    });

    // dialog resize
    $(window).resize(function () {
        $(".ui-dialog-content:visible").each(function () {
            $(this).dialog("option", "position", $(this).dialog("option", "position"));
        })
    });



    /*=============================================
	Datepicker
	=============================================*/
    var tg_dpi;
    var pos_dpi_x;
    var pos_dip_y;
    $.datepicker.setDefaults({
        // showOn: 'text',
        // changeMonth: true,
        // changeYear: true,
        dateFormat: 'yy.mm.dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        monthNamesShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        dayNames: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        dayNamesShort: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        dayNamesMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        showMonthAfterYear: true,
        showOtherMonths: true,
        yearSuffix: '.',
        beforeShow:function(el){
            tg_dpi = $(el);
        },
    });

    $('.datepicker').datepicker();

    $(window).resize(function () {
        if (tg_dpi) {
            pos_dpi_x = tg_dpi.offset().left;
            pos_dip_y = tg_dpi.offset().top + tg_dpi.outerHeight();
            $('#ui-datepicker-div').css({'left': pos_dpi_x, 'top': pos_dip_y});
        }

    });

    /*=============================================
	TAB
	=============================================*/
    var tabMenu = $('.tabs li');
    var tabContent = $('.tab_contents');

    tabMenu.on('click', function(e) {
        e.preventDefault();
        var idx =tabMenu.index(this);

        $(this).addClass('current').siblings().removeClass('current');
        tabContent.eq(idx).addClass('on').siblings().removeClass('on');
    });

     /*=============================================
        jquery ui selectmenu
    =============================================*/
    // $('select.selbox').selectmenu();
    // console.log($('#jobs').on('selectmenuchange',function(){
    //     console.log($(this)[0].selectedIndex);
    // }))
    // $(window).resize(function () {
    //     $('select.selbox').selectmenu("close");
    // });

});


    
        

