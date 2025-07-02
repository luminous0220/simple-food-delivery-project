package io.github.luminous0220.foodDelivery.exception;


import io.github.luminous0220.foodDelivery.constant.MessageConstant;
import org.springframework.http.HttpStatus;


public class UnauthorizedException extends BadException{
    public UnauthorizedException() {
        super(MessageConstant.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }
}