package com.an.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.TreeNode;
import com.an.ego.manager.service.ManagerItemCatService;
import com.an.ego.rpc.pojo.TbItemCat;
import com.an.ego.rpc.service.ItemCatService;
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

	//远程代理服务对象
	@Autowired
	private ItemCatService itemCatServiceProxy;
	
	@Override
	public List<TreeNode> getItemCatList(Long id) {
//调用远程服务
		List<TbItemCat> list = itemCatServiceProxy.getItemCatListParentId(id);
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		TreeNode node = null;
		for (TbItemCat cat : list) {
			node = new TreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		
		return nodeList;
	}

}
