package io.github.luminous0220.foodDelivery.exception;

import io.github.luminous0220.foodDelivery.constant.MessageConstant;


public class AccountForbiddenException extends BadException{
    public AccountForbiddenException() {
        super(MessageConstant.ACCOUNT_LOCKED);
    }
}