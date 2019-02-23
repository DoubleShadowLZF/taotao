package com.taotao.manager.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * @Description
 */
public interface ItemCatService {
	/**
	 * 查询商品类目
	 * @param parentId 当前点击的父类目id
	 * @return EasyUI 的异步树控件数据结构体
	 */
	List<EUTreeNode> getTreeNodes(Long parentId);
}
