package com.an.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.manager.service.ManagerParamItemService;
import com.an.ego.rpc.pojo.TbItemParamItem;
import com.an.ego.rpc.pojo.TbItemParamItemExample;
import com.an.ego.rpc.service.ParamItemService;

@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {

	@Autowired
	private ParamItemService paramItemServiceProxy;
	
	
	@Override
	public EgoResult loadParamItemService(Long itemid) {
		
		EgoResult result =null;
		
		try {
			TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamItemService(itemid);
			result = new EgoResult();
			result.setStatus(200);
			result.setData(tbItemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
