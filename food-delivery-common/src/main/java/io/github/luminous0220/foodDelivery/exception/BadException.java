package io.github.luminous0220.foodDelivery.exception;
import io.github.luminous0220.foodDelivery.constant.MessageConstant;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * 业务异常基类
 */
@Getter
public class BadException extends RuntimeException {

    private final HttpStatus status;

    public BadException() {
        this(MessageConstant.SERVER_ERROR, HttpStatus.BAD_REQUEST);
    }

    public BadException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }

    public BadException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
