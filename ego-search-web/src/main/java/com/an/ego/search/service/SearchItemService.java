package com.an.ego.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.an.ego.rpc.pojo.TbItem;
import com.an.ego.search.entity.SearchResult;

public interface SearchItemService {

	public SearchResult loadItemService(String item_keywords,Integer page);
	
	public TbItem loadItemService(Long id);
	

}
