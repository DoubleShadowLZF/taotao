package com.taotao.taotaosearch.service.impl;

import com.taotao.common.client.FtpClient;
import com.taotao.taotaosearch.conf.SolrConfig;
import com.taotao.taotaosearch.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Description
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {
	@Autowired
	private SolrClient solrClient;

	@Autowired
	private SolrConfig solrConfig;

	/**
	 * 根据传入的 关键词查找
	 * @param queryString
	 * @param page
	 * @return
	 */
	@Override
	public Object queryList(String queryString, Integer page,Integer row) {
		try {
			SolrQuery params = new SolrQuery();
			if(!StringUtils.isEmpty(queryString)){
				params.set("q", "title:"+queryString);
			}
			params.set("start", page);
			params.set("rows", row);
			params.set("wt","json");

			QueryResponse query1 = solrClient.query(solrConfig.getCollection(),params);
			SolrDocumentList results = query1.getResults();
			for (SolrDocument sd : results) {
				String strImage = (String) sd.get("image");
				if(!StringUtils.isEmpty(strImage)){
					String[] split = strImage.split(",");
					if(split.length > 1){
						sd.put("img",split[0]);
					}
				}
			}
			return results;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
