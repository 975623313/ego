package com.an.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.an.ego.beans.JsonUtils;
import com.an.ego.portal.entity.CatNode;
import com.an.ego.portal.entity.CatResult;
import com.an.ego.portal.service.PortalItemCatService;
import com.an.ego.rpc.pojo.TbItemCat;
import com.an.ego.rpc.service.ItemCatService;

import redis.clients.jedis.JedisCluster;

@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {

	@Value("${ITEM_CAT}")
	private String itemCatkey;
	
	@Autowired
	private JedisCluster cluster;
	@Autowired
	private ItemCatService itemCatServiceProxy;

	@Override
	public String loadItemCatService() {
		
		String jsonStr = cluster.get(itemCatkey);
		if(!StringUtils.isEmpty(jsonStr)){
			System.out.println("缓存了");
			return jsonStr;
		}
		System.out.println("不缓存了");
		List<TbItemCat> list = itemCatServiceProxy.loadItemCatListService();
		CatResult result = new CatResult();
		//将 List 转化为符合前端规范数据格式,递归遍历 list
		List<?> data = getChildren(0L, list);
		 result.setData(data);
		
		String str = JsonUtils.objectToJson(result);
		cluster.set(itemCatkey, str);
		return str;
	}

	private List<?> getChildren(Long parentId, List<TbItemCat> itemCats) {
		// 盛放指定分类下的所有子分类信息
		List resultList = new ArrayList();

		for (TbItemCat itemCat : itemCats) {

			if (itemCat.getParentId().equals(parentId)) {
				if (itemCat.getIsParent()) {
					// 如果itemCat代表一级分类或者二级分类

					CatNode catNode = new CatNode();

					if (itemCat.getParentId().longValue() == 0) {
						// 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
						catNode.setName(
								"<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
					} else {
						// 如果是二级分类 "电子书刊",
						catNode.setName(itemCat.getName());
					}
					// "/products/2.html",
					catNode.setUrl("/products/" + itemCat.getId() + ".html");
					catNode.setList(getChildren(itemCat.getId(), itemCats));
					// 将节点添加到list集合中
					resultList.add(catNode);
				} else {
					// 如果itemCat表示三级分类 "/products/3.html|电子书",
					resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
				}
			}
		}
		return resultList;
	}

}
