package com.an.ego.order.service;

import java.util.List;
import java.util.Map;

import com.an.ego.order.entity.CarItem;
import com.an.ego.rpc.pojo.TbOrder;
import com.an.ego.rpc.pojo.TbOrderItem;
import com.an.ego.rpc.pojo.TbOrderShipping;

public interface OrderService {

	// 购物车集合
	public Map<Long, CarItem> loadCarItemMapService(Long id);

	public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping orderShipping);

	public List<TbOrder> loadOrderListService(Long id);
	
	public List<TbOrderItem> loadOrderItemListService(String orderid);
}
