package com.an.ego.item.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.an.ego.beans.JsonUtils;
import com.an.ego.item.dao.CarItemDao;
import com.an.ego.item.entity.CarItem;
import com.an.ego.item.service.CarItemService;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.service.ItemService;


@Service
public class CarItemServiceImpl implements CarItemService {

	@Autowired
	private CarItemDao carItemDao;
	
	@Autowired
	private ItemService itemServiceProxy;
	
	@Override
	public void addItemToCarService(Long itemid, Long uid) {
		// TODO Auto-generated method stub
		Map<String,String> carMap=null;
		CarItem carItem=null;
		//获得商品对象
		TbItem tbItem = itemServiceProxy.loadTbItemById(itemid);
		//判断用户是否第一次购物，或者是判断是否已经存在 carMap
		carMap = carItemDao.loadCarMap(String.valueOf(uid));
		if(carMap==null){
		//创建 Map 集合对象
		carMap=new HashMap<>();
		}
		//判断 itemid对象的商品是否存在一个购物车对象
		String carItemStr = 
		carItemDao.loadCarItem(String.valueOf(uid),String.valueOf(itemid));
		if(StringUtils.isEmpty(carItemStr)){
		//创建购物车对象
		carItem=new CarItem();
		//将商品信息放入到购物车
		carItem.setItem(tbItem);
		carItem.setNum(1);
		}else{
		carItem=JsonUtils.jsonToPojo(carItemStr, CarItem.class);
		carItem.setNum(carItem.getNum()+1);//修改购物车数量
		}
		//将购物车对象转化为 json字符串
		String jsonStr = JsonUtils.objectToJson(carItem);
		//购物车对象放入 map 集合
		carMap.put(String.valueOf(itemid), jsonStr);
		//将 carMap 集合保存到 redis数据库
		carItemDao.addCartMap(String.valueOf(uid), carMap);
		}


	@Override
	public Map<Long, CarItem> loadCarItemListService(Long uid) {
		Map<Long, CarItem> carMap=new HashMap<>();
		// 获得某个用户的购物车列表
		Map<String, String> carMapStr = carItemDao.loadCarMap(String.valueOf(uid));
		for(Entry<String,String> e:carMapStr.entrySet()){
		String key=e.getKey();
		String value=e.getValue();
		//将 value 字符串，转化为 CarItem 对象
		CarItem carItem= JsonUtils.jsonToPojo(value, CarItem.class);
		carMap.put(Long.parseLong(key),carItem);
		}
		return carMap;
	}


	@Override
	public String updateCarItemNumService(Long itemid, Long uid, Integer num) {
		try {
			//获得需要修改的购物车对象
			String carItemStr = carItemDao.loadCarItem(String.valueOf(uid), String.valueOf(itemid));
			
			//将carItemStr转化为carItem对象
			CarItem carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
			//修改
			carItem.setNum(num);
			//将修改后的数据更新到redis
		carItemDao.updateCarMapNum(String.valueOf(uid), String.valueOf(itemid), JsonUtils.objectToJson(carItem));
			
		return "ok";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	@Override
	public void deleteCarItemService(Long itemid, Long uid) {
		carItemDao.deleteCarMapItem(String.valueOf(uid),String.valueOf(itemid));
		
	}


	@Override
	public void deleteCarItemAllService(Long uid) {
		carItemDao.deleteCarMapAll(String.valueOf(uid));
		
	}

}
