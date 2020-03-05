package com.an.ego.rpc.service;

import java.util.List;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.pojo.TbItemParam;

public interface ItemParamService {
	
	/*
	 * 商品规格参数的模板分页
	 */
	public PageResults<TbItemParam> loadTbItemParamListService(Integer page,Integer rows);
	
	
	
	/*
	 * 查询商品规格模板对象，根据Id
	 */
	public TbItemParam loadTbItemParamByCidService(Long cid);
	
	/*
	 * 添加商品规格参数
	 */
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam);
	
	public EgoResult deleteTbItemParamService(List<Long> ids);

}
