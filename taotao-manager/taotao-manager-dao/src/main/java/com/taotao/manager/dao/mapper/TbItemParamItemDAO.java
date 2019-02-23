package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbItemParamItem;
import com.taotao.manager.pojo.domain.TbItemParamItemExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemParamItemDAO继承基类
 */
@Repository
public interface TbItemParamItemDAO extends MyBatisBaseDao<TbItemParamItem, Long, TbItemParamItemExample> {
}
