<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

<!-- 프로퍼티메세지 -->
<c:set var="messageValidationEmpty_REPORT_NAME"><spring:message code="validation.empty.input" arguments="이름을"/></c:set>
<c:set var="messageValidationEmpty_REPORT_PHONE"><spring:message code="validation.empty.input" arguments="연락처를"/></c:set>
<c:set var="messageValidationEmpty_STR_EMAIL"><spring:message code="validation.empty.input" arguments="이메일을"/></c:set>
<c:set var="messageValidationEmpty_REPORT_SUBJECT"><spring:message code="validation.empty.input" arguments="제목을"/></c:set>
<c:set var="messageValidationEmpty_REPORT_CONTENT"><spring:message code="validation.empty.input" arguments="상담내용을"/></c:set>
<c:set var="messageValidationCHECK_PERSONAL_INFO_CHECK"><spring:message code="validation.check.requiredMail" /></c:set>
</head>

<body>

<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="고객의 소리"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/thank/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/thank/include/lnb.jsp" %>
		<!-- lnb -->
		
		<section class="contents">
			<div class="group_title">
				<ul class="breadcrumb">
					<li>Home</li>
					<li>고마워요 미소금융</li>
					<li>고객의 소리</li>
				</ul>
				<h3>고객의 소리</h3>
			</div>
			
			<div class="group_policy">
				<p class="msg">현대차미소금융재단의 발전을 위해 칭찬 또는 개선 사항을 말씀해주세요.</p>
				<h4>개인정보처리방침</h4>
				<div class="desc">
				&nbsp;귀하는 동의를 거부할 권리가 있으나, 동의하지 않으실 경우 상담접수가 불가능할 수 있음을 알려드립니다.
				</div>
				<div class="detail_area">
				<p>
					현대차미소금융재단(이하 "재단"이라 합니다.)은 개인정보보호법 제30조에 따라 고객의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 고객의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
				</p>
	
				<h5>■ 제1조(개인정보의 처리 목적)</h5>
				<p>
					현대차미소금융재단은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.
				</p>
				<ol>
					<li>
						1.&nbsp;&nbsp;신용정보 제공ㆍ이용
						<ul>
							<li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						2.&nbsp;&nbsp;(금융)거래 관계
						<ul>
							<li>- 대출 등 지원여부 결정, 복지사업 중복지원 여부 조회, 대출자에 대한 컨설팅 등 사후관리 지원, 금융사고 예방 조사, 분쟁 해결 및 민원 처리 등의 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						3.&nbsp;&nbsp;봉사단 활동 등 관리
						<ul>
							<li>- 봉사자 및 수혜자의 봉사활동 등 신청과 관리업무 등을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						4.&nbsp;&nbsp;홈페이지 관리
						<ul>
							<li>- 홈페이지 이용자의 질문에 관한 응답과 서비스 제공을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						5.&nbsp;&nbsp;직원채용ㆍ인사관리 등
						<ul>
							<li>- 재단 직원채용ㆍ인사관리 등을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제2조(개인정보의 처리 및 보유 기간)</h5>
				<ol>
					<li>① 대출 등 지원여부 결정을 목적으로 처리하는 개인(신용)정보는 계약의 효력이 종료되는 날까지 보유ㆍ이용됩니다.&nbsp;&nbsp;단, 목적 달성일 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>② 복지사업 중복지원 여부 조회 등은 제공받은 날로부터 조회 목적을 달성한 날까지 보유ㆍ이용됩니다.&nbsp;&nbsp;단, 목적 달성 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>③ 홈페이지 이용자로부터 수집된 개인정보는 이용자의 홈페이지 이용계약 성립 시부터 이용계약 해지 시까지 보유ㆍ이용됩니다.&nbsp;&nbsp;단, 홈페이지 이용자의 이용계약 해지 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>④ 재단 임직원 등의 개인정보는 퇴직 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
				</ol>
	
				<h5>■ 제3조(개인정보 수집 출처 등 고지)</h5>
				<ol>
					<li>① 재단은 정보주체 이외로부터 수집한 개인정보를 처리하는 때에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 수집 출처, 처리 목적, 개인정보 처리의 정지를 요구할 권리가 있다는 사실을 정보주체에게 알립니다.</li>
					<li>② 개인정보 보호법 제20조 제2항 각 호에 근거하여 제1항에 따른 정보주체의 요구를 거부하는 경우에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 그 거부의 근거와 사유를 정보주체에게 알립니다.</li>
				</ol>
	
				<h5>■ 제4조(개인정보의 제3자 제공)</h5>
				<ol>
					<li>1.&nbsp;&nbsp;고객이 사전에 제3자 제공 및 공개에 동의한 경우</li>
					<li>2.&nbsp;&nbsp;다른 법률에 특별한 규정이 있는 경우</li>
					<li>3.&nbsp;&nbsp;고객 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 고객 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
					<li>4.&nbsp;&nbsp;통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</li>
				</ol>
	
				<h5>■ 제5조(고객의 권리ㆍ의무 및 그 행사방법)</h5>
				<ol>
					<li>① 고객은 재단이 처리하는 본인 및 14세 미만 아동(법정대리인만 해당)의 개인정보의 열람을 요구할 수 있습니다.</li>
					<li>② 자신의 개인정보를 열람한 고객은 사실과 다르거나 확인할 수 없는 개인정보에 대하여 재단에 정정 또는 삭제를 요구할 수 있습니다.&nbsp;&nbsp;다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</li>
					<li>③ 고객은 재단에 대하여 본인의 개인정보처리의 정지를 요구할 수 있습니다.&nbsp;&nbsp;다만 다음 각 호의 어느 하나에 해당하거나 다른 정당한 사유가 있는 경우에는 재단은 해당 사유를 고객에게 알리고, 처리정지 요구를 거절할 수 있습니다.
						<ol>
							<li>1.&nbsp;&nbsp;법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
							<li>2.&nbsp;&nbsp;다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
							<li>3.&nbsp;&nbsp;개인정보를 처리하지 아니하면 고객과 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 고객이 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
						</ol>
					</li>
				</ol>
	
				<h5>■ 제6조(개인정보처리 위탁)</h5>
				<ol>
					<li>① 재단은 다음 각 호와 같이 개인정보의 처리를 위탁하고 있습니다.
						<ol>
							<li>
								1.&nbsp;&nbsp;수탁업체 : 아이언마운틴 코리아(유)
							</li>
							<li>
								2.&nbsp;&nbsp;수탁의 목적 : 재단 내부 업무자료(대출원장 및 회계서류, 장부 등)
							</li>
							<li>3.&nbsp;&nbsp;개인정보제공항목: 개인식별정보(성명, 주민등록번호, 계좌번호, 주소, 연락처 등)</li>
							<li>4.&nbsp;&nbsp;개인정보 보유 기간<br />- 개인(신용)정보는 제공된 날로부터 동의 철회 또는 제공된 목적을 달성할 때까지 보유ㆍ이용됩니다.&nbsp;&nbsp;동의 철회 또는 제공된 목적 달성 후에는 위에 기재된 이용목적과 관련된 사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유ㆍ이용됩니다.</li>
						</ol>
					</li>
					<li>② 위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다.</li>
				</ol>
	
				<h5>■ 제7조(수집하는 개인정보의 항목)</h5>
				<p>재단은 업무 수행에 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
				<ol>
					<li>1.&nbsp;&nbsp;필수적 정보
						<ul>
							<li>◎ 개인식별정보 : 성명, 주민등록번호 등 고유식별정보, 국적, 직업, 주소, 전자우편 주소, 전화번호 등 연락처</li>
							<li>◎ (금융)거래정보<br />- 상품종류, 거래조건(만기, 담보 등), 거래일시, 금액 등 거래 설정 및 내역 정보</li>
							<li>◎ 신용평가를 위한 정보(여신거래에 한정)<br />- 신용능력정보: 채무ㆍ소득의 총액, 납세실적<br />- 신용도판단정보: 연체, 대위변제, 대지급, 부도, 관련인 발생사실 등</li>
							<li>◎ 기타 금융거래의 설정 유지ㆍ이행ㆍ관리를 위한 상담 등을 통해 생성되는 정보<br />※ 재단은 고객의 사생활을 침해할 우려가 있는 민감정보에 대해서는 원칙적으로 수집하지 않으며, 필요한 경우 고객의 별도 동의를 받아 수집하고 동의 목적을 위해서만 제한적으로 이용합니다.&nbsp;&nbsp;다만, 민감정보의 정확성, 최신성을 주기적으로 확인합니다.</li>
						</ul>
					</li>
					<li>2.&nbsp;&nbsp;수집방법
						<ol>
							<li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
							<li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
						</ol>
					</li>
				</ol>
	
				<h5>■ 제9조(개인정보의 안전성 확보 조치)</h5>
				<p>재단은 개인정보보호법 제29조에 따라 다음 각 호와 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</p>
				<ol>
					<li>1.&nbsp;&nbsp;개인정보의 암호화
						<ul>
							<li>고객의 개인정보 중 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
						</ul>
					</li>
					<li>2.&nbsp;&nbsp;해킹 등에 대비한 기술적 대책
						<ul>
							<li>재단은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신ㆍ점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.</li>
						</ul>
					</li>
					<li>3.&nbsp;&nbsp;개인정보처리시스템 접근 제한
						<ul>
							<li>개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
						</ul>
					</li>
					<li>4.&nbsp;&nbsp;개인정보 취급 직원의 최소화 및 교육
						<ul>
							<li>개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제10조(개인정보 처리방침의 변경)</h5>
				<p>재단은 개인정보 처리방침을 변경하는 경우에는 변경 및 시행의 시기, 변경된 내용을 지속적으로 공개하며, 변경된 내용은 고객이 쉽게 확인할 수 있도록 변경 전ㆍ후를 비교하여 공개합니다.</p>
	
				<h5>■ 제11조(임직원의 개인정보 처리)</h5>
				<p>재단 임직원의 개인정보 처리에 관하여도 본 개인정보 처리방침의 규정이 준용됩니다.</p>
	
				<h5>■ 제12조 (권익침해 구제방법)</h5>
				<p>고객은 개인정보침해로 인한 신고나 상담이 필요하신 경우 아래 기관에 문의하시기 바랍니다.</p>
				<ol>
					<li>1.&nbsp;&nbsp;개인정보분쟁조정위원회</li>
					<li>2.&nbsp;&nbsp;한국인터넷진흥원 개인정보침해신고센터(privacy.kisa.or.kr / 국번없이 118)</li>
					<li>3.&nbsp;&nbsp;정보보호마크인증위원회(www.eprivacy.or.kr / 02-580-0533~4)</li>
					<li>4.&nbsp;&nbsp;대검찰청 첨단범죄수사과 (www.spo.go.kr / 02-3480-2000)</li>
					<li>5.&nbsp;&nbsp;경찰청 사이버테러대응센터 (www.ctrc.go.kr / 1566-0112)</li>
				</ol>
	
				<h5>■ 제13조(개인정보 보호책임자 등)</h5>
				<p>재단은 개인정보 보호법 제31조 제1항에 따라 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보보호실무자를 지정하고 있습니다.</p>
				<ul>
					<li>개인정보보호책임자성명 : 경영관리팀장</li>
					<li>전화번호 : 02-361-6030</li>
					<li>이메일 : hsc032@hyundaismile.or.kr</li>
					<li>팩스 : 070-4850-8537</li>
				</ul>
	
				<ul>
					<li>고객서비스담당부서 : 경영관리팀</li>
					<li>전화번호 : 02-361-6000</li>
					<li>* 근무시간 : 09:00 ~ 18:00</li>
				</ul>
	
				<h5>■ 고지의 의무</h5>
				<p>현 개인정보처리방침 내용 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일전부터 홈페이지의 '공지사항'을 통해 고지할 것입니다.</p>
				</div>
				<label class="input_check">
					<input type="checkbox" id="" name="personal_info_check">
					<span class="label_text">동의합니다</span>
				</label>
			</div>
				<form name="form_report">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="report_email" />
				<div class="group_report">
					<h4>문의내용 작성</h4>
					<div class="desc">항목은 필수입력 항목입니다</div>
					<!-- 등록 테이블 -->
					<dl class="table_responsive">
						<dt><span class="required">성명</span></dt>
						<dd>
							<input name="report_name" type="text" class="form_control md" maxlength="" placeholder="">
						</dd>
						<dt><span class="required">연락처</span></dt>
						<dd>
							<input name="report_phone" type="text" class="form_control md" maxlength="" placeholder="">
						</dd>
						<dt><span class="required">이메일</span></dt>
						<dd>
							<div class="form_email">
								<input name="str_email01" type="text" class="form_control sm" maxlength="" placeholder="">
								<span class="dash">@</span>
								<input name="str_email02" type="text" class="form_control sm" maxlength="" placeholder="">
								<select id="selectEmail" name="selectEmail" class="form_control">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="hanmail.com">hanmail.com</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</div>
						</dd>
						<dt><span class="">문의유형</span></dt>
						<dd>
							<select class="form_control" name="report_type">
								<option value="칭찬합니다">칭찬합니다</option>
								<option value="불편해요">불편해요</option>
							</select>
						</dd>
						<dt><span class="required">제목</span></dt>
						<dd>
							<input name="report_subject" type="text" class="form_control" maxlength="" placeholder="">
						</dd>
						<dt><span class="required">상담내용</span></dt>
						<dd>
							<textarea name="report_content" id="" class="form_control"></textarea>
						</dd>
					</dl>
				</div>
			</form>
			<div class="submit_area">
				<button class="btn btn_lg btn_blue" data-btn="email_send">완료</button>
				<button class="btn btn_lg btn_white">취소</button>
			</div>
		</section>	<!--section-->
		

	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->
