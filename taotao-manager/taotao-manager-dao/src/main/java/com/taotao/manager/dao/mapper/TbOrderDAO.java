package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbOrder;
import com.taotao.manager.pojo.domain.TbOrderExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderDAO继承基类
 */
@Repository
public interface TbOrderDAO extends MyBatisBaseDao<TbOrder, String, TbOrderExample> {
}
