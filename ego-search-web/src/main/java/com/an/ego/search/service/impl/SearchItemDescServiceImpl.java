package com.an.ego.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.an.ego.rpc.pojo.TbItemDesc;
import com.an.ego.rpc.service.ItemDescService;
import com.an.ego.search.service.SearchItemDescService;

@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {
	// 注入远程服务代理对象
	@Autowired
	private ItemDescService itemDescServiceProxy;

	@Override
	public String loadItemDescService(Long id) {
		// TODO Auto-generated method stub
		
		TbItemDesc itemDesc = itemDescServiceProxy.getItemDesc(id);
		return itemDesc.getItemDesc();
	}
}