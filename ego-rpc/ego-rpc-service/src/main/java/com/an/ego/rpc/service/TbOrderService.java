package com.an.ego.rpc.service;

import java.util.List;

import com.an.ego.rpc.pojo.TbOrder;
import com.an.ego.rpc.pojo.TbOrderItem;
import com.an.ego.rpc.pojo.TbOrderShipping;

public interface TbOrderService {
	public void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping);
	
	public List<TbOrder> loadTbOrderListService(Long id);
	
	public List<TbOrderItem> loadTbOrderItemListService(String orderid);

}
