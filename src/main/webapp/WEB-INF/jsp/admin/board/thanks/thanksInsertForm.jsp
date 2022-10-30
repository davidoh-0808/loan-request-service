<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>


<script src="/resources/common/js/jquery.form.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>

	<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.insert.confirm"/></c:set>
	<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>
	
	<c:set var="messageValidationEmpty_TITLE"><spring:message code="validation.empty.input" arguments="제목을"/></c:set>
	<c:set var="messageValidationEmpty_CONTENT"><spring:message code="validation.empty.input" arguments="내용을"/></c:set>
	
	<!-- 프로퍼티메세지 -->
</head>


<body>
<!--wrap -->
<div class="admin_wrap">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/admin/header.jsp" %>
	<!-- header -->
	
	<!-- 관리자 히단 -->
	<div class="admin_bottom">
		
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/common/include/admin/lnb.jsp" %>
		<!-- // lnb -->
		
		<!--content-->
		<div class="content">
			<h2 class="con_tit">고마워요미소금융 관리</h2>
			<h3 class="s_tit">고마워요미소금융 등록</h3>

			<div class="tb_style tb_htype sp10">
			<form name="frm_editor" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="CONTENT" />
				<table>
					<caption>고마워요미소금융 등록</caption>
					<colgroup>
						<col style="width: 138px;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">제목</th>
							<td>
								<input type="text" class="input_text w_100" title="제목입력" name="TITLE">
							</td>
						</tr>
						<tr>
							<th scope="row">썸네일</th>
							<td>
								<div class="filebox"> 
									<input class="upload_name input_text" value="" disabled="disabled"> 
									<label for="ex_filename" class="btn btn_s btn_black"><span>파일찾기</span></label> 
									<input type="file" id="ex_filename" class="upload-hidden" name="BOARD_FILE"> 
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">노출내용</th>
							<td>
								<div class="memo_area w_100" style="height:90px">
									<textarea name="VIEW_CONTENT"></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td>
								<div class="memo_area w_100">
									<textarea id="ckeditor_write" style="resize: none;"></textarea>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			</div>	

			<div class="btn_area sp30">
				<div class="center_a">
					<button type="button" class="btn btn_blue btn_l" data-btn="btnSave">완료</button>
					<button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnCancel">취소</button>
				</div>
			</div>
		
		
		</div>
		<!--//content-->

	</div>
	<!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/admin/footer.jsp" %>
<!-- footer -->
<script>

// 에디터저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CKeditor
CKEDITOR.replace('ckeditor_write', {
	height: 130, //editor height 높이 지정
	resize_enabled: false //editor 크기조정 삭제
});

//목록가기
function thanksList(){
	$("form[name=frm_editor]").attr("action", "/admin/board/thanks/thanksList");
	$("form[name=frm_editor] input[name=CONTENT]").val("");
	$("form[name=frm_editor]").submit();
}

//저장
$("[data-btn=btnSave]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", thanksInsert);
});

// 취소
$("[data-btn=btnCancel]").on("click", function() {
	openPopConfirmAlert("${messageSaveCancelConfirm}", thanksList);
});


//파일 업로드
var fileTarget = $('.filebox .upload-hidden');

fileTarget.on('change', function(){ // 값이 변경되면 
	if(window.FileReader){ // modern browser 
		var filename = $(this)[0].files[0].name; 
	} 
	else { // old IE 
		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	} 
	// 추출한 파일명 삽입 
	$(this).siblings('.upload_name').val(filename); 
});


// 등록
function thanksInsert(){
	// S: 유효성검사
	if(isEmpty($("form[name=frm_editor] input[name=TITLE]").val())){
		openPopAlertFocus($("form[name=frm_editor] input[name=TITLE]"), "${messageValidationEmpty_TITLE}");
		return;
	}
	
	// CKeditor
	var txt = CKEDITOR.instances.ckeditor_write.getData();
	if(isEmpty(txt)){
		openPopAlertFocus($("#ckeditor_write"), "${messageValidationEmpty_CONTENT}");
		return;
	}
	$("form[name=frm_editor] input[name=CONTENT]").val(txt);
	
	// E: 유효성검사
	
	$("form[name=frm_editor]").ajaxSubmit({
		type:"POST"
		, url: "/admin/board/thanks/thanksInsert"
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", thanksList, null);
			} else {
				openPopAlertAction(data.rMsg);
			}
		}
		,error : function(xhr,status,error) {
			if(xhr.status == 401) {
				openPopAlert("${errorXhr401}");
			} else if(xhr.status == 403) {
				openPopAlert("${errorXhr403}");
			} else if(xhr.status == 500) {
				openPopAlert("${errorXhr500}");
			} else {
				openPopAlert("${errorXhrElse}");
			}
		},
	});
}
// 에디터저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



</script>
</body>
</html>