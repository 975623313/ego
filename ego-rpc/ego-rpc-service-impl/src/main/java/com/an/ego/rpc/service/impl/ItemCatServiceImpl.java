package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.rpc.mapper.TbItemCatMapper;
import com.an.ego.rpc.mapper.TbItemMapper;
import com.an.ego.rpc.pojo.TbItemCat;
import com.an.ego.rpc.pojo.TbItemCatExample;
import com.an.ego.rpc.pojo.TbItemCatExample.Criteria;
import com.an.ego.rpc.pojo.TbItemCatExample.Criterion;
import com.an.ego.rpc.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> getItemCatListParentId(Long id) {
		//创建TaItemCatExample对象
		TbItemCatExample example = new TbItemCatExample();
		
		Criteria c = example.createCriteria();
		//where parent_id=?
		c.andParentIdEqualTo(id);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		
		return list;
	}

	@Override
	public List<TbItemCat> loadItemCatListService() {
		// 查询所有的商品类目不需要添加
		TbItemCatExample example= new TbItemCatExample();
		
		return tbItemCatMapper.selectByExample(example);
	}

}
