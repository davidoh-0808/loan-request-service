<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<script src='/resources/common/js/jquery.cookie.js'></script>
 
<spring:eval expression="@environment.getProperty('mastercode.member_gubun.consult')"
             var="MASTERCODE_MEMBER_GUBUN_CONSULT"/>
<spring:eval expression="@environment.getProperty('mastercode.platform_gubun.pc')" var="MASTERCODE_PLATFORM_GUBUN_PC"/>

<!-- 프로퍼티메세지 -->
<c:set var="messageMissingFieldRequired_ID"><spring:message code="validation.empty.input" arguments="ID를"/></c:set>
<c:set var="messageMissingFieldRequired_PASSWORD"><spring:message code="validation.empty.input" arguments="PASSWORD를"/></c:set>

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>
<!-- 프로퍼티메세지 -->

</head>

<body>

<!--wrap -->
<div class="admin_main">
    <form name="frm" method="post" action="/login/loginProcess">
        <input type="hidden" name="member_gubun" value="${MASTERCODE_MEMBER_GUBUN_CONSULT }">
        <input type="hidden" name="platform_gubun" value="${MASTERCODE_PLATFORM_GUBUN_PC }">
        <input type="hidden" id="login_otp" name="login_otp" value="">
        <input type="hidden" id="otp_key" name="otp_key" value="${otpKey}">
        <!-- 로그인 -->
        <div class="login_wrap pt">
            <div class="main_login pd">
                <h1><img src="/resources/publish/img/h_main_logo.gif" alt="서민금융진흥원 사업수행기관 현대차미소금융재단"></h1>
                <dl class="id">
                    <dt>ID</dt>
                    <dd><input type="text" name="MEMBER_ID" value="${memberId}" placeholder="아이디를 입력해주세요">
                    </dd>
                </dl>
                <dl class="pw">
                    <dt>PASSWORD</dt>
                    <dd><input type="password" name="MEMBER_PW" value="${memberPw}"
                               placeholder="비밀번호를 입력해주세요"></dd>
                </dl>
                <button type="button" class="btn btn_radius pop_btn btn_login" data-btn="btnLogin">로그인</button>
                <div class="privacy">
					<span class="tit">개인 정보 취급 안내<i class="icon_detail"></i></span>
					<div class="txt_box">
						<h5>개인정보처리방침</h5>
						<p>현대차미소금융재단은 개인정보보호법 제30조에 따라 고객의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 고객의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.</p>
						<h5>■ 제1조(개인정보의 처리 목적)</h5>
						<p>현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.</p>
						<ol>
							<li>
								1. 신용정보 제공ㆍ이용
								<ul>
									<li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
								</ul>
							</li>
							<li>
								2. (금융)거래 관계
								<ul>
									<li>- 대출 등 지원여부 결정, 복지사업 중복지원 여부 조회, 대출자에 대한 컨설팅 등 사후관리 지원, 금융사고 예방 조사, 분쟁 해결 및 민원 처리 등의 목적으로 개인정보를 처리합니다.</li>
								</ul>
							</li>
							<li>
								3. 봉사단 활동 등 관리
								<ul>
									<li>- 봉사자 및 수혜자의 봉사활동 등 신청과 관리업무 등을 목적으로 개인정보를 처리합니다.</li>
								</ul>
							</li>
							<li>
								4. 홈페이지 관리
								<ul>
									<li>- 홈페이지 이용자의 질문에 관한 응답과 서비스 제공을 목적으로 개인정보를 처리합니다.</li>
								</ul>
							</li>
							<li>
								5. 직원채용ㆍ인사관리 등
								<ul>
									<li>- 재단 직원채용ㆍ인사관리 등을 목적으로 개인정보를 처리합니다.</li>
								</ul>
							</li>
						</ol>
						<h5>■ 제2조(개인정보의 처리 및 보유 기간)</h5>
						<ol>
							<li>① 대출 등 지원여부 결정을 목적으로 처리하는 개인(신용)정보는 계약의 효력이 종료되는 날까지 보유ㆍ이용됩니다. 단, 목적 달성일 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
							<li>② 복지사업 중복지원 여부 조회 등은 제공받은 날로부터 조회 목적을 달성한 날까지 보유ㆍ이용됩니다. 단, 목적 달성 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
							<li>③ 홈페이지 이용자로부터 수집된 개인정보는 이용자의 홈페이지 이용계약 성립 시부터 이용계약 해지 시까지 보유ㆍ이용됩니다. 단, 홈페이지 이용자의 이용계약 해지 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
							<li>④ 재단 임직원 등의 개인정보는 퇴직 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
						</ol>
						<h5>■ 제3조(개인정보 수집 출처 등 고지)</h5>
						<ol>
							<li>① 재단은 정보주체 이외로부터 수집한 개인정보를 처리하는 때에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 수집 출처, 처리 목적, 개인정보 처리의 정지를 요구할 권리가 있다는 사실을 정보주체에게 알립니다.</li>
							<li>② 개인정보 보호법 제20조 제2항 각 호에 근거하여 제1항에 따른 정보주체의 요구를 거부하는 경우에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 그 거부의 근거와 사유를 정보주체에게 알립니다.</li>
						</ol>
						<h5>■ 제4조(개인정보의 제3자 제공)</h5>
						<ol>
							<li>1. 고객이 사전에 제3자 제공 및 공개에 동의한 경우</li>
							<li>2. 다른 법률에 특별한 규정이 있는 경우</li>
							<li>3. 고객 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 고객 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
							<li>4. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</li>
						</ol>
						<h5>■ 제5조(고객의 권리ㆍ의무 및 그 행사방법)</h5>
						<ol>
							<li>① 고객은 재단이 처리하는 자신 및 14세 미만 아동(법정대리인만 해당)의 개인정보의 열람을 요구할 수 있습니다.</li>
							<li>② 자신의 개인정보를 열람한 고객은 사실과 다르거나 확인할 수 없는 개인정보에 대하여 재단에 정정 또는 삭제를 요구할 수 있습니다. 다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</li>
							<li>
								③ 고객은 재단에 대하여 자신의 개인정보 처리의 정지를 요구할 수 있습니다. 다만 다음 각 호의 어느 하나에 해당하거나 다른 정당한 사유가 있는 경우에는 재단은 해당 사유를 고객에게 알리고, 처리정지 요구를 거절할 수 있습니다.
								<ul>
									<li>1. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
									<li>2. 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
									<li>3. 개인정보를 처리하지 아니하면 고객과 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 고객이 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
								</ul>
							</li>
						</ol>
						<h5>■ 제6조(개인정보처리 위탁)</h5>
						<ol>
							<li>① 재단은 다음 각 호와 같이 개인정보의 처리를 위탁하고 있습니다.
								<ol>
									<li>1. 수탁업체<br>- ㈜다은종합물류</li>
									<li>2. 수탁의 목적<br>- 재단 내부 업무자료(대출원장 및 회계서류, 장부 등)</li>
									<li>3. 개인정보 제공항목<br>- 개인식별정보: 성명, 주민번호, 계좌번호, 주소, 연락처 등</li>
									<li>4. 개인정보 보유 기간
										<br>- 개인(신용)정보는 제공된 날로부터 동의 철회 또는 제공된 목적을 달성할 때까지 보유ㆍ이용됩니다. 동의 철회 또는 제공된 목적 달성 후에는 위에 기재된 이용목적과 관련된 사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유ㆍ이용됩니다.</li>
								</ol>
							</li>
							<li>② 위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다.</li>
						</ol>
						<h5>■ 제7조(수집하는 개인정보의 항목)</h5>
						<p>재단은 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
						<p>재단은 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
						<ol>
							<li>1. 필수적 정보
								<ul>
									<li>◎ 개인식별정보
										<br>- 성명, 주민등록번호 등 고유식별정보, 국적, 직업, 주소 전자우편 주소 전화번호 등 연락처
									</li>
									<li>
										◎ (금융)거래정보
										<br>- 상품종류, 거래조건(만기, 담보 등), 거래일시, 금액 등 거래 설정 및 내역 정보
									</li>
									<li>
										◎ 신용평가를 위한 정보(여신거래에 한정)
										<br>- 신용능력정보: 채무ㆍ소득의 총액, 납세실적
										<br>- 신용도판단정보: 연체, 대위변제, 대지급, 부도, 관련인 발생사실 등
									</li>
									<li>
										◎ 기타 금융거래의 설정 유지ㆍ이행ㆍ관리를 위한 상담 등을 통해 생성되는 정보
										<br>※ 재단은 고객의 사생활을 침해할 우려가 있는 민감정보에 대해서는 원칙적으로 수집하지 않으며, 필요한 경우 고객의 별도 동의를 받아 수집하고 동의 목적을 위해서만 제한적으로 이용합니다. 다만, 민감정보의 정확성, 최신성을 주기적으로 확인합니다.
									</li>
								</ul>
							</li>
							<li>
								2. 수집방법
								<ul>
									<li>◎ 금융회사, 공공기관 등으로부터 전산매체를 통해 수집</li>
									<li>◎ 홈페이지, 서면양식</li>
								</ul>
							</li>
						</ol>
						<h5>■ 제8조(개인정보의 파기)</h5>
						<ol>
							<li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
							<li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
						</ol>
						<h5>■ 제9조(개인정보의 안전성 확보 조치)</h5>
						<p>재단은 개인정보보호법 제29조에 따라 다음 각 호와 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</p>
						<ol>
							<li>1. 개인정보의 암호화
								<ul>
									<li>고객의 개인정보 중 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
								</ul>
							</li>
							<li>
								2. 해킹 등에 대비한 기술적 대책
								<ul>
									<li>
										재단은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신ㆍ점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.</li>
								</ul>
							</li>
							<li>3. 개인정보처리시스템 접근 제한
								<ul>
									<li>개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
								</ul>
							</li>
							<li>
								4. 개인정보 취급 직원의 최소화 및 교육
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
							<li>1. 개인정보분쟁조정위원회</li>
							<li>2. 한국인터넷진흥원 개인정보침해신고센터(privacy.kisa.or.kr / 국번없이 118)</li>
							<li>3. 정보보호마크인증위원회(www.eprivacy.or.kr / 02-580-0533~4)</li>
							<li>4. 대검찰청 첨단범죄수사과 (www.spo.go.kr / 02-3480-2000)</li>
							<li>5. 경찰청 사이버테러대응센터 (www.ctrc.go.kr / 1566-0112)</li>
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
							<li>고객서비스담당부서 : 리스크관리팀</li>
							<li>전화번호 : 02-361-6000</li>
							<li>* 근무시간 : 09:00 ~ 18:00</li>
						</ul>
						<h5>■ 고지의 의무</h5>
						<p>현 개인정보처리방침 내용 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일전부터 홈페이지의 '공지사항'을 통해 고지할 것입니다.</p>
					</div>
				</div>
            </div>
        </div>
        <!-- //로그인 -->
    </form>
