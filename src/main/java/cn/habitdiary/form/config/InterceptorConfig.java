package cn.habitdiary.form.config;

import cn.habitdiary.form.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserInterceptor userInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/design");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("error/**").addResourceLocations("classpath:/static/error/");
    }
}
