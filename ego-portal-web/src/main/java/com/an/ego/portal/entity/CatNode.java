package com.an.ego.portal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatNode {
	// @JsonProperty 指定将 java 对象转化为 json 格式的时候，对应的 key
	@JsonProperty(value = "u")
	private String url;
	@JsonProperty(value = "n")
	private String name;
	@JsonProperty(value = "i")
	private List<?> list;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}



}
