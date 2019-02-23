package com.taotao.manager.pojo.qo;

import com.taotao.manager.pojo.domain.TbOrder;
import com.taotao.manager.pojo.domain.TbOrderItem;
import com.taotao.manager.pojo.domain.TbOrderShipping;
import lombok.Data;

import java.util.List;

/**
 * @Description 订单交互类
 * @Author Double
 */
@Data
public class OrderQo extends TbOrder{
	private List<TbOrderItem> orderItems;

	private TbOrderShipping orderShipping;

}
