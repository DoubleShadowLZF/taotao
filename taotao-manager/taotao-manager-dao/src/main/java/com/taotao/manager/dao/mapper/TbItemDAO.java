package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.manager.pojo.domain.TbItemExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemDAO继承基类
 */
@Repository
public interface TbItemDAO extends MyBatisBaseDao<TbItem, Long, TbItemExample> {
}
