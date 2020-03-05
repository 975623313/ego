package com.an.ego.sso.service.impl;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.an.ego.beans.CookieUtils;
import com.an.ego.beans.EgoResult;
import com.an.ego.beans.JsonUtils;
import com.an.ego.rpc.pojo.TbUser;
import com.an.ego.rpc.service.TbUserService;
import com.an.ego.sso.service.SsoUserService;

import redis.clients.jedis.JedisCluster;

@Service
public class SsoUserServiceImpl implements SsoUserService {

	@Autowired
	private JedisCluster cluster;
	
	@Autowired
	private TbUserService tbUserServiceProxy;
	
	@Override
	public EgoResult loadUserByCondService(String cond, Integer type) {
		// TODO Auto-generated method stub
		return tbUserServiceProxy.loadTbUserByCondSerrvice(cond, type);
	}

	@Override
	public EgoResult saveUserService(TbUser tbUser) {
		
		return tbUserServiceProxy.saveUserTbService(tbUser);
	}

	@Override
	public EgoResult selectUser(String uname, String password,HttpServletRequest request,HttpServletResponse response) {
		EgoResult result = new EgoResult();
		TbUser tbUser = tbUserServiceProxy.selectUserByUserName(uname);
		if(tbUser!=null){
			//对前台数据进行加密，然后跟数据库里面的值比较
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			if(password.equals(tbUser.getPassword())){
				String jsonStr = JsonUtils.objectToJson(tbUser);
				String token=UUID.randomUUID().toString();
				//保存到redis
				cluster.set(token, jsonStr);
				//cluster.expire(token, 3000);
				
				//将token响应到客户端
				
				CookieUtils.setCookie(request, response, "sso_token", token);
				result.setData(token);
				result.setMsg("ok");
				result.setStatus(200);
				return result;
			}
		}
		result.setData(null);
		result.setMsg("error");
		result.setStatus(400);
		return result;
	}

	@Override
	public EgoResult loadUserByToken(String token) {
		EgoResult result = new EgoResult();
		
		String jsonStr = cluster.get(token);
		if(!StringUtils.isEmpty(jsonStr)){
			result.setStatus(200);
			result.setMsg("ok");
			result.setData(jsonStr);
			return result;
			
			
		}
		result.setData(null);
		result.setMsg("error");
		result.setStatus(400);
		return result;
	}

	@Override
	public EgoResult deleteUserByToken(String token) {
		
		EgoResult result = new EgoResult();
		Long del = cluster.del(token);
		if(!del.equals(0L)){
			result.setStatus(200);
			result.setMsg("ok");
			result.setData("");
			return result;
		}
		
		result.setData(null);
		result.setMsg("error");
		result.setStatus(400);
		return result;
	}

}
