<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>


<!-- 프로퍼티메세지 -->
<c:set var="messageValidationEmpty_CONSULT_NAME"><spring:message code="validation.empty.input" arguments="이름을"/></c:set>
<c:set var="messageValidationEmpty_CONSULT_REGI_NO"><spring:message code="validation.empty.input" arguments="주민등록 번호를"/></c:set>
<c:set var="messageValidationEmpty_CONSULT_HP_NO"><spring:message code="validation.empty.input" arguments="휴대폰 번호를"/></c:set>
<c:set var="messageValidationCHECK_INFO_CHECK"><spring:message code="validation.check.required"/></c:set>
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
                        <li class="on">본인확인</li>
                        <li>정보입력</li>
                        <li>신청완료</li>
                    </ul>
                </div>
                <div class="right">
                    <div class="notice">
                        <p>대출상담은 고객님의 신용평점에 영향을 주지 않습니다.</p>
                        <span>대출상담 02 - 361 - 6000으로 연락주시면 상담원과 상담 가능합니다. (평일 오전 9시 ~ 오후 5시)</span>
                    </div>
                    
		<form name="form_chk" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
		<input type="hidden" id="CUST_REGI_NO" name="CUST_REGI_NO" placeholder="">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="EncodeData" name="EncodeData" value="${sEncData }">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
		<input type="hidden" id="AUTH_TYPE" name="AUTH_TYPE" value="">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
 				<!-- nice 모듈 리턴값 start-->
 				<input type="hidden" id="sName" name="sName" value="${sName}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sBirthDate" name="sBirthDate" value="${sBirthDate}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sGender" name="sGender" value="${sGender}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sMobileNo" name="sMobileNo" value="${sMobileNo }">
              <!-- nice 모듈 리턴값 end-->
                    <div class="request_certification">
                        <h4><span class="emp_red">본인인증</span></h4>
                        <div class="tabs">
                            <ul>
                                <li><a href="javascript:void(0);" class="on">휴대폰 인증</a></li>
                                <li><a href="javascript:void(0);">인증서</a></li>
                            </ul>
                        </div>
                        <!-- 휴대폰 인증tab -->
                        <div class="tab_contents  on">
                            <ul class="terms_list">
                                <li class="terms_item terms_all">
                                    <label class="input_check blank">
                                        <input type="checkbox" id="">
                                        <span class="label_text">체크박스 체크해제</span>
                                    </label>
                                    <div class="cont">
                                        <h5>휴대폰 인증 이용약관 전체 동의</h5>
                                        <button type="button" class="icon icon_arrow btn_phall"></button>
                                    </div>
                                </li>
                                <li class="terms_item">
                                    <label class="input_check blank">
                                        <input type="checkbox">
                                        <span class="label_text">체크박스 체크해제</span>
                                    </label>
                                    <div class="cont">
                                        <span><em class="c_red">(필수)</em> 개인정보 수집·이용·취급위탁 동의</span>
                                        <button type="button" class="icon icon_arrow btn_ph01"></button>
                                    </div>
                                </li>
                                <li class="terms_item">
                                    <label class="input_check blank">
                                        <input type="checkbox">
                                        <span class="label_text">체크박스 체크해제</span>
                                    </label>
                                    <div class="cont">
                                        <span><em class="c_red">(필수)</em> 본인확인 이용약관</span>
                                        <button type="button" class="icon icon_arrow btn_ph02"></button>
                                    </div>
                                </li>
                                <li class="terms_item">
                                    <label class="input_check blank">
                                        <input type="checkbox">
                                        <span class="label_text">체크박스 체크해제</span>
                                    </label>
                                    <div class="cont">
                                        <span><em class="c_red">(필수)</em> 고유식별정보 처리 동의</span>
                                        <button type="button" class="icon icon_arrow btn_ph03"></button>
                                    </div>
                                </li>
                                <li class="terms_item">
                                    <label class="input_check blank">
                                        <input type="checkbox">
                                        <span class="label_text">체크박스 체크해제</span>
                                    </label>
                                    <div class="cont">
                                        <span><em class="c_red">(필수)</em> 통신사 본인확인 이용약관 동의</span>
                                        <button type="button" class="icon icon_arrow btn_ph04"></button>
                                    </div>
                                </li>
                            </ul>
                            <dl class="table_responsive">
                                <dt><span>성명</span></dt>
                                <dd>
                                    <input type="text" name="custNm" class="form_control lg" placeholder="성명">
                                </dd>
                                <dt><span>주민등록번호</span></dt>
                                <dd>
                                    <div class="form_number">
                                        <input type="text" name="custRegiNo1" class="form_control xs" placeholder="생년월일" maxlength="6" >
                                        <span class="hyphen">-</span>
                                        <input type="password" name="custRegiNo2" class="form_control xs" placeholder="뒷자리 7자리" maxlength="7">
                                    </div>
                                </dd>
                                <dt><span>휴대폰 번호</span></dt>
                                <dd>
                                    <ul class="content_box">
                                        <li>
                                            <select class="form_control">
                                                <option >통신사</option>
                                                <option value>SKT</option>
                                                <option value>KT</option>
                                                <option value>LG U+</option>
                                                <option value>SKT알뜰폰</option>
                                                <option value>KT알뜰폰</option>
                                                <option value>LG U+알뜰폰</option>
                                         </select>
                                        </li>
                                        <li>
                                            <input type="text" name="custMobile" class="form_control" placeholder="연락처('-'없이 입력)" minlength="11" maxlength="11">
                                        </li>
                                        <li>
                                            <button type="button" id="btnSendSms" class="btn btn_sm btn_black">인증번호 발송</button>
                                        </li>
                                    </ul>
                                </dd>
                                <dt class="form_confirm">
                                    <span>인증번호</span>
                                </dt>
                                <dd class="form_confirm">
                                    <ul class="content_box">
                                        <li><input type="text" class="form_control lg" minlength="6" maxlength="6"></li>
                                        <li><button type="button" id="btnConfirmSms" class="btn btn_sm btn_black">인증번호 확인</button></li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <!-- 인증서tab -->
                        <div class="tab_contents">

                            <dl class="table_responsive">
                                <dt><span>성명</span></dt>
                                <dd>
                                    <input type="text" class="form_control lg" placeholder="성명">
                                </dd>
                                <dt><span>주민등록번호</span></dt>
                                <dd>
                                    <div class="form_number">
                                        <input type="text" id="regiNo1" name="REGI_NO1" class="form_control xs" placeholder="생년월일" maxlength="6" >
                                        <span class="hyphen">-</span>
                                        <input type="password" id="regiNo2" name="REGI_NO2" class="form_control xs" placeholder="뒷자리 7자리" maxlength="7">
                                    </div>
                                </dd>
                                <dt><span>휴대폰 번호</span></dt>
                                <dd>
                                    <input type="text" id="custHpNo" name="CUST_HP_NO" class="form_control lg" placeholder="연락처('-'없이 입력)">
                                </dd>
                            </dl>
                            <div class="tab_inner">
                                <button class="btn btn_md  btn_radius btn_black w200">인증서 선택하기</button>
                                <p class="desc">인증서는 가까운,은행,우체국,증권사에서 인터넷뱅킹,증권거래용 인증서를 발급 받으신 후 이용하시기 바랍니다.</p>
                            </div>
                        </div>
                    </div>
                    <div class="request_agree" style="display:none">
                        <h4><span class="emp_red">약관 및 이용동의</span></h4>
                        <div class="desc">귀하는 개인정보 수집 이용동의를 거부할 권리가 있으나, 동의하지 않으실 경우 상담이 불가능할 수 있습니다.</div>
                        <ul class="terms_list">
                            <li class="terms_item terms_all btn_all">
                                <label class="input_check blank">
                                    <input type="checkbox" id="check1" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <h5>전체 동의</h5>
                                    <button type="button" class="icon icon_arrow"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more01">
                                <label class="input_check blank">
                                    <input type="checkbox" id="check2" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span><em class="c_red">(필수)</em> 개인(신용) 정보조회 및 수집·이용·제공 동의(여신금융거래)</span>
                                    <button type="button" class="icon icon_arrow"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more02">
                                <label class="input_check blank">
                                    <input type="checkbox" id="check3" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span><em class="c_red">(필수)</em> 개인(신용) 정보조회 및 수집·이용·제공 동의(정책상품거래)</span>
                                    <button type="button" class="icon icon_arrow"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more03">
                                <label class="input_check blank">
                                    <input type="checkbox" id="check4" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span><em class="c_red">(필수)</em> 개인(신용) 정보조회 및 수집·이용·제공 동의서</span>
                                    <button type="button" class="icon icon_arrow btn_more03"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more04">
                                <label class="input_check blank">
                                    <input type="checkbox" id="check5" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span><em class="c_red">(필수)</em> 개인(신용) 정보조회 및 수집·이용·제공 동의(공공마이데이터꾸러미)</span>
                                    <button type="button" class="icon icon_arrow btn_more04"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more05">
                                <label class="input_check blank btn_more05">
                                    <input type="checkbox" id="check6" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span>(선택) 개인(신용) 정보 수집·이용 동의(서민금융진흥원)</span>
                                    <button type="button" class="icon icon_arrow btn_more05"></button>
                                </div>
                            </li>
                            <li class="terms_item btn_more06">
                                <label class="input_check blank btn_more06">
                                    <input type="checkbox" id="check7" name="CHECK">
                                    <span class="label_text">체크박스 체크해제</span>
                                </label>
                                <div class="cont">
                                    <span>(선택) 개인(신용)정보 수집·이용·제공 동의(상품 서비스 안내 등)</span>
                                    <button type="button" class="icon icon_arrow btn_more06"></button>
                                </div>
                            </li>
                        </ul>
                    </div>
                    </form>
                
                
              <!--   <div class="request_certification">
                    <h4><span class="emp_red">인증선택</span></h4>
                    <ul class="select_list">
                        <li class="on">
                            <a href="javascript:void(0)" onclick="fnNicePopup('M')" class="btn btn_md btn_lightgray btn_radius w200">휴대폰 인증</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="fnNicePopup('U')" class="btn btn_md btn_lightgray btn_radius w200">공동인증서</a>
                        </li>
                    </ul>
                </div> -->
                <div class="btn_area">
                    <button type="button" class="btn btn_lg btn_blue" onclick="fnNicePopup();">나이스팝업</button>
                    <button type="button" class="btn btn_lg btn_blue" onclick="fnNiceSocket();">나이스소켓</button>
                    <button type="button" class="btn btn_lg btn_blue" onclick="fnNiceCerti();">나이스공인</button>
                    <button type="button" class="btn btn_lg btn_blue" onclick="nextStep2();">다음</button>
                </div>
            </section>
            <!--section-->
        </main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<!-- 약관 팝업 -->
