package com.an.ego.rpc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.mapper.TbUserMapper;
import com.an.ego.rpc.pojo.TbUser;
import com.an.ego.rpc.pojo.TbUserExample;
import com.an.ego.rpc.pojo.TbUserExample.Criteria;
import com.an.ego.rpc.service.TbUserService;

@Service
public class TbUserServiceImpl implements TbUserService {

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Override
	public EgoResult loadTbUserByCondSerrvice(String cond, Integer type) {
		EgoResult result=new EgoResult();
		
		
		TbUserExample example = new TbUserExample();
		Criteria c = example.createCriteria();
		
		if(type.equals(1)){
		c.andUsernameEqualTo(cond);	
		}else if(type.equals(2)){
			c.andPhoneEqualTo(cond);
		}else {
			c.andEmailEqualTo(cond);
		}
		List<TbUser> list = tbUserMapper.selectByExample(example);
		result.setStatus(200);
		result.setMsg("ok");
		if(list!=null&&list.size()>0){
			result.setData(false);
		}else{
			result.setData(true);
		}
		return result;
	}

	@Override
	public EgoResult saveUserTbService(TbUser tbUser) {
		EgoResult result = new EgoResult();
		try {
			
			String pwd = tbUser.getPassword();
			String md5= DigestUtils.md5DigestAsHex(pwd.getBytes());
			tbUser.setPassword(md5);
			Date date = new Date();
			tbUser.setCreated(date);
			tbUser.setUpdated(date);
			tbUserMapper.insert(tbUser);
			result.setStatus(200);
			result.setMsg("注册成功");
		} catch (Exception e) {
			result.setStatus(400);
			result.setMsg("注册失败，请重新检查");
			
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public TbUser selectUserByUserName(String uname) {
		TbUserExample example = new TbUserExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(uname);
		
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
