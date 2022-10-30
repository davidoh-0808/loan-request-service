<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>


<!-- 프로퍼티메세지 -->
<c:set var="validationEmptyRequired"><spring:message code="validation.empty.input" arguments="이름을"/></c:set>
</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="대출상담 신청"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/consultation/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/consultation/include/lnb.jsp" %>
		<!-- lnb -->
            <section class="contents">
				<form name="frm">
				<input type="hidden" class="csrfname" name="${_csrf.parameterName}" value="${_csrf.token }"/>
				<input type="hidden" id="ADDR_TYPE" name="ADDR_TYPE" value=""/>
				<input type="hidden" id="TEAM_CODE" name="TEAM_CODE" value="MC0000900099"/><!-- 심사팀코드 -->
				<input type="hidden" id="BRANCH_CODE" name="BRANCH_CODE" value=""/>
				<input type="hidden" id="BRANCH_NAME" name="BRANCH_NAME" value=""/>
				<input type="hidden" id="CUST_NM" name="CUST_NM" value="${CUST_NM}"/>
				<input type="hidden" id="CUST_REGI_NO" name="CUST_REGI_NO" value="${CUST_REGI_NO}"/>
				<input type="hidden" id="CUST_HP_NO" name="CUST_HP_NO" value="${CUST_HP_NO}"/>
				<input type="hidden" id="TYPE_NAME" name="TYPE_NAME" value=""/>
				<input type="hidden" id="CUST_TYPE_NAME" name="CUST_TYPE_NAME" value=""/>
				<input type="hidden" id="VULN_CLASS_NAME" name="VULN_CLASS_NAME" value=""/>
				<input type="hidden" id="JOB_TYPE_NAME" name="JOB_TYPE_NAME" value=""/>
				
				<input type="hidden" name="sBirthDate" value="${sBirthDate }"/>
				<input type="hidden" name="sGender" value="${sGender }"/>
				<input type="hidden" name="sMobileNo" value="${sMobileNo }"/>
				
                <div class="group_title">
                    <ul class="breadcrumb">
                        <li>Home</li>
                        <li>대출상담 신청</li>
                        <li>대출상담 신청</li>
                    </ul>
                    <h3>대출상담 신청</h3>
                </div>
                <div class="group_content">
                    <div class="left">
                        <ul class="step">
                            <li >본인확인</li>
                            <li class="on">정보입력</li>
                            <li>신청완료</li>
                        </ul>
                    </div>
                    <div class="right">
                        <div class="notice">
                            <p>대출상담은 고객님의 신용평점에 영향을 주지 않습니다.</p>
                            <span>대출상담 02 - 361 - 6000으로 연락주시면 상담원과 상담 가능합니다. (평일 오전 9시 ~ 오후 5시)</span>
                        </div>
                        <div class="request_creditinfo">
                            <h4><span class="emp_red">신청고객 정보</span></h4>
                            <dl class="table_responsive">
                                <dt class="col"><span>이름</span></dt>
                                <dd class="col">${CUST_NM}</dd>
                                <dt class="col">주민등록번호</dt>
                                <dd class="col">${sBirthDate}-${sGender}******</dd>
                                <dt class="col">연락처</dt>
                                <dd class="col formatPhone">${sMobileNo}</dd>
                                <dt class="col">유입경로</dt>
                                <dd class="col">홈페이지</dd>
                            </dl>
                        </div>
                        <div class="request_registration">
                            <h4><span class="emp_red">사업자 등록여부</span></h4>
                        
                            <dl class="table_responsive">
                                <dt class="col"><span>사업자 등록</span></dt>
                                <dd class="col">
                                    <select class="form_control md" id="CUST_TYPE" name="CUST_TYPE">
										<option value="">선택</option>
		                                   <c:forEach var="code" items="${CUST_TYPE}">
		                                       <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
		                                   </c:forEach>
									</select>
                                </dd>
                                <dt class="col optionBox"><span>취약계층 여부</span></dt>
                                <dd class="col optionBox">
                                    <select class="form_control md" id="VULN_CLASS" name="VULN_CLASS">
										<option value="">선택</option>
		                                   <c:forEach var="code" items="${VULN_CLASS}">
		                                       <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
		                                   </c:forEach>
									</select>
                                </dd>
                            </dl>
                        </div>
                        <!-- 사업자 정보 -->
                        <div class="request_business">
                            <h4>
                                <span class="emp_red">사업자정보</span>
                                <button type="button" class="btn btn_sm btn_blue" onclick="window.open('https://hometax.go.kr')">사업자번호 조회</button>
                            </h4>
                            <dl class="table_responsive">
                                <dt><span>사업자 번호</span></dt>
                                <dd>
                                    <input type="text" class="form_control md" id="CUST_CORP_NO" name="CUST_CORP_NO" maxlength="13"  placeholder="- 제외">
                                </dd>
                                <dt><span>사업자 등록일</span></dt>
                                <dd>
                                    <input type="text" id="CORP_REGI_DT" name="CORP_REGI_DT" class="datepicker form_control md" placeholder="YYYY-MM-DD" />
                                </dd>
                                <dt ><span>사업년수</span></dt>
								<dd>
								     <input type="text" id="CORP_HIS" name="CORP_HIS" class="form_control md" maxlength="20" placeholder="">
								</dd>
                                <dt><span >업종</span></dt>
                                <dd>
		                            <select class="form_control md" id="TYPE_CODE" name="TYPE_CODE">
										<option value="">선택</option>
		                                   <c:forEach var="code" items="${TYPE_CODE}">
		                                       <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
		                                   </c:forEach>
									</select>
                                </dd>
                                <dt><span class="address">사업자주소</span></dt>
                                <dd>
                        	<input type="hidden" id="CUST_CORP_AREA" name="CUST_CORP_AREA" maxlength="10" class="addressInput input_text">
							<input type="hidden" id="CUST_CORP_ADDR1" name="CUST_CORP_ADDR1" maxlength="100" class="addressInput input_text">
							<input type="hidden" id="CUST_CORP_ADDR2" name="CUST_CORP_ADDR2" maxlength="50" class="addressInput input_text">
                            <input type="text" id="CORP_ADDR" name="CORP_ADDR" class="form_control mr10 addressInput" placeholder="">
                            <button type="button" class="btn btn_sm btn_lightgray btn_search" onclick="modalHandler('corp');">검색</button>
                                </dd>
                            </dl>
                            <h4>담당자 정보</h4>
                            <dl class="table_responsive">
                                <dt><span>부서명</span></dt>
                                <dd>심사팀</dd>
                                <dt><span>담당자 </span></dt>
                                <dd class="branchNm"></dd>
                            </dl>
                        </div>
                        
                        <!-- 직장 정보 -->
                        <div class="request_company">
                            <h4><span class="emp_red">직장 정보</span></h4>
                        
                            <dl class="table_responsive">
                                <dt><span>직장명</span></dt>
                                <dd>
                                    <input type="text" id="CORP_NM" name="CORP_NM" class="form_control md" maxlength="50" placeholder="">
                                </dd>
                                <dt><span>입사일</span></dt>
                                <dd>
                                    <input type="text" id="JOIN_DATE" name="JOIN_DATE" class="datepicker form_control md" placeholder="YYYY-MM-DD" />
                                </dd>
                                <dt><span>직업</span></dt>
                                <dd>
		                            <select class="form_control md" id="JOB_TYPE" name="JOB_TYPE">
										<option value="">선택</option>
		                                   <c:forEach var="code" items="${JOB_TYPE}">
		                                       <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
		                                   </c:forEach>
									</select>
                                </dd>
                                <dt><span>자택주소</span></dt>
                                <dd>
		                        	<input type="hidden" id="HOME_ADDR1" name="HOME_ADDR1" maxlength="100" class="addressInput input_text">
									<input type="hidden" id="HOME_ADDR2" name="HOME_ADDR2" maxlength="50" class="addressInput input_text">
		                            <input type="text" id="HOME_ADDR" name="HOME_ADDR" class="form_control mr10">
		                            <button type="button" class="btn btn_sm btn_lightgray btn_search" onclick="modalHandler('home');">검색</button>
                                </dd>
                            </dl>
                            <h4>담당자 정보</h4>
                            <dl class="table_responsive">
                                <dt><span>부서명</span></dt>
                                <dd>심사팀</dd>
                                <dt><span>담당자 </span></dt>
                                <dd class="branchNm"></dd>
                            </dl>
                        </div>
                        <div class="btn_area">
                            <button type="button" class="btn btn_lg btn_blue" data-btn="btnNext">다음</button>
                        </div>
                    </div>
                </div>
                </form>
            </section>
            <!--section-->
        </main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>
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
					<caption class="sr_only">검색 결과</caption>
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
			<caption class="sr_only">주소 입력</caption>
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
					<th scope="row">상세주소입력</th>
					<td>
						<input type="hidden" id="sggNm" value="">
						<input type="text" id="ADDR_AF" value="" class="form_control addDetail">
						<p class="addDetail2"></p>
						<p class="addDetail3">※ 해당 주소지는 상세주소가 등록되어 있지 않습니다.</p>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_area">
			<button type="button" class="btn btn_blue btn_juso" onclick="jusoCallBack();">주소입력</button>
		</div>
	</div>
	<!--// 상세주소 -->
	<div class="logo">
		<span class="ir">현대차미소금융재단 로고</span>
	</div>
