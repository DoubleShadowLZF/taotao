package com.taotao.manager.service.impl;

import com.taotao.manager.dao.mapper.TbItemCatDAO;
import com.taotao.manager.pojo.domain.TbItemCat;
import com.taotao.manager.pojo.domain.TbItemCatExample;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatDAO tbItemCatDAO;


	@Override
	public List<EUTreeNode> getTreeNodes(Long parentId) {
		List<EUTreeNode> result = new ArrayList<EUTreeNode>();
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId).andStatusEqualTo(1);
		List<TbItemCat> tbItemCats = tbItemCatDAO.selectByExample(example);
		for (TbItemCat cat : tbItemCats) {
			EUTreeNode node = new EUTreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent() ? "closed" : "open");
			result.add(node);
		}
		return result;
	}
}
