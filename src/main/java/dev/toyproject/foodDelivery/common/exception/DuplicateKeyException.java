package dev.toyproject.foodDelivery.common.exception;

import dev.toyproject.foodDelivery.common.response.ErrorCode;

public class DuplicateKeyException extends BaseException{

    public DuplicateKeyException() {
        super(ErrorCode.COMMON_DUPLICATE_KEY);
    }

    public DuplicateKeyException(String message) {
        super(message, ErrorCode.COMMON_DUPLICATE_KEY);
    }
}
