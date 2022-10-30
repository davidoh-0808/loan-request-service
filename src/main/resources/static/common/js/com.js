$(document).ready(function(){
	$("body").removeClass("loading"); //첫 시작시 로딩바를 숨겨준다.
})
.ajaxStart(function(){
	$("body").addClass("loading"); //ajax실행시 로딩바를 보여준다.
})
.ajaxStop(function(){
	$("body").removeClass("loading"); //ajax종료시 로딩바를 숨겨준다.
});

$(document).on('click', '.btn_top', function() { 
	if(! $('html, body').scrollTop() <= 0){
		$('html, body').animate({
			scrollTop: 0
		}, 800, 'swing')
	}
});

$(function(){
	//콤마있는 숫자필드로 변경
	$(document).on("keyup", "[data-pattern=numberComma]", function(){
		$(this).val($(this).val().replace(/[^0-9]/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	});
	$(document).on("keyup", "[data-pattern=numberCommaIncludeMinus]", function(){
		$(this).val($(this).val().replace(/[^0-9^\-]/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	});
	//숫자만 입력가능하게
	$(document).on("keyup", "[data-pattern=number]", function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
	});
	//소숫점포함숫자만 입력가능하게
	$(document).on("keyup", "[data-pattern=numberDot]", function(){
		$(this).val($(this).val().replace(/^([^.]*\.)|\.+/g, '$1').replace(/[^\d.]+/g, ''));
	});
	
	// 모바일 상단 뒤로가기버튼
	$("[data-btnBack]").on("click", function(){
		if(isEmpty($(this).attr("data-btnBack"))){
//	 		console.log("히스토리백");
			history.back();
		}else{
//	 		console.log($(this).attr("data-btnBack"));
			goMenu($(this).attr("data-btnBack"));
		}
	});
	
	// 달력 오토컴플리트 해제
	$(".datepicker").attr("autocomplete", "off");
});

/*
	밸류값 널체크
*/
var isEmpty = function(value){
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		return true
	}else{
		return false;
	}
};

var isNotEmpty = function(value){
	return !isEmpty(value);
};


/*	알럿	*/
var openPopAlert = function(message){
	$("#popMsg").text(message);
	$('.alert_msg').dialog({
		dialogClass: 'pop_alert',
		showAlertMessage: message,
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
				}
			},
		]
	});
}

/*	알럿후포커스	*/
var openPopAlertFocus = function(obj, message){
	$("#popMsg").text(message);
	$('.alert_msg').dialog({
		dialogClass: 'pop_alert',
		showAlertMessage: message,
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
					obj.focus();
				}
			},
			{
				text: "취소",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
		]
	});
}

/*	알럿후포커스(취소 버튼 없는)	*/
var openPopAlertFocusNoCancel = function(obj, message){
	$("#popMsg").text(message);
	$('.alert_msg').dialog({
		dialogClass: 'pop_alert',
		showAlertMessage: message,
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
					obj.focus();
				}
			}
		]
	});
}

/* 확인필요 알럿*/
var openPopAlertAction = function(message, callback_ok){
	$("#popMsg").text(message);
	$('.alert_msg').dialog({
		dialogClass: 'pop_alert',
		showAlertMessage: message,
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
					callback_ok();
				}
			}
		]
	});
}

/* 컨펌 알럿 */
var openPopConfirmAlert = function(message, callback_ok, callback_cancel = voidFunction){
	$("#popMsg").text(message);
	$('.alert_msg').dialog({
		dialogClass: 'pop_alert',
		showAlertMessage: message,
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function() {
					$( this ).dialog( "close" );
					callback_ok();
				}
			},
			{
				text: "취소",
				class: "btn btn_m btn_white",
				click: function() {
					$( this ).dialog( "close" );
					callback_cancel();
				}
			}
		]
	});
}

/* S:모바일알럿 */
/*	알럿	*/
var mobileOpenPopAlert = function(message){
	$("#popMsg").text(message);
	$('#pop_alert').dialog({
		dialogClass: 'pop_alert no_close',
		buttons: [
			{
				text: "확인",
				class: "btn w100",
				click: function() {
					$( this ).dialog( "close" );
				}
			},
		]
	});
}

