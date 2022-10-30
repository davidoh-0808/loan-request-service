<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

<div class="chart_wrap">
								<div class="labels">
									<p>접수</p>
									<p>진행중</p>
									<p>완료</p>
									<!--<p>대기</p>  << 지점은 대기상태가 없다-->
									<!--<p>부재</p>  << 지점은 부재상태가 없다-->
									<p>취소</p>
								</div>
								<!-- canvas id="userStatus" 를 사용하여 Chart.JS에 add, drop data 하기 -->
								<canvas id="userStatus"  class="dash_chart"  height="150px" width="150px"></canvas>
							</div>
							<div class="chart_grid">
								<table class="chart_table">
									<caption>전체 상담 현황</caption>
									<colgroup>
										<col style="width: 110px;">
										<col style="width: 105px;">
										<col style="width: 105px;">
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">접수</th>
											<td><p id="chSubmit">${chartResult.SUBMIT_CNT}<span>건</span></p></td>
											<td><p id="chSubmitPer"><span>%</span></p></td>
										</tr>
										<tr>
											<th scope="row">진행 중</th>
											<td><p id="chProgress">${chartResult.SUM_PROGRESS_CNT}<span>건</span></p></td>
											<td><p id="chProgressPer"><span>%</span></p></td>
										</tr>
										<!--
										<tr>
											<th scope="row">대기</th>
											<td><p>120<span>건</span></p></td>
											<td><p>50<span>%</span></p></td>
										</tr>
										<< 지점은 대기상태가 없다
										-->
										<!--
										<tr>
											<th scope="row">부재</th>
											<td><p>120<span>건</span></p></td>
											<td><p>50<span>%</span></p></td>
										</tr>
										<< 지점은 부재상태가 없다
										-->
										<tr>
											<th scope="row">완료</th>
											<td><p id="chComplete">${chartResult.COMPLETE_CNT}<span>건</span></p></td>
											<td><p id="chCompletePer"><span>%</span></p></td>
										</tr>
										<tr>
											<th scope="row">취소</th>
											<td><p id="chCancel">${chartResult.CANCEL_CNT}<span>건</span></p></td>
											<td><p id="chCancelPer"><span>%</span></p></td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<th scope="row">전체</th>
											<td><p id="chTotal">${chartResult.TOTAL_CNT}<span>건</span></p></td>
											<td><p id="chTotalPer">100<span>%</span></p></td>
										</tr>
									</tfoot>
								</table>
							</div>
							
							<script>
							var userCtx = document.querySelector('#userStatus').getContext('2d');

							
							var CHART_TOTAL_CNT = ( "${chartResult.TOTAL_CNT}" ) || 0;
							var CHART_SUBMIT_CNT = ( "${chartResult.SUBMIT_CNT}" ) || 0;
							var CHART_PROGRESS_CNT = ( "${chartResult.SUM_PROGRESS_CNT}" ) || 0;
							var CHART_COMPLETE_CNT = ( "${chartResult.COMPLETE_CNT}" ) || 0;
							var CHART_CANCEL_CNT = ( "${chartResult.CANCEL_CNT}" ) || 0;

							var CHART_SUBMIT_PER = ( (CHART_SUBMIT_CNT / CHART_TOTAL_CNT) * 100 ) || 0;
							var CHART_PROGRESS_PER = ( (CHART_PROGRESS_CNT / CHART_TOTAL_CNT) * 100 ) || 0;
							var CHART_COMPLETE_PER = ( (CHART_COMPLETE_CNT / CHART_TOTAL_CNT) * 100 ) || 0;
							var CHART_CANCEL_PER = ( (CHART_CANCEL_CNT / CHART_TOTAL_CNT) * 100 ) || 0;
							
							//지점 상담 (건수, 퍼센트)
							$("#chTotal").html("<span>" + CHART_TOTAL_CNT + "건</span>");
							$("#chSubmit").html("<span>" + CHART_SUBMIT_CNT + "건</span>");
							$("#chProgress").html("<span>" + CHART_PROGRESS_CNT + "건</span>");
							$("#chComplete").html("<span>" + CHART_COMPLETE_CNT + "건</span>");
							$("#chCancel").html("<span>" + CHART_CANCEL_CNT + "건</span>");

							$("#chTotalPer").html("<span>" + Math.round( chTotalPer(CHART_SUBMIT_PER, CHART_PROGRESS_PER, CHART_COMPLETE_PER, CHART_CANCEL_PER) ) + "%</span>");
							$("#chSubmitPer").html("<span>" + Math.round( CHART_SUBMIT_PER ) + "%</span>");
							$("#chProgressPer").html("<span>" + Math.round( CHART_PROGRESS_PER ) + "%</span>");
							$("#chCompletePer").html("<span>" + Math.round( CHART_COMPLETE_PER ) + "%</span>");
							$("#chCancelPer").html("<span>" + Math.round( CHART_CANCEL_PER ) + "%</span>");
							//$("#branchDate").text("${currDate}");
							
							var userData = {
								labels: ['접수', '진행중', '완료', '취소', '0건'],
								datasets: [{
									label: '전체 상담 현황',
									data: [BOARD_SUBMIT_PER, BOARD_PROGRESS_PER, BOARD_COMPLETE_PER, BOARD_CANCEL_PER],
									backgroundColor: [
										//'#50B9F3', //전체
										'#64DECF', //접수
										'#1A528F', //진행중
										'#D9CBE2', //완료
										//'#50B9F3', //대기		<< 지점은 대기상태가 없다
										//'#EE4B6B', // 부재		<< 지점은 부재상태가 없다
										'#FDECB2', //취소
									],
									borderWidth: 1,
									hoverOffset: 4,
								}]
							}

							var userOption = {
								responsive: false,
								legend: {
									display: false,
								},
								interaction: {
									intersect: false,
									mode: 'index',
								 },
								tooltips: {
									callbacks: {
										title: function (tooltipItem, data) {
											return data['labels'][tooltipItem[0]['index']];
										},
										label: function (tooltipItem, data) {
											return data['datasets'][0]['data'][tooltipItem['index']] + '%';
										},
									},
									backgroundColor: '#000',
									titleFontSize: 12,
									titleFontColor: '#9B9FB5',
									bodyFontColor: '#fff',
									bodyFontSize: 14,
									displayColors: false,
								},
								plugins: {
									datalabels: {
										font: {
											size: 0,
										}
									},
								}

							}

							var userChart = new Chart(userCtx, {
								type: 'doughnut',
								data: userData,
								options: userOption
							});
							
							/* ==========================================================================
							Chart PluginService ( Chart Style )
							========================================================================== */
							Chart.pluginService.register({
								beforeDraw: function (chart) {
									let count = chart.chart.data.datasets[0].data
									let width = chart.chart.width,
										height = chart.chart.height,
										ctx = chart.chart.ctx;
									ctx.restore();

									if (chart.canvas.id === 'userStatus') {
										const _fill = ctx.fill;
										chart.data.datasets.forEach(set => {
											ctx.fill = function () {
												ctx.save();
												ctx.shadowColor = "rgba(0, 0, 0, 0.2)";
												ctx.shadowBlur = 16;
												ctx.shadowOffsetX = 0;
												ctx.shadowOffsetY = 2;
												_fill.apply(this);
												ctx.restore();
											};
										})
									}
								},
						    });
							
							//지점 상담 (차트)
							var chData = userChart.config.data.datasets[0].data;
							var newData = [CHART_SUBMIT_PER, CHART_PROGRESS_PER, CHART_COMPLETE_PER, CHART_CANCEL_PER];

							//꼭 차트 업데이트를 해주자
							if( CHART_SUBMIT_PER == 0 &&
									CHART_PROGRESS_PER == 0 &&
									CHART_COMPLETE_PER == 0 &&
									CHART_CANCEL_PER == 0
							) {
								userChart.clear();
								chData[0] = 0;
								chData[1] = 0;
								chData[2] = 0;
								chData[3] = 0;
								chData[4] = 100;
								userChart.update();
							} else {
								chData[0] = newData[0];
								chData[1] = newData[1];
								chData[2] = newData[2];
								chData[3] = newData[3];
								chData[4] = newData[4];
								userChart.update();
							}
							

							
							function chTotalPer(submit_per, progress_per, complete_per, cancel_per) {
								if ( isEmpty(submit_per) && isEmpty(progress_per) && isEmpty(complete_per) && isEmpty(cancel_per) ) {
									return 0;
								} else {
									return 100;
								}
							}
							</script>