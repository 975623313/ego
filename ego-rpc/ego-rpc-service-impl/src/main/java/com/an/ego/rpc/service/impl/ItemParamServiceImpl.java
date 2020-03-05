package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.container.page.PageHandler;
import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.mapper.TbItemParamMapper;
import com.an.ego.rpc.pojo.TbItemParam;
import com.an.ego.rpc.pojo.TbItemParamExample;
import com.an.ego.rpc.pojo.TbItemParamExample.Criteria;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ItemParamServiceImpl implements com.an.ego.rpc.service.ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public PageResults<TbItemParam> loadTbItemParamListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		
		PageResults<TbItemParam> result = new PageResults<>();
		
		//进行分页参数的指定
		Page pe = PageHelper.startPage(page,rows);
		
		TbItemParamExample example = new TbItemParamExample();
		//selectByExampleWithBLOBs可以大字段数据库的text
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		result.setRows(list);
		result.setTotal(pe.getTotal());
		return result;
	}

	@Override
	public TbItemParam loadTbItemParamByCidService(Long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example=new TbItemParamExample();
		
		//封装查询条件
		Criteria c = example.createCriteria();
		c.andItemCatIdEqualTo(cid);
		
		 
		
		List<TbItemParam> list = 
				tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam) {
		
		try {
			tbItemParamMapper.insert(tbItemParam);
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EgoResult deleteTbItemParamService(List<Long> ids) {
		try {
			//创建 Example 对象
			TbItemParamExample example=new TbItemParamExample();
			Criteria c = example.createCriteria();
			//封装删除条件 where id in(1,23,3)
			c.andIdIn(ids); 
			tbItemParamMapper.deleteByExample(example);
			return EgoResult.ok();
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		return null;
	}

}
