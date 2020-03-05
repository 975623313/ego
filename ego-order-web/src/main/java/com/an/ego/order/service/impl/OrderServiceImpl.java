package com.an.ego.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.IDUtils;
import com.an.ego.beans.JsonUtils;
import com.an.ego.order.dao.CarItemDao;
import com.an.ego.order.entity.CarItem;
import com.an.ego.order.service.OrderService;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.pojo.TbOrder;
import com.an.ego.rpc.pojo.TbOrderItem;
import com.an.ego.rpc.pojo.TbOrderShipping;
import com.an.ego.rpc.service.TbOrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CarItemDao carItemDao;

	@Autowired
	private TbOrderService tbOrderServiceProxy;

	@Override
	public Map<Long, CarItem> loadCarItemMapService(Long id) {
		Map<String, String> carMapStr = carItemDao.loadCarItemMap(String.valueOf(id));
		Map<Long, CarItem> carMap = new HashMap<>();
		for (Entry<String, String> e : carMapStr.entrySet()) {
			carMap.put(Long.parseLong(e.getKey()), JsonUtils.jsonToPojo(e.getValue(), CarItem.class));
		}
		return carMap;
	}

	@Override
	public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping orderShipping) {
		try {
			Date date = new Date();
			// 产生订单号
			String orderid = String.valueOf(IDUtils.genItemId());
			tbOrder.setOrderId(orderid);
			tbOrder.setPostFee("123");
			tbOrder.setStatus(2);
			tbOrder.setCreateTime(date);
			tbOrder.setUpdateTime(date);
			tbOrder.setPaymentTime(date);
			tbOrder.setConsignTime(date);
			tbOrder.setEndTime(date);
			tbOrder.setCloseTime(date);
			tbOrder.setShippingName("EMS");
			tbOrder.setShippingCode("110110");
			tbOrder.setUserId(uid);
			tbOrder.setBuyerMessage("message");
			tbOrder.setBuyerNick("9527");
			tbOrder.setBuyerRate(0);
			// 获得用户的购物车集合
			Map<Long, CarItem> carMap = this.loadCarItemMapService(uid);
			// 创建 List<TbOrderItem>
			List<TbOrderItem> list = new ArrayList<>();
			for (CarItem carItem : carMap.values()) {
				// 产生订单明细主键
				String id = String.valueOf(IDUtils.genItemId());
				// 创建订单明细对象
				TbOrderItem orderItem = new TbOrderItem();
				orderItem.setId(id);
				orderItem.setOrderId(orderid);
				// 获得购物车中的商品对象
				TbItem item = carItem.getItem();
				orderItem.setItemId(String.valueOf(item.getId()));
				orderItem.setNum(carItem.getNum());
				orderItem.setTitle(item.getTitle());
				orderItem.setPrice(item.getPrice());
				orderItem.setTotalFee(item.getPrice() * carItem.getNum());
				orderItem.setPicPath(item.getImages()[0]);
				list.add(orderItem);
			}
			orderShipping.setOrderId(orderid);
			orderShipping.setReceiverPhone("1110");
			orderShipping.setCreated(date);
			orderShipping.setUpdated(date);
			// 调用 rpc远程服务
			tbOrderServiceProxy.saveTbOrderService(tbOrder, list, orderShipping);
			Map<String, String> map = new HashMap<>();
			map.put("orderid", orderid);
			map.put("total", tbOrder.getPayment());
			carItemDao.deleteCarItemMap(String.valueOf(uid));
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TbOrder> loadOrderListService(Long id) {
		// TODO Auto-generated method stub
		return tbOrderServiceProxy.loadTbOrderListService(id);
	}

	@Override
	public List<TbOrderItem> loadOrderItemListService(String orderid) {
		// TODO Auto-generated method stub
		return tbOrderServiceProxy.loadTbOrderItemListService(orderid);
	}

}
