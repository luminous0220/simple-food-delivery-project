package io.github.luminous0220.foodDelivery;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j // 日志
public class FoodDeliveryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApiApplication.class, args);
        log.info("服务启动");
    }

}
