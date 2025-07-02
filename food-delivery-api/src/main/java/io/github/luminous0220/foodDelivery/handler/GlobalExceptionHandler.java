package io.github.luminous0220.foodDelivery.handler;

import io.github.luminous0220.foodDelivery.constant.MessageConstant;
import io.github.luminous0220.foodDelivery.exception.BadException;
import io.github.luminous0220.foodDelivery.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Result<String>>  handleException(BadException e) {
        log.info("服务器异常: {}", e.getMessage());
        Result<String> res=Result.error(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(res);
    }

    @ExceptionHandler
    public ResponseEntity<Result<String>>  handleSQLException(SQLException e) {
        String msg=e.getMessage();
        Result<String> res=Result.error(e.getMessage());
        if (msg.contains("Duplicate entry")) {
            String[] split = msg.split(" ");
            String username = split[2];
            res.setMsg(username + MessageConstant.ALREADY_EXIST);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
