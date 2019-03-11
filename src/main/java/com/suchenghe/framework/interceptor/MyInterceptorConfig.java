package com.suchenghe.framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SuChenghe
 * @date 2018/12/16 22:51
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    InterceptorsAuth interceptorsAuth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorsAuth).addPathPatterns("/**")
                //排除对静态资源的拦截
                .excludePathPatterns("/js/**", "/css/**", "/image/**", "/fonts/**")
                //排除对登陆界面的拦截
                .excludePathPatterns("/login");
    }

}
