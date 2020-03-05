package com.an.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.manager.service.ManagerTbContentService;
import com.an.ego.rpc.pojo.TbContent;

@Controller
public class TbContentConteoller {

	@Autowired
	private ManagerTbContentService managerTbContentService;

	@RequestMapping(value = "/content/query/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public PageResults<TbContent> contentQueryList(Long categoryId, Integer page, Integer rows) {
		return managerTbContentService.selectContentByCidListService(categoryId, page, rows);
	}

	@RequestMapping(value = "/content/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public EgoResult contentSave(TbContent tbContent) {
		return managerTbContentService.saveContentService(tbContent);
	}
	
	@RequestMapping(value="content/delete",produces=MediaType.APPLICATION_JSON_VALUE+";charset")
	@ResponseBody
	public EgoResult contentDelete(String ids){
		return managerTbContentService.deleteContentService(ids);
	}
	@RequestMapping(value="/rest/content/edit",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
			@ResponseBody
			public EgoResult contentEdit(TbContent tbContent){
			return managerTbContentService.updateContentService(tbContent);
			}
	

}
