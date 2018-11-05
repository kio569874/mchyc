package com.xiandaojia.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiandaojia.auth.bean.UserDetail;
import com.xiandaojia.common.utils.JwtUtil;

public class TokenInterceptor implements HandlerInterceptor {
	//是否关闭token校验
	public static boolean CLOSE_FLAG = false;
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception arg3) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if("OPTIONS".equals(request.getMethod())) {
			return true;
		}
		if(CLOSE_FLAG) {
			return true;
		}
		response.setCharacterEncoding("utf-8");
		String token = request.getHeader("Authorization");
		// token不存在
		if (token != null) {
			UserDetail user = JwtUtil.unsign(token, UserDetail.class);
			if (user != null) {
				return true;
			}
			response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers","Content-Type,Authorization,x-requested-with");
			response.setStatus(401);
			return false;
		} else {
			response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers","Content-Type,Authorization,x-requested-with");
		    response.setStatus(401);
			return false;
		}
	}

}
