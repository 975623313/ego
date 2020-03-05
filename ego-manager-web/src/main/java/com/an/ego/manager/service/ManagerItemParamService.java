package com.an.ego.manager.service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.pojo.TbItemParam;

public interface ManagerItemParamService {

	public PageResults<TbItemParam> loadItemParamListService(Integer page,Integer rows);
	public EgoResult loadItemParamByCidService(Long cid);
	public EgoResult saveItemParamService(Long cid,String paramData);
	public EgoResult deleteItemParamService(Long[] ids);
}
