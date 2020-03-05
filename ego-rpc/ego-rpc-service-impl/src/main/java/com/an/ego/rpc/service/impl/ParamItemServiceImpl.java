package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.rpc.mapper.TbItemParamItemMapper;
import com.an.ego.rpc.pojo.TbItemParamItem;
import com.an.ego.rpc.pojo.TbItemParamItemExample;
import com.an.ego.rpc.service.ParamItemService;

@Service
public class ParamItemServiceImpl implements ParamItemService {

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public TbItemParamItem loadTbItemParamItemService(Long itemid) {
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria c = example.createCriteria();
		c.andItemIdEqualTo(itemid);
		
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()==1)
		return list.get(0);
		return null;
	}

}