<div id="pop_terms" class="pop_wrap" title="이용약관">
    <div class="pop_contents">
        <div class="desc">
            현대차미소금융재단 사이트를 이용해 주셔서 감사합니다. 본 약관은 현대차 미소금융재단과 이용자의 권리&middot;의무 및 책임사항을 규정함을 목적으로 합니다.
        </div>
        <div class="terms">
            <h4>개인정보처리방침</h4>
            <p>
                현대차미소금융재단은 개인정보보호법 제30조에 따라 고객의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 고객의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
            </p>

            <h5>■ 제1조(개인정보의 처리 목적)</h5>
            <p>
                현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.
            </p>
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
                <li>③ 고객은 재단에 대하여 자신의 개인정보 처리의 정지를 요구할 수 있습니다. 다만 다음 각 호의 어느 하나에 해당하거나 다른 정당한 사유가 있는 경우에는 재단은 해당 사유를 고객에게 알리고, 처리정지 요구를 거절할 수 있습니다.
                    <ol>
                        <li>1. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
                        <li>2. 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
                        <li>3. 개인정보를 처리하지 아니하면 고객과 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 고객이 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
                    </ol>
                </li>
            </ol>

            <h5>■ 제6조(개인정보처리 위탁)</h5>
            <ol>
                <li>① 재단은 다음 각 호와 같이 개인정보의 처리를 위탁하고 있습니다.
                    <ol>
                        <li>
                            1. 수탁업체<br />- ㈜다은종합물류
                        </li>
                        <li>
                            2. 수탁의 목적<br />- 재단 내부 업무자료(대출원장 및 회계서류, 장부 등)
                        </li>
                        <li>3. 개인정보 제공항목<br />- 개인식별정보: 성명, 주민번호, 계좌번호, 주소, 연락처 등</li>
                        <li>4. 개인정보 보유 기간<br />- 개인(신용)정보는 제공된 날로부터 동의 철회 또는 제공된 목적을 달성할 때까지 보유ㆍ이용됩니다. 동의 철회 또는 제공된 목적 달성 후에는 위에 기재된 이용목적과 관련된 사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유ㆍ이용됩니다.</li>
                    </ol>
                </li>
                <li>② 위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다.</li>
            </ol>

            <h5>■ 제7조(수집하는 개인정보의 항목)</h5>
            <p>재단은 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
            <ol>
                <li>1. 필수적 정보
                    <ul>
                        <li>◎ 개인식별정보<br />- 성명, 주민등록번호 등 고유식별정보, 국적, 직업, 주소 전자우편 주소 전화번호 등 연락처</li>
                        <li>◎ (금융)거래정보<br />- 상품종류, 거래조건(만기, 담보 등), 거래일시, 금액 등 거래 설정 및 내역 정보</li>
                        <li>◎ 신용평가를 위한 정보(여신거래에 한정)<br />- 신용능력정보: 채무ㆍ소득의 총액, 납세실적<br />- 신용도판단정보: 연체, 대위변제, 대지급, 부도, 관련인 발생사실 등</li>
                        <li>◎ 기타 금융거래의 설정 유지ㆍ이행ㆍ관리를 위한 상담 등을 통해 생성되는 정보<br />※ 재단은 고객의 사생활을 침해할 우려가 있는 민감정보에 대해서는 원칙적으로 수집하지 않으며, 필요한 경우 고객의 별도 동의를 받아 수집하고 동의 목적을 위해서만 제한적으로 이용합니다. 다만, 민감정보의 정확성, 최신성을 주기적으로 확인합니다.</li>
                    </ul>
                </li>
                <li>2. 수집방법
                    <ol>
                        <li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
                        <li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
                    </ol>
                </li>
            </ol>

            <h5>■ 제9조(개인정보의 안전성 확보 조치)</h5>
            <p>재단은 개인정보보호법 제29조에 따라 다음 각 호와 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</p>
            <ol>
                <li>1. 개인정보의 암호화
                    <ul>
                        <li>고객의 개인정보 중 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
                    </ul>
                </li>
                <li>2. 해킹 등에 대비한 기술적 대책
                    <ul>
                        <li>재단은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신ㆍ점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.</li>
                    </ul>
                </li>
                <li>3. 개인정보처리시스템 접근 제한
                    <ul>
                        <li>개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
                    </ul>
                </li>
                <li>4. 개인정보 취급 직원의 최소화 및 교육
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
                <li>개인정보보호책임자성명 : 오현덕</li>
                <li>전화번호 : 02-361-6060</li>
                <li>이메일 : hsc025@hyundaismile.or.kr</li>
                <li>팩스 : 02-361-6038</li>
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

