package top.weless.quiz.config;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
                String token = request.getHeader("token");
                if (token != null && template.opsForValue().get(token) != null) {
                    template.expire(token, 1, TimeUnit.HOURS);
                    return true;
                }
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }).excludePathPatterns("/user/login", "/user/signup", "/user/test");
    }
}
