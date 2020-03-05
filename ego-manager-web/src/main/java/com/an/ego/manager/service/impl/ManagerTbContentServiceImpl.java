package com.an.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;


import com.an.ego.manager.service.ManagerTbContentService;
import com.an.ego.rpc.pojo.TbContent;
import com.an.ego.rpc.service.TbContentService;
@Service
public class ManagerTbContentServiceImpl implements ManagerTbContentService {

	@Autowired
	private TbContentService tbContentServiceProxy;
	
	@Override
	public PageResults<TbContent> selectContentByCidListService(Long id, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return tbContentServiceProxy.selectContentByCidList(id, page, rows);
	}

	@Override
	public EgoResult saveContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		Date date=new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		return tbContentServiceProxy.saveTbContentService(tbContent);
	}

	@Override
	public EgoResult deleteContentService(String ids) {
		String[] idss=ids.split(",");
		List<Long> list = new ArrayList<>();
		for (String id : idss) {
			list.add(Long.parseLong(id));
		}
		return tbContentServiceProxy.deleteTbContentService(list);
	}

	@Override
	public EgoResult updateContentService(TbContent tbContent) {
		Date date = new Date();
		tbContent.setUpdated(date);
		return tbContentServiceProxy.updateTbContentService(tbContent);
	}

}
