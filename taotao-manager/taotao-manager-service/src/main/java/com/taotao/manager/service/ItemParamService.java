package com.taotao.manager.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;


/**
 * @Description 规格参数服务接口
 */
public interface ItemParamService {

	/**
	 * 获取规格参数列表
	 * @param page 当前页码
	 * @param rows 当前页面条目数
	 * @return EasyUI DataGrid 结构体
	 */
	EUDataGridResult getItemParamList(int page , int rows);

	/**
	 * 通过Id获取规格参数
	 * @param id 类目Id
	 * @return TaotaoResult
	 */
	TaotaoResult getItemParam(Long id);

	/**
	 * 新增规格参数
	 * @param paramData
	 * @return
	 */
	TaotaoResult saveItemParam(Long itemCatId ,String paramData);

}