<div id="pop_policy" class="pop_wrap" title="개인정보 처리방침">
    <div class="pop_contents">
        <div class="desc">
            현대차미소금융재단 사이트를 이용해 주셔서 감사합니다. 본 약관은 현대차 미소금융재단과 이용자의 권리&middot;의무 및 책임사항을 규정함을 목적으로 합니다.
        </div>
        <div class="terms">
            <h4>개인정보처리방침</h4>
            <p>
                현대차미소금융재단은 개인정보보호법 제30조에 따라 고객의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 고객의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
            </p>

            <h5>■ 제1조(개인정보의 처리 목적)</h5>
            <p>
                현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.
            </p>
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
                <li>③ 고객은 재단에 대하여 자신의 개인정보 처리의 정지를 요구할 수 있습니다. 다만 다음 각 호의 어느 하나에 해당하거나 다른 정당한 사유가 있는 경우에는 재단은 해당 사유를 고객에게 알리고, 처리정지 요구를 거절할 수 있습니다.
                    <ol>
                        <li>1. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
                        <li>2. 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
                        <li>3. 개인정보를 처리하지 아니하면 고객과 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 고객이 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
                    </ol>
                </li>
            </ol>

            <h5>■ 제6조(개인정보처리 위탁)</h5>
            <ol>
                <li>① 재단은 다음 각 호와 같이 개인정보의 처리를 위탁하고 있습니다.
                    <ol>
                        <li>
                            1. 수탁업체<br />- ㈜다은종합물류
                        </li>
                        <li>
                            2. 수탁의 목적<br />- 재단 내부 업무자료(대출원장 및 회계서류, 장부 등)
                        </li>
                        <li>3. 개인정보 제공항목<br />- 개인식별정보: 성명, 주민번호, 계좌번호, 주소, 연락처 등</li>
                        <li>4. 개인정보 보유 기간<br />- 개인(신용)정보는 제공된 날로부터 동의 철회 또는 제공된 목적을 달성할 때까지 보유ㆍ이용됩니다. 동의 철회 또는 제공된 목적 달성 후에는 위에 기재된 이용목적과 관련된 사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유ㆍ이용됩니다.</li>
                    </ol>
                </li>
                <li>② 위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다.</li>
            </ol>

            <h5>■ 제7조(수집하는 개인정보의 항목)</h5>
            <p>재단은 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
            <ol>
                <li>1. 필수적 정보
                    <ul>
                        <li>◎ 개인식별정보<br />- 성명, 주민등록번호 등 고유식별정보, 국적, 직업, 주소 전자우편 주소 전화번호 등 연락처</li>
                        <li>◎ (금융)거래정보<br />- 상품종류, 거래조건(만기, 담보 등), 거래일시, 금액 등 거래 설정 및 내역 정보</li>
                        <li>◎ 신용평가를 위한 정보(여신거래에 한정)<br />- 신용능력정보: 채무ㆍ소득의 총액, 납세실적<br />- 신용도판단정보: 연체, 대위변제, 대지급, 부도, 관련인 발생사실 등</li>
                        <li>◎ 기타 금융거래의 설정 유지ㆍ이행ㆍ관리를 위한 상담 등을 통해 생성되는 정보<br />※ 재단은 고객의 사생활을 침해할 우려가 있는 민감정보에 대해서는 원칙적으로 수집하지 않으며, 필요한 경우 고객의 별도 동의를 받아 수집하고 동의 목적을 위해서만 제한적으로 이용합니다. 다만, 민감정보의 정확성, 최신성을 주기적으로 확인합니다.</li>
                    </ul>
                </li>
                <li>2. 수집방법
                    <ol>
                        <li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
                        <li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
                    </ol>
                </li>
            </ol>

            <h5>■ 제9조(개인정보의 안전성 확보 조치)</h5>
            <p>재단은 개인정보보호법 제29조에 따라 다음 각 호와 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</p>
            <ol>
                <li>1. 개인정보의 암호화
                    <ul>
                        <li>고객의 개인정보 중 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
                    </ul>
                </li>
                <li>2. 해킹 등에 대비한 기술적 대책
                    <ul>
                        <li>재단은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신ㆍ점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.</li>
                    </ul>
                </li>
                <li>3. 개인정보처리시스템 접근 제한
                    <ul>
                        <li>개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
                    </ul>
                </li>
                <li>4. 개인정보 취급 직원의 최소화 및 교육
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
                <li>개인정보보호책임자성명 : 오현덕</li>
                <li>전화번호 : 02-361-6060</li>
                <li>이메일 : hsc025@hyundaismile.or.kr</li>
                <li>팩스 : 02-361-6038</li>
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

