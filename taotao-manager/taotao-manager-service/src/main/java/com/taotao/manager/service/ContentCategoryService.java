package com.taotao.manager.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;

import java.util.List;

/**
 * @Description
 */
public interface ContentCategoryService {
	/**
	 * 内容分类管理节点展示
	 * @param id
	 * @return
	 */
	List<EUTreeNode> getTreeNodes(Long id);

	TaotaoResult createNode(Long parentId,String name);

	TaotaoResult deleteNode(Long id);

	TaotaoResult update(Long id, String name);
}
