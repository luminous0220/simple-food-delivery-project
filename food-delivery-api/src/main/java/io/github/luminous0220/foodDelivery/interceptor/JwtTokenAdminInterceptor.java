package io.github.luminous0220.foodDelivery.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.luminous0220.foodDelivery.constant.JwtClaimsConstant;
import io.github.luminous0220.foodDelivery.context.BaseContxt;
import io.github.luminous0220.foodDelivery.exception.BadException;
import io.github.luminous0220.foodDelivery.exception.UnauthorizedException;
import io.github.luminous0220.foodDelivery.properties.JwtProperties;
import io.github.luminous0220.foodDelivery.result.Result;
import io.github.luminous0220.foodDelivery.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    private static final ObjectMapper objectMapper = new ObjectMapper(); // 用于返回 JSON 结果

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：", empId);
            BaseContxt.setCurrentId(empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应未授权
            throw new UnauthorizedException();
        }
    }

}
