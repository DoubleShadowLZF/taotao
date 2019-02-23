package com.taotao.portal.service;

import com.taotao.common.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Author Double
 */
public interface ICartService {
	/**
	 * 购物车添加页面
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addPage(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

	List getCartItemList(HttpServletRequest request);

	TaotaoResult deleteCartList(HttpServletRequest request, HttpServletResponse response);

	void deleteItem(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult updateNum(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
}
