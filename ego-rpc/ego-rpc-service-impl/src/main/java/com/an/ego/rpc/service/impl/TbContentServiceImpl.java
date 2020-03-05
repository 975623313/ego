package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.mapper.TbContentMapper;
import com.an.ego.rpc.pojo.TbContent;
import com.an.ego.rpc.pojo.TbContentExample;
import com.an.ego.rpc.pojo.TbContentExample.Criteria;
import com.an.ego.rpc.service.TbContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class TbContentServiceImpl implements TbContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public PageResults<TbContent> selectContentByCidList(Long id, Integer page, Integer rows) {
		
		
			// 执行分页操作,利用PageHelper分页处理，传参拦截
			Page pe =PageHelper.startPage(page, rows);
			
			TbContentExample example = new TbContentExample();
			Criteria c = example.createCriteria();
			c.andCategoryIdEqualTo(id);
			List<TbContent> list = tbContentMapper.selectByExample(example);
			PageResults<TbContent> result = new PageResults<>();
			result.setRows(list);
			result.setTotal(pe.getTotal());
			return result;
		
		
		
		

	}

	@Override
	public EgoResult saveTbContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		try {
			tbContentMapper.insert(tbContent);
			return EgoResult.ok();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public EgoResult deleteTbContentService(List<Long> ids) {
		try {
			TbContentExample example = new TbContentExample();
			Criteria c = example.createCriteria();
			c.andIdIn(ids);
			 tbContentMapper.deleteByExample(example);
			 return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public EgoResult updateTbContentService(TbContent tbContent) {
		try {
			tbContentMapper.updateByPrimaryKeySelective(tbContent);
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<TbContent> loadTbContentListByCidService(Long cid) {
		try {
			TbContentExample example = new TbContentExample();
			Criteria c = example.createCriteria();
			c.andCategoryIdEqualTo(cid);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			// TODO Auto-generated method stub
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	
	
	
}