</div>

<script>

//이메일 입력방식 선택
$('#selectEmail').selectmenu({
	change: function(event, data){
		if("" == $(this).val()){
			$("form[name=form_report] input[name=str_email02]").attr("readonly", false);
		}else{
			$("form[name=form_report] input[name=str_email02]").attr("readonly", true);
		}
		$("form[name=form_report] input[name=str_email02]").val($(this).val());
	}
});


$('.btn_detail').on('click', function() {
	$('.group_terms').slideToggle(200);
})


//메일 발송
$("[data-btn=email_send]").on("click", function() {
	//openPopConfirmAlert("${messageSaveConfirm}", sendmail);
	sendmail();
});

// 신청
function sendmail(){
	// S: 유효성검사
		if (!$("input:checked[Name='personal_info_check']").is(":checked")){ 
			openPopAlertFocus($("form[name=form_report] input[name=personal_info_check]"), "${messageValidationCHECK_PERSONAL_INFO_CHECK}"); 
			return; 
		}
		if(isEmpty($("form[name=form_report] input[name=report_name]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=report_name]"), "${messageValidationEmpty_REPORT_NAME}");
			return;
		}
		if(isEmpty($("form[name=form_report] input[name=report_phone]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=report_phone]"), "${messageValidationEmpty_REPORT_PHONE}");
			return;
		}
		if(isEmpty($("form[name=form_report] input[name=str_email01]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=str_email02]"), "${messageValidationEmpty_STR_EMAIL}");
			return;
		}
		if(isEmpty($("form[name=form_report] input[name=str_email02]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=str_email02]"), "${messageValidationEmpty_STR_EMAIL}");
			return;
		}
		if(isEmpty($("form[name=form_report] input[name=report_subject]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=report_subject]"), "${messageValidationEmpty_REPORT_SUBJECT}");
			return;
		}
		if(isEmpty($("form[name=form_report] textarea[name=report_content]").val())){
			openPopAlertFocus($("form[name=form_report] input[name=report_content]"), "${messageValidationEmpty_REPORT_CONTENT}");
			return;
		}
	// E: 유효성검사
	
	$("form[name=form_report] input[name=report_email]").val($("form[name=form_report] input[name=str_email01]").val() + "@" + $("form[name=form_report] input[name=str_email02]").val());
	
	
	$.ajax({
		type:"POST"
		, url: "/common/sendmailReport"
		, data: $("form[name=form_report]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlert("문의메일이 발송되었습니다.");
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

