package com.an.ego.manager.service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.pojo.TbContent;

public interface ManagerTbContentService {
	public PageResults<TbContent> selectContentByCidListService(Long id,Integer page,Integer rows);

	public EgoResult saveContentService(TbContent tbContent);
	public EgoResult deleteContentService(String ids);
	public EgoResult updateContentService(TbContent tbContent);
	
	
}
