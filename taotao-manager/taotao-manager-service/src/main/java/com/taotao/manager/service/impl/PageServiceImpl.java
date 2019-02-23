package com.taotao.manager.service.impl;

import com.taotao.manager.dao.mapper.TbUserDAO;
import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.manager.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description
 */
@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private TbUserDAO tbUserDAO;

	@Override
	public TbUser getTbUser(long id) {
		return tbUserDAO.selectByPrimaryKey(id);
	}
}