</div>
	<!-- pop_otp -->
	<div id="pop_otp" style="display: none;">
        <div class="pop_contents">
            <div class="qr_box">
                <img id="otp_qr_img" src="${qrOtpUrl}" alt="">
            </div>
            <ul class="step_list">
                <li> 휴대폰으로 QR코드 인식 후 Google Authenticator 설치<br>(iOS일 경우 Google Authenticator 설치) </li>
                <li>어플 실행 후 QR 코드 스캔 메뉴 선택</li>
                <li>하단에 OTP 인증번호 입력</li>
            </ul>
			<div class="otp_input">
				<strong>인증번호</strong>
				<input type="number" placeholder="OTP 인증번호 입력" class="form_control" id="first_login_otp_val">
			</div>
        </div>
    </div>

	<div id="pop_fail" class="pop_wrap" style="display: none;">
        <div class="pop_contents">
            <p class="msg">OTP 인증번호가 일치하지 않습니다.</p>
        </div>
    </div>

	<div id="pop_success" class="pop_wrap" style="display: none;">
        <div class="pop_contents">
            <p class="msg">OTP 등록이 완료되었습니다.</p>
        </div>
    </div>

<!-- //Wrap-->
<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->


<script type="text/javascript">
 $(function () {
	$('#pop_otp').dialog({
		dialogClass: 'pop_otp',
		width: 700,
		title: 'OTP 등록',
		modal: true,
		buttons: [
			{
				text: "확인",
				class: "btn btn_l btn_blue btn_otp",
				click: function () {
					
					otpCheck();
					//$(this).dialog("close");
				}
			},
		]
	});
	/*
	$('#pop_fail').dialog({
		dialogClass: 'pop_alert',
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function () {
					$(this).dialog("close");
				}
			},
		]
	});

	$('#pop_success').dialog({
		dialogClass: 'pop_alert',
		buttons: [
			{
				text: "확인",
				class: "btn btn_m btn_blue",
				click: function () {
					$(this).dialog("close");
				}
			},
		]
	});
	*/
}); 
/* 
    <c:if test="${not empty exceptionMsg}">
    openPopAlert("${exceptionMsg}");
    <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
    </c:if>
 */
    // 임시
    //$("form[name=frm] input[name=MEMBER_ID]").val("admin");
    //$("form[name=frm] input[name=MEMBER_PW]").val("1234");


    //쿠키에저장된아이디 사용하기
    var cookie_admin_member_id = $.cookie("cookie_admin_member_id");
    if (isNotEmpty(cookie_admin_member_id)) {
        $("form[name=frm] input[name=MEMBER_ID]").val(cookie_admin_member_id);
    }

    $("form[name=frm] input[name=MEMBER_ID], form[name=frm] input[name=MEMBER_PW]").keyup(function (key) {
        if (key.keyCode === 13) {
            goLogin();
        	//googleOtpCall();
        }
    });
    $("[data-btn=btnLogin]").on("click", function () {
        goLogin();
        //googleOtpCall();
    });

    // 최초 로그인 사용자 OTP 팝업 엔터 이벤트
    $("#first_login_otp_val").keyup(function (key) {
        if (key.keyCode == 13) {
        	
        	otpCheck();
        }
    });
    

    
    // 로그인
    function goLogin() {
        if (isEmpty($("form[name=frm] input[name=MEMBER_ID]").val())) {
            openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${messageMissingFieldRequired_ID}");
            return;
        }

        if (isEmpty($("form[name=frm] input[name=MEMBER_PW]").val())) {
            openPopAlertFocus($("form[name=frm] input[name=MEMBER_PW]"), "${messageMissingFieldRequired_PASSWORD}");
            return;
        }
        // 전송시 로딩바노출
        $("body").addClass("loading");

        // 쿠키에아이디저장(1년)
        $.cookie("cookie_admin_member_id", $("form[name=frm] input[name=MEMBER_ID]").val(), {expires: 365, path: '/'});

        $("form[name=frm]").submit();
    }

    // otp 인증하기
    function otpCheck(){
    	var first_login_otp_val = $("#first_login_otp_val").val();
    	if(first_login_otp_val ==""){	//입력된 인증번호가 없으면..
    		$('#pop_fail').dialog({
				dialogClass: 'pop_alert',
				buttons: [
					{
						text: "확인",
						class: "btn btn_m btn_blue",
						click: function () {
							$(this).dialog("close");
						}
					},
				]
			});
    	}else{
        	$("#login_otp").val(first_login_otp_val);
    		//console.log("login_otp-=------------------"+first_login_otp_val);
    		//console.log("login_otp-=------------------"+$("#login_otp").val());
    		
        	$.ajax({
        		type:"GET"
        		, url: "/login/loginCheckOtp"
        		, data: $("form[name=frm]").serialize()
        		, dataType:"json"
        		, success : function(responseData){
        			var data = responseData.resultJson;
        			if(data.rCode == "0000") {
        				
        				$('#pop_success').dialog({
        					dialogClass: 'pop_alert',
        					buttons: [
        						{
        							text: "확인",
        							class: "btn btn_m btn_blue",
        							click: function () {
        								location.href = data.return_url;
        								//$(this).dialog("close");
        							}
        						},
        					]
        				});
        				
        			} else {
        				
        				$('#pop_fail').dialog({
        					dialogClass: 'pop_alert',
        					buttons: [
        						{
        							text: "확인",
        							class: "btn btn_m btn_blue",
        							click: function () {
        								$(this).dialog("close");
        							}
        						},
        					]
        				});
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

    }
    /* 
    function first_login_otp(qr_img_url) {
        $("#otp_qr_img").attr('src', qr_img_url);
        $('#pop_otp').dialog({
                dialogClass: 'popup pop_table pop_login',
                width: 480,
                title: 'OTP 등록',
                modal: true,
                buttons: [{
                    text: "확인",
                    class: "btn btn_md btn_blue",
                    click: function () {
                        login_form_submit("first_login");
                    }
                }, ]
            },
            $('#pop_otp').find('.qr_box').addClass('on')
        );
    } */
</script>

</body>
</html>