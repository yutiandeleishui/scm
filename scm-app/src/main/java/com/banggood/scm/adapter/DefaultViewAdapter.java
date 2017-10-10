package com.banggood.scm.adapter;

import com.banggood.scm.interceptor.authorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/8/30
 * 适配器-用于配置项目启动的默认地址.
 */
@Configuration
public class DefaultViewAdapter extends WebMvcConfigurerAdapter {

    //指定默认页
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("/list");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new authorityInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
