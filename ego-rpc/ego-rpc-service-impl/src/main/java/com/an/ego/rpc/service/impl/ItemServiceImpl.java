package com.an.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.EgoResult;
import com.an.ego.beans.PageResults;
import com.an.ego.rpc.mapper.TbItemDescMapper;
import com.an.ego.rpc.mapper.TbItemMapper;
import com.an.ego.rpc.mapper.TbItemParamItemMapper;
import com.an.ego.rpc.mapper.TbItemParamMapper;
import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.rpc.pojo.TbItemDesc;
import com.an.ego.rpc.pojo.TbItemDescExample;
import com.an.ego.rpc.pojo.TbItemExample;
import com.an.ego.rpc.pojo.TbItemParam;
import com.an.ego.rpc.pojo.TbItemParamItem;
import com.an.ego.rpc.pojo.TbItemExample.Criteria;
import com.an.ego.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ItemServiceImpl implements ItemService {

	// 注入mapper接口代理对象
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public PageResults<TbItem> selectItenList(Integer page, Integer rows) {
		// 执行分页操作,利用PageHelper分页处理，传参拦截
// 执行分页操作,利用PageHelper分页处理，传参拦截
		//执行分页操作
		Page ps = PageHelper.startPage(page, rows);

		TbItemExample example=new  TbItemExample();

		//执行数据库查询操作
		List<TbItem> list = tbItemMapper.selectByExample(example);

		PageResults<TbItem> result = new PageResults<TbItem>();

		result.setRows(list);
		result.setTotal(ps.getTotal());
		return result;
		 


	}

	@Override
	public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag) {

		// TbItem item = new TbItem();
		//
		// if(flag){
		// item.setStatus((byte)1);
		// }else{
		// item.setStatus((byte)2);
		// }
		//
		// //动态产生where条件
		//
		// TbItemExample example = new TbItemExample();
		//
		// Criteria c = example.createCriteria();
		// c.andIdIn(itemIds);//相当于in（）
		// tbItemMapper.updateByExampleSelective(item, example);
		//
		// return EgoResult.ok();

		Byte by = null;
		TbItem item = new TbItem();
		if (flag) {
			by = 1;
		} else {
			by = 2;
		}
		item.setStatus(by);

		// 动态产生where条件
		TbItemExample example = new TbItemExample();
		Criteria c = example.createCriteria();
		c.andIdIn(itemIds);

		// where id in(3,4,5)
		// tbItemMapper.updateByExample(item, example);
		tbItemMapper.updateByExampleSelective(item, example);
		// where id=?
		// tbItemMapper.updateByPrimaryKey(record)
		return EgoResult.ok();

	}

	@Override
	public EgoResult deleteItemById(List<Long> itemIds) {
		
		TbItemExample example = new TbItemExample();
		Criteria c = example.createCriteria();
		c.andIdIn(itemIds);
		tbItemMapper.deleteByExample(example);
		
		return EgoResult.ok();
	}

	@Override
	public EgoResult saveItem(TbItem item, TbItemDesc desc,TbItemParamItem itemParamItem) {
		// TODO Auto-generated method stub
		
		tbItemMapper.insert(item);
		tbItemDescMapper.insert(desc);
		tbItemParamItemMapper.insert(itemParamItem);
		return EgoResult.ok();
	}

	@Override
	public EgoResult updateItem(TbItem item, TbItemDesc desc) {
//		//更新商品的基本信息
//		this.tbItemMapper.updateByPrimaryKeySelective(item);//Selective
//		TbItemDescExample example = new TbItemDescExample();
//		com.an.ego.rpc.pojo.TbItemDescExample.Criteria c = example.createCriteria();
//		c.andItemIdEqualTo(desc.getItemId());//where utemId=?
//		
//		//查询商品描述
//		Integer rows = tbItemDescMapper.countByExample(example);
//		//判断是否存在描写
//		if(rows==0){
//			this.tbItemDescMapper.insert(desc);
//		}else{
//			desc.setCreated(null);
//			this.tbItemDescMapper.updateByPrimaryKeySelective(desc);
//		}
//		
//		return EgoResult.ok();
		//更新商品基本信息
		this.tbItemMapper.updateByPrimaryKeySelective(item);
		TbItemDescExample example=new TbItemDescExample();
		com.an.ego.rpc.pojo.TbItemDescExample.Criteria c = example.createCriteria();
		c.andItemIdEqualTo(desc.getItemId());
		//where itemId=?
		//查询某个商品对于的描述
		Integer rows=tbItemDescMapper.countByExample(example);
		/**
		* 判断该商品是否存在描述信息
		* **/
		if(rows==0){
		this.tbItemDescMapper.insert(desc);
		}else{
		desc.setCreated(null);
		this.tbItemDescMapper.updateByPrimaryKeySelective(desc);
		}
		return EgoResult.ok();
	}

	@Override
	public TbItem loadTbItemById(Long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
