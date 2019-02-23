package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbOrderShipping;
import com.taotao.manager.pojo.domain.TbOrderShippingExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderShippingDAO继承基类
 */
@Repository
public interface TbOrderShippingDAO extends MyBatisBaseDao<TbOrderShipping, String, TbOrderShippingExample> {
}
