package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.common.config.CookieConfig;
import com.taotao.common.config.UrlConfig;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.ICartService;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description 购物车
 * @Author Double
 */
@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CookieConfig cookieConfig;
	@Autowired
	private UrlConfig urlConfig;

	@Override
	public TaotaoResult addPage(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItems = null;
		cartItems = getListFromCookie(request);

		CartItem cartItem = null;
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		} else {
			for (CartItem c : cartItems) {
				if (itemId.equals(c.getId()+"")) {
					c.setNum(c.getNum() + num);
					cartItem = c;
				}
			}
		}
		if (cartItem == null) {
			RestTemplate restTemplate = new RestTemplate();

			Map<String, Object> param = new HashMap<>(1);
			param.put("itemId", itemId);
			TbItem item = restTemplate.getForObject(urlConfig.getItemUrl(), TbItem.class, param);
			if (item == null) {
				return TaotaoResult.fail();
			}
			cartItem = new CartItem();
			cartItem.setId(item.getId()).setNum(num).setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]).setPrice(item.getPrice()).setTitle(item.getTitle());
			cartItems.add(cartItem);
		}
		//半个钟过期
		CookieUtils.setCookie(request, response, cookieConfig.getCartName(), JSONObject.toJSON(cartItems).toString(),1800);
		return TaotaoResult.ok();
	}

	private List<CartItem> getListFromCookie(HttpServletRequest request) {
		List<CartItem> cartItems;
		String itemsJson = CookieUtils.getCookieValue(request, cookieConfig.getCartName(), true);
		cartItems = JSON.parseArray(itemsJson, CartItem.class);
		return cartItems;
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request) {
		return getListFromCookie(request);
	}

	@Override
	public TaotaoResult deleteCartList(HttpServletRequest request, HttpServletResponse response){
		CookieUtils.setCookie(request, response,cookieConfig.getCartName(),null);
		return TaotaoResult.ok();
	}

	@Override
	public void deleteItem(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartItemList(request);
		for (int i = 0; i < cartItemList.size(); i++) {
			CartItem cartItem = cartItemList.get(i);
			if (itemId.equals(cartItem.getId() + "")) {
				cartItemList.remove(i);
			}
		}
		CookieUtils.setCookie(request,response,cookieConfig.getCartName(), JSONObject.toJSON(cartItemList).toString());
	}

	@Override
	public TaotaoResult updateNum(String itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartItemList(request);
		for (int i = 0; i < cartItemList.size(); i++) {
			CartItem cartItem = cartItemList.get(i);
			if (itemId.equals(cartItem.getId() + "")) {
				if (cartItem.getNum() - num <= 0) {
					cartItem.setNum(1);
					cartItemList.add(i,cartItem);
				} else {
					cartItem.setNum(cartItem.getNum() - num);
					cartItemList.add(i,cartItem);
				}
			}
		}
		CookieUtils.setCookie(request,response,cookieConfig.getCartName(), JSONObject.toJSON(cartItemList).toString());
		return TaotaoResult.ok();
	}




}
