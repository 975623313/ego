package com.an.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.manager.service.ManagerItemDescService;
import com.an.ego.rpc.pojo.TbItemDesc;

@Controller
public class ItemDescController {

	// 注入 service 对象
	@Autowired
	private ManagerItemDescService managerItemDescService;

	/**
	 * 处理加载商品描述信息的请求
	 **/
	@RequestMapping(value = "query/item/desc/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult itemDesc(@PathVariable Long itemId) {
		return managerItemDescService.getItemDescService(itemId);
	}
}
