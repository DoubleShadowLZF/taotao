package com.taotao.manager.service.impl;

import com.github.pagehelper.PageInfo;
import com.taotao.manager.dao.mapper.TbItemParamDAO;
import com.taotao.manager.pojo.domain.TbItemParam;
import com.taotao.manager.pojo.domain.TbItemParamExample;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.manager.service.ItemParamService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 规格参数实现
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamDAO itemParamDAO;


	@Override
	public EUDataGridResult getItemParamList(int page , int rows) {
		List<TbItemParam> tbItemParams = itemParamDAO.list(page,rows);
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbItemParam> tbItemPageInfo = new PageInfo(tbItemParams);
		result.setRows(tbItemParams).setTotal(tbItemPageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult getItemParam(Long id) {
		TbItemParamExample example = new TbItemParamExample();
		TbItemParamExample.Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(id);
		List<TbItemParam> tbItemParams = itemParamDAO.selectByExampleWithBLOBs(example);
		if(tbItemParams != null && tbItemParams.size() > 0){
			return TaotaoResult.ok(tbItemParams.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult saveItemParam(Long itemCatId ,String paramData) {
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		tbItemParam.setParamData(paramData);
		tbItemParam.setItemCatId(itemCatId);
		itemParamDAO.insert(tbItemParam);
		return TaotaoResult.ok();
	}
}
