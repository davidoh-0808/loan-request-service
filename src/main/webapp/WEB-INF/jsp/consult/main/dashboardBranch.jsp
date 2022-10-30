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
			<div class="content spot">
				<h2 class="con_tit">대시보드</h2>
				<div class="df board">
					<form name="boardChart">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
						<input type="hidden" id="dateRange" name="dateRange" value=""/>
					</form>
					<section class="dash_area first">
						<div class="top">
							<h3 class="s_tit">나의 상담</h3>
							<div class="event menu01">
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
							<%@ include file="/WEB-INF/jsp/consult/main/innerJsp/innerBranchBoard.jsp" %>
						</div>
					</section>
					<section class="dash_area ml20">
						<div class="top">
							<h3 class="s_tit">지점 상담 현황</h3>
							<div class="event menu02">
								<c:if test="${'daily' eq dateRange}"><span id="chartDate" class="date">${currDate}</span></c:if>
								<c:if test="${'monthly' eq dateRange}"><span id="chartDate" class="date">${currMonth}</span></c:if>
								<c:if test="${'yearly' eq dateRange}"><span id="chartDate" class="date">${currYear}</span></c:if>
								<ul>
									<li id="generalDaily"  <c:if test="${'daily' eq dateRange}">class="on"</c:if> onclick="getChart('${currDate}', 'daily')">일별</li>
									<li id="generalMonthly" <c:if test="${'monthly' eq dateRange}">class="on"</c:if> onclick="getChart('${currMonth}', 'monthly')">월별</li>
									<li id="generalYearly" <c:if test="${'yearly' eq dateRange}">class="on"</c:if> onclick="getChart('${currYear}', 'yearly')">연별</li>
								</ul>
							</div>
						</div>
						<div class="chart">
							<%@ include file="/WEB-INF/jsp/consult/main/innerJsp/innerBranchChart.jsp" %>
						</div>
					</section>
				</div>
				</div>
			</div>
			<!--//content-->

		</div>
		<!-- //관리자 하단 -->

	<!-- //Wrap-->

	<script>
		
	
	//********************* 날짜필터버튼 클릭 디스플레이 *********************
	//유저 필터링 메뉴
	$('.event.menu01 ul li').on('click', function(e){
		e.preventDefault();
		let isOn = $(this).hasClass('on');
		if(isOn) return;

		$('.event.menu01 ul li').removeClass('on');
		$(this).addClass('on');
	})

	//지점 필터링 메뉴
	$('.event.menu02 ul li').on('click', function(e){
		e.preventDefault();
		let isOn = $(this).hasClass('on');
		if(isOn) return;

		$('.event.menu02 ul li').removeClass('on');
		$(this).addClass('on');
	})
	//********************* 날짜필터버튼 클릭 디스플레이 *********************

		//페이지 이동 & 필터 sorting
	function getBoard(currDate, dateRange) {
		
		$("#boardDate").text(currDate);
		$("#dateRange").val(dateRange);
		
		$(".board_wrap").html("");
		
		//담당자배정 메소드 호출
		$.ajax({
			type:"POST"
			, url: "/consult/main/getBoard"
			, data: $("form[name=boardChart]").serialize()
			, dataType:"html"
			, success : function(responseData){
				var resultList = responseData;
				
				//console.log("0000===="+responseData);
				//console.log("1111===="+resultList);
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
		//페이지 이동 & 필터 sorting
	function getChart(currDate, dateRange) {
			
		
		$("#chartDate").text(currDate);
		$("#dateRange").val(dateRange);
		
		$(".chart").html("");
		
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
				$(".chart").html(resultList);

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