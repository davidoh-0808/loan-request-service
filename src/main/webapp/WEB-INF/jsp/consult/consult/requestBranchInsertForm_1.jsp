<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<!-- 프로퍼티 메시지 -->
<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>

</head>

<body>
<!--wrap -->
<div class="admin_wrap">
    <!-- 관리자 상단 -->
    <%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
    <!-- // 관리자 상단 -->

    <!-- 관리자 히단 -->
    <div class="admin_bottom">

        <!-- lnb -->
        <%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
        <!-- // lnb -->

        <!-- Update 혹은 취소 하기 위한 폼-->
        <form name="frm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
            <input type="hidden" name="MEMBER_CODE" value="${MEMBER_CODE }"/>
            <input type="hidden" id="BRANCH_CODE" name="BRANCH_CODE" value="${BRANCH_CODE }"/>
            <input type="hidden" id="BRANCH_NAME" name="BRANCH_NAME" value="${BRANCH_NAME }"/>
            <input type="hidden" id="ADDR_TYPE" name="ADDR_TYPE" value=""/>
            <input type="hidden"  id="CUST_REGI_NO" name="CUST_REGI_NO" value="${result.CUST_REGI_NO}"/>
			<!--content-->
			<div class="content">
				<h2 class="con_tit">신청내역 등록</h2>
				
				<h3 class="s_tit">상담 정보</h3>
				<div class="tb_style tb_htype sp10">
					<table>
						<caption>신청고객 정보 테이블</caption>
						<colgroup>
							<col style="width: 138px;">
							<col style="width: auto;">
							<col style="width: 138px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">상품명</th>
								<td>
									<select name="PRODUCT_TYPE" class="selbox w_100">
										<option value="">선택</option>
	                                    <c:forEach var="code" items="${PRODUCT_TYPE}">
	                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
	                                    </c:forEach>
									</select>
								</td>
								<th scope="row">접수일</th>
								<td>${TODAY}</td>
							</tr>
							<tr>
								<th scope="row">지점</th>
								<td class="branchNm">${BRANCH_NAME}</td>
								<th scope="row">담당자</th>
								<td>${MEMBER_NAME}</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<h3 class="s_tit sp40">신청고객 정보</h3>
				<div class="tb_style tb_htype sp10">
					<table>
						<caption>신청고객 정보 테이블</caption>
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
									<input type="text" id="CUST_NM" name="CUST_NM" maxlength="20" class="input_text w_100" >
								</td>
								<th scope="row">주민등록번호</th>
								<td>
									<input type="text" class="input_text form_sm" id="sBirthDate" maxlength="6" value="${sBirthDate }">
									<span class="hyphen">-</span>
									<input type="text" class="input_text form_one" id="sGender" maxlength="1" value="${sGender }">
									<span class="starkey">******</span>
								</td>
							</tr>
							<tr>
								<th scope="row">연락처</th>
								<td>
									<input type="text" class="input_text w_100" name="CUST_HP_NO" maxlength="11" oninput="maxLengthCheck(this)">
								</td>
								<th scope="row">유입경로</th>
								<td>
									<select name="INFLOW_ROUTE1" class="selbox w_100">
										<option value="">선택</option>
	                                    <c:forEach var="code" items="${INFLOW_ROUTE1}">
	                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
	                                    </c:forEach>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<h3 class="s_tit sp40">사업자 등록여부</h3>
				<div class="tb_style tb_htype sp10 registration_table">
					<table>
						<caption>사업자 등록여부 테이블</caption>
						<colgroup>
							<col style="width: 138px;">
							<col style="width: auto;">
							<col style="width: 138px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">사업자 등록</th>
								<td>
									<select class="selbox w_100" id="CUST_TYPE" name="CUST_TYPE">
										<option value="">선택</option>
	                                    <c:forEach var="code" items="${CUST_TYPE}">
	                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
	                                    </c:forEach>
									</select>
								</td>
								<th scope="row" class="optionBox">취약계층 여부</th>
								<td class="optionBox">
									<select class="selbox w_100" id="VULN_CLASS" name="VULN_CLASS">
										<option value="">선택</option>
	                                    <c:forEach var="code" items="${VULN_CLASS}">
	                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
	                                    </c:forEach>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 사업자정보 -->
				<div class="business_info">
					<div class="info sp40 cellbox">
						<div class="left cell auto">
							<h3 class="s_tit">사업자 정보</h3>
						</div>
						<div class="right cell">
							<button type="button" class="btn btn_white btn_m"  onclick="window.open('https://hometax.go.kr')">사업자번호 조회</button>
						</div>
					</div>
					<div class="tb_style tb_htype sp10">
						<table>
							<caption>사업자 정보 테이블</caption>
							<colgroup>
								<col style="width: 138px;">
								<col style="width: auto;">
								<col style="width: 138px;">
								<col style="width: auto;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">사업자번호</th>
									<td colspan="3">
										<input type="number" id="CUST_CORP_NO" name="CUST_CORP_NO" maxlength="13" oninput="maxLengthCheck(this)" class="input_text form_md" placeholder="- 제외">
									</td>
								</tr>
								<tr>
									<th scope="row">사업자 등록일</th>
									<td>
										<input type="text" id="CORP_REGI_DT" name="CORP_REGI_DT" class="input_text form_md datepicker">
									</td>
									<th scope="row">사업년수</th>
									<td><input type="text" class="input_text form_md"  id="CORP_HIS" name="CORP_HIS" value="${result.CORP_HIS }" maxlength="20" oninput="maxLengthCheck(this)"></td>
								</tr>
								<tr>
									<th scope="row">업종</th>
									<td colspan="3">
										<div class="selbox_form">
											<select class="selbox" id="TYPE_CODE" name="TYPE_CODE">
												<option value="">선택</option>
			                                    <c:forEach var="code" items="${TYPE_CODE}">
			                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
			                                    </c:forEach>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th><span class="address">사업자 주소</span></th>
									<td colspan="3">
										<input type="hidden" id="CUST_CORP_AREA" name="CUST_CORP_AREA" maxlength="10" class="addressInput input_text">
										<input type="hidden" id="CUST_CORP_ADDR1" name="CUST_CORP_ADDR1" maxlength="100" class="addressInput input_text">
										<input type="hidden" id="CUST_CORP_ADDR2" name="CUST_CORP_ADDR2" maxlength="50" class="addressInput input_text">
										<input type="text" id="CORP_ADDR" name="CORP_ADDR" class="addressInput input_text" readonly onclick="modalHandler('corp');">
										<button type="button" class="btn btn_white btn_sm ml5" onclick="modalHandler('corp');">검색</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<!-- 직장정보 -->
				<div class="company_info">
					<div class="info sp40 cellbox">
						<div class="left cell auto">
							<h3 class="s_tit">직장 정보</h3>
						</div>
					</div>
					<div class="tb_style tb_htype sp10">
						<table>
							<caption>직장 정보 테이블</caption>
							<colgroup>
								<col style="width: 138px;">
								<col style="width: auto;">
								<col style="width: 138px;">
								<col style="width: auto;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">직장명</th>
									<td colspan="3">
										<input type="text" id="CORP_NM" name="CORP_NM" maxlength="50"  class="input_text form_md">
									</td>
								</tr>
								<tr>
									<th scope="row">입사일</th>
									<td colspan="3">
										<input type="text" id="JOIN_DATE" name="JOIN_DATE" class="input_text form_md datepicker">
									</td>
								</tr>
								<tr>
									<th scope="row">직업</th>
									<td colspan="3">
										<div class="selbox_form">
											<select class="selbox" id="JOB_TYPE" name="JOB_TYPE">
												<option value="">선택</option>
			                                    <c:forEach var="code" items="${JOB_TYPE}">
			                                        <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
			                                    </c:forEach>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th class="address">자택주소</th>
									<td colspan="3">
										<input type="hidden" id="HOME_ADDR1" name="HOME_ADDR1" maxlength="100" class="addressInput input_text">
										<input type="hidden" id="HOME_ADDR2" name="HOME_ADDR2" maxlength="50" class="addressInput input_text">
										<input type="text" id="HOME_ADDR" name="HOME_ADDR" class="addressInput input_text" readonly onclick="modalHandler('home');">
										<button type="button" class="btn btn_white btn_sm ml5" onclick="modalHandler('home');">검색</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 기타(비고란)-->
				<div class="memo">
					<h3 class="s_tit sp40">기타</h3>
					<div class="tb_style tb_htype sp10">
						<table>
							<caption>비고 작성</caption>
							<colgroup>
								<col style="width: 138px;">
								<col style="width: auto;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">비고</th>
									 <td>
										 <div class="memo_area ht w_100">
											  <textarea name="NOTE" id="NOTE" style="resize: none;">${result.NOTE}</textarea>
										 </div>
									</td>
								 </tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area sp50">
					<div class="right_a">
						<button type="button" class="btn btn_outline btn_l" data-btn="btnCancel">취소</button>
						<button type="button" class="btn btn_blue btn_l" data-btn="btnSave">등록</button>
					</div>
				</div>
			</div>
			<!--//content-->

        </form>
        <!-- Update 혹은 취소 하기 위한 폼-->

    </div>
    <!-- //관리자 하단 -->

