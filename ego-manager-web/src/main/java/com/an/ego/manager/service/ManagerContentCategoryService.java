package com.an.ego.manager.service;

import java.util.List;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.TreeNode;
import com.an.ego.rpc.pojo.TbContentCategory;


public interface ManagerContentCategoryService {

	public List<TreeNode> loadContentCategoryService(Long pid);
	
	public EgoResult saveContentCategoryService(TbContentCategory contentCategory);

	public void deleteContentCategorySevice(Long id);


}
