$(function(){
    /*=============================================
    pc / mobile 구분
    =============================================*/
    var filter = "win16|win32|win64|macintel|mac|"; // PC일 경우 가능한 값
    if (navigator.platform) {
        if (filter.indexOf(navigator.platform.toLowerCase()) < 0) {
            console.log("모바일에서 접속하셨습니다");
            $("#wrap").addClass('mobile');
        } else {
            $("#wrap").addClass('pc');
        }
    }

    /*=============================================
    Gnb
    =============================================*/
    var gnb = $('#gnb');
    var lst_gnb = $('.lst_gnb');

    gnb.on('mouseenter', function() {
        gnb.addClass('on');
    });
    gnb.on('mouseleave', function() {
        if ($(window).width() > 767) {
            gnb.removeClass('on');
        }
        
    });
    
    $(document).on('click', '.btn_gnb', function(){
        gnb.addClass('on');
        $('#header').append('<div class="overlay"></div>');
        $('.overlay').fadeIn(200);
        $('body').css('overflow', 'hidden');
    });

    $(document).on('click', '.btn_close_gnb', function(){
        gnb.removeClass('on');
        $('.overlay').fadeOut(200).remove();
        $('body').css('overflow', 'auto');

    });

    /*=============================================
	TAB
	=============================================*/
    var tabMenu = $('.tabs li a');
    var tabContent = $('.tab_contents');

    tabMenu.on('click', function(e) {
        e.preventDefault();
        var idx =tabMenu.index(this);

        $(this).addClass('on').closest('li').siblings().children('a').removeClass('on');
        tabContent.eq(idx).addClass('on').siblings().removeClass('on');
    });

    var tabMenu_in = $('.tabs_in li a');
    var tabContent_in = $('.tab_contents_in');

    tabMenu_in.on('click', function(e) {
        e.preventDefault();
        var idx =tabMenu_in.index(this);

        $(this).addClass('on').closest('li').siblings().children('a').removeClass('on');
        tabContent_in.eq(idx).addClass('on').siblings().removeClass('on');
    });

    /*=============================================
    File Upload
    =============================================*/
    var fileTarget = $('.wrap_file .upload_hidden');
    fileTarget.on('change', function () { // 값이 변경되면
        if (window.FileReader) { // modern browser
            if ($(this)[0].files && $(this)[0].files[0]) {
                var filename = $(this)[0].files[0].name;
            }
        } else { // old IE
            var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
        };
        // 추출한 파일명 삽입
        $(this).siblings('.upload_name').val(filename);
        showDeleteBtn();
    });

    fileTarget.on({
        focus: function() {
            $(this).closest('.wrap_file').addClass('outline');
        }, focusout: function() {
            $(this).closest('.wrap_file').removeClass('outline');
        }
    });
    showDeleteBtn();
    function showDeleteBtn() {
        //console.log('function 작동시');
        $('.wrap_file').each(function() {
            if($(this).find('.upload_name').val() !== '') {
                if($(this).find('.btn_fileuploade_close').length < 1) {
                    $(this).append('<button type="button" class="btn_fileuploade_close"></button>');
                }

            }
        });
    };

    $(document).on('click', '.btn_fileuploade_close', function() {
        $(this).siblings('input[type="file"]').val('');
        $(this).siblings('input.upload_name').val('');
        $(this).remove();
    });


    // input 입력시 btn_del 활성화
    checkSearchForm();
    function checkSearchForm() {
        var searchForm = $(".form_search input");
        if(searchForm.val() === ''){
            searchForm.siblings('.btn_clear').removeClass('on');
        } else{
            searchForm.siblings('.btn_clear').addClass('on');
        }
    }
    
    $(document).on("propertychange change keyup paste input", ".form_search input", function() {
		checkSearchForm();
    });
    // btn_del 클릭시 input value 삭제
    $(document).on('click', '.group_search .btn_clear', function(){
		$(this).siblings('.form_control').val('');
		$(this).removeClass('on');
	});

    

    /*=============================================
    jquery ui selectmenu
    =============================================*/
    $('select.form_control').selectmenu();
    $(window).resize(function() {
        $('select.form_control').selectmenu( "close" );
    });


    /*=============================================
    jquery ui modal
    =============================================*/
    var arr_pid = [];

    $.widget("ui.dialog", $.ui.dialog, {
        options: {
            modal: true,
            resizable: false,
            draggable: false,
            maxHeight: $(window).height(),
            show: {
                effect: 'fade',
                duration: 500,
            },
            hide: {
                effect: 'fade',
                duration: 200
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
                if (!overlayBlock) {
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
    $(window).resize(function() {
        if($(".ui-dialog").is(":visible")){
            $(".ui-dialog-content").dialog("option", "position", {my: "center", at: "center", of: window});
        }
    });

    /*=============================================
	jquery ui autocomplete
	=============================================*/
    // 기본 옵션 추가
    $.ui.autocomplete.prototype._resizeMenu = function () {
        var ul = this.menu.element;
        ul.outerWidth(this.element.outerWidth());
    }
    $.extend($.ui.autocomplete.prototype.options, {
        open: function( event, ui ) {
            var $input = $(event.target),
                $results = $input.autocomplete("widget"),
                top = $results.position().top + 3;

            $results.css("top", top + "px");
        },
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

    /*
    $('.timepicker').timepicker({
        timeFormat: 'hh:mm p',
        interval: 30,
        // minTime: '10',
        // maxTime: '6:00pm',
        // defaultTime: '11',
        // startTime: '10:00',
        // dynamic: false,
        // dropdown: true,
        scrollbar: true,
        change: function(time) {
            // console.log(time);
            // the input field
            var element = $(this), text;
            // get access to this Timepicker instance
            var timepicker = element.timepicker();
            // text = 'Selected time is: ' + timepicker.format(time);
            // element.siblings('span.help-line').text(text);
        }
    });
    */
    
   
    /*=============================================
	FOOTER
	=============================================*/
    $('.btn_terms').on('click', function() {
        $('#pop_terms').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
            ]
        });
    });

    $('.btn_policy').on('click', function() {
        $('#pop_policy').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
            ]
        });
    });

    $('.btn_emailreject').on('click', function() {
        $('#pop_emailreject').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
            ]
        });
    });

    $('.btn_manage_image').on('click', function() {
        $('#pop_manage_image').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
            ]
        });
    });

    $('.btn_trust_work').on('click', function() {
        $('#pop_trust_work').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
            ]
        });
    });
});
