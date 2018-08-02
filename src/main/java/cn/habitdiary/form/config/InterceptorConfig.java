package cn.habitdiary.form.config;

import cn.habitdiary.form.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserInterceptor userInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/register","/index");
    }
}
