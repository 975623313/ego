package com.an.ego.item.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.ego.item.entity.CarItem;
import com.an.ego.item.service.CarItemService;
import com.an.ego.rpc.pojo.TbUser;

@Controller
public class CarItemController {
	@Autowired
	private CarItemService carItemService;
	@RequestMapping("/cart/add/{itemid}")
	public String cartAdd(@PathVariable Long itemid, HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		carItemService.addItemToCarService(itemid, user.getId());
		return "cartSuccess";
	}
	@RequestMapping("/cart/cart")
	public String loadCarItemList(HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		Long uid = user.getId();
		Map<Long, CarItem> carMap = carItemService.loadCarItemListService(uid);
		request.setAttribute("carMap", carMap);
		return "cart";
	}
	@RequestMapping("/cart/update/num/{itemid}/{num}")
	@ResponseBody
	public String cartUpdateNum(@PathVariable Long itemid, @PathVariable Integer num, HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		Long uid = user.getId();
		return carItemService.updateCarItemNumService(itemid, uid, num);
	}
	@RequestMapping("/cart/delete/{itemid}")
	public String cartDelete(@PathVariable Long itemid, HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		Long uid = user.getId();
		carItemService.deleteCarItemService(itemid, uid);
		return "redirect:/cart/cart.html";
	}
	@RequestMapping("/delete/cart/all")
	public String deleteCartAll(HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		Long id = user.getId();
		carItemService.deleteCarItemAllService(id);
		return "redirect:/cart/cart.html";
	}
}
