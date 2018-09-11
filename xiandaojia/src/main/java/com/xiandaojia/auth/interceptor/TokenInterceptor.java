package com.xiandaojia.auth.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.auth.bean.UserDetail;
import com.xiandaojia.common.utils.JwtUtil;

public class TokenInterceptor implements HandlerInterceptor {
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception arg3) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		String token = request.getHeader("Authorization");
		// token不存在
		if (token != null) {
			UserDetail user = JwtUtil.unsign(token, UserDetail.class);
			if (user != null) {
				return true;
			}
			responseMessage(response, response.getWriter());
			return false;
		} else {
			responseMessage(response, response.getWriter());
			return false;
		}
	}

	private void responseMessage(HttpServletResponse response, PrintWriter out) {
		Map<String, String> authError = new HashMap<String, String>();
		authError.put("retCode", "401");
		response.setContentType("application/json; charset=utf-8");
		String json = JSONObject.toJSONString(authError);
		out.print(json);
		out.flush();
		out.close();
	}
}
