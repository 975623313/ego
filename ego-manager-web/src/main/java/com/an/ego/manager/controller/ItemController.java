package com.an.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.manager.service.ManagerItemService;
import com.an.ego.rpc.pojo.TbItem;

@Controller
public class ItemController {

	@Autowired
	private ManagerItemService managerItemService;
	/*
	 * 处理商品信息分页查询请求
	 */
	@RequestMapping(value = "item/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public PageResults<TbItem> itemList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows) {

		return managerItemService.selectItemListService(page, rows);

	}
	@RequestMapping(value = "/item/reshelf", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult reshelfItem(Long[] ids) {
		return managerItemService.reshelfItem(ids);
	}
	@RequestMapping(value = "/item/instock", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult instockItem(Long[] ids) {
		System.out.println("instock:---");
		for (int i=0;i<ids.length;i++){
			System.out.print(ids[i]+",");
		}
		return managerItemService.instockItem(ids);
	}
	@RequestMapping(value = "/item/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult deleteItem(Long[] ids) {
		return managerItemService.deleteItem(ids);
	}
	/*
	 * 处理商品信息的发布请求
	 * 
	 */
	@RequestMapping(value = "/item/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult itenSave(TbItem item, String desc,String itemParams) {
		return managerItemService.saveItemService(item, desc,itemParams);
	}
	@RequestMapping(value = "item/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult itemUpate(TbItem item, String desc) {
		return managerItemService.updateItemService(item, desc);
	}

}
