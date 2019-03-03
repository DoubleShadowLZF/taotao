package com.taotao.manager.service;

import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.EasyUIResult;
import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.manager.pojo.qo.ContentQo;

import java.util.List;

/**
 * @Description
 */
public interface ContentService {
	TaotaoResult save(TbContent content);

	String getAdItemList();

	EasyUIResult getContentList(ContentQo query);

	TaotaoResult delete(ContentQo query);
}
