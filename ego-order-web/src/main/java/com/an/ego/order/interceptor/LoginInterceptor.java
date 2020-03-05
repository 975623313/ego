package com.an.ego.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.an.ego.beans.CookieUtils;
import com.an.ego.beans.JsonUtils;
import com.an.ego.rpc.pojo.TbUser;

import redis.clients.jedis.JedisCluster;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JedisCluster cluster;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
			String token = CookieUtils.getCookieValue(request, "sso_token");
			
			if(!StringUtils.isEmpty(token)){
				String jsonStr = cluster.get(token);
				
				if(!StringUtils.isEmpty(jsonStr)){
					TbUser tbUser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
					request.setAttribute("user", tbUser);
					return true;
				}
			}
			String url = request.getRequestURL().toString();
			
			response.sendRedirect("http://localhost:8083/login?redirect="+url);
			return false;
	}
	
}
