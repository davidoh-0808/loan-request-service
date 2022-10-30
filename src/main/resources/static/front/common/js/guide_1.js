
/* 웹사이트 좌측메뉴 추가 */
var webSnb = function(){
	var webSnb = '';
	webSnb += '<ul>';
	webSnb += '<li><a href="web_layout.html">LAYOUT</a></li>';
	webSnb += '<li><a href="button.html">BUTTON</a></li>';
	webSnb += '<li><a href="tab.html">TAB</a></li>';
	webSnb += '<li><a href="form.html">FORM</a></li>';
	webSnb += '<li><a href="table.html">TABLE</a></li>';
	webSnb += '<li><a href="label.html">LABEL</a></li>';
	webSnb += '<li><a href="popup.html">POPUP</a></li>';
	webSnb += '</ul>';

	$('.g-nav-snb').append(webSnb);
};

/* 웹사이트 좌측메뉴 추가 */
var mobileSnb = function(){
	var mobileSnb = '';
	mobileSnb += '<ul>';
	mobileSnb += '<li><a href="mobile_layout.html">LAYOUT</a></li>';
	mobileSnb += '<li><a href="button.html">BUTTON</a></li>';
	mobileSnb += '<li><a href="tab.html">TAB</a></li>';
	mobileSnb += '<li><a href="form.html">FORM</a></li>';
	mobileSnb += '<li><a href="list.html">LIST</a></li>';
	mobileSnb += '<li><a href="table2.html">TABLE2</a></li>';
	mobileSnb += '<li><a href="alert.html">ALERT</a></li>';
	// mobileSnb += '<li><a href="ckeditor.html">Ckeditor</a></li>';
	mobileSnb += '</ul>';

	$('.g-nav-snb').append(mobileSnb);
};

/* 웹사이트 사용 script.css 연결 추가*/
var webHead = function(){
	var webHead = '';
	/*webHead += '<link rel="stylesheet" href="/static/css/basic.css">';
	webHead += '<link rel="stylesheet" href="/static/css/layout.css">';
	webHead += '<link rel="stylesheet" href="/static/css/ui.css">';*/

	$('head#website').append(webHead);
};

/* ***********************************************************
 * 네임스페이스 생성
************************************************************ */
;(function(window, $){
	'use strict';

	var global = "$utils", nameSpace = "PROMETHEUS.utils", nameSpaceRoot = null;

	function createNameSpace(identifier, module){
		var win = window, name = identifier.split('.'), p, i = 0;

		if(!!identifier){
			for (i = 0; i < name.length; i += 1){
				if(!win[ name[ i ] ]){
					if(i === 0){
						win[ name[ i ] ] = {};
						nameSpaceRoot = win[ name[ i ] ];
					} else {
						win[ name[ i ] ] = {};
					}
				}
				win = win[ name[ i ] ];
			}
		}
		if(!!module){
			for (p in module){
				if(!win[ p ]){
					win[ p ] = module[ p ];
				} else {
					throw new Error("module already exists! >> " + p);
				}
			}
		}
		return win;
	}

	if(!!window[ global ]){
		throw new Error("already exists global!> " + global);
	}

	/* ---------------------------------------------------------------------------
		namespace PROMETHEUS.utils

		* 네임스페이스 생성
		* method namespace
		* memberof PROMETHEUS.utils
		* param {Object} identifier 구분자
		* param {Object} module 네임스페이스 하위로 생성 할 객체
		* return createNameSpace
		* example

			$utils.namespace('a.b.c', {
				functionA: function(){
					console.log("call a!");
				},
				functionB: function {
					console.log("call b!");
				}
			});

			a.b.c.functionA(); // call a!
			a.b.c.functionB(); // call b!
	--------------------------------------------------------------------------- */
	window[ global ] = createNameSpace(nameSpace, {
		namespace : function(identifier, module){
			return createNameSpace(identifier, module);
		}
	});
})(window, jQuery);

var guide;
;(function(window, $) {
	'use strict';

	guide = $utils.namespace('PROMETHEUS.common', {

		/* ---------------------------------------------------------------------------------
		 * guide script 초기화
		 * @methods init
		 * @memberof PROMETHEUS.common
		--------------------------------------------------------------------------------- */

		/* ---------------------------------------------------------------------------------
			레이아웃 : navAside
		--------------------------------------------------------------------------------- */
		navAside : function(){
			var $aside = $('#g-aside');
			var $aside_area = $('#g-aside .wrap-aside');
			var $aside_snb = $aside.find('.g-nav-snb');
			var $aside_ctrl = $aside.find('.g-area-control a');

			//좌측영역 영역 디스플레이
			$(document).on("click", '#g-aside .g-area-control a', function(e) {
				e.preventDefault();
				if(!$('#g-wrap').hasClass('open-aside')) $('#g-wrap').addClass('open-aside');
				else $('#g-wrap').removeClass('open-aside');
			});

			//화면해상도 변경
			$(window).on('resize',function(e){
				chkWinSize();
			});

			function chkWinSize(){
				if($(window).width()>1400){
					$('#g-wrap').addClass('open-aside').removeClass('minwidth');
				}else{
					$('#g-wrap').removeClass('open-aside').addClass('minwidth');
				}
			}


			//snb 토글
			$(document).on('click', '.g-nav-snb a', function(e) {
				if($(this).next('ul').length){
					if(!$(this).parent('li').hasClass('open')) $(this).parent('li').addClass('open');
					else $(this).parent('li').removeClass('open');
				}else{
					$('.g-nav-snb li').removeClass('active');
					$(this).parent('li').addClass('active');
				}
			});

			$(document).on('click','.g-nav-snb a',function(){
				if ($(this).is('[data-target]')){
					var _target = $(this).attr('data-target');
					$('html, body').animate({scrollTop : $('#'+_target).offset().top} , 300);
					return false;
				}
			});

			//좌측영역 스크롤디자인
			//$aside_area.mCustomScrollbar({theme:"minimal-dark"});
		},


		/* ---------------------------------------------------------------------------------
			기본 : onload 실행함수
		--------------------------------------------------------------------------------- */
		init: function() {
			/* 기본호출함수 */
			//guide.navGnb();
			guide.navAside();
		}
	});

	$(document).ready(guide.init);

})(window, jQuery);

jQuery(document).ready(function(e) {
	$(document).on("click",".g-tab-inner > p",function(e){
		$(this).addClass("on").siblings("p").removeClass("on");
	});
});

