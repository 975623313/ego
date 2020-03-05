package com.an.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.rpc.mapper.TbItemDescMapper;
import com.an.ego.rpc.pojo.TbItemDesc;
import com.an.ego.rpc.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public TbItemDesc getItemDesc(Long itemId) {
		// TODO Auto-generated method stub
		System.out.println(itemId+"````````````````````````");
		TbItemDesc desc = tbItemDescMapper.selectByPrimaryKey(itemId);
		System.out.println(desc+"2222222222222");
		return desc;
	}

}
