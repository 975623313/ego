package com.an.ego.search.dao.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.an.ego.search.dao.ItemDao;

@Repository
public class ItemDaoImpl implements ItemDao{

	@Autowired
	private CloudSolrServer solrServer;
	
	@Override
	public QueryResponse loadItem(SolrQuery params) {

		try {
			return solrServer.query(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
