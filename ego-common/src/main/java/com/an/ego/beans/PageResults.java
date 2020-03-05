package com.an.ego.beans;

import java.io.Serializable;
import java.util.List;

/*
 * 页面查询的条数总计和每一页有多少条记录
 */
public class PageResults<T> implements Serializable {
	private List<T> rows;
	
	private Long total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	

}