<div id="pop_emailreject" class="pop_wrap" title="이메일무단수집거부">
    <div class="pop_contents">
        <div class="terms mt0">
            <p>
                정보통신망 이용촉진 및 정보보호 등에 관한 법률 제50조의2,
                제50조의7 등에 근거하여,
            </p>
            <ol>
                <li>
                    ① 사전동의 없이 현대차미소금융재단 사이트에서 자동으로 전자우편
                    주소를 수집하는 프로그램 그 밖의 기술적 장치를 이용하여
                    전자우편주소를 수집하는것을 거부합니다.
                </li>
            </ol>
        </div>
    </div>
</div>

<div id="pop_manage_image" class="pop_wrap" title="영상정보처리기기 운영&middot;관리 방침">
    <div class="pop_contents">
        <div class="desc">
            현대차미소금융재단 사이트를 이용해 주셔서 감사합니다. 본 약관은 현대차 미소금융재단과 이용자의 권리&middot;의무 및 책임사항을 규정함을 목적으로 합니다.
        </div>
        <div class="terms">
            <p>현대차미소금융재단(이하 재단이라 함)은 영상정보처리기기 운영⋅관리 방침을 통해 재단에서 처리하는 영상정보가 어떠한 용도와 방식으로 이용⋅관리되고 있는지 알려드립니다.</p>
            <h5>1. 영상정보처리기기의 설치 근거 및 설치 목적</h5>
            <p>재단은「개인정보 보호법」제25조 제1항에 따라 다음과 같은 목적으로 영상정보처리기기를 설치⋅운영 합니다.</p>
            <ul>
                <li>- 시설안전 및 화재 예방</li>
                <li>- 고객의 안전을 위한 범죄 예방</li>
            </ul>
            <h5>2. 설치 대수, 설치 위치 및 촬영범위</h5>
            <!-- 정보안내 가로형  테이블 -->
            <div class="table_list ac">
                <table>
                    <caption class="sr_only">리스트</caption>
                    <colgroup>
                        <col width="30%">
                        <col width="*">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>설치 대수</th>
                        <th>설치 위치 및 촬영 범위</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>총 10대</td>
                        <td>지점 내부 상담창구</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <h5>3. 관리책임자 및 접근권한자</h5>
            <p>귀하의 영상정보를 보호하고 개인영상정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인영상정보 보호책임자를 두고 있습니다.</p>
            <!-- 정보안내 가로형  테이블 -->
            <div class="table_list ac">
                <table>
                    <caption class="sr_only">리스트</caption>
                    <colgroup>
                        <col width="20%">
                        <col width="20%">
                        <col width="20%">
                        <col width="20%">
                        <col width="*">
                    </colgroup>
                    <thead>
                    <tr>
                        <th></th>
                        <th>이름</th>
                        <th>직위</th>
                        <th>소속</th>
                        <th>연락처</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>관리책임자</td>
                        <td>오현덕</td>
                        <td>리스크관리팀장</td>
                        <td>사무국</td>
                        <td>02-361-6060</td>
                    </tr>
                    <tr>
                        <td>접근권한자</td>
                        <td>노광일</td>
                        <td>주임</td>
                        <td>사무국</td>
                        <td>02-361-6042</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <h5>4. 영상정보의 촬영시간, 보관기간, 보관장소 및 처리방법</h5>
            <!-- 정보안내 가로형  테이블 -->
            <div class="table_list ac">
                <table>
                    <caption class="sr_only">리스트</caption>
                    <colgroup>
                        <col width="30%">
                        <col width="30%">
                        <col width="*">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>촬영시간</th>
                        <th>보관기간</th>
                        <th>보관장소</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>24시간</td>
                        <td>촬영일로부터 30일</td>
                        <td>사무국 전산실 DVR</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <p>- 처리방법 : 개인영상정보의 목적 외 이용, 제3자 제공, 파기, 열람 등 요구에 관한 사항을 기록⋅관리하고, 보관기간 만료 시 복원이 불가능한 방법으로 영구 삭제(출력물의 경우 파쇄 또는 소각)합니다.</p>
            <h5>5. 개인영상정보의 확인 방법 및 장소에 관한 사항</h5>
            <ul>
                <li>- 확인 방법 : 영상정보 관리책임자에게 미리 연락하고 재단을 방문하시면 확인 가능합니다.</li>
                <li>- 확인 장소 : 사무국 경영관리팀</li>
            </ul>
            <h5>6. 정보주체의 영상정보 열람 등 요구에 대한 조치</h5>
            <p>귀하는 개인영상정보에 관하여 열람 또는 존재확인⋅삭제를 원하는 경우 언제든지 영상정보처리기기 운영자에게 요구하실 수 있습니다. 단, 귀하가 촬영된 개인영상정보 및 명백히 정보주체의 급박한 생명, 신체, 재산의 이익을 위하여 필요한 개인영상정보에 한정됩니다.<br />
            재단은 개인영상정보에 관하여 열람 또는 존재확인⋅삭제를 요구한 경우 지체 없이 필요한 조치를 하겠습니다.</p>
            <h5>7. 영상정보의 안전성 확보조치</h5>
            <p>재단은 처리하는 영상정보는 암호화 조치 등을 통하여 안전하게 관리되고 있습니다. 또한 재단은 개인영상정보보호를 위한 관리적 대책으로서 개인정보에 대한 접근 권한을 차등부여하고 있고, 개인영상정보의 위⋅변조 방지를 위하여 개인영상정보의 생성 일시, 열람 시 열람 목적⋅열람자⋅열람 일시 등을 기록하여 관리하고 있습니다. 이 외에도 개인영상정보의 안전한 물리적 보관을 위하여 잠금장치를 설치하고 있습니다.</p>
            <h5>8. 개인정보 처리방침 변경에 관한 사항</h5>
            <p>이 영상정보처리기기 운영⋅관리방침은 2017년 5월 18일에 제정되었으며 법령ㆍ정책 또는 보안기술의 변경에 따라 내용의 추가ㆍ삭제 및 수정이 있을 시에는 시행하기 최소 7일전에 재단 홈페이지를 통해 변경사유 및 내용 등을 공지하도록 하겠습니다.<br />- 공고일자 : 2017년 5월 18일 / 시행일자 : 2017년 5월 18일</p>
        </div>
    </div>
