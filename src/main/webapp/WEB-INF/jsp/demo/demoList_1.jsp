<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

<c:set var="messageSaveConfirm"><spring:message code="message.insert.confirm"/></c:set>

</head>

<body>

<c:set var="menu_depth_1" value="01"></c:set>
<c:set var="menu_depth_2" value="01"></c:set>

<div id="wrap">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->
	
	<a href="#" data-lang="ko" >한국어</a>
	<a href="#" data-lang="en" >English</a>

	<div id="container">
		<div>
			<h2>데모리스트</h2>
			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
				<input type="hidden" name="DEMO_CODE"/>
				
				<!-- input 세로형 테이블 -->
				<div>
					<table>
						<caption>리스트(${messageSaveConfirm})</caption>
						<colgroup>
							<col width="15%">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th>키워드</th>
							<td>
								<input type="text" name="searchValue" value="${paramVO.searchValue }">
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<div>
				<button type="button" data-btn="btnSearch">조회</button>
				<button type="button" data-btn="btnInit">초기화</button>
			</div>

			<div>
				<span>목록</span>
				<small>총 ${totCount} 건</small>
				<div>
					<button type="button" data-btn="btnInsert">등록하기</button>
				</div>
			</div>

			<div>
				<table>
					<caption>리스트</caption>
					<colgroup>
						<col width="5%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col>
						<col>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>순번</th>
							<th>코드</th>
							<th>타이틀</th>
							<th>컨텐츠</th>
							<th>등록일</th>
							<th>수정자(ID)</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty resultList }">
								<tr>
									<td colspan="7">해당 키워드로 검색된 결과가 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${resultList }" var="list">
									<tr data-btn="detailInfo" data-detailCode="${list.DEMO_CODE }">
										<td>${list.ROW_NUM }</td>
										<td>${list.DEMO_CODE }</td>
										<td>${list.DEMO_TITLE }</td>
										<td>${list.DEMO_CONTENTS }</td>
										<td>
											<fmt:formatDate value="${list.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
										</td>
										<td>${list.UP_USER_NAME}</td>
										<td>
											<fmt:formatDate value="${list.UP_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<!-- paging -->
			<%@include file="/WEB-INF/jsp/common/include/front/paging.jsp" %>
			<!-- paging -->
			
		</div>	<!--.inner-->
	</div>	<!--#container-->
</div>	<!--#wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

<!-- 등록폼이동 -->
<form name="frm_form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<!-- 언어설정 -->
<form name="frm_locale">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="lang" />
</form>

<script>

// 조회폼 초기화
$("[data-btn=btnInit]").on("click", function() {
	$("form[name=frm] input[name=searchValue]").val("");
	goPage(1);
});

// 조회
$("[data-btn=btnSearch]").on("click", function() {
	goPage(1);
});

// 등록폼이동
$("[data-btn=btnInsert]").on("click", function() {
	$("form[name=frm_form]").attr("action", "/demo/demoInsertForm")
	$("form[name=frm_form]").attr("method", "GET")
	$("form[name=frm_form]").submit();
});

function goPage(pageNum){
	$("form[name=frm] input[name=pageNum]").val(pageNum);
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/demo/demoList")
	$("form[name=frm]").submit();
}

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=DEMO_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/demo/demoDetail");
	$("form[name=frm]").submit();
});


$("[data-lang]").on("click", function(){

	$("form[name=frm_locale] input[name=lang]").val($(this).data("lang"));

	$.ajax({
		type:"PUT"
		,url: "/common/localeChange"
		,data: $("form[name=frm_locale]").serialize()
		,dataType:"json"
		,success : function(responseData){
			location.reload();
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
});
</script>
</body>
</html>
