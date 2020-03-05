package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.mapper.TbContentCategoryMapper;
import com.an.ego.rpc.pojo.TbContentCategory;
import com.an.ego.rpc.pojo.TbContentCategoryExample;
import com.an.ego.rpc.pojo.TbContentCategoryExample.Criteria;
import com.an.ego.rpc.service.TbContentCateGoryService;

@Service
public class TbContentCateGoryServiceImpl implements TbContentCateGoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid) {
		// TODO Auto-generated method stub
		System.out.println("loadTbContentCateGoryByPidService-------------------------");
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria c = example.createCriteria();
		c.andParentIdEqualTo(pid);

		return tbContentCategoryMapper.selectByExample(example);
	}

	@Override
	public EgoResult saveTbContentCateGory(TbContentCategory contentCategory) {
		
		EgoResult reuslt = null;

		try {
			TbContentCategory record = new TbContentCategory();
			record.setIsParent(true);
			record.setId(contentCategory.getParentId());
			
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
			System.out.println(contentCategory.getName());
			tbContentCategoryMapper.insert(contentCategory);
			System.out.println(contentCategory.getName());
			reuslt = new EgoResult();
			reuslt.setStatus(200);
			reuslt.setData(contentCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reuslt;
	}

	@Override
	public void deleteTbContentCateGoryService(Long id) {
		// TODO Auto-generated method stub
		// 更新当前点击的节点的父节点 is_parent
		// 获得当前点击的节点父节点 id
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		Long pid = contentCategory.getParentId();
		// 根据 pid 查询，pid 对应的节点是否有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria c = example.createCriteria();
		// where parent_id=?
		c.andParentIdEqualTo(pid);
		int count = tbContentCategoryMapper.countByExample(example);
		if (count == 1) {// 更新当前需要删除的节点的父节点的 is_parent
			TbContentCategory record = new TbContentCategory();
			record.setId(pid);
			record.setIsParent(false);
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
		}
		// 删除当前点击的节点
		tbContentCategoryMapper.deleteByPrimaryKey(id);
	}

}
