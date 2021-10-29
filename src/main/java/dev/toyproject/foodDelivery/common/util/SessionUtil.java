package dev.toyproject.foodDelivery.common.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";
    private static final String LOGIN_RIDER_ID = "LOGIN_RIDER_ID";

    // 인스턴스화 방지
    private SessionUtil(){}

    /********************************* Member ***********************************/

    /**
     * 사용자 정보 session 조회
     *
     * @param session
     * @return
     */
    public static String getLoginMemberId(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_ID);
    }

    /**
     * 사용자 Token 정보 session 저장
     *
     * @param session
     * @param memberToken
     */
    public static void setLoginMemberId(HttpSession session, String memberToken){
        session.setAttribute(LOGIN_MEMBER_ID, memberToken);
    }

    /**
     * 사용자 Token 정보 session 삭제
     *
     * @param session
     */
    public static void removeLogoutMember(HttpSession session){
        session.removeAttribute(LOGIN_MEMBER_ID);
    }
}
