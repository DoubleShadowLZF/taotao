package com.taotao.manager.dao.mapper;

import com.taotao.manager.pojo.domain.TbItemParam;
import com.taotao.manager.pojo.domain.TbItemParamExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TbItemParamDAO继承基类
 */
@Repository
public interface TbItemParamDAO extends MyBatisBaseDao<TbItemParam, Long, TbItemParamExample> {
	/**
	 * 获取规格参数列表
	 * @return
	 */
	List<TbItemParam> list(@Param("page") Integer page ,@Param("rows") Integer rows);
}
