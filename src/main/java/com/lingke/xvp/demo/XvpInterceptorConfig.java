package com.lingke.xvp.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingke.xvp.demo.utils.SessionUtil;

@Configuration
public class XvpInterceptorConfig extends WebMvcConfigurerAdapter {
	
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration sellerInterceptor = registry.addInterceptor(new SellerInterceptor());
        // 拦截配置
        sellerInterceptor.addPathPatterns("/seller/**");
        // 排除配置
        sellerInterceptor.excludePathPatterns("/seller/seller/login");
        sellerInterceptor.excludePathPatterns("/seller/seller/reset");
        sellerInterceptor.excludePathPatterns("/seller/seller/register");
        sellerInterceptor.excludePathPatterns("/seller/seller/verify");
        sellerInterceptor.excludePathPatterns("/seller/seller/index.html");
        sellerInterceptor.excludePathPatterns("/seller/seller/index.html/**");
        InterceptorRegistration userInterceptor = registry.addInterceptor(new UserInterceptor());
        // 拦截配置
        userInterceptor.addPathPatterns("/user/**");
        // 排除配置
        userInterceptor.excludePathPatterns("/user/user/login");

    }

    private class SellerInterceptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			if (SessionUtil.checkSellerLogin()) {
				return true;
			}
			response.getWriter().append(XvpConstants.SELLER_NO_LOGIN_RESP);
			return false;
		}
    }
    
    private class UserInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
        	if(SessionUtil.checkUserLogin()){
        		return true;
        	}
            response.getWriter().append(XvpConstants.USER_NO_LOGIN_RESP);
            return false;
        }
    }


}
