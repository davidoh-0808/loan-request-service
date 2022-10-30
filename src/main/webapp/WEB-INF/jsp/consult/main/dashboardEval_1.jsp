<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<script type="text/javascript" src="/resources/publish/js/chart.min.js"></script>
<script type="text/javascript" src="/resources/publish/js/chartjs-plugin-labels.js"></script>

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

			<!--content-->
			<div class="content">
				<h2 class="con_tit">대시보드</h2>
				<section class="dash_area first w_100">
					<form name="boardChart">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
						<input type="hidden" id="dateRange" name="dateRange" value=""/>
					</form>
					<%--<h3 class="s_tit">나의 상담<em class="total">총 <span>${userResult.TOTAL_CNT}</span>건</em></h3>--%>
					<div class="top">
						<h3 class="s_tit">나의 상담<em class="total">총 <span id="chTotalUser">${boardResult.TOTAL_CNT}</span>건</em></h3>
						<div id="eventUser" class="event">
							<c:if test="${'daily' eq dateRange}"><span id="boardDate" class="date">${currDate}</span></c:if>
							<c:if test="${'monthly' eq dateRange}"><span id="boardDate" class="date">${currMonth}</span></c:if>
							<c:if test="${'yearly' eq dateRange}"><span id="boardDate" class="date">${currYear}</span></c:if>
							<ul>
								<li id="userDaily" <c:if test="${'daily' eq dateRange}">class="on"</c:if> onclick="getBoard('${currDate}', 'daily')">일별</li>
								<li id="userMonthly" <c:if test="${'monthly' eq dateRange}">class="on"</c:if> onclick="getBoard('${currMonth}', 'monthly')">월별</li>
								<li id="userYearly" <c:if test="${'yearly' eq dateRange}">class="on"</c:if> onclick="getBoard('${currYear}', 'yearly')">연별</li>
							</ul>
						</div>
					</div>

					<div class="board_wrap">
						<%@ include file="/WEB-INF/jsp/consult/main/innerJsp/innerEvalBoard.jsp" %>
					</div>
				</section>
				<div class="second">
					<div class="top">
						<h3 class="s_tit">상담내역 현황</h3>
						<div id="eventEval" class="event">
							<c:if test="${'daily' eq dateRange}"><span id="chartDate" class="date">${currDate}</span></c:if>
							<c:if test="${'monthly' eq dateRange}"><span id="chartDate" class="date">${currMonth}</span></c:if>
							<c:if test="${'yearly' eq dateRange}"><span id="chartDate" class="date">${currYear}</span></c:if>
							<ul>
								<li id="generalDaily"  <c:if test="${'daily' eq dateRange}">class="on"</c:if> onclick="getChartList('${currDate}', 'daily')">일별</li>
								<li id="generalMonthly" <c:if test="${'monthly' eq dateRange}">class="on"</c:if> onclick="getChartList('${currMonth}', 'monthly')">월별</li>
								<li id="generalYearly" <c:if test="${'yearly' eq dateRange}">class="on"</c:if> onclick="getChartList('${currYear}', 'yearly')">연별</li>
							</ul>
						</div>
					</div>
					<div class="df board">
						<section id="chartList" class="dash_area">	
							<%@ include file="/WEB-INF/jsp/consult/main/innerJsp/innerEvalChartList.jsp" %>
						</section>
						<section id="chart" class="dash_area">
							<%@ include file="/WEB-INF/jsp/consult/main/innerJsp/innerEvalChart.jsp" %>	
						</section>
					</div>
				</div>
				</div>
			</div>
			<!--//content-->

		</div>
		<!-- //관리자 하단 -->

	<!-- //Wrap-->

	<script>
	/* =======================================================
	나의상담 현황 일별/월별/연별 버튼
	========================================================= */
	//********************* 날짜필터버튼 클릭 디스플레이 *********************
	$('#eventUser ul li').on('click', function(e){
		e.preventDefault();

		let isOn = $(this).hasClass('on');
		if(isOn) return;

		$('#eventUser ul li').removeClass('on');
		$(this).addClass('on');
	});

	$('#eventEval ul li').on('click', function(e){
		e.preventDefault();

		let isOn = $(this).hasClass('on');
		if(isOn) return;

		$('#eventEval ul li').removeClass('on');
		$(this).addClass('on');

	});
	//********************* 날짜필터버튼 클릭 디스플레이 *********************

		//페이지 이동 & 필터 sorting
	function getBoard(currDate, dateRange) {
		
		$("#dateRange").val(dateRange);
		$("#boardDate").text(currDate);
		
		$(".board_wrap").html("");
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/main/getBoard"
			, data: $("form[name=boardChart]").serialize()
			, dataType:"html"
			, success : function(responseData){
				var resultList = responseData;
				
				$(".board_wrap").html(resultList);

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
	
	//ChartList
	function getChartList(currDate, dateRange) {
			
		$("#chartDate").text(currDate);
		$("#dateRange").val(dateRange);
		
		$("#chartList").html("");
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/main/getChartList"
			, data: $("form[name=boardChart]").serialize()
			, dataType:"html"
			, success : function(responseData){
				var resultList = responseData;
				
				$("#chartList").html(resultList);

				getChart(currDate, dateRange); 
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
	function getChart(currDate, dateRange) {
			
		$("#chart").html("");
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/main/getChart"
			, data: $("form[name=boardChart]").serialize()
			, dataType:"html"
			, success : function(responseData){
				var resultList = responseData;
				
				//console.log("0000===="+responseData);
				//console.log("1111===="+resultList);
				$("#chart").html(resultList);

				//페이징 및 카운트 조회
				//goPageAndCount(pageNum);
				
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