package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbContentCategory;
import com.taotao.manager.pojo.domain.TbContentCategoryExample;
import org.springframework.stereotype.Repository;

/**
 * TbContentCategoryDAO继承基类
 */
@Repository
public interface TbContentCategoryDAO extends MyBatisBaseDao<TbContentCategory, Long, TbContentCategoryExample> {
}
