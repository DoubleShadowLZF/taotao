package com.taotao.manager.service.impl;

import com.taotao.manager.dao.mapper.TbContentCategoryDAO;
import com.taotao.manager.pojo.domain.TbContentCategory;
import com.taotao.manager.pojo.domain.TbContentCategoryExample;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.manager.service.ContentCategoryService;
import com.taotao.common.utils.TaotaoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbContentCategoryDAO contentCategoryDAO;

	@Override
	@Cacheable(cacheNames = "contentCategoryList")
	public List<EUTreeNode> getTreeNodes(Long id) {
		List<EUTreeNode> result = new ArrayList<EUTreeNode>();
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id).andStatusEqualTo(1);
		List<TbContentCategory> list = contentCategoryDAO.selectByExample(example);
		for (TbContentCategory cat : list) {
			EUTreeNode node = new EUTreeNode();
			cat.getName();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent() ? "closed" : "open");
			result.add(node);
		}
		return result;
	}

	@Override
	@CacheEvict(cacheNames = "contentCategoryList")
	public TaotaoResult createNode(Long parentId,String name) {
		if(parentId == null || parentId < 0 ){
			return TaotaoResult.fail("parentId无效");
		}
		if(StringUtils.isEmpty(name)){
			return TaotaoResult.fail("类目名称不能为空");
		}
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		Integer createId = contentCategoryDAO.insertSelective(contentCategory);
		logger.debug("createId:{}", createId);
		contentCategory.setId(Long.valueOf(createId));
		//更新parentId为父节点状态
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(parentId).andStatusEqualTo(1);
		List<TbContentCategory> contentCategories = contentCategoryDAO.selectByExample(example);
		TbContentCategory category = contentCategories.get(0);
		logger.debug("parent:{}",category);
		if(category.getIsParent()){
			return TaotaoResult.ok(contentCategory);
		}
		TbContentCategory cat = new TbContentCategory();
		cat.setIsParent(true);
		contentCategoryDAO.updateByExampleSelective(cat, example);
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	@CacheEvict(cacheNames = "contentCategoryList")
	public TaotaoResult deleteNode(Long id) {
		if(id == null || id < 0){
			return TaotaoResult.fail("id无效");
		}
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		TbContentCategory cat = new TbContentCategory();
		cat.setStatus(2);
		contentCategoryDAO.updateByExampleSelective(cat, example);

		List<TbContentCategory> contentCategories = contentCategoryDAO.selectByExample(example);
		TbContentCategory contentCategory = contentCategories.get(0);
		Long parentId = contentCategory.getParentId();

		TbContentCategoryExample exPerant = new TbContentCategoryExample();
		criteria = exPerant.createCriteria();
		criteria.andParentIdEqualTo(parentId).andStatusEqualTo(1);
		List<TbContentCategory> contentCategoryList = contentCategoryDAO.selectByExample(exPerant);
		if (contentCategoryList.size() == 0) {
			TbContentCategory cat1 = new TbContentCategory();
			cat1.setIsParent(false);
			TbContentCategoryExample e1 = new TbContentCategoryExample();
			TbContentCategoryExample.Criteria criteria1 = e1.createCriteria();
			criteria1.andIdEqualTo(parentId).andStatusEqualTo(1);
			contentCategoryDAO.updateByExampleSelective(cat1, e1);
		}
		return TaotaoResult.ok("删除成功");
	}

	@Override
	@CacheEvict(cacheNames = "contentCategoryList")
	public TaotaoResult update(Long id, String name) {
		if(id == null || id < 0 ){
			return TaotaoResult.fail("parentId无效");
		}
		if(StringUtils.isEmpty(name)){
			return TaotaoResult.fail("类目名称不能为空");
		}
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id).andStatusEqualTo(1);
		TbContentCategory cat = new TbContentCategory();
		cat.setName(name);
		contentCategoryDAO.updateByExampleSelective(cat, example);
		return TaotaoResult.ok();
	}


}
