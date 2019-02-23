package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbItemCat;
import com.taotao.manager.pojo.domain.TbItemCatExample;
import org.springframework.stereotype.Component;

/**
 * TbItemCatDAO继承基类
 */
@Component
public interface TbItemCatDAO extends MyBatisBaseDao<TbItemCat, Long, TbItemCatExample> {
}
