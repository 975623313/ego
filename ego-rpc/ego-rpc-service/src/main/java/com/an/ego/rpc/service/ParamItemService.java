package com.an.ego.rpc.service;


import com.an.ego.rpc.pojo.TbItemParamItem;

public interface ParamItemService {
	
	/*
	 * 根据商品id查询对应的规格信息
	 */
	public TbItemParamItem loadTbItemParamItemService(Long itemid);

}
