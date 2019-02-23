package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.dao.mapper.TbItemDAO;
import com.taotao.manager.dao.mapper.TbItemDescDAO;
import com.taotao.manager.dao.mapper.TbItemParamItemDAO;
import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.manager.pojo.domain.TbItemDesc;
import com.taotao.manager.pojo.domain.TbItemExample;
import com.taotao.manager.pojo.domain.TbItemParamItem;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.manager.service.ItemService;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemDAO tbItemDAO;
	@Autowired
	private TbItemDescDAO itemDescDAO;
	@Autowired
	private TbItemParamItemDAO itemParamItemDAO;

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> tbItems = tbItemDAO.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbItem> tbItemPageInfo = new PageInfo(tbItems);
		result.setRows(tbItems).setTotal(tbItemPageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) {
		long id = IDUtils.genItemId();
		this.saveItem(item, id);
		this.saveItemDesc(desc, id);
		this.saveItemParamItem(itemParams, id);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult getItem(String itemId) {
		TbItem tbItem = tbItemDAO.selectByPrimaryKey(Long.valueOf(itemId));
		return TaotaoResult.ok(tbItem);
	}

	private void saveItemParamItem(String itemParams, long id) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setId(IDUtils.genItemId());
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItem.setItemId(id);
		itemParamItem.setParamData(itemParams);
		itemParamItemDAO.insert(itemParamItem);
	}

	private void saveItemDesc(String desc, long id) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDescDAO.insert(itemDesc);
	}

	private void saveItem(TbItem item, long id) {
		item.setId(id);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		int insert = tbItemDAO.insert(item);
	}
}
