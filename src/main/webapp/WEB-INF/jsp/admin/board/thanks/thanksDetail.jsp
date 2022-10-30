<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>


<script src="/resources/common/js/jquery.form.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>

<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.delete.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.delete.success"/></c:set>
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
			<h3 class="s_tit">고마워요미소금융 상세</h3>

			<div class="tb_style tb_htype sp10">
				<table>
					<caption>고마워요미소금융 상세</caption>
					<colgroup>
						<col style="width: 138px;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">작성자</th>
							<td>
								${result.IN_USER_NAME } 
								<ul class="board_info">
									<li><span class="date">등록일</span> <span>
									${result.IN_DTTM}
									<li><span class="count">조회</span> <span>${result.VIEW_CNT }</span></li>
								</ul>
							</td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td>
								<span>${result.TITLE }</span>
							</td>
						</tr>
						<tr>
							<th scope="row">썸네일</th>
							<td>
								<c:choose>
									<c:when test="${empty resultFileList }">
										첨부파일 없음 
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${resultFileList }">
											<img src="/external${item.UPLOAD_PATH}/${item.SYS_FILE_NAME}">
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th scope="row">노출내용</th>
							<td>
								<div class="memo_con" style="min-height:90px">
									${result.VIEW_CONTENT}
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="memo_con">
									${result.CONTENT }
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	

			<div class="btn_area sp30">
				<div class="left_a">
					<button type="button" class="btn btn_gray btn_l" data-btn="btnList">목록</button>
				</div>
				<div class="center_a">
					<button type="button" class="btn btn_blue btn_l" data-btn="btnUpdate">수정</button>
					<button type="button" class="btn btn_white btn_l pop_btn btn_delete" data-btn="btnDelete">삭제</button>
				</div>
			</div>
			
		
		</div>
		<!--//content-->

	</div>
	<!-- //관리자 하단 -->

</div>
<!-- //Wrap-->


<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/member/footer.jsp" %>
<!-- footer -->

<form name="frm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="searchValue" value="${paramVO.searchValue }" />
	<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
	<input type="hidden" name="BOARD_CODE" value="${paramVO.BOARD_CODE }" />
</form>	

<script>


//목록가기
function thanksList(){
	$("form[name=frm]").attr("action", "/admin/board/thanks/thanksList");
	$("form[name=frm]").submit();
}

//수정 페이지 이동
$("[data-btn=btnUpdate]").on("click", function() {
	$("form[name=frm]").attr("action", "/admin/board/thanks/thanksUpdateForm");
	$("form[name=frm]").submit();	
});

//목록페이지 이동
$("[data-btn=btnList]").on("click", function() {
	thanksList();
});

//삭제
$("[data-btn=btnDelete]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", thanksDelete, null);
});

function thanksDelete() {
	$.ajax({
		type:"POST"
		,url: "/admin/board/thanks/thanksDelete"
		,data: $("form[name=frm]").serialize()
		,dataType:"json"
		,success : function(responseData){
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
</script>
</body>
</html>
