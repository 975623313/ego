package com.an.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.manager.service.ManagerParamItemService;

@Controller
public class ParamItemParamController {
	@Autowired
	private ManagerParamItemService managerParamItemService;

	/****
	 * 处理加载商品规格参数信息的请求
	 */
	@RequestMapping(value = "/param/item/query/{itemid}", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public EgoResult paramItemQuery(@PathVariable Long itemid) {
		return managerParamItemService.loadParamItemService(itemid);
	}

}
