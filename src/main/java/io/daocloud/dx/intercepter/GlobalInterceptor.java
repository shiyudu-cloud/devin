package io.daocloud.dx.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: Devin
 * @Date: 2020-03-30 11:51
 * @Version 1.0
 */
public class GlobalInterceptor implements HandlerInterceptor {
    /**在业务处理器处理请求之前被调用*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据请求头获取用户的语言设置
        String language = request.getHeader("Accept-Language");
        if (StringUtils.isNotBlank(language)){
            String locale = StringUtils.splitByWholeSeparator(language, ",")[0];
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (locale.equals("zh-CN")){
                localeResolver.setLocale(request,response,new Locale("zh","CN"));
                return true;
            }
            localeResolver.setLocale(request,response,new Locale("en","US"));
        }

        return true;
    }
    /**在业务处理器处理请求完成之后，生成视图之前执行*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**在DispatcherServlet完全处理完请求之后被调用，可用于清理资源*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
