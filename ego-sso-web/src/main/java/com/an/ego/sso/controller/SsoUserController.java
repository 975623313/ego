package com.an.ego.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.pojo.TbUser;
import com.an.ego.sso.service.SsoUserService;



@Controller
public class SsoUserController {

	@Autowired
	private SsoUserService ssoUserService;
	
	/***
	 * 处理用户名唯一性验证请求
	 */
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue userCheck(@PathVariable String param, @PathVariable Integer type,@RequestParam(required=false)String callback){
		  EgoResult result = ssoUserService.loadUserByCondService(param, type);
		  MappingJacksonValue value=new MappingJacksonValue(result);
		  //处理json响应数据格式
		  if(!StringUtils.isEmpty(callback)){
			  value.setJsonpFunction(callback);
			  //return value;
		  }
		  return value;
	}
	/****
	 * 处理用户注册请求
	 */
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult userRegister(TbUser tbUser){
		System.out.println(tbUser);
		return ssoUserService.saveUserService(tbUser);
	}
	/****
	 * 处理用户登录请求
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult userLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		return ssoUserService.selectUser(username, password,request,response);
	}
	/****
	 * 处理获得用户登录状态的请求
	 * @return
	 */
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public MappingJacksonValue userToken(@PathVariable String token,
			@RequestParam(required=false)String callback){
		  EgoResult result = ssoUserService.loadUserByToken(token);
		  MappingJacksonValue value=new MappingJacksonValue(result);
		  //处理json响应数据格式
		  if(!StringUtils.isEmpty(callback)){
			  value.setJsonpFunction(callback);
			  //return value;
		  }
		  return value;
	}
	
	/****
	 * 处理获得用户登录状态的请求
	 * @return
	 */
	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public MappingJacksonValue userLogout(@PathVariable String token,
			@RequestParam(required=false)String callback){
		  EgoResult result = ssoUserService.deleteUserByToken(token);
		  MappingJacksonValue value=new MappingJacksonValue(result);
		  //处理json响应数据格式
		  if(!StringUtils.isEmpty(callback)){
			  value.setJsonpFunction(callback);
			  //return value;
		  }
		  return value;
	}
}
