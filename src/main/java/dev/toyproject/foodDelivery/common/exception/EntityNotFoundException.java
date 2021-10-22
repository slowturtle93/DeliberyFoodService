package dev.toyproject.foodDelivery.common.exception;

import dev.toyproject.foodDelivery.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException{

    public EntityNotFoundException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
