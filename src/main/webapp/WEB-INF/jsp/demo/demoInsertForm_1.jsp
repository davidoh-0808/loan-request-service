<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %> 

<script src="/resources/common/js/jquery.form.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>

	<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.insert.confirm"/></c:set>
	<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>
	
	<c:set var="messageValidationEmpty_DEMO_TITLE"><spring:message code="validation.empty.input" arguments="타이틀을"/></c:set>
	<c:set var="messageValidationEmpty_DEMO_CONTENTS"><spring:message code="validation.empty.input" arguments="내용을"/></c:set>
	
	<!-- 프로퍼티메세지 -->

</head>

<body>

<c:set var="menu_depth_1" value="01"></c:set>
<c:set var="menu_depth_2" value="01"></c:set>

<div id="wrap" class="wrap_admin">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<div id="container">
		<div>
			<h2>데모등록</h2>
			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				
				<!-- input 세로형 테이블 -->
				<div class="table_list align_left">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="15%"/>
							<col width="*"/>
							<col width="15%"/>
							<col width="*"/>
						</colgroup>
						<tbody>
							<tr>
								<th><span>타이틀</span></th>
								<td colspan="3"><input type="text"name="DEMO_TITLE" maxlength="100"></td>
							</tr>
							<tr>
								<th><span>컨텐츠</span></th>
								<td colspan="3">
									<textarea name="DEMO_CONTENTS"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<div>
				<button type="button" class="btn btn_white btn_l pop_btn" data-btn="btnSave">완료</button>
				<button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnCancel">취소</button>
			</div>

		</div>	<!--.inner-->

<hr style="border: solid 1px red;">
<hr style="border: solid 2px blue;">

		<div>
			<h2>데모등록(파일)</h2>
			<form name="frm_file" enctype="multipart/form-data" >
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				
				<!-- input 세로형 테이블 -->
				<div class="table_list align_left">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="15%"/>
							<col width="*"/>
							<col width="15%"/>
							<col width="*"/>
						</colgroup>
						<tbody>
							<tr>
								<th><span>타이틀</span></th>
								<td colspan="3"><input type="text"name="DEMO_TITLE" maxlength="100"></td>
							</tr>
							<tr>
								<th><span>컨텐츠</span></th>
								<td colspan="3">
									<textarea name="DEMO_CONTENTS"></textarea>
								</td>
							</tr>
							<tr>
								<th><span>파일첨부</span></th>
								<td colspan="3">
									<input type="file" name="DEMO_FILE">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<div>
				<button type="button" class="btn btn_white btn_l pop_btn" data-btn="btnSave2">완료</button>
				<button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnCancel2">취소</button>
			</div>

		</div>	<!--.inner-->
		
<hr style="border: solid 1px red;">
<hr style="border: solid 2px blue;">
		
		<div>
			<h2>데모등록(에디터)</h2>
			<form name="frm_editor">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="DEMO_CONTENTS" />
				
				<!-- input 세로형 테이블 -->
				<div class="table_list align_left">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="15%"/>
							<col width="*"/>
							<col width="15%"/>
							<col width="*"/>
						</colgroup>
						<tbody>
							<tr>
								<th><span>타이틀</span></th>
								<td colspan="3"><input type="text"name="DEMO_TITLE" maxlength="100"></td>
							</tr>
							<tr>
								<th><span>컨텐츠</span></th>
								<td colspan="3">
									<textarea id="ckeditor_write"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<div>
				<button type="button" class="btn btn_white btn_l pop_btn" data-btn="btnSave3">완료</button>
				<button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnCancel3">취소</button>
			</div>

		</div>	<!--.inner-->
		
	</div>	<!--#container-->
</div>	<!--#wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

<script>

//저장
$("[data-btn=btnSave]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", demoInsert);
});

// 취소
$("[data-btn=btnCancel]").on("click", function() {
	openPopConfirmAlert("${messageSaveCancelConfirm}", demoList);
});

// 등록
function demoInsert(){
	// S: 유효성검사
	if(isEmpty($("form[name=frm] input[name=DEMO_TITLE]").val())){
		openPopAlertFocus($("form[name=frm] input[name=DEMO_TITLE]"), "${messageValidationEmpty_DEMO_TITLE}");
		return;
	}
	// E: 유효성검사
	
	$.ajax({
		type:"POST"
		, url: "/demo/demoInsert"
		, data: $("form[name=frm]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", demoList, null);
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

// 목록가기
function demoList(){
	$("form[name=frm]").attr("action", "/demo/demoList");
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").submit();
}

// 파일업로드저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//저장
$("[data-btn=btnSave2]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", demoInsert2);
});

//취소
$("[data-btn=btnCancel2]").on("click", function() {
	openPopConfirmAlert("${messageSaveCancelConfirm}", demoList);
});
// 등록
function demoInsert2(){
	// S: 유효성검사
	if(isEmpty($("form[name=frm_file] input[name=DEMO_TITLE]").val())){
		openPopAlertFocus($("form[name=frm_file] input[name=DEMO_TITLE]"), "${messageValidationEmpty_DEMO_TITLE}");
		return;
	}
	// E: 유효성검사
	
	$("form[name=frm_file]").ajaxSubmit({
		type:"POST"
		, url: "/demo/demoInsert2"
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", demoList, null);
			} else {
				openPopAlertAction(data.rMsg);
			}
		}
		, error : function(xhr,status,error) {
			if(xhr.status == 401) {
				openPopAlert("${errorXhr401}");
			} else if(xhr.status == 403) {
				openPopAlert("${errorXhr403}");
			} else if(xhr.status == 500) {
				openPopAlert("${errorXhr500}");
			} else {
				openPopAlert("${errorXhrElse}");
			}
		}
	});
}

// 파일업로드저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// 에디터저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CKeditor
CKEDITOR.replace('ckeditor_write', {
	height: 300, //editor height 높이 지정
	resize_enabled: false //editor 크기조정 삭제
});

//저장
$("[data-btn=btnSave3]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", demoInsert3);
});

// 취소
$("[data-btn=btnCancel3]").on("click", function() {
	openPopConfirmAlert("${messageSaveCancelConfirm}", demoList);
});

// 등록
function demoInsert3(){
	// S: 유효성검사
	if(isEmpty($("form[name=frm_editor] input[name=DEMO_TITLE]").val())){
		openPopAlertFocus($("form[name=frm_editor] input[name=DEMO_TITLE]"), "${messageValidationEmpty_DEMO_TITLE}");
		return;
	}
	
	// CKeditor
	var txt = CKEDITOR.instances.ckeditor_write.getData();
	if(isEmpty(txt)){
		openPopAlertFocus($("#ckeditor_write"), "${messageValidationEmpty_DEMO_CONTENTS}");
		return;
	}
	$("form[name=frm_editor] input[name=DEMO_CONTENTS]").val(txt);
	
	// E: 유효성검사
	
	$.ajax({
		type:"POST"
		, url: "/demo/demoInsert"
		, data: $("form[name=frm_editor]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", demoList, null);
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
