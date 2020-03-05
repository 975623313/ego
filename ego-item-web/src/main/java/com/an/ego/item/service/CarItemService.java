package com.an.ego.item.service;

import java.util.Map;

import com.an.ego.item.entity.CarItem;

public interface CarItemService {
	
	public void addItemToCarService(Long itemid,Long uid);

	public Map<Long,CarItem> loadCarItemListService(Long uid);
	
	public String updateCarItemNumService(Long itemid,Long uid,Integer num);
	
	public void  deleteCarItemService(Long itemid,Long uid);
	
	public void deleteCarItemAllService(Long uid);
}
