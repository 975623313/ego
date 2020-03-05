package com.an.ego.order.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class PageController {
	 @RequestMapping("/{url}")
	 public String loadPage(@PathVariable String url){
		 return url;
	 }
	 
}
