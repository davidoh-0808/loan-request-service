<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

<%-- 스프링 시큐리티 의 Authorization Tag 추가 -  --%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE"/>
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME"/>

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>


<!-- lnb -->
<div class="lnb_wrap">


    <%--  권한 별 메뉴 보여주기 / 감추기 테스트  --%>
    <!-- 세션에 담겨있는 menusToShow 는 현재 로그인한 유저가 볼 수 있는 메뉴들의 이름이 담겨있다
         세션 데이터 : MEMBER_AUTHORITY 에는 MC0000600001 같은 코드가 담겨있다
    -->

    <div class="lnb">
        <ul id="insertMenusToShow" class="dep1">
            <c:set var="MEMBER_AUTHORITY" value="${MEMBER_AUTHORITY}"/>

            <%--    유저 : 지점(상담원)    --%>
            <c:if test="${MEMBER_AUTHORITY eq 'MC0002300001'}">
                <!-- requestList -->
                <li><p><a href="#none" class="tit">상담신청내역</a></p>
                    <ul class="dep2">
                        <!-- <li id="requestList"><a href="/consult/consult/requestList" data-auth="REQUEST_LIST">- 신청내역 -->
                        <li id="requestList"><a href="javascript:movePage('/consult/consult/requestList');" data-auth="REQUEST_LIST">- 신청내역
                            조회/수정</a>
                        </li>
                    </ul>
                </li>

                <!-- boardMgmt -->
                <!-- <li><p><a href="#none" class="tit">게시판 관리</a></p>
                    <ul class="dep2">
                        <li id="confirmList"><a href="/consult/board/confirm/confirmList" data-auth="CONFIRM_LIST">- 대표번호
                            승인현황</a>
                        </li>
                        <li id="salesList"><a href="/consult/board/sales/salesList" data-auth="SALES_LIST">- 영업현황</a></li>
                    </ul>
                </li> -->

                <!-- userMgmt -->
                <li><p><a href="#none" class="tit">마이페이지</a></p>
                    <ul class="dep2">
                        <li id="profileMgmt"><a href="/consult/user/profile" data-auth="PROFILE_MGMT">- 프로필설정</a></li>
                    </ul>
                </li>

            </c:if>

            <%--    유저 : 심사팀   --%>
            <c:if test="${MEMBER_AUTHORITY eq 'MC0002300002'}">
                <!-- requestList -->
                <li><p><a href="#none" class="tit">상담신청내역</a></p>
                    <ul class="dep2">
                        <li id="requestList"><a href="javascript:movePage('/consult/consult/requestList');" data-auth="REQUEST_LIST">- 신청내역
                            조회/수정</a>
                        </li>
                    </ul>
                </li>

                <!-- boardMgmt -->
                <!-- <li><p><a href="#none" class="tit">게시판 관리</a></p>
                    <ul class="dep2">
                        <li id="confirmList"><a href="/consult/board/confirm/confirmList" data-auth="CONFIRM_LIST">- 대표번호
                            승인현황</a>
                        </li>
                        <li id="salesList"><a href="/consult/board/sales/salesList" data-auth="SALES_LIST">- 영업현황</a></li>
                    </ul>
                </li> -->

                <!-- userMgmt -->
                <li><p><a href="#none" class="tit">마이페이지</a></p>
                    <ul class="dep2">
                        <li id="profileMgmt"><a href="/consult/user/profile" data-auth="PROFILE_MGMT">- 프로필설정</a></li>
                    </ul>
                </li>
            </c:if>

            <%--    유저 : 심사팀 관리자    --%>
            <c:if test="${MEMBER_AUTHORITY eq 'MC0002300003'}">
                <!-- consultMgmt -->
                <li><p><a href="#none" class="tit">대출상담 관리</a></p>
                    <ul class="dep2">
                        <!-- <li id="consultList"><a href="/consult/consult/consultList" data-auth="CONSULT_LIST">- 상담리스트</a> -->
                        <li id="consultList"><a href="javascript:movePage('/consult/consult/consultList');" data-auth="CONSULT_LIST">- 상담리스트</a>
                        </li>
                        <li id="consultantList"><a href="/consult/consult/consultantList" data-auth="CONSULTANT">- 상담원</a>
                        </li>
                        <li id="connList"><a href="/consult/consult/connList" data-auth="CONN_LIST">- 접속현황</a></li>
                    </ul>
                </li>

                <!-- boardMgmt -->
                <!-- <li><p><a href="#none" class="tit">게시판 관리</a></p>
                    <ul class="dep2">
                        <li id="confirmList"><a href="/consult/board/confirm/confirmList" data-auth="CONFIRM_LIST">- 대표번호
                            승인현황</a>
                        </li>
                        <li id="salesList"><a href="/consult/board/sales/salesList" data-auth="SALES_LIST">- 영업현황</a></li>
                    </ul>
                </li> -->

                <!-- userMgmt -->
                <li><p><a href="#none" class="tit">마이페이지</a></p>
                    <ul class="dep2">
                        <li id="profile"><a href="/consult/user/profile" data-auth="PROFILE">- 프로필설정</a></li>
                    </ul>
                </li>
            </c:if>

            <%--    유저 : 시스템 관리자    --%>
            <c:if test="${MEMBER_AUTHORITY eq 'MC0002300004'}">

                <!-- consultMgmt -->
                <li><p><a href="#none" class="tit">대출상담 관리</a></p>
                    <ul class="dep2">
                        <!-- <li id="consultList"><a href="/consult/consult/consultList" data-auth="CONSULT_LIST">- 상담리스트</a> -->
                        <li id="consultList"><a href="javascript:movePage('/consult/consult/consultList');" data-auth="CONSULT_LIST">- 상담리스트</a>
                        </li>
                        <li id="consultantList"><a href="/consult/consult/consultantList" data-auth="CONSULTANT">- 상담원</a>
                        </li>
                        <li id="connList"><a href="/consult/consult/connList" data-auth="CONN_LIST">- 접속현황</a></li>
                    </ul>
                </li>

                <!-- boardMgmt -->
                <!-- <li><p><a href="#none" class="tit">게시판 관리</a></p>
                    <ul class="dep2">
                        <li id="confirmList"><a href="/consult/board/confirm/confirmList" data-auth="CONFIRM_LIST">- 대표번호
                            승인현황</a>
                        </li>
                        <li id="salesList"><a href="/consult/board/sales/salesList" data-auth="SALES_LIST">- 영업현황</a></li>
                    </ul>
                </li> -->

                <!-- systemMgmt -->
                <li><p><a href="#none" class="tit">시스템 관리</a></p>
                    <ul class="dep2">
                        <li id="memberList"><a href="/consult/system/memberList" data-auth="MEMBER_LIST">- 계정/권한 관리</a></li>
                        <!-- <li id="detailMgmt"><a href="/consult/user/profile" data-auth="DETAIL_MGMT">- 세부항목 관리</a></li> -->
                    </ul>
                </li>

                <!-- userMgmt -->
                <li><p><a href="#none" class="tit">마이페이지</a></p>
                    <ul class="dep2">
                        <li id="profile"><a href="/consult/user/profile" data-auth="PROFILE">- 프로필설정</a></li>
                    </ul>
                </li>
            </c:if>

        </ul>
    </div>
<form name="frmLnb">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
<input type="hidden" name="pageNum" value=1 />
</form>

</div>
<!-- // lnb -->

<script>

	function movePage(pageUrl) {
        /*
            page move 하기 전, paramVO 초기화 필요 (안할 경우 각 페이지의 input paramVO 가 다를 경우 에러가 난다)
              (ex. 상담원 페이지 sort 클릭 -> 상담리스트 이동)
         */
        $("form[name=frmLnb] input[name=orderValue]").val("");
        $("form[name=frmLnb] input[name=orderName]").val("");

		$("form[name=frmLnb] input[name=pageNum]").val("1");
		$("form[name=frmLnb]").attr("method", "POST");
		$("form[name=frmLnb]").attr("action", pageUrl);
		$("form[name=frmLnb]").submit();
	};
</script>