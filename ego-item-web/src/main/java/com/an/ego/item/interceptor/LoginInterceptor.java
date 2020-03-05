package com.an.ego.item.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.an.ego.beans.CookieUtils;
import com.an.ego.beans.JsonUtils;
import com.an.ego.rpc.pojo.TbUser;

import redis.clients.jedis.JedisCluster;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private JedisCluster cluster;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获得 token
		String token = CookieUtils.getCookieValue(request, "sso_token");
		if (!StringUtils.isEmpty(token)) {
			// 查询 redis数据
			String jsonStr = cluster.get(token);
			// 验证用户是否登录
			if (!StringUtils.isEmpty(jsonStr)) {
				TbUser tbuser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
				request.setAttribute("user", tbuser);
				// 说明登录，放行
				String url = request.getRequestURL().toString();
				// 重定向到登录页面
				System.out.println(url+"222222222");
				return true;
			}
		}
		// 在用户登录成功后需要调整的路径
		String url = request.getRequestURL().toString();
		// 重定向到登录页面
		System.out.println(url+"222222222");
		response.sendRedirect("http://localhost:8083/login?redirect=" + url);
		return false;
	}

}
