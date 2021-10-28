package dev.toyproject.foodDelivery.member.domain;


/**
 * 사용자 정보 CREATE, UPDATE, DELETE 관련 기능
 */
public interface MemberStore {
    Member store(Member initMember);
}