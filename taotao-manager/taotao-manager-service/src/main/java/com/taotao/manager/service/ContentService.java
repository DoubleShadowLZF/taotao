package com.taotao.manager.service;

import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.common.utils.TaotaoResult;

/**
 * @Description
 */
public interface ContentService {
	TaotaoResult save(TbContent content);

	String getAdItemList();
}
