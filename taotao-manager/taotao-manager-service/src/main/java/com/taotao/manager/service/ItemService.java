package com.taotao.manager.service;

import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;

/**
 * @Description
 */
public interface ItemService {

	/**
	 * 获取商品列表
	 * @param page 当前页码
	 * @param rows 当前页面总条目数
	 * @return EasyUI DataGrid 结构体
	 */
	EUDataGridResult getItemList(int page, int rows);

	/**
	 * 添加单个商品信息
	 * @param item 商品信息
	 *
	 * cid: 560
	 * title: 华为
	 * sellPoint: 华为
	 * priceView: 3000.00
	 * price: 300000
	 * num: 10000
	 * barcode: 123456789
	 * image: http://192.168.100.120/2018/10/12\1539325536580853.jpg
	 * desc: 华为手机
	 * itemParams:
	 *
	 * @return 200为成功
	 */
	TaotaoResult saveItem(TbItem item, String desc,String itemParams);

	TaotaoResult getItem(String itemId);
}
