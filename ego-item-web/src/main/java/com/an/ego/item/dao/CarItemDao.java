package com.an.ego.item.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;


public interface CarItemDao {
	public void addCartMap(String uid,Map<String, String> carMap);
	public Map<String,String> loadCarMap(String uid);
	public String loadCarItem(String uid,String itemid);
	
	public void updateCarMapNum(String uid,String itemid,String carItemStr);

	public void deleteCarMapItem(String uid,String itemid);
	
	public void deleteCarMapAll(String uid);
}
