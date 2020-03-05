package com.an.ego.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.ego.beans.JsonUtils;
import com.an.ego.rpc.pojo.TbItemParamItem;
import com.an.ego.rpc.service.ParamItemService;
import com.an.ego.search.service.SearchItemParamService;

@Service
public class SearchItemParamServiceImpl implements SearchItemParamService {
	@Autowired
	private ParamItemService paramItemServiceProxy;

	@Override
	public String loadItemParamService(Long id) {
		// TODO Auto-generated method stub
		TbItemParamItem paramItem = paramItemServiceProxy.loadTbItemParamItemService(id);
		// 获得商品规格参数,是 json 字符串
		String paramData = paramItem.getParamData();
		return getItemParamData(paramData);
	}

	private String getItemParamData(String paramData) {
		// 解析 json 字符串
		List<Map> listMap = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append(" <tbody>\n");
		for (Map m1 : listMap) {
			sb.append(" <tr>\n");
			sb.append(" <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
			sb.append(" </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append(" <tr>\n");
				sb.append(" <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
				sb.append(" <td>" + m2.get("v") + "</td>\n");
				sb.append(" </tr>\n");
			}
		}
		sb.append(" </tbody>\n");
		sb.append("</table>");
		// 返回 html 片段
		return sb.toString();
	}
}