</div>

<div id="pop_trust_work" class="pop_wrap" title="업무위탁현황">
    <div class="pop_contents">
        <div class="terms mt0">
            <!-- 정보안내 가로형  테이블 -->
            <div class="table_list ac">
                <table>
                    <caption class="sr_only">리스트</caption>
                    <colgroup>
                        <col width="20%">
                        <col width="*">
                        <col width="20%">
                        <col width="20%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>업체명</th>
                        <th>제공하는 항목</th>
                        <th>제공받는 자의 이용 목적</th>
                        <th>보유 및 이용기간</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>다은종합물류</td>
                        <td>대출서류(개인정보 포함)</td>
                        <td>문서보관</td>
                        <td>수신 후 5년</td>
                    </tr>
                    <tr>
                        <td>현대오토에버</td>
                        <td>성명, 주민등록번호, 주소, 핸드폰번호, 이메일, 직장명, 직장주소,
                            전화번호, 계좌번호, 그 외 운영에 필요한 정보</td>
                        <td>시스템 운영</td>
                        <td>보유안함</td>
                    </tr>
                    <tr>
                        <td>캡스</td>
                        <td>내부 영상정보</td>
                        <td>방범보안</td>
                        <td>보유안함</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
    </div>

    <!--약관 및 이용동의 팝업 -->
    <!--  전체동의 팝업 -->
    <div id="pop_termsAll" class="pop_wrap">
        <div class="pop_contents slide">
            <div class="swiper swiper_popup">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="title">
                            <p>개인(신용)정보 필수 수집ㆍ이용 동의</p>
                        </div>
                        <div class="desc">
                            [현대차 미소금융재단]
                        </div>
                        <div class="terms">
                            <!-- <h4>개인정보처리방침</h4> -->
                            <p>
                                이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                                만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                            </p>
            
                            <h5>■ 제1조(개인정보의 처리 목적)</h5>
                            <p>
                                현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                                시에는 사전 동의를 구할 예정입니다.
                            </p>
                            <ol>
                                <li>
                                    1. 신용정보 제공ㆍ이용
                                    <ul>
                                        <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="title">
                            <p>개인(신용)정보 필수 수집ㆍ이용 동의</p>
                        </div>
                        <div class="desc">
                            [현대차 미소금융재단] 두번째
                        </div>
                        <div class="terms">
                            <!-- <h4>개인정보처리방침</h4> -->
                            <p>
                                이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                                만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                            </p>
            
                            <h5>■ 제1조(개인정보의 처리 목적)</h5>
                            <p>
                                현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                                시에는 사전 동의를 구할 예정입니다.
                            </p>
                            <ol>
                                <li>
                                    1. 신용정보 제공ㆍ이용
                                    <ul>
                                        <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="title">
                            <p>개인(신용)정보 필수 수집ㆍ이용 동의</p>
                        </div>
                        <div class="desc">
                            [현대차 미소금융재단] 두번째
                        </div>
                        <div class="terms">
                            <!-- <h4>개인정보처리방침</h4> -->
                            <p>
                                이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                                만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                            </p>
            
                            <h5>■ 제1조(개인정보의 처리 목적)</h5>
                            <p>
                                현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                                시에는 사전 동의를 구할 예정입니다.
                            </p>
                            <ol>
                                <li>
                                    1. 신용정보 제공ㆍ이용
                                    <ul>
                                        <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 개인(신용) 정보조회 및 수집 · 이용 · 제공 동의(여신금융거래) -->
    <div id="pop_terms01" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>
    <!--  개인(신용) 정보조회 및 수집 · 이용 · 제공 동의(정책상품거래) -->
    <div id="pop_terms02" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>
    <!--  개인(신용) 정보조회 및 수집 · 이용 · 제공 동의서 -->
    <div id="pop_terms03" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>
    <!--  개인(신용) 정보조회 및 수집 · 이용 · 제공 동의(공공마이데이터꾸러미) -->
    <div id="pop_terms04" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>
    <!-- (선택) 개인(신용) 정보 수집 · 이용 동의(서민금융진흥원) -->
    <div id="pop_terms05" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>
    <!-- (선택) 개인(신용)정보 수집 · 이용 · 제공 동의(상품 서비스 안내 등) -->
    <div id="pop_terms06" class="pop_wrap">
        <div class="pop_contents">
            <div class="desc">
                [현대차 미소금융재단]
            </div>
            <div class="terms">
                <!-- <h4>개인정보처리방침</h4> -->
                <p>
                    이 대출거래기본약관(이하 "약관"이라 합니다)은 (이하 "채권자"라 합니다)와 거래처(이하 "채무자"라 합니다)와의 상호신뢰를 바탕으로 대출거래의 원활하고 공정한 처리를 위하여
                    만들어진 것입니다. 채권자는 이 약관을 모든 지점 및 전다금융매체에 비치. 게시하고 채무자는 이를 열람하거나 교부를 청구할 수 있습니다.<개정 2017.5.1>
                </p>

                <h5>■ 제1조(개인정보의 처리 목적)</h5>
                <p>
                    현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될
                    시에는 사전 동의를 구할 예정입니다.
                </p>
                <ol>
                    <li>
                        1. 신용정보 제공ㆍ이용
                        <ul>
                            <li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
                        </ul>
                    </li>
                </ol>
            </div>
        </div>
    </div>

    <div id="pop_send1" class="pop_wrap">
        <div class="pop_contents">
            <p class="msg">본인인증 항목은 필수입니다.</p>
        </div>
    </div>

    <div id="pop_send2" class="pop_wrap">
        <div class="pop_contents">
            <p class="msg">휴대폰 인증 이용동의 항목은 필수입니다.</p>
        </div>
    </div>
    <div id="pop_send3" class="pop_wrap">
        <div class="pop_contents">
            <p class="msg">휴대폰 항목은 필수입니다.</p>
        </div>
    </div>

