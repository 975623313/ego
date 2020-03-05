package com.an.ego.manager.service;

import org.springframework.web.multipart.MultipartFile;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.beans.PictureResult;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.pojo.TbItemParam;

public interface ManagerItemService {
	
	/*
	 *完成商品信息的分页查询 
	 */
	public PageResults<TbItem> selectItemListService(Integer page,Integer rows);
	
	//商品上架
	public EgoResult reshelfItem(Long[] ids);
	//商品下架
	public EgoResult instockItem(Long[] ids);
	
	//删除商品
		public EgoResult deleteItem(Long[] ids);
		
		/**
		* 完成商品图片的上传
		* **/
		public PictureResult uploadItemPic(MultipartFile file);
		
		
		public EgoResult saveItemService(TbItem item,String desc,String paramData);
		public EgoResult updateItemService(TbItem item,String desc);

}
