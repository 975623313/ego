package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.rpc.mapper.TbOrderItemMapper;
import com.an.ego.rpc.mapper.TbOrderMapper;
import com.an.ego.rpc.mapper.TbOrderShippingMapper;
import com.an.ego.rpc.pojo.TbOrder;
import com.an.ego.rpc.pojo.TbOrderExample;
import com.an.ego.rpc.pojo.TbOrderExample.Criteria;
import com.an.ego.rpc.pojo.TbOrderItem;
import com.an.ego.rpc.pojo.TbOrderItemExample;
import com.an.ego.rpc.pojo.TbOrderShipping;
import com.an.ego.rpc.service.TbOrderService;

@Service
public class TbOrderServiceImpl implements TbOrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;

	@Override
	public void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping) {
		try {

			tbOrderMapper.insert(tbOrder);
			for (TbOrderItem tbOrderItem : orderItems) {
				tbOrderItemMapper.insert(tbOrderItem);

			}
			tbOrderShippingMapper.insert(tbOrderShipping);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public List<TbOrder> loadTbOrderListService(Long id) {
		TbOrderExample example = new TbOrderExample();
		Criteria c = example.createCriteria();
		c.andUserIdEqualTo(id);

		return tbOrderMapper.selectByExample(example);
	}

	@Override
	public List<TbOrderItem> loadTbOrderItemListService(String orderid) {
		// TODO Auto-generated method stub
		TbOrderItemExample example = new TbOrderItemExample();
		com.an.ego.rpc.pojo.TbOrderItemExample.Criteria c = example.createCriteria();
		// where order_id=orderid; c.andOrderIdEqualTo(orderid);
		List<TbOrderItem> list = tbOrderItemMapper.selectByExample(example);
		return list;
	}
}
