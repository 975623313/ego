package com.an.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url){
		System.out.println("urlllllllllll"+url);

		return url;
	}
}
