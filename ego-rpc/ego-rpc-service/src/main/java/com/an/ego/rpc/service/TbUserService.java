package com.an.ego.rpc.service;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.pojo.TbUser;

public interface TbUserService {

	public EgoResult loadTbUserByCondSerrvice(String cond,Integer type);
	
	
	public EgoResult saveUserTbService(TbUser tbUser);
	public TbUser selectUserByUserName(String uname);
}
