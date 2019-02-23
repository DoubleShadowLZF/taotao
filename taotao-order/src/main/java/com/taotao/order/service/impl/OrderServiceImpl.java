package com.taotao.order.service.impl;

import com.taotao.common.config.CookieConfig;
import com.taotao.common.config.TaotaoRedisConf;
import com.taotao.common.config.UrlConfig;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.IDUtils;
import com.taotao.manager.dao.mapper.TbOrderDAO;
import com.taotao.manager.dao.mapper.TbOrderItemDAO;
import com.taotao.manager.dao.mapper.TbOrderShippingDAO;
import com.taotao.manager.pojo.domain.TbOrder;
import com.taotao.manager.pojo.domain.TbOrderItem;
import com.taotao.manager.pojo.domain.TbOrderShipping;
import com.taotao.order.service.IOrderService;
import com.taotao.manager.pojo.qo.OrderQo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Double
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private TbOrderDAO orderDAO;
	@Autowired
	private TbOrderItemDAO orderItemDAO;
	@Autowired
	private TbOrderShippingDAO orderShippingDAO;
	@Autowired
	private UrlConfig urlConfig;
	@Autowired
	private CookieConfig cookieConfig;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private TaotaoRedisConf taotaoRedisConf;

	/**
	 * 创建订单
	 * @param query
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity create(OrderQo query, HttpServletRequest request, HttpServletResponse response){
		TbOrder order = new TbOrder();
		BeanUtils.copyProperties(query,order);
		String orderId = generateId(taotaoRedisConf.getOrderId());
		order.setOrderId(orderId);
		order.setStatus(1);
		orderDAO.insert(order);
		List<TbOrderItem> tbOrderItems = query.getOrderItems();
		for (TbOrderItem item : tbOrderItems) {
			item.setOrderId(orderId);
			item.setId(IDUtils.genItemId()+"");
			orderItemDAO.insert(item);
		}
		TbOrderShipping orderShipping = query.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShippingDAO.insert(orderShipping);
		//删除购物车列表
		HttpHeaders headers = new HttpHeaders();
		headers.add("Set-Cookie",cookieConfig.getCartName()+"=[];Max-Age=0;");
		CookieUtils.setCookie(request,response,cookieConfig.getCartName(),"[]",0);
		return new ResponseEntity(orderId,headers,HttpStatus.OK);
	}

	public Long incr(String key, long liveTime) {
		RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		Long increment = entityIdCounter.getAndIncrement();
		//初始设置过期时间
		if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
			entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
		}

		return increment;
	}

	private String generateId(String key){
		ValueOperations valueOperations = redisTemplate.opsForValue();
		Object o = valueOperations.get(key);
		if(o == null){
			valueOperations.set(key,10086);
			o = valueOperations.get(key);
		}
		Long incr = incr(key, -1);
		return incr + "";
	}
}
