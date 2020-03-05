package com.an.ego.manager.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.FtpUtils;
import com.an.ego.beans.IDUtils;
import com.an.ego.beans.PageResults;
import com.an.ego.beans.PictureResult;
import com.an.ego.manager.service.ManagerItemService;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.pojo.TbItemDesc;
import com.an.ego.rpc.pojo.TbItemParam;
import com.an.ego.rpc.pojo.TbItemParamItem;
import com.an.ego.rpc.service.ItemService;

@Service
public class ManagerItemServiceImpl implements ManagerItemService {

	//通过 spring 的 EL 表达式注入 ftp信息
	@Value("${FTP_HOST}")
	private String FTP_HOST;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_PATH}")
	private String FTP_PATH;
	@Value("${IMAGE_HTTP_PATH}")
	private String IMAGE_HTTP_PATH;
	
	
	@Autowired
	private ItemService itemServiceProxy;
	
	@Override
	public PageResults<TbItem> selectItemListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemServiceProxy.selectItenList(page, rows);
	}

	@Override
	public EgoResult reshelfItem(Long[] ids) {
		//将数组转为List集合
		List<Long> itemIds = Arrays.asList(ids);
		return itemServiceProxy.updateItemStatus(itemIds, true);
	}

	@Override
	public EgoResult instockItem(Long[] ids) {
		
		
		List<Long> itemIds = Arrays.asList(ids);
		
		return itemServiceProxy.updateItemStatus(itemIds, false);
	}

	@Override
	public EgoResult deleteItem(Long[] ids) {
		List<Long> itemIds = Arrays.asList(ids);
		return itemServiceProxy.deleteItemById(itemIds);
	}

	@Override
	public PictureResult uploadItemPic(MultipartFile file) {

		System.out.println("t图片上传--------------service");


		// TODO Auto-generated method stub
				boolean flag=false;
				String fileName=null;
				try{

					//获得信息的文件名字
					fileName=IDUtils.genImageName();
					//获得上传的文件的原始名字
					String oriName = file.getOriginalFilename();
					//获得文件扩展名
					String ext=oriName.substring(oriName.lastIndexOf("."));
					
					fileName=fileName+ext;
					
					InputStream local = file.getInputStream();
					
					//实现文件上传到ftp
					flag=FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
							FTP_PATH, fileName, local);
					
					
				}catch(Exception ex){
					ex.printStackTrace();
					flag=false;
				}
				
				PictureResult result=null;
				if(flag){
					result=new PictureResult();
					result.setError(0);
				
					result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
					result.setMessage("ok");
				}else{
					result=new PictureResult();
					result.setError(1);
					result.setUrl("url");
					result.setMessage("error");
				}
				return result;
	}

	@Override
	public EgoResult saveItemService(TbItem item, String desc,String paramData) {
		
		Date date = new Date();
		
		//自己产生商品id，满足后期分表的需求，使用mycat，不能数据接库自动的产生id
		Long id = IDUtils.genItemId();
		
		//封装商品封装数据
		item.setId(id);
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		
		
		//创建TbItemDesc对象
		
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(id);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		
		//调用远程服务，实现商品的信息发布
		
		
		
		return itemServiceProxy.saveItem(item, tbItemDesc,itemParamItem);
	}

	@Override
	public EgoResult updateItemService(TbItem item, String desc) {
		Date date = new Date();
		item.setUpdated(date);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(item.getId());
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		return itemServiceProxy.updateItem(item, tbItemDesc);
	}

}
