package com.an.ego.rpc.service;

import java.util.List;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.pojo.TbContentCategory;

public interface TbContentCateGoryService {
	
	/**
	 * 加载内容分类树
	 */
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid);
	
	/*
	 * 增加内容分类节点
	 */
	public EgoResult saveTbContentCateGory(TbContentCategory contentCategory);
	
	//删除内容分类节点
	public void deleteTbContentCateGoryService(Long id);
}