</div>
<!-- //Wrap-->
	<!-- 우편번호 팝업 -->
	<div id="pop_zipcode" class="pop_wrap">
	<div class="pop_contents">
	    <form name="formPop" id="formPop" method="post">
	    	<!-- <input type="text" id="searchJuso" name="searchJuso" class="addressInput input_text"> -->
			<input type="hidden" id="currentPage" name="currentPage" value="1"/> <!-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
			<input type="hidden" id="countPerPage" name="countPerPage" value="5"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
			<input type="hidden" name="resultType" value="json"/> <!-- 요청 변수 설정 (검색결과형식 설정, json) --> 
			<input type="hidden" name="confmKey" value="${zipcodeConfmKey }"/><!-- 2022.09.15 90일용: 이후 새로 받아야함.. 요청 변수 설정 (승인키) -->
			<input type="hidden" name="keyword" value=""/>
	   </form>     
				<!-- 주소검색 -->
				<div class="search_wrap">
					<div class="wrap">
						<input type="text" class="popSearchInput" id="keyword" value="" onkeyup="enterSearch();"/>
						<button type="button" class="searchBt ir" onclick="getAddr();">검색</button>
					</div>
					<a class="pop_close ir" onclick="closePop();">close</a>
				</div>
				<!--// 주소검색 -->
				<!-- 검색결과 -->
				<div class="result">
					<p class="guide">도로명 주소 검색 결과 <strong id="totCnt"></strong></p>
					<table class="data_table">
						<caption>검색 결과</caption>
						<colgroup>
							<col style="width:10%;">
							<col style="width:auto;">
							<col style="width:15%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">No</th>
								<th scope="col">도로명주소</th>
								<th scope="col">우편번호</th>
							</tr>
						</thead>
						<tbody id="list">
							<!-- <div id="list" ></div> --><!-- 검색 결과 리스트 출력 영역 -->
						</tbody>
					</table>
				</div>
				<!-- //검색결과 -->
				<!-- //페이징 -->
				<!-- <div class="paginate" id="pageApi"></div> -->
				<div class="paging" >
					<div class="page-num" id="pageApi">
					</div>
				</div>
				<!-- //페이징 -->
		</div>
						<!-- 상세주소 -->
				<div class="detail" style="display:none">
					<p>상세주소 입력</p>
					<table class="detail_table">
						<caption>주소 입력</caption>
						<colgroup>
							<col style="width:21%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">도로명주소</th>
								<td id="ADDR_BF"></td>
							</tr>
							<tr>
								<th scop="row">상세주소입력</th>
								<td>
									<input type="hidden" id="sggNm" value="" class="addDetail w_100">
									<input type="text" id="ADDR_AF" value="" class="addDetail w_100">
									<p class="addDetail2"></p>
									<p class="addDetail3">※ 해당 주소지는 상세주소가 등록되어 있지 않습니다.</p>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_area txt_c">
						<button type="button" class="btn btn_blue btn_juso" onclick="jusoCallBack();">주소입력</button>
					</div>
				</div>
				<!--// 상세주소 -->
		<div class="logo">
			<span class="ir">현대차미소금융재단 로고</span>
		</div>
    </div>
    
