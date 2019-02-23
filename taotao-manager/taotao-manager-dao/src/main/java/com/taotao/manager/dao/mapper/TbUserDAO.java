package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.manager.pojo.domain.TbUserExample;
import org.springframework.stereotype.Repository;

/**
 * TbUserDAO继承基类
 */
@Repository
public interface TbUserDAO extends MyBatisBaseDao<TbUser, Long, TbUserExample> {
}
