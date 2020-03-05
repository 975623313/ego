package com.an.ego.order.entity;

import java.io.Serializable;

import com.an.ego.rpc.pojo.TbItem;

public class CarItem implements Serializable{
	private TbItem item; //购买商品对象
	private Integer num; //购买商品的数量
	public TbItem getItem() {
		return item;
	}
	public void setItem(TbItem item) {
		this.item = item;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
