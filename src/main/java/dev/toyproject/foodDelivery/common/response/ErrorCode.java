package dev.toyproject.foodDelivery.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 서비스 운영에서 예상이 가능한 Exception Error Code 표현
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),
    COMMON_DUPLICATE_KEY("중복된 값이 있습니다.");

    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
