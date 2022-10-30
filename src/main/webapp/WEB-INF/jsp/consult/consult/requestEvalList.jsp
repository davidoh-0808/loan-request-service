<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<c:set var="messageAssignConsultantConfirm"><spring:message code="message.assignConsultant.confirm"/></c:set>
<c:set var="messageAssignConsultantSuccess"><spring:message code="message.assignConsultant.success"/></c:set>
<c:set var="validationSearchReg"><spring:message code="validation.search.reg"/></c:set>
<c:set var="validationDateReg"><spring:message code="validation.date.reg"/></c:set>


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
        
         <!-- 세션 정보의 유저권한 체크 -->
        <c:set var="MEMBER_AUTHORITY" value="${MEMBER_AUTHORITY}"/>
        <!--content-->
        <div class="content">
            <h2 class="con_tit">신청내역조회/수정</h2>
           	<h3 class="s_tit">신청내역조회/수정</h3>

            <!-- 조회를 위한 form -->
            <form name="frm">
                <!-- csrf 및 페이징 -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
                <input type="hidden" name="pageNum" value="${paramVO.pageNum }"/>
                <input type="hidden" name="CONS_SEQ" value="${paramVO.CONS_SEQ}"/>
                <input type="hidden" name="checkedConsSeqArr" />
				<input type="hidden" name="orderValue" value="${paramVO.orderValue}"/>
				<input type="hidden" name="orderName" value="${paramVO.orderName}"/>
                <!-- csrf 및 페이징 -->

                <div class="tb_style tb_htype sp10">
                    <table>
                        <caption>신청내역조회/수정 테이블</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">고객명</th>
                            <td>
                                <input name="customer" type="text" class="input_text w_100" maxlength="20" oninput="maxLengthCheck(this)">
                            </td>
                            <th scope="row">연락처</th>
                            <td>
                                <input name="phoneNumber" type="text" class="input_text w_100" maxlength="11" oninput="maxLengthCheck(this)" placeholder="- 를 제외한 숫자를 입력해주세요">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">진행상태</th>
                            <td>
                                <select id="status" name="status" class="selbox w_100">
                                    <option value="">전체</option>
                                    <c:forEach var="status" items="${statuses}">
                                       	<option value="${status.MASTER_CODE}">${status.CODE_NAME}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th scope="row">접수일자</th>
                            <td>
                                <div class="cal_wrap">
                                    <input name="searchStartDt" type="text" class="input_text md datepicker"> ~
                                    <input name="searchEndDt" type="text" class="input_text md datepicker">
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
                <div class="left cell auto bt">
                    <ul class="list">
						<li><p class="total">접수 <span id="submitCnt">${statsCnt.SUBMIT_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
						<li><p class="total">진행 중(대기) <span id="standbyCnt">${statsCnt.STANDBY_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
						<li><p class="total">진행 중 <span id="progressCnt">${statsCnt.PROGRESS_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
						<li><p class="total">부재/보류 <span id="delayCnt">${statsCnt.DELAY_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
						<li><p class="total">취소 <span id="cancelCnt">${statsCnt.CANCEL_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
						<li><p class="total">완료 <span id="completeCnt">${statsCnt.COMPLETE_CNT}<c:if test="${empty statsCnt.TOTAL_CNT}">0</c:if></span>건</p></li>
                    </ul>
                </div>

				<div class="right cell">
					<c:if test="${not empty statsCnt.TOTAL_CNT}"><button type="button" class="btn btn_white btn_m" data-btn="btnAssign">상담자 배정</button></c:if>
					<button type="button" class="btn btn_blue btn_m" data-btn="btnInsert">등록</button>
				</div>

            </div>

            <div class="tb_style tb_vtype sp10 table_scroll">
	            <c:choose>
					<c:when test="${empty resultList}">
						<%--<tr>
							<td colspan="5">불러올 상담 신청 내역이 없습니다.</td>
						</tr>--%>
						<div class="empty_list sp10">
							<p>검색 결과가 존재하지 않습니다.</p>
						</div>
					</c:when>
					<c:otherwise>
						<%-- 지점(상담자) 권한 --%>
						<table>
							<caption>신청내역 목록</caption>
							<colgroup>
								<col style="width: 48px;">
								<col style="width: 48px;">
								<col style="width: 90px;">
								<col style="width: 90px;">
								<col style="width: 80px;">
								<col style="width: 90px;">
								<col style="width: 80px;">
								<col style="width: 80px;">
								<col style="width: 140px;">
								<col style="width: 80px">
								<col style="width: 90px;">
								<col style="width: 110px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<!-- <col style="width: 80px;"> -->
								<!-- <col style="width: 80px;"> -->
								<col style="width: 110px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><input id="check_all" class="input_check blank" type="checkbox"></th>
									<th scope="col">번호</th>
									<th scope="col"><span class="sort">접수일<a id="IN_DTTM" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">상담일<a id="CONS_DTTM" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">상담자<a id="CONS_MB_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">지점<a id="BRANCH_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">게시자<a id="BRANCH_MB_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">고객명<a id="CUST_NM" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">주민등록번호<a id="CUST_REGI_NO" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">조회동의<a id="INQU_CONS" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">녹취시간<a id="RECORD_TIME" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">유입경로(1)<a id="INFLOW_ROUTE1" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">유입경로(2)<a id="INFLOW_ROUTE2" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">상품명<a id="PRODUCT_TYPE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">업종<a id="TYPE_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">사업년수<a id="CORP_HIS" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">채무조정<a id="DEBT_SETT" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">처리현황<a id="STATS_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">완료일<a id="COMP_DATE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">상담결과<a id="EVAL_RESULT_CODE" href="#" class="icon_sort"></a></span></th>
									<th scope="col"><span class="sort">거절사유<a id="DECLINE_REASON_CODE" href="#" class="icon_sort"></a></span></th>
									<!-- <th scope="col">비고</th> -->
									<!-- <th scope="col">기타</th> -->
									<th scope="col"><span class="sort">연락처<a id="CUST_HP_NO" href="#" class="icon_sort"></a></span></th>
								</tr>
							</thead>
							<tbody id="listContent">
							<%@ include file="/WEB-INF/jsp/consult/consult/innerJsp/innerEvalList.jsp" %>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
                
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

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/admin/footer.jsp" %>
<!-- footer -->

<script>
	// ********************************* 컬럼필터 ORDER BY *********************************
	// 컬럼필터 클릭 시, 폼에 orderValue 와 orderName (asc, desc 체크 후) 입력하여 submit
	$(".icon_sort").click(function() {
		let clickCol = $(this).attr('id');
		let orderByAscDesc = $(this).hasClass("up") ? "ASC" : "DESC";
		
		if(orderByAscDesc == "ASC"){
			$(".icon_sort").removeClass("up");
		}else if(orderByAscDesc == "DESC"){
			$(".icon_sort").removeClass("up");
			$(this).addClass("up");
		}
		
		$("form[name=frm] input[name=orderValue]").val(clickCol);
		$("form[name=frm] input[name=orderName]").val(orderByAscDesc);
		goPage(1);
	});

	//페이지 이동 & 필터 sorting
	function goPage(pageNum) {
		/*
        페이징 처리 할때도 sort 정보를 넘겨주어야한다

        orderValue 와 orderName ( ORDER BY <컬럼변수> / <ASC, DESC 변수> )
        페이지 로드 시 받아놓은 paramVO 의 orderValue, sort
         */
		$("form[name=frm] input[name=pageNum]").val(pageNum);
		
		$("#listContent").html("");
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/consult/requestSearch"
			, data: $("form[name=frm]").serialize()
			, dataType:"html"
			, success : function(responseData){
				var resultList = responseData;
				
				//console.log("1111===="+resultList);
				$("#listContent").html(resultList);

				//페이징 및 카운트 조회
				goPageAndCount(pageNum);
				
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
	
	//페이지 이동 & 필터 sorting
	function goPageAndCount(pageNum) {
		
		$("form[name=frm] input[name=pageNum]").val(pageNum);
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/consult/requestCountPage"
			, data: $("form[name=frm]").serialize()
			, dataType:"json"
			, success : function(responseData){
				//var resultList = responseData;
				var statsCnt = responseData.statsCnt;
				var paging = responseData.paging;
				var param = responseData.paramVO;
				
				$("#paging").html(paging);

				if(0 == "${statsCnt.TOTAL_CNT}"){
					//$("#TotalCnt").html("${statsCnt.TOTAL_CNT}");
					$("#submitCnt").html(0);
					$("#progressCnt").html(0);
					$("#delayCnt").html(0);
					$("#cancelCnt").html(0);
					$("#completeCnt").html(0);
					$("#standbyCnt").html(0);
				}else{
					//$("#TotalCnt").html("${statsCnt.TOTAL_CNT}");
					$("#submitCnt").html("${statsCnt.SUBMIT_CNT}");
					$("#progressCnt").html("${statsCnt.PROGRESS_CNT}");
					$("#delayCnt").html("${statsCnt.DELAY_CNT}");
					$("#cancelCnt").html("${statsCnt.CANCEL_CNT}");
					$("#completeCnt").html("${statsCnt.COMPLETE_CNT}");
					$("#standbyCnt").html("${statsCnt.STANDBY_CNT}");
					
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
	// ********************************* 컬럼필터 ORDER BY *********************************


	//등록
	$("[data-btn=btnInsert]").on("click", function() {
		//일단 전체 출력 - 추후에 조회결과만 출력 되도록 해보기
		$("form[name=frm] input[name=pageNum]").val(1);
		$("form[name=frm]").attr("method", "GET");
		$("form[name=frm]").attr("action", "/consult/consult/requestInsertForm");
		$("form[name=frm]").submit();
	});

    //조회폼 초기화
    $("[data-btn=btnInit]").on("click", function () {
    	$("#status option:eq(0)").prop("selected", true);
        $("form[name=frm] input[name=customer]").val("");
        $("form[name=frm] input[name=phoneNumber]").val("");
        $("form[name=frm] input[name=searchStartDt]").val("");
        $("form[name=frm] input[name=searchEndDt]").val("");
        //$("form[name=frm] input[name=CONS_SEQ]").val(0);
        goPage(1);
    });


    // 조회 param 의 특수문자 체크
    $("[data-btn=btnSearch]").on("click", function () {
        //작성자 (author) 값에 특수문자 체크
        var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;

        checkValCUST_NM = $("input[name=CUST_NM]").val();
        checkValCUST_HP_NO = $("input[name=CUST_HP_NO]").val();

        if (reg.test(checkValCUST_NM && checkValCUST_HP_NO)) {
            $("input[name=customer]").val("");
            $("input[name=customer]").attr("placeholder", "특수 문자를 제외하고 검색해주세요");
			openPopAlert("${validationSearchReg}");
        } else {
            goPage(1);
        }
    });

	//***************** 날짜 param: 시작일 < 종료일 *****************
	$("form[name=frm] input[name=searchStartDt]").on("change", function() {
		let startDt = $("form[name=frm] input[name=searchStartDt]").val();
		let endDt = $("form[name=frm] input[name=searchEndDt]").val();

		if( isNotEmpty(startDt) && isNotEmpty(endDt) && (startDt > endDt) ) {
			openPopAlert("${validationDateReg}");
			$("form[name=frm] input[name=searchStartDt]").val("");
			$("form[name=frm] input[name=searchEndDt]").val("");
		}
	});
	$("form[name=frm] input[name=searchEndDt]").on("change", function() {
		let startDt = $("form[name=frm] input[name=searchStartDt]").val();
		let endDt = $("form[name=frm] input[name=searchEndDt]").val();

		if( isNotEmpty(startDt) && isNotEmpty(endDt) && (startDt > endDt) ) {
			openPopAlert("${validationDateReg}");
			$("form[name=frm] input[name=searchStartDt]").val("");
			$("form[name=frm] input[name=searchEndDt]").val("");
		}
	});
	//***************** 날짜 param: 시작일 < 종료일 *****************

    //*****************  상세페이지이동 << checkStatsAndDirect *****************
    function checkStatsAndDirect(consSeq, statsCode) {
		let toUpdatePage = new Set();
        let toDetailPage = new Set();

		/*    변경된 마스터코드 STATS_CODE
			MC0001100001	접수
			MC0001100002	진행중(대기)
			MC0001100003	진행중
			MC0001100004	부재/보류
			MC0001100005	완료
			MC0001100006	취소
	 	*/
		toUpdatePage.add("MC0001100001");     //접수
		toUpdatePage.add("MC0001100002");     //진행중(대기)
		toUpdatePage.add("MC0001100003");     //진행중
		toUpdatePage.add("MC0001100004");     //부재/보류

        toDetailPage.add("MC0001100005");     //완료
        toDetailPage.add("MC0001100006");     //취소

		//신청내역접수번호 입력
		$("form input[name=CONS_SEQ]").val( consSeq );

		console.log("statcode===="+statsCode);
		
		//신청내역상세 이동
		let frm = $("form[name=frm]");
        if(toUpdatePage.has(statsCode) ) {
			frm.attr("method", "POST");
            frm.attr("action", "/consult/consult/requestUpdateForm");
            frm.submit();
            return;
        }
        if( toDetailPage.has(statsCode) ) {
            frm.attr("method", "POST");
            frm.attr("action", "/consult/consult/requestDetail");
            frm.submit();
            return;
        }
    }
	//*****************  상세페이지이동 << checkStatsAndDirect *****************


	//********************************** 담당자 배정 기능 **********************************

	//전체 체크박스 클릭
	$("#check_all").click(function() {
		if($("#check_all").is(":checked")) {
			$("input[name=CHECK]").prop("checked", true);
		} else {
			$("input[name=CHECK]").prop("checked", false);
		}
	});
	//전체 체크박스 체크 및 체크해제
	$("input[name=CHECK]").click(function() {
		var total = $("input[name=CHECK]").length;
		var checked = $("input[name=CHECK]:checked").length;

		if(total != checked) $("#check_all").prop("checked", false);
		else $("#check_all").prop("checked", true);
	});
	

    //담당자 배정 버튼 클릭 시
    $("[data-btn=btnAssign]").on("click", function() {
    	
    	//체크박스가 클릭되면 CONS_SEQ (상담신청등록번호)를 array에 넣어준다
    	let chkCnt = 0;
    	
		//체크배열 업데이트
		$("input[name=CHECK]:checked").each( function(index, input) {
			chkCnt++;
		});
		
    	if(chkCnt > 0){
			openPopConfirmAlert("${messageAssignConsultantConfirm}", assignConsultantOnList);
    	}else{
    		openPopAlert("선택된 대상이 없습니다.");
    	}
    	
    });
    function assignConsultantOnList() {
    	
    	//체크박스가 클릭되면 CONS_SEQ (상담신청등록번호)를 array에 넣어준다
    	var checkedConsSeqArr = [];
    	
		//체크배열 업데이트
		$("input[name=CHECK]:checked").each( function(index, input) {
			checkedConsSeqArr.push( input.value );
		});

		//submit 전에 폼에 checkedConsSeqArr 를 담아준다
		$("form[name=frm] input[name=checkedConsSeqArr]").val(checkedConsSeqArr);
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/consult/assignConsultantOnList"
			, data: $("form[name=frm]").serialize()
			, dataType:"json"
			, success : function(responseData){
				var data = responseData.resultJson;
				if(data.rCode == "0000") {
					openPopAlertAction("${messageAssignConsultantSuccess}", function () {goPage(1)});
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
	//********************************** 담당자 배정 기능 **********************************



	//input 의 자릿수 설정
    function maxLengthCheck(object) {
        if (object.value.length > object.max.length){
            object.value = object.value.slice(0, object.maxLength);
        }
    }

</script>