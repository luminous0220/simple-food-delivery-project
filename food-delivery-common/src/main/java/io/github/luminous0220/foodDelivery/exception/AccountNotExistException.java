package io.github.luminous0220.foodDelivery.exception;

import io.github.luminous0220.foodDelivery.constant.MessageConstant;

public class AccountNotExistException extends BadException{
    public AccountNotExistException() {
        super(MessageConstant.ACCOUNT_NOT_FOUND);
    }
}