<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

<script>

	$('.company_info').hide();
	$('.optionBox').hide();

	$('#CUST_TYPE').on('change',function(){
		var business_option = $("#CUST_TYPE option:selected").val();

		//무등록사업자(MC0001500002), 직장인(MC0001500003)인 경우
		if ( business_option == 'MC0001500002' || business_option == 'MC0001500003'){
			//주소필드 이름, placeholder 초기화
			$('.address').text('자택주소');
			$('.addressInput').attr('placeholder', '');

            $('.business_info').hide();
            $('.company_info').show();
            $('.optionBox').show();

            //business_info 내용 지워주기
            $('#VULN_CLASS').val("");
            $('#CUST_CORP_NO').val("");
            $('#CORP_REGI_DT').val("");
            $('#TYPE_CODE').val("");
            $('#CUST_CORP_AREA').val("");
            $('#CUST_CORP_ADDR1').val("");
            $('#CUST_CORP_ADDR2').val("");
            $('#CORP_ADDR').val("");

        }else{
			//주소필드 이름, placeholder 초기화
			$('.address').text('사업자 주소');
			$('.addressInput').attr('placeholder', '');

            $('.business_info').show();
            $('.company_info').hide();
            $('.optionBox').hide();

            //company_info 내용 지워주기
            $('#VULN_CLASS').val("");
            $('#CORP_NM').val("");
            $('#JOIN_DATE').val("");
            $('#JOB_TYPE').val("");
            $('#HOME_ADDR1').val("");
            $('#HOME_ADDR2').val("");
            $('#HOME_ADDR').val("");

        }
		
	});
	
	$('#TYPE_CODE').on('change',function(){
		var type = $("#TYPE_CODE option:selected").val();

		//운수업:MC0000800001, 운수업(지입):MC0000800002, 건설업(건설기계대여):MC0000800003
        if( type == 'MC0000800001' || type == 'MC0000800002' || type == 'MC0000800003'){
            $('.address').text('자택주소');
            $('.addressInput').attr('placeholder', '※ 운수업, 운수업(지입), 건설(건설기계 대여)인 경우 자택 주소 기재');
        } else{
            $('.address').text('사업자주소');
            $('.addressInput').attr('placeholder', '');
        }
	});

	//number 타입 input에 자릿수 설정시 사용
	function maxLengthCheck(object){
		
	    if (object.value.length > object.max.length){
	        object.value = object.value.slice(0, object.maxLength);
	    }    
	}


    //목록페이지 이동
    $("[data-btn=btnCancel]").on("click", function () {

		openPopConfirmAlert("${messageSaveCancelConfirm}", requestList, null);
		
    });
    
    //목록페이지 이동
    function requestList() {
		$("form[name=frm]").attr("method", "POST");
        $("form[name=frm]").attr("action", "/consult/consult/requestList");
        $("form[name=frm]").submit();
    }

    //수정내역 저장
    $("[data-btn=btnSave]").on("click", function () {
        //신청자의 성명 유효성 검사
		// 상품코드
		/* if(isEmpty( $('#PRODUCT_TYPE option:selected').val()) ) {
			//alert("****************** 1.11 ******************");
			openPopAlert("상품을 선택해 주세요.");
			valChk = false;
			return;
		}
		// 고객명
		if(isEmpty( $("#CUST_NM").val() )) {
			//alert("****************** 1.44 ******************");
			openPopAlert("고객명을 입력해 주세요.");
			valChk = false;
			return;
		}
		 */
		//실명번호 셋팅
		if(isNotEmpty($("#sBirthDate").val()) && isNotEmpty($("#sGender").val()) ) {
			let regiNo = $("#sBirthDate").val()+$("#sGender").val()+"******";
			$("#CUST_REGI_NO").val(regiNo);
		} else {
			openPopAlert("주민등록번호를 입력해 주세요.");
			valChk = false;
			return;
		}

		//등록 함수 콜
        requestInsert();
    });

    //등록
    function requestInsert() {
        $.ajax({
            type: "POST"
            , url: "/consult/consult/requestInsert"
            , data: $("form[name=frm]").serialize()
            , dataType: "json"
            , success: function (responseData) {
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    openPopAlertAction("${messageSaveSuccess}", requestList, null);
                } else {
                    openPopAlertAction(data.rMsg);
                }
            }
            , error: function (xhr, status, error) {
                if (xhr.status == 401) {
                    openPopAlert("${errorXhr401}");
                } else if (xhr.status == 403) {
                    openPopAlert("${errorXhr403}");
                } else if (xhr.status == 500) {
                    openPopAlert("${errorXhr500}");
                } else {
                    openPopAlert("${errorXhrElse}");
                }
            },
        });

    }
    
	function modalHandler(addrType){
		
		$("#ADDR_TYPE").val(addrType);
		
		$('#pop_zipcode').dialog({
			dialogClass: 'zip_code',
			width: 537,
			height:554
		});
		
	}
	
	function closePop(){
		$('#pop_zipcode').dialog("close");
	}
	
    function jusoDetail(idx){
    	
    	$('.result').hide();	
    	$('.paging').hide();	
    	$('.detail').show();	
    	
    	var sggNm = $("#sggNm"+idx).val();
    	$("#sggNm").val(sggNm);
    	
    	var addrstr = $("#roadAdd"+idx).text();
    	$("#ADDR_BF").text(addrstr);
    	
    	var emdNm = $("#emdNm"+idx).val();
    	$(".addDetail2").text('('+emdNm+')');
    	
    	$("#ADDR_AF").focus();
    }
    
    function jusoCallBack(){
    	
    	var trmStr = $("#ADDR_AF").val().trim();
    	
    	if("" == trmStr){
    		alert("상세주소를 입력해 주십시오.") ;
    		return false
    	}else{
    		var addrType = $("#ADDR_TYPE").val();
        	var addr = $("#ADDR_BF").text().trim()+" "+$("#ADDR_AF").val().trim();
        	
       		if("home" == addrType){
    			
       			$("#HOME_ADDR").val(addr);
       			$("#HOME_ADDR1").val($("#ADDR_BF").text().trim());
       			$("#HOME_ADDR2").val($("#ADDR_AF").val());
        		
       		}else{ // corp
       			$("#CUST_CORP_AREA").val($("#sggNm").val());
       			$("#CUST_CORP_ADDR1").val($("#ADDR_BF").text().trim());
       			$("#CUST_CORP_ADDR2").val($("#ADDR_AF").val());
       			$("#CORP_ADDR").val(addr);
       		}
        	
       		//담당자/지점 자동셋팅 지점 로그인시 사용안함.
       		//$("#BRANCH_NAME").val($("#sggNm").val());
       		//setBreanch();	
       		
       		closePop();	
    	}
    	
    }    
    
  	//담당자/지점 조회
    function setBreanch(){
        
    	 $.ajax({
             type: "POST"
             , url: "/common/searchBranch"
             , data: $("form[name=frm]").serialize()
             , dataType: "json"
             , success: function (responseData) {
            	 
                 var data = responseData.codeVo;
                 
                 $(".branchNm").text(data.code_NAME);
                 $("#BRANCH_NAME").val(data.code_NAME);
                 $("#BRANCH_CODE").val(data.master_CODE);
             }
             , error: function (xhr, status, error) {
                 if (xhr.status == 401) {
                     openPopAlert("${errorXhr401}");
                 } else if (xhr.status == 403) {
                     openPopAlert("${errorXhr403}");
                 } else if (xhr.status == 500) {
                     openPopAlert("${errorXhr500}");
                 } else {
                     openPopAlert("${errorXhrElse}");
                 }
             },
         });
    }
    
    function getAddr(){
    	// 적용예 (api 호출 전에 검색어 체크) 	
		if (!checkSearchedWord($("#keyword").val())) {
    		return ;
    	}else{
    		$("form[name=formPop] input[name=keyword]").val($("#keyword").val());
    	}
    	
    	$('.result').show();	
    	$('.paging').show();	
    	$('.detail').hide();	

    	$.ajax({
    		 url :"https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do"  //인터넷망
    		,type:"post"
    		,data:$("form[name=formPop]").serialize()
    		,dataType:"jsonp"
    		,crossDomain:true
    		,success:function(jsonStr){
    			$("#list").html("");
    			var errCode = jsonStr.results.common.errorCode;
    			var errDesc = jsonStr.results.common.errorMessage;
    			if(errCode != "0"){
    				alert(errCode+"="+errDesc);
    			}else{
    				if(jsonStr != null){
    					
    			    	var totCnt = jsonStr.results.common.totalCount;
    			    	var curPage = jsonStr.results.common.currentPage;
    			    	
    			    	$("#totCnt").text("("+totCnt+" 건)");
    			    	$("#currentPage").val(curPage);
    			    	
    					makeListJson(jsonStr);
    					pageMake(jsonStr);
    					
    				}
    			}
    		}
    	    ,error: function(xhr,status, error){
    	    	alert("에러발생");
    	    }
    	});
    }

    function makeListJson(jsonStr){
    	
    	var htmlStr = "";
		var pageNum = $("#currentPage").val();// 현재페이지
		var pageSize = parseInt($("#countPerPage").val());
		var trIndex = 0;
		
		if(pageNum <= 1){
			trIndex = 0;
		}else{
			trIndex = pageNum * pageSize;
		}
		
    	$(jsonStr.results.juso).each(function(){
    		trIndex++;
    		htmlStr += "<tr data-btn='jusoInfo' data-detailCode=''>";
    		htmlStr += "<input type='hidden' id='siNm"+trIndex+"' value='"+this.siNm+"'/>";
    		htmlStr += "<input type='hidden' id='sggNm"+trIndex+"' value='"+this.sggNm+"'/>";
    		htmlStr += "<input type='hidden' id='emdNm"+trIndex+"' value='"+this.emdNm+"'/>";
    		htmlStr += "<input type='hidden' id='liNm"+trIndex+"' value='"+this.liNm+"'/>";
    		htmlStr += "<input type='hidden' id='rn"+trIndex+"' value='"+this.rn+"'/>";
    		htmlStr += "<td>"+trIndex+"</td>";
    		htmlStr += "<td class='subj txt_l'>";
    		htmlStr += "	<a href='javascript:void(0);' onclick='jusoDetail("+trIndex+");' attr-a=''>";
    		htmlStr += "		<div  class='roadAdd' id='roadAdd"+trIndex+"'>";
//    		htmlStr += "			<b>"+this.roadAddr+"</b>";
    		htmlStr += "			<b>"+this.roadAddrPart1+"</b>";
    		htmlStr += "		</div>";
    		htmlStr += "		<span>[지번]</span>";
    		htmlStr += "		<span class='jibunAdd'>"+this.jibunAddr+"</span>";
    		htmlStr += "	</a>";
    		htmlStr += "</td>";
    		htmlStr += "<td>";
    		htmlStr += "	<span class='zipNo'>"+this.zipNo+"</span>";
    		htmlStr += "</td>";
    		htmlStr += "</tr>";
		
    	});
    	$("#list").html(htmlStr);
    }
    
   //페이지 이동
    function goPage(pageNum){
	   
    	$("#currentPage").val(pageNum);
    	getAddr();
    }
   
    // json타입 페이지 처리 (주소정보 리스트 makeListJson(jsonStr); 다음에서 호출)
    /* 
    *  json타입으로 페이지 처리시 수정 
    *  function pageMake(jsonStr){ 
    *  	var total = jsonStr.results.common.totalCount; // 총건수 
    */
    function pageMake(jsonStr){
    	var total = jsonStr.results.common.totalCount; // 총건수
    	var pageNum = $("#currentPage").val();// 현재페이지
    	var paggingStr = "";
    	
    	if(total < 1){
    	}else{
    		var PAGEBLOCK=parseInt($("#countPerPage").val()); //document.form.countPerPage.value
    		var pageSize=parseInt($("#countPerPage").val()); //document.form.countPerPage.value
    		
    		var totalPages = Math.floor((total-1)/pageSize) + 1;
    		
    		var firstPage = Math.floor((pageNum-1)/PAGEBLOCK) * PAGEBLOCK + 1;
    		if( firstPage <= 0 ) firstPage = 1;		
    		
    		var lastPage = firstPage-1 + PAGEBLOCK;
    		
    		if( lastPage > totalPages ) lastPage = totalPages;		
    		
    		var nextPage = lastPage+1 ;
    		
    		var prePage = firstPage-5 ;		
    		
    		if( firstPage > PAGEBLOCK ){
    			paggingStr +=  "<a href='javascript:goPage("+prePage+");'>◁</a>  " ;
    		}		
    		
    		for( var i=firstPage; i<=lastPage; i++ ){
    			if( pageNum == i )
    				paggingStr += "<a style='font-weight:bold;color:blue;font-size:15px;' href='javascript:goPage("+i+");'>" + i + "</a>  ";
    			else
    				paggingStr += "<a href='javascript:goPage("+i+");'>" + i + "</a>  ";
    		}		
    		if( lastPage < totalPages ){
    			paggingStr +=  "<a href='javascript:goPage("+nextPage+");'>▷</a>";
    		}		
    		
    		$("#pageApi").html(paggingStr);
    	}	
    }

    
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

    function enterSearch() {
    	if (window.event.keyCode == 13) {
        	// 엔터키가 눌렸을 때
        	event.preventDefault();
        	getAddr(); //jsonp사용시 enter검색
        }
    }
    
</script>
</body>
</html>