<script>

$(function () {

    // $('#pop_send1').dialog({
    //         dialogClass: 'pop_alert',
    //         width: 500,
    //         buttons:[
    //             {
    //                 text: "확인",
    //                 class: "btn btn_sm btn_blue",
    //                 click: function () {
    //                     $(this).dialog("close");
    //                 }
    //             },
    //         ]
    // });


    $('.btn_all').on('click', function () {
        $('#pop_termsAll').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            buttons:[
                {
                    text: "확인",
                    class: "btn btn_sm btn_gray",
                    disabled: true,
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });

        let siwperBtn = `<div class="swiper-button-prev">
            </div> <div class="swiper-button-next">
                </div>`
         $('.pop_modal').append(siwperBtn);
         $('.ui-dialog-titlebar').css('display','none');
         
         var swiper = new Swiper("#pop_termsAll .swiper_popup", {
                touchRatio: 0,
                navigation: {
                    nextEl: ".pop_modal .swiper-button-next",
                    prevEl: ".pop_modal .swiper-button-prev",
                },
                on : {
                    activeIndexChange : function(){
                        if(this.activeIndex == 2){
                            $('.ui-dialog-buttonpane').children('.ui-dialog-buttonset').children('button').removeClass('btn btn_sm btn_gray').addClass('btn btn_sm btn_blue');

                            $('.ui-dialog-buttonpane').children('.ui-dialog-buttonset').children('button').removeClass('ui-button-disabled ui-state-disabled');
                            $('.ui-dialog-buttonpane').children('.ui-dialog-buttonset').children('button').prop('disabled', false);
                        }else{
                            $('.ui-dialog-buttonpane').children('.ui-dialog-buttonset').children('button').removeClass('btn btn_sm btn_blue').addClass('btn btn_sm btn_gray');
                            
                            $('.ui-dialog-buttonpane').children('.ui-dialog-buttonset').children('button').prop('disabled', true);
                        }
                    }
                }
            }
         )
         swiper.update();
    });

    $('.btn_more01').on('click', function () {
        $('#pop_terms01').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title:"개인(신용)정보조회·수집·이용·제공 동의서(필수)",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    });

    $('.btn_more02').on('click', function () {
        $('#pop_terms02').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title: "사후용도 관리 강화 관련 개인(신용)정보 조회·수집·이용·제공 동의서(필수)",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    })

    $('.btn_more03').on('click', function () {
        $('#pop_terms03').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title:"여신 공공 마이 데이터 제공 동의(선택)",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    })

    $('.btn_more04').on('click', function () {
        $('#pop_terms04').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title:"개인(신용) 정보 수집·이용·제공·제3자 제공 필수 동의",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    })

    $('.btn_more05').on('click', function () {
        $('#pop_terms05').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title:"고유식별정보 처리 동의",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    })

    $('.btn_more06').on('click', function () {
        $('#pop_terms06').dialog({
            dialogClass: 'pop_modal',
            width: 500,
            title:"본인 행정정보 제3자 제공 요구서",
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_sm btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
            ]
        });
    })

    
    // 인증번호 발송btn
    $('.form_confirm').hide();
    $('#btnSendSms').on("click", function(){
        $('.form_confirm').show();
    })
})

