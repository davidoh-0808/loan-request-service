<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

</head>

<body>
	<!--wrap -->
	<div class="admin_wrap">
		<!-- 관리자 상단 -->
		<%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
		<!-- // 관리자 상단 -->

		<!-- 관리자 히단 -->
		<div class="admin_bottom">
			<!-- lnb_지점/심사팀 -->
			<%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
			<!-- // lnb -->

			<!--content-->
			<div class="content">
				<h2 class="con_tit">접속현황</h2>

				<!-- 조회를 위한 form -->
				<form name="frm">
				<!-- csrf 및 페이징 -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
				<input type="hidden" name="pageNum" value="${paramVO.pageNum }"/>

				<div class="tb_style tb_htype sp10">
					<table>
						<caption>접속현황 검색</caption>
						<colgroup>
							<col style="width: 138px;">
							<col style="width: auto;">
							<col style="width: 138px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">성명</th>
								<td>
									<input name="CUST_NM" type="text" class="input_text w_100">
								</td>
								<th scope="row">사업자 번호</th>
								<td>
									<input name="CUST_CORP_NO" type="text" class="input_text w_100" onkeydown="checkSearchedWord(this)" maxlength="13" oninput="maxLengthCheck(this)" placeholder="- 제외">
								</td>
							</tr>
							<tr>
								<th scope="row">인증수단</th>
								<td colspan="3">
									<div class="selbox_form">
										<select name="CERTI_MTHD_TP" class="selbox">
											<option value>전체</option>
											<c:forEach var="code" items="${certiMthdTP}">
												<option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
											</c:forEach>
										</select>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				</form>
				<!-- 조회를 위한 form -->

				<div class="btn_area sp10">
					<div class="right_a">
						<button type="button" class="btn btn_white btn_m" data-btn="btnInit">초기화</button>
						<button type="button" class="btn btn_black btn_m" data-btn="btnSearch">검색</button>
					</div>
				</div>

				<div class="info sp40 cellbox">
					<div class="left cell auto">
						<p class="total">총 <span>${totCnt}</span>건</p>
					</div>
				</div>
				<div class="tb_style tb_vtype sp10">
					<table>
						<caption>상담원 리스트</caption>
						<colgroup>
							<col style="width: 48px;">
							<col style="width: 80px;">
							<col style="width: 80px;">
							<col style="width: 80px;">
							<col style="width: 80px;">
							<col style="width: 120px;">
							<col style="width: 120px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">NO</th>
								<th scope="col">성명</th>
								<th scope="col">주민등록번호</th>
								<th scope="col">성별</th>
								<th scope="col">내/외국인</th>
								<th scope="col">사업자번호</th>
								<th scope="col">인증수단</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty resultList}">
									<tr>
										<td class="empty" colspan="7">
											<div class="empty_list">
												<p>검색 결과가 존재하지 않습니다.</p>
											</div>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="result" items="${resultList}">
										<tr>
											<td>${result.ROW_NUM}</td>
											<td>${result.CUST_NM}</td>
											<td>${result.CUST_REGI_NO}</td>
											<td>${result.GENDER}</td>
											<td>${result.FRN_CHK}</td>
											<td>${result.CUST_CORP_NO}</td>
											<td>${result.CERTI_MTHD_TP_NAME}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>

				<!-- //페이징 -->
				<%@ include file="/WEB-INF/jsp/common/include/consult/paging.jsp" %>
				<!-- //페이징 -->
			</div>
			<!--//content-->

		</div>
		<!-- //관리자 하단 -->

	</div>
	<!-- //Wrap-->
</body>

<script>
	//페이지 이동
	function goPage(pageNum) {
		$("form[name=frm] input[name=pageNum]").val(pageNum);
		$("form[name=frm]").attr("method", "GET");
		$("form[name=frm]").attr("action", "/consult/consult/connList");
		$("form[name=frm]").submit();
	}
	//조회폼 초기화
	$("[data-btn=btnInit]").on("click", function () {
		$("form[name=frm] input[name=status]").val("");
		$("form[name=frm] input[name=customer]").val("");
		$("form[name=frm] input[name=searchStartDt]").val("");
		$("form[name=frm] input[name=searchEndDt]").val("");
		goPage(1);
	});


	//특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
	function checkSearchedWord(obj){
		if(obj.length >0){
			//특수문자 제거
			var expText = /[%=><]/ ;
			if(expText.test(obj) == true){
				alert("특수문자를 입력 할수 없습니다.") ;
				obj = obj.split(expText).join("");
				return false;
			}

			//특정문자열(sql예약어의 앞뒤공백포함) 제거
			var sqlArray = new Array(
					//sql 예약어
					"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
					"UNION",  "FETCH", "DECLARE", "TRUNCATE"
			);

			var regex;
			for(var i=0; i<sqlArray.length; i++){
				regex = new RegExp( sqlArray[i] ,"gi") ;

				if (regex.test(obj) ) {
					alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
					obj =obj.replace(regex, "");
					return false;
				}
			}
		}
		return true ;
	}

	//조회
	$("[data-btn=btnSearch]").on("click", function() {
		// 적용예 (api 호출 전에 검색어 체크)

		goPage(1);
	});

	//input 의 자릿수 설정
	function maxLengthCheck(object) {
		if (object.value.length > object.max.length){
			object.value = object.value.slice(0, object.maxLength);
		}
	}

</script>

</html>