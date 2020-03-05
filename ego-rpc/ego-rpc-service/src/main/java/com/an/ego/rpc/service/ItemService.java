package com.an.ego.rpc.service;

import java.util.List;

import javax.swing.table.TableModel;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.pojo.TbItemDesc;
import com.an.ego.rpc.pojo.TbItemParam;
import com.an.ego.rpc.pojo.TbItemParamItem;

public interface ItemService {
	/**
	 * 实现商品信息分页查询
	 */
	public PageResults<TbItem> selectItenList(Integer page,Integer rows);
	
	
	/**
	 * 商品的上下架修改
	 * @param itemIds商品的id集合
	 * @param flag true上架，false下架
	 * @return
	 */
	public EgoResult updateItemStatus(List<Long> itemIds,Boolean flag);
	
	//商品的删除
	public EgoResult deleteItemById(List<Long> itemIds);
	
	public EgoResult saveItem(TbItem item,TbItemDesc desc,TbItemParamItem itemParamItems);
	
	public EgoResult updateItem(TbItem item,TbItemDesc desc);
	
	public TbItem loadTbItemById(Long id);
		

}
