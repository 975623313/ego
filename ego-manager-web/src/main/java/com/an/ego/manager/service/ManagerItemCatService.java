package com.an.ego.manager.service;

import java.util.List;

import com.an.ego.beans.TreeNode;

public interface ManagerItemCatService {

	/**
	 * 个悲剧节点id，加载当前节点的所有子节点的集合
	 */
	public List<TreeNode> getItemCatList(Long id);
}
