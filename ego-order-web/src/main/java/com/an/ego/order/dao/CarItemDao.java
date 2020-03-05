package com.an.ego.order.dao;

import java.util.Map;

public interface CarItemDao {
	
	//加载购物车集合
	public Map<String, String> loadCarItemMap(String uid);
	
	//清空购物车
	public void deleteCarItemMap(String uid);

}
