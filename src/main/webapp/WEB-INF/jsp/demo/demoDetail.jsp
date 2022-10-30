<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %> 
	
	<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.delete.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.delete.success"/></c:set>
	<!-- 프로퍼티메세지 -->
	
</head>

<body>

<c:set var="menu_depth_1" value="01"></c:set>
<c:set var="menu_depth_2" value="01"></c:set>

<div id="wrap">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->
	
	<div id="container">
		<div>
			<h2>데모상세</h2>
			<div>
				<table>
					<caption>리스트</caption>
					<colgroup>
						<col width="15%"/>
						<col width="*"/>
						<col width="15%"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th>코드</th>
							<td>${result.DEMO_CODE }</td>
							</td>
							<th>타이틀</th>
							<td>${result.DEMO_TITLE }</td>
						</tr>
						<tr>
							<th>컨텐츠</th>
							<td colspan="3">${result.DEMO_CONTENTS }</td>
							</td>
						</tr>
						<tr>
							<th>등록자</th>
							<td>${result.IN_USER_NAME }</td>
							<th>등록일시</th>
							<td>
								<fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
								<span/></span>
							</td>
						</tr>
						<tr>
							<th>수정자</th>
							<td>${result.UP_USER_NAME }</td>
							<th>최종 수정일시</th>
							<td>
								<fmt:formatDate value="${result.UP_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
								<span/></span>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3">
								<c:choose>
									<c:when test="${empty resultFileList }">
										첨부파일 없음 
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${resultFileList }">
											<a href="javascript:fileDownloadPublic('${item.FILE_SEQ }', '${item.UPLOAD_GROUP }');" class="item">${item.ORG_FILE_NAME }(${item.FILE_SIZE }KB)</a>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div>
				<button type="button" data-btn="btnUpdate">수정</button>
				<button type="button" data-btn="btnDelete">삭제</button>
				<button type="button" data-btn="btnList">목록</button>
			</div>

		</div>	<!--.inner-->
	</div>	<!--#container-->
</div>	<!--#wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

<form name="frm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="searchValue" value="${paramVO.searchValue }" />
	<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
	<input type="hidden" name="DEMO_CODE" value="${paramVO.DEMO_CODE }" />
</form>	

<script>
//수정 페이지 이동
$("[data-btn=btnUpdate]").on("click", function() {
	$("form[name=frm]").attr("action", "/demo/demoUpdateForm");
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").submit();	
});

//목록페이지 이동
$("[data-btn=btnList]").on("click", function() {
	demoList();
});

//삭제
$("[data-btn=btnDelete]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", demoDelete, null);
});

function demoList(){
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/demo/demoList")
	$("form[name=frm]").submit();
}

function demoDelete() {
	$.ajax({
		type:"DELETE"
		,url: "/demo/demoDelete"
		,data: $("form[name=frm]").serialize()
		,dataType:"json"
		,success : function(responseData){
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
</script>
</body>
</html>
