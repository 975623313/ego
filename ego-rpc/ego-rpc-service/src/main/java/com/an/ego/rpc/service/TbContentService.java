package com.an.ego.rpc.service;

import java.util.List;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.pojo.TbContent;


public interface TbContentService {
	public PageResults<TbContent> selectContentByCidList(Long id,Integer page,Integer rows);

	public EgoResult saveTbContentService(TbContent tbContent);
	public EgoResult deleteTbContentService(List<Long> ids);
	public EgoResult updateTbContentService(TbContent tbContent);
	public List<TbContent> loadTbContentListByCidService(Long cid);
}
