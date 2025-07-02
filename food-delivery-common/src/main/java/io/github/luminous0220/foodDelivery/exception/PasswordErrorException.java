package io.github.luminous0220.foodDelivery.exception;

import io.github.luminous0220.foodDelivery.constant.MessageConstant;


public class PasswordErrorException extends BadException{
    public PasswordErrorException() {
        super(MessageConstant.PASSWORD_ERROR);
    }
}