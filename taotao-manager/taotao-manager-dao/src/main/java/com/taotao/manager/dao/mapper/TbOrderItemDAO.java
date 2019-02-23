package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbOrderItem;
import com.taotao.manager.pojo.domain.TbOrderItemExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderItemDAO继承基类
 */
@Repository
public interface TbOrderItemDAO extends MyBatisBaseDao<TbOrderItem, String, TbOrderItemExample> {
}
