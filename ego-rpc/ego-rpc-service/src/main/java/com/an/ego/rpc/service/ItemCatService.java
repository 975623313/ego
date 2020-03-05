package com.an.ego.rpc.service;

import java.util.List;

import com.an.ego.rpc.pojo.TbItemCat;

public interface ItemCatService {
	

	/*
	 * 根据某节点id，查询响应的子节点
	 */
	public List<TbItemCat> getItemCatListParentId(Long id);
	
	/**
	 * 加载门户首页的商品类目
	 */
	public List<TbItemCat> loadItemCatListService();
	
}
