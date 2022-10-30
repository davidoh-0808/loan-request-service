package com.neo.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UtilSession {

    /*
                        이하 내용 추후 수정 가능 (AuthProvider 참조)
                  -------------------------------------------------------------------------------------------
                    switch (MEMBER_AUTHORITY) {
                        case "MC0002300001":
                            pageAuth.add("/consult/consult/requestList");
                            pageAuth.add("/consult/board/confirm/confirmList");
                            pageAuth.add("/consult/board/sales/salesList");
                            pageAuth.add("/consult/user/profileMgmt");
                            break;
                        case "MC0002300002":
                            pageAuth.add("/consult/consult/requestList");
                            pageAuth.add("/consult/board/confirm/confirmList");
                            pageAuth.add("/consult/board/salesLis");
                            pageAuth.add("/consult/user/profileMgmt");
                            break;
                        case "MC0002300003":
                            pageAuth.add("/consult/consult/requestList");
                            pageAuth.add("/consult/board/confirm/confirmList");
                            pageAuth.add("/consult/board/sales/salesList");
                            pageAuth.add("/consult/consult/consultList");
                            pageAuth.add("/consult/consult/consultMember");
                            pageAuth.add("/consult/consult/connList");
                            pageAuth.add("/consult/user/profileMgmt");
                            break;
                        case "MC0002300004":
                            pageAuth.add("/consult/consult/requestList");
                            pageAuth.add("/consult/board/confirm/confirmList");
                            pageAuth.add("/consult/board/sales/salesList");
                            pageAuth.add("/consult/consult/consultList");
                            pageAuth.add("/consult/consult/consultMember");
                            pageAuth.add("/consult/consult/connList");
                            pageAuth.add("/consult/system/accountMgmt");
                            pageAuth.add("/consult/system/detailMgmt");
                            pageAuth.add("/consult/user/profileMgmt");
                            break;
                        default:
                            //rMsg = messageSource.getMessage("login.fail.missmatchPassword", null, localeResolver.resolveLocale(request));
                            logger.error("AuthProvider.authenticate() 스위치 문 에러 발생");
                    }
     */

    /**
     * 현재 HttpServletRequest 의 HttpSession 에 담긴 MEMBER_AUTHORITY (어드민 권한 구분)
     * 와 현재 접근한 페이지 를 비교하여 권한이 있으면 true, 아니면 false 반환
     * <p>
     * 어드민 권한 구분: 지점, 시스템 관리팀, 심사팀 관리자, 상담원
     *
     * @param request
     * @return boolean
     */
    public static boolean checkPageAuthInSession(HttpServletRequest request) {
        boolean result = false;
        String requestURI = request.getRequestURI(); //ex. /consult/consult/requestList
        
        HttpSession session = request.getSession();
        List<String> menuAuths = (List) session.getAttribute("menuAuths");

        for (String allowedURI : menuAuths) {
        	//System.out.println("allowedURI=============="+allowedURI);
            if (requestURI.equals(allowedURI)) {
                return true;
            }
        }

        return result;
    }

}