/*	알럿후포커스	*/
var mobileOpenPopAlertFocus = function(obj, message){
	$("#popMsg").text(message);
	$('#pop_alert').dialog({
		dialogClass: 'pop_alert no_close',
		buttons: [
			{
				text: "확인",
				class: "btn w100",
				click: function() {
					$( this ).dialog( "close" );
					obj.focus();
				}
			},
		]
	});
}

/* 확인필요 알럿*/
var mobileOpenPopAlertAction = function(message, callback_ok){
	$("#popMsg").text(message);
	$('#pop_alert').dialog({
		dialogClass: 'pop_alert no_close',
		buttons: [
			{
				text: "확인",
				class: "btn w100",
				click: function() {
					$( this ).dialog( "close" );
					callback_ok();
				}
			},
		]
	});
}

/* 컨펌 알럿 */
var mobileOpenPopConfirmAlert = function(message, callback_ok, callback_cancel = voidFunction){
	$("#popMsg").text(message);
	$('#pop_alert').dialog({
		dialogClass: 'pop_alert no_close',
		buttons: [
			{
				text: "확인",
				class: "btn",
				click: function() {
					$( this ).dialog( "close" );
					callback_ok();
				}
			},
			{
				text: "취소",
				class: "btn",
				click: function() {
					$( this ).dialog( "close" );
					callback_cancel();
				}
			}
		]
	});
}
/* E:모바일알럿 */

// 펑션파람 기본값 : 아무짓도안함
var voidFunction = function(){};

/*	전화번호형식으로 보여주기 */
var showPhoneNumber = function(str){
	var result = "";
	if(!isEmpty(str)){
		result = str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
	}
	return result;
}

/**
 * 오브젝트의 콤마를 제거해서 새로운 오브젝트로 SET 한다
 * @param beforeObj 
 * @param afterObj
 * @returns
 */
function removeComma(beforeObj, afterObj) {  // 콤마제거
	if ( typeof beforeObj == "undefined" || beforeObj.val() == null || beforeObj.val() == "" ) {
		return afterObj.val("");
	}
	var txtNumber = '' + beforeObj.val();
	return afterObj.val(txtNumber.replace(/(,)/g, ""));
}

/**
 * 오브젝트의 콤마를 제거해서 새로운 오브젝트로 SET 한다
 * @param beforeObj 
 * @param afterObj
 * @returns
 */
function addCommaObj(beforeObj, afterObj) {  // 콤마제거
	if ( typeof beforeObj == "undefined" || beforeObj.val() == null || beforeObj.val() == "" ) {
		return afterObj.val("");
	}
	var beforStr = beforeObj.val().replace(/,/g, '')
	
	return afterObj.val(addCommas(beforStr));
}

/*	콤마를 추가한다	*/
function addCommas(num) {
	var len, point, str;
	var minchk = false;
	
	num = num + "";
	
	if(num.indexOf('-') != -1) minchk = true;
	
	num = num.replace(/,/g, '').replace(/-/g, '');
	point = num.length % 3;
	len = num.length;
	
	str = num.substring(0, point);
	while(point < len) {
		if(str != '') str += ',';
		str += num.substring(point, point + 3);
		point += 3;
	}
	
	if(minchk) str = '-' + str;
	
	return str;
}

/* 영문(소문자) 및 숫자 */
function fnOnlyEng(obj) {
	$(obj).keyup(function() {
		$(this).val($(this).val().replace(/[^.a-z0-9]/gi, "").toLowerCase());
	});
}

