package com.an.ego.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.portal.service.PortalItemCatService;

@Controller
public class PortalItemCatController {

	@Autowired
	private PortalItemCatService portalItemCatService;
	@RequestMapping(value="/item/cat",produces=MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
	@ResponseBody
	public String itemCat(){
		return portalItemCatService.loadItemCatService();
	}
}
