package io.daocloud.dx.config;

import io.daocloud.dx.intercepter.GlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * @Author: Devin
 * @Date: 2020-03-30 11:10
 * @Version 1.0
 */
@Configuration
public class I18nConfig extends WebMvcConfigurationSupport {

    /**
     * 创建cookie区域解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        // 设置cookie有效期
        cookieLocaleResolver.setCookieMaxAge(3600);
        return cookieLocaleResolver;
    }

    /**
     * 创建拦截器，可以通过不同的配置来获取国际化的文件
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //默认是请求地址的参数，默认为local
        return localeChangeInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(localeChangeInterceptor());
        super.addInterceptors(registry);
    }
}
