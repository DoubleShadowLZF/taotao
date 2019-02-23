package com.taotao.sso.service;

import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.common.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 */
public interface IUserService {
	/**
	 * 检查用户参数
	 *
	 * @param param
	 * @param type
	 * @return
	 */
	boolean checkParam(String param, Integer type);

	int save(TbUser user);

	TaotaoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult getUserByToken(HttpServletRequest request, String token);

	void logout(HttpServletRequest request, String token);
}
