package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbItemDesc;
import com.taotao.manager.pojo.domain.TbItemDescExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemDescDAO继承基类
 */
@Repository
public interface TbItemDescDAO extends MyBatisBaseDao<TbItemDesc, Long, TbItemDescExample> {
}
