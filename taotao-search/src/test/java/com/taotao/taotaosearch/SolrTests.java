package com.taotao.taotaosearch;

import com.taotao.taotaosearch.conf.SolrConfig;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrTests {

	@Autowired private SolrClient solrClient;

	@Autowired
	private SolrConfig solrConfig;

	@Test
	public void test01(){
		try {
//			SolrQuery query = new SolrQuery();
//			query.setQuery("*:*");
//			query.setFields("sellPoint");
//			query.setStart(5);//当设置为5时，表示从第6记录取，默认取值为0，就是从第1条开始取
//		    query.setRows(5);//表示取出的记录数，默认是10条
			SolrQuery params = new SolrQuery();
			System.out.println("======================query===================");
			params.set("q", "title:努比亚");
			params.set("start", 0);
			params.set("rows", 100);
			params.set("wt","json");

			QueryResponse query1 = solrClient.query("collection1",params);
			SolrDocumentList list = query1.getResults();
			for (int i = 0; i <list.size(); i++){
				SolrDocument entries = list.get(i);
				System.out.println(entries);
			}
			System.out.println(list);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test02(){
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			QueryResponse query1 = solrClient.query(query);
			SolrDocumentList list = query1.getResults();
			for (int i = 0; i <list.size(); i++){
				System.out.println(list.get(i));
			}
			System.out.println();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
