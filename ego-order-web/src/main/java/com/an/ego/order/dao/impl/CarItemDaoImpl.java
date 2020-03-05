package com.an.ego.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.order.dao.CarItemDao;

import redis.clients.jedis.JedisCluster;

@Service
public class CarItemDaoImpl implements CarItemDao{

	@Autowired
	private JedisCluster cluster;
	@Override
	public Map<String, String> loadCarItemMap(String uid) {
		// TODO Auto-generated method stub
		return cluster.hgetAll(uid);
	}

	@Override
	public void deleteCarItemMap(String uid) {
		cluster.del(uid);
		
	}

}
