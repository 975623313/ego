package com.an.ego.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.pojo.TbUser;

public interface SsoUserService {

	/**
	 * 验证用户名的唯一性
	 * @param cond
	 * @param type
	 * @return
	 */
	
	public EgoResult loadUserByCondService(String cond,Integer type);
	
	public EgoResult saveUserService(TbUser tbUser);
	public EgoResult selectUser(String uname,String password,HttpServletRequest request,HttpServletResponse response);
	
	public EgoResult loadUserByToken(String token);
	public EgoResult deleteUserByToken(String token);
}
