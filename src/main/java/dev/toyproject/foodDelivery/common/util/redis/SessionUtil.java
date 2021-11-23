package dev.toyproject.foodDelivery.common.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final String LOGIN_MEMBER_ID    = "LOGIN_MEMBER_ID";
    private static final String LOGIN_OWNER_TOKEN  = "LOGIN_OWNER_TOKEN";
    private static final String LOGIN_RIDER_TOKEN  = "LOGIN_RIDER_TOKEN";

    // 인스턴스화 방지
    private SessionUtil(){}

    /********************************* Member ***********************************/

    /**
     * 사용자 정보 session 조회
     *
     * @param session
     * @return
     */
    public static String getLoginMemberToken(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_ID);
    }

    /**
     * 사용자 Token 정보 session 저장
     *
     * @param session
     * @param memberToken
     */
    public static void setLoginMemberToken(HttpSession session, String memberToken){
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

    /********************************* OWNER ***********************************/

    /**
     * 사장님 session 정보 조회
     *
     * @param session
     * @return
     */
    public static String getLoginOwnerToken(HttpSession session) {
        return (String) session.getAttribute(LOGIN_OWNER_TOKEN);
    }

    /**
     * 사장님 Token 정보 session 저장
     *
     * @param session
     * @param ownerToken
     */
    public static void setLoginOwnerToken(HttpSession session, String ownerToken){
        session.setAttribute(LOGIN_OWNER_TOKEN, ownerToken);
    }

    /**
     * 사장님 Token 정보 session 삭제
     *
     * @param session
     */
    public static void removeLogoutOwner(HttpSession session){
        session.removeAttribute(LOGIN_OWNER_TOKEN);
    }

    /********************************* RIDER ***********************************/

    /**
     * 라이더 session 정보 조회
     *
     * @param session
     * @return
     */
    public static String getLoginRiderToken(HttpSession session) {
        return (String) session.getAttribute(LOGIN_RIDER_TOKEN);
    }

    /**
     * 라이더 Token 정보 session 저장
     *
     * @param session
     * @param riderToken
     */
    public static void setLoginRiderToken(HttpSession session, String riderToken){
        session.setAttribute(LOGIN_RIDER_TOKEN, riderToken);
    }

    /**
     * 라이더 Token 정보 session 삭제
     *
     * @param session
     */
    public static void removeLogoutRider(HttpSession session){
        session.removeAttribute(LOGIN_RIDER_TOKEN);
    }
}