window.name ="현대차미소금융";

function fnNicePopup(chktype){
	
	$("#AUTH_TYPE").val(chktype);
	
	// 유효성검사
	if(ajaxCheck()){
		
		$.ajax({
			type:"POST"
			, url: "/front/consultation/niceCheck"
			, data: $("form[name=form_chk]").serialize()
			, dataType:"json"
			, success : function(responseData){
				
				let sencData = responseData.sEncData;
				$("#EncodeData").val(sencData);
					
				window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
				document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
				document.form_chk.target = "popupChk";
				document.form_chk.submit();
				
				document.form_chk.action = "/front/consultation/newRequest_step2";
				document.form_chk.target = "";
				
			    //nextStep2();
					
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

function fnNiceSocket(){
	
	//$("#AUTH_TYPE").val(chktype);
	
	// 유효성검사
	if(ajaxCheck()){
		
		$.ajax({
			type:"POST"
			, url: "/front/consultation/niceCheckSocket"
			, data: $("form[name=form_chk]").serialize()
			, dataType:"json"
			, success : function(responseData){
				
				let iReturn = responseData.iReturn;
				
				console.log("iReturn======="+iReturn);
					
				let sencData = responseData.sEncData;
				$("#EncodeData").val(sencData);
					
				//window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
				//document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
				//document.form_chk.target = "popupChk";
				//document.form_chk.submit();
				
				document.form_chk.action = "/front/consultation/newRequest_step2";
				document.form_chk.target = "";
				
			    //nextStep2();
					
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

function fnNiceCerti(){
	
	//$("#AUTH_TYPE").val(chktype);
	
	// 유효성검사
	if(ajaxCheck()){
		
		$.ajax({
			type:"POST"
			, url: "/front/consultation/fnNiceCerti"
			, data: $("form[name=form_chk]").serialize()
			, dataType:"json"
			, success : function(responseData){
				
				let iReturn = responseData.iReturn;
				
				console.log("iReturn======="+iReturn);
					
				let sencData = responseData.sEncData;
				$("#EncodeData").val(sencData);
					
				//window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
				//document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
				//document.form_chk.target = "popupChk";
				//document.form_chk.submit();
				
				document.form_chk.action = "/front/consultation/newRequest_step2";
				document.form_chk.target = "";
				
			    //nextStep2();
					
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

function nextStep2(){
	//window.opener.location.href = "/front/consultation/newRequest_step2?sName="+sName+"&sBirthDate="+sBirthDate+"&sGender="+sGender+"&sMobileNo="+sMobileNo;
    
    $("form[name=form_chk]").attr("action", "/front/consultation/newRequest_step2");
    $("form[name=form_chk]").attr("method", "post");
    $("form[name=form_chk]").submit();
    
	//window.opener.document.form_chk.action = "/front/consultation/newRequest_step2";
	//document.form_chk.target = "popupChk";
	//window.opener.document.form_chk.submit();	
}

$('.btn_detail').on('click', function() {
	$('.group_terms').slideToggle(200);
});

//체크박스 전체 체크
$("#check_all").click(function() {
	if($("#check_all").is(":checked")) $("input[name=CHECK]").prop("checked", true);
	else $("input[name=CHECK]").prop("checked", false);
});

//체크박스 체크
$("input[name=CHECK]").click(function() {
	var total = $("input[name=CHECK]").length;
	var checked = $("input[name=CHECK]:checked").length;

	if(total != checked) $("#check_all").prop("checked", false);
	else $("#check_all").prop("checked", true); 
});

// 유효성검사
function ajaxCheck(){
	
	let chk = true;
	// S: 유효성검사
	
	//이름
	if(isEmpty($("form[name=form_chk] input[name=CUST_NM]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=CUST_NM]"), "${messageValidationEmpty_CONSULT_NAME}");
		chk = false;
		return; 
	}
	
	//실명번호1
	if(isEmpty($("form[name=form_chk] input[name=REGI_NO1]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=REGI_NO1]"), "${messageValidationEmpty_CONSULT_REGI_NO}");
		chk = false;
		return; 
	}
	
	//실명번호2
	if(isEmpty($("form[name=form_chk] input[name=REGI_NO2]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=REGI_NO2]"), "${messageValidationEmpty_CONSULT_REGI_NO}");
		chk = false;
		return; 
	}
	
	//실명번호
	let custRegiNo = $("#regiNo1").val()+$("#regiNo2").val();
	//console.log("실명번호=============="+custRegiNo);
	$("#CUST_REGI_NO").val(custRegiNo);
	
	//휴대폰
	if(isEmpty($("form[name=form_chk] input[name=CUST_HP_NO]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=CUST_HP_NO]"), "${messageValidationEmpty_CONSULT_HP_NO}");
		chk = false;
		return; 
	}
	
	//전체동의
	if (!$("input:checked[Name='CHECK_ALL']").is(":checked")){ 
		openPopAlertFocus($("form[name=form_chk] input[name=CHECK_ALL]"), "${messageValidationCHECK_INFO_CHECK}"); 
		chk = false;
		return; 
	}
	// E: 유효성검사
	
	return chk;
}

</script>

</body>
</html>
