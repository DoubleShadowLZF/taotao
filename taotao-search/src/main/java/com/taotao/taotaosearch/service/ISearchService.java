package com.taotao.taotaosearch.service;

/**
 * @Description 搜素服务
 */
public interface ISearchService {

	Object queryList(String queryString,Integer page,Integer row);
}
