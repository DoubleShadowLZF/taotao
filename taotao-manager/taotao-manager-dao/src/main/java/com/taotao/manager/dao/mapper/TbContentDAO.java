package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.manager.pojo.domain.TbContentExample;
import org.springframework.stereotype.Repository;

/**
 * TbContentDAO继承基类
 */
@Repository
public interface TbContentDAO extends MyBatisBaseDao<TbContent, Long, TbContentExample> {
}
