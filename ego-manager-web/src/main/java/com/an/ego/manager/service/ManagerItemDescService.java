package com.an.ego.manager.service;

import com.an.ego.beans.EgoResult;
import com.an.ego.rpc.pojo.TbItemDesc;

public interface ManagerItemDescService {
/**
* 获得需要回显的商品描述
* **/
public EgoResult getItemDescService(Long itemId);
}
