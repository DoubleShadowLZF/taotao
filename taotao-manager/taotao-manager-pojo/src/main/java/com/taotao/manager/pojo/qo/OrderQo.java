package com.taotao.manager.pojo.qo;

import com.taotao.manager.pojo.domain.TbOrder;
import com.taotao.manager.pojo.domain.TbOrderItem;
import com.taotao.manager.pojo.domain.TbOrderShipping;

import java.util.List;

/**
 * @Description 订单交互类
 * @Author Double
 */
public class OrderQo extends TbOrder{
	private List<TbOrderItem> orderItems;

	private TbOrderShipping orderShipping;


	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

	@Override
	public String toString() {
		return "OrderQo{" +
				"orderItems=" + orderItems +
				", orderShipping=" + orderShipping +
				'}';
	}
}