</div>
    
<script>


   $('.request_company').hide();
   $('.request_business').hide();
   $('.optionBox').hide();

	$('#CUST_TYPE').on('selectmenuchange',function(){
		var business_option = $("#CUST_TYPE option:selected").val();
		var custTypeName = $("#CUST_TYPE option:selected").text();

		$("#CUST_TYPE_NAME").val(custTypeName);
		
		//무등록사업자(MC0001500002), 직장인(MC0001500003)인 경우
		if ( business_option == 'MC0001500002' || business_option == 'MC0001500003'){
			//주소필드 이름, placeholder 초기화
			$('.address').text('자택주소');
			$('.addressInput').attr('placeholder', '');

            $('.request_business').hide();
            $('.request_company').show();
            $('.optionBox').show();

            //request_business 내용 지워주기
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

            $('.request_business').show();
            $('.request_company').hide();
            $('.optionBox').hide();

            //request_company 내용 지워주기
            $('#VULN_CLASS').val("");
            $('#CORP_NM').val("");
            $('#JOIN_DATE').val("");
            $('#JOB_TYPE').val("");
            $('#HOME_ADDR1').val("");
            $('#HOME_ADDR2').val("");
            $('#HOME_ADDR').val("");

        }
		
		$(".branchNm").text("");
        $("#BRANCH_NAME").val("");
        $("#BRANCH_CODE").val("");
	});
	
	$('#TYPE_CODE').on('selectmenuchange',function(){
		var type = $("#TYPE_CODE option:selected").val();
		
		var typename = $("#TYPE_CODE option:selected").text();
		$("#TYPE_NAME").val(typename);

		//운수업:MC0000800001, 운수업(지입):MC0000800002, 건설업(건설기계대여):MC0000800003
        if( type == 'MC0000800001' || type == 'MC0000800002' || type == 'MC0000800003'){
            $('.address').text('자택주소');
            $('.addressInput').attr('placeholder', '※ 운수업, 운수업(지입), 건설(건설기계 대여)인 경우 자택 주소 기재');
        } else{
            $('.address').text('사업자주소');
            $('.addressInput').attr('placeholder', '');
        }
	});
	
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
        	
       		
       		//담당자/지점 자동셋팅
       		$("#BRANCH_NAME").val($("#sggNm").val());
       		setBreanch();	
       		
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
    		htmlStr += "<td class='subj al'>";
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

    //수정내역 저장
    $("[data-btn=btnNext]").on("click", function () {
        //신청자의 성명 유효성 검사
		if( isEmpty($("#CUST_NM").val()) ) {
			openPopAlertFocus($("#CUST_NM"), "${validationEmptyRequired}");

			return;
		}
        
        //값 전달하기
        //업종명
        var jobTypeName = $("#JOB_TYPE option:selected").text();
		$("#JOB_TYPE_NAME").val(jobTypeName);

		//취약계층명
		var vulnClassName = $("#VULN_CLASS option:selected").text();
		$("#VULN_CLASS_NAME").val(vulnClassName);
		
		
        //3단계 이동
        sendStep3();
    });


 // 유효성검사
 /* function ajaxCheck(){
 	
 	let chk = true;
 	// S: 유효성검사
 	
 	//이름
 	if(isEmpty($("form[name=form_chk] input[name=CUST_NM]").val())){
 		openPopAlertFocus($("form[name=form_chk] input[name=CUST_NM]"), "${messageValidationEmpty_CUST_NM}");
 		chk = false;
 		return; 
 	}
 	
 	//실명번호
 	if(isEmpty($("form[name=form_chk] input[name=CUST_REGI_NO]").val())){
 		openPopAlertFocus($("form[name=form_chk] input[name=CUST_REGI_NO]"), "${messageValidationEmpty_CUST_REGI_NO}");
 		chk = false;
 		return; 
 	}
 	
 	//휴대폰
 	if(isEmpty($("form[name=form_chk] input[name=CUST_HP_NO]").val())){
 		openPopAlertFocus($("form[name=form_chk] input[name=CUST_HP_NO]"), "${messageValidationEmpty_CUST_HP_NO}");
 		chk = false;
 		return; 
 	}
 	
 	//전체동의
 	if (!$("input:checked[Name='CHECK_ALL']").is(":checked")){ 
 		openPopAlertFocus($("form[name=form_chk] input[name=CHECK_ALL]"), "${messageValidationCHECK_PERSONAL_CHECK_ALL}"); 
 		chk = false;
 		return; 
 	}
 	// E: 유효성검사
 	
 	return chk;
 } */
 
    
// step3로 이동
function sendStep3(){
    
    $("form[name=frm]").attr("action", "/front/consultation/newRequest_step3");
    $("form[name=frm]").attr("method", "post");
    $("form[name=frm]").submit();
    
	//document.frm.action = "/front/consultation/newRequest_step2";
	//document.frm.target = "popupChk";
	//document.frm.submit();
}


</script>

</body>
</html>
