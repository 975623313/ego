package com.an.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface ItemDao {
	
	/**
	 * 商品检索
	 */
	public QueryResponse loadItem(SolrQuery params);

}
