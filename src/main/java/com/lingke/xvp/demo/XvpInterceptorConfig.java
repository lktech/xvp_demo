package com.lingke.xvp.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingke.xvp.demo.utils.SessionUtil;
import com.lingke.xvp.demo.utils.XvpConstants;

@Configuration
public class XvpInterceptorConfig extends WebMvcConfigurerAdapter {
	
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new SecurityInterceptor());
        // 拦截配置
        addInterceptor.addPathPatterns("/seller/**");
        // 排除配置
        addInterceptor.excludePathPatterns("/seller/seller/login");
        addInterceptor.excludePathPatterns("/seller/seller/reset");
        addInterceptor.excludePathPatterns("/seller/seller/register");

    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
        	if(SessionUtil.checkSellerLogin()){
        		return true;
        	}
            response.getWriter().append(XvpConstants.SELLER_NO_LOGIN_RESP);
            return false;
        }
    }


}
