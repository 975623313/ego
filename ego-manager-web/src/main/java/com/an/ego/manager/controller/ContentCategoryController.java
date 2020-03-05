package com.an.ego.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.TreeNode;
import com.an.ego.manager.service.ManagerContentCategoryService;
import com.an.ego.rpc.pojo.TbContentCategory;

@Controller
public class ContentCategoryController {

	@Autowired
	private ManagerContentCategoryService managerContentCategoryService;
	//*
	@RequestMapping(value = "/content/category/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public List<TreeNode> contentCategoryList(@RequestParam(defaultValue="0")Long id){
		
		return managerContentCategoryService.loadContentCategoryService(id);
		
		
	}
	
	
	@RequestMapping(value="/content/category/create",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentCategoryCreate(TbContentCategory contentCategory){
		System.out.println(contentCategory+"11111");
		return managerContentCategoryService.saveContentCategoryService(contentCategory);
		
	}
	
	@RequestMapping(value="/content/category/delete",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
			@ResponseBody
			public EgoResult contentCategroyDelete(Long id){
			managerContentCategoryService.deleteContentCategorySevice(id);
			return null; }
}
