package com.an.ego.manager.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.manager.service.ManagerItemParamService;
import com.an.ego.rpc.pojo.TbItemParam;
import com.an.ego.rpc.service.ItemParamService;

@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

	@Autowired
	private ItemParamService itemParamServiceProxy;

	@Override
	public PageResults<TbItemParam> loadItemParamListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemParamServiceProxy.loadTbItemParamListService(page, rows);
	}

	@Override
	public EgoResult loadItemParamByCidService(Long cid) {
		// TODO Auto-generated method stub
		EgoResult result = null;
		try {

			TbItemParam tbItemParam = itemParamServiceProxy.loadTbItemParamByCidService(cid);
			result = new EgoResult();
			result.setStatus(200);
			result.setData(tbItemParam);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public EgoResult saveItemParamService(Long cid, String paramData) {
		Date date = new Date();
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
	}

	@Override
	public EgoResult deleteItemParamService(Long[] ids) {
		List<Long> id = Arrays.asList(ids);
		return itemParamServiceProxy.deleteTbItemParamService(id);
	}

}
