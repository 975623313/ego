package com.an.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.IDUtils;
import com.an.ego.beans.TreeNode;
import com.an.ego.manager.service.ManagerContentCategoryService;
import com.an.ego.rpc.pojo.TbContentCategory;
import com.an.ego.rpc.service.TbContentCateGoryService;

@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {

	@Autowired
	private TbContentCateGoryService tbContentCateGoryServiceProxy;
	
	@Override
	public List<TreeNode> loadContentCategoryService(Long pid) {
		List<TreeNode> list = new ArrayList<>();
		System.out.println("(ManagerContentCategoryServiceImpl.java:26)------------------");
		List<TbContentCategory> contentCategoryList = tbContentCateGoryServiceProxy.loadTbContentCateGoryByPidService(pid);
		for (TbContentCategory c : contentCategoryList) {
			TreeNode node= new TreeNode();
			node.setId(c.getId());
			node.setText(c.getName());
			node.setState(c.getIsParent()?"closed":"open");
			list.add(node);
		}
		
		
		return list;
	}

	@Override
	public EgoResult saveContentCategoryService(TbContentCategory contentCategory) {
	Date date = new Date();
	Long id= IDUtils.genItemId();
	contentCategory.setId(id);
	contentCategory.setCreated(date);
	contentCategory.setUpdated(date);
	contentCategory.setStatus(1);
	contentCategory.setSortOrder(1);
	contentCategory.setIsParent(false);
		return tbContentCateGoryServiceProxy.saveTbContentCateGory(contentCategory);
	}

	@Override
	public void deleteContentCategorySevice(Long id) {
		// TODO Auto-generated method stub
		tbContentCateGoryServiceProxy.deleteTbContentCateGoryService(id);
	}



}
