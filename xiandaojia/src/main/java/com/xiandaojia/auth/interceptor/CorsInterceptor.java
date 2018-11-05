package com.xiandaojia.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class CorsInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//response.setHeader("Access-Control-Allow-Origin", "*");
	    //response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	   // response.setHeader("Access-Control-Max-Age", "3600");
	    //response.setHeader("Access-Control-Allow-Headers","Content-Type,Authorization,x-requested-with");
	    return true;
	}

}