/* 영문,숫자,특수문자 */	
function fnOnlyPwd(obj) {
	$(obj).keyup(function() {
	
		// 허용하지 않는 문자 발생시 알럿
		var pwdReg = /[^.A-Za-z0-9~!@#$%^*()]/gi;
		if($(this).val().match(pwdReg) == null) {
			return false;
		}
		
		mobileOpenPopAlert("허용하지 않는 문자입니다");
		
		$(this).val($(this).val().replace(/[^.A-Za-z0-9~!@#$%^*()]/gi, ""));
	});
}

/* 비밀번호 정규식 체크 */
function checkPassword(pwd) {
	var pwReg = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^*()]).{8,16}$/;
	var checkNum = pwd.search(/[0-9]/g);
	var checkAlp = pwd.search(/[a-z]/ig);
	
	if(pwd.match(pwReg) == null) {
		return false;
	}
	if(pwd.length < 8 || 16 < pwd.length) {
		return false;
	}
	if(checkNum < 0 || checkAlp < 0) {
		return false;
	}
	return true;
}

/* 아이디 정규식 체크 */
function checkId(id) {
	var idReg = /^(?=.*[a-z]).{5,20}$/;
	var checkAlp = id.search(/[a-z]/ig);
	
	if(id.match(idReg) == null) {
		return false;
	}
	if(id.length < 5 || 20 < id.length) {
		return false;
	}
	return true;
}	

// 메뉴이동
function goMenu(url){
	$("form[name=frm_menu]").attr("action", url);
	$("form[name=frm_menu]").attr("method", "GET");
	$("form[name=frm_menu]").submit();
}

// str(숫자만있는형식 20210501) 날짜포맷으로변경
function fnGetDateFormat(strDate){
	var result = "";
	if(!isEmpty(strDate)){
		strDate = strDate.replace(/[^.a-z0-9]/gi, "").toLowerCase();
		
		var yyyy = strDate.substring(0,4);
		var mm = strDate.substring(4,6);
		var dd = strDate.substring(6,8);
		
		result = yyyy + "." + mm + "." + dd;
	}
	return result;
}

// str(숫자만있는형식 20210501) 요일 구하기
function fnGetWeek(strDate){
	var result = "";
	if(!isEmpty(strDate)){
	
		strDate = strDate.replace(/[^.a-z0-9]/gi, "").toLowerCase();
		
		var yyyy = strDate.substring(0,4);
		var mm = strDate.substring(4,6);
		var dd = strDate.substring(6,8);
	
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		result = week[new Date(yyyy, mm-1, dd).getDay()];
	}
	return result;
}

// 파일다운로드(서버에서검증필요없는것)
function fileDownloadPublic(FILE_SEQ, UPLOAD_GROUP){
	$("form[name=frm_fileDownload] input[name=FILE_SEQ]").val(FILE_SEQ);
	$("form[name=frm_fileDownload]").attr("action", "/public/fileDownload/" + UPLOAD_GROUP);
	$("form[name=frm_fileDownload]").submit();
}

// 파일다운로드멤버(서버에서검증필요한것)
function fileDownloadMember(FILE_SEQ, UPLOAD_GROUP){
	$("form[name=frm_fileDownload] input[name=FILE_SEQ]").val(FILE_SEQ);
	$("form[name=frm_fileDownload]").attr("action", "/member/fileDownload/" + UPLOAD_GROUP);
	$("form[name=frm_fileDownload]").submit();
}

// 파일다운로드어드민(서버에서검증필요한것)
function fileDownloadAdmin(FILE_SEQ, UPLOAD_GROUP){
	$("form[name=frm_fileDownload] input[name=FILE_SEQ]").val(FILE_SEQ);
	$("form[name=frm_fileDownload]").attr("action", "/admin/fileDownload/" + UPLOAD_GROUP);
	$("form[name=frm_fileDownload]").submit();
}

// 서버에 올려논 파일 양식샘플같은
function fileDownloadCommon(UPLOAD_GROUP, ORG_FILE_NAME, SYS_FILE_NAME){
	$("form[name=frm_fileDownload] input[name=ORG_FILE_NAME]").val(ORG_FILE_NAME);
	$("form[name=frm_fileDownload] input[name=SYS_FILE_NAME]").val(SYS_FILE_NAME);
	$("form[name=frm_fileDownload]").attr("action", "/common/fileDownload/" + UPLOAD_GROUP);
	$("form[name=frm_fileDownload]").submit();
}

// 숫자형으로 변환해서 숫자값을 넘겨준다
function fnStringToNumber(str) {
	var result = 0;

	try{
		if(isEmpty(str.trim())){
			return result;
		}
		
		result = Number(str.trim().replace(/,/g, ''));
		
	}catch(e){
		console.log("E:"+ e);
		return result;
	}
	return result;
	
}

function getNowDate(gbn){
	var today = new Date();
	
	var year = today.getFullYear(); // 년도
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	var day = today.getDay();  // 요일
	return year + gbn + month + gbn + date;
}
