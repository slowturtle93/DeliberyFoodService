package dev.toyproject.foodDelivery.common.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final String LOGIN_MEMBER_TOKEN = "LOGIN_MEMBER_TOKEN";

    // 인스턴스화 방지
    private SessionUtil(){}

    /********************************* Member ***********************************/

    /**
     * 로그인한 사용자의 Mail 을 session 에서 조회
     *
     * @param session
     * @return
     */
    public static String getLoginMemberToken(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_TOKEN);
    }

    /**
     * 사용자 Token 정보 session 저장
     *
     * @param session
     * @param memberToken
     */
    public static void setLoginMemberToken(HttpSession session, String memberToken){
        session.setAttribute(LOGIN_MEMBER_TOKEN, memberToken);
    }

    /**
     * 사용자 Token 정보 session 삭제
     *
     * @param session
     */
    public static void removeLogoutMember(HttpSession session){
        session.removeAttribute(LOGIN_MEMBER_TOKEN);
    }
}
