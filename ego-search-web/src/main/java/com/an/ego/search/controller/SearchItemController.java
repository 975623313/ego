package com.an.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.search.entity.SearchResult;
import com.an.ego.search.service.SearchItemService;

@Controller
public class SearchItemController {
	
	
	
	@Autowired
	private SearchItemService searchItemService;
	
	
	/****
	* 进行页面跳转
	*/
	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url,String q,
	@RequestParam(defaultValue="1") Integer page,Model model){
		System.out.println(q);
		
		//调用service层方法
		String kws = null;
		try {
			kws = new String(q.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(kws);
			SearchResult result = searchItemService.loadItemService(kws, page);
			System.out.println(result);
			model.addAttribute("query", kws);
			model.addAttribute("itemList", result.getList());
			model.addAttribute("page", page);
			model.addAttribute("maxpage", result.getMaxpage());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return url;
	}
	@RequestMapping("/item/{id}")
	public String loadItem(@PathVariable Long id,Model model){
		
		TbItem item = searchItemService.loadItemService(id);
		model.addAttribute("item",item);
		return "item";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
