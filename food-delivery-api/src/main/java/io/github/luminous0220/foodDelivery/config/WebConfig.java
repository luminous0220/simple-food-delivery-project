package io.github.luminous0220.foodDelivery.config;
import io.github.luminous0220.foodDelivery.interceptor.JwtTokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // 标识这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor; // 注入我们定义的拦截器 Bean

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并指定拦截的路径模式
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/api/**", "/admin/**") // 拦截以 /api/ 或 /admin/ 开头的所有请求
                .excludePathPatterns("/api/public/**", "/admin/employee/login", "/static/**"); // 排除不需要拦截的路径
    }
}
