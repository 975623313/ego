package com.an.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.an.ego.beans.JsonUtils;
import com.an.ego.portal.entity.BigPicture;
import com.an.ego.portal.service.PortalContentService;
import com.an.ego.rpc.pojo.TbContent;
import com.an.ego.rpc.service.TbContentService;

import redis.clients.jedis.JedisCluster;
@Service
public class PortalContentServiceImpl implements PortalContentService {

	@Value("${CONTENT_PICTURE}")
	private String contentPictureKey;
	@Autowired
	private JedisCluster cluster;
	@Autowired
	private TbContentService tbContentServiceProxy;
	@Override
	public String loadContentListByCidService(Long cid) {
		
		
		//redis
		String jsonStr = cluster.get(contentPictureKey);
		if(!StringUtils.isEmpty(jsonStr)){
			return jsonStr;
		}
		
		List<TbContent> list = tbContentServiceProxy.loadTbContentListByCidService(cid);
		//封装前端所需数据
		List<BigPicture> biglist = new ArrayList<>();
		for (TbContent content : list) {
			BigPicture pic = new BigPicture();
			pic.setSrcb(content.getPic());
			pic.setHeight(240);
			pic.setAlt(content.getTitle());
			pic.setWidth(670);
			pic.setSrc(content.getPic2());
			pic.setWidthb(550);
			pic.setHref(content.getUrl());
			pic.setHeightb(240);
			biglist.add(pic);
		}
		//转为json字符串
		String str = JsonUtils.objectToJson(biglist);
		//将 str 保存到 redis 缓存
		cluster.set(contentPictureKey, str);
		cluster.expire(contentPictureKey, 86400);
		return str;
	}

}
