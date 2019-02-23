package com.taotao.sso.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.manager.dao.mapper.TbUserDAO;
import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.manager.pojo.domain.TbUserExample;
import com.taotao.sso.conf.TaotaoRedisConf;
import com.taotao.sso.service.IUserService;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.TaotaoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 */
@Service
@Slf4j
public class UserSeviceImpl implements IUserService {

	@Autowired
	private TbUserDAO userDAO;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private TaotaoRedisConf redisConf;

	@Override
	public boolean checkParam(String param, Integer type) {
		TbUserExample e = new TbUserExample();
		TbUserExample.Criteria criteria = e.createCriteria();
		switch (type) {
			//username
			case 1:
				criteria.andUsernameEqualTo(param);
				break;
			//phone
			case 2:
				criteria.andPhoneEqualTo(param);
				break;
			case 3:
				criteria.andEmailEqualTo(param);
				break;
			default:
				break;
		}
		List<TbUser> tbUsers = userDAO.selectByExample(e);
		return tbUsers.size() == 0;
	}

	@Override
	public int save(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		return userDAO.insert(user);
	}

	@Override
	public TaotaoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		TbUserExample e = new TbUserExample();
		TbUserExample.Criteria criteria = e.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		List<TbUser> tbUsers = userDAO.selectByExample(e);
		if (tbUsers.size() == 0) {
			return TaotaoResult.fail("用户不存在或密码错误");
		}
		TbUser tbUser = tbUsers.get(0);
		if (!tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
			return TaotaoResult.fail("用户不存在或密码错误");
		}
		//生成token,并保存在redis和session
		tbUser.setPassword(null);
		String token = UUID.randomUUID().toString();
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set(redisConf.getToken() + ":" + token, JSONObject.toJSONString(tbUser));

		HttpSession session = request.getSession();
		Map<String, Object> sessionMap = (Map<String, Object>) session.getAttribute("token");
		if (sessionMap == null) {
			sessionMap = new HashMap(10);
			sessionMap.put(token, tbUser);
			session.setAttribute("token", sessionMap);
		} else {
			sessionMap.put(token, tbUser);
			session.setAttribute("token", sessionMap);
		}
		log.info("{}登录token:{}", tbUser.getUsername(), token);
		//将token写入到cookie的逻辑，cookie的有效期是浏览器关闭后失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);

		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(HttpServletRequest request, String token) {
		String result = null;
		ValueOperations valueOperations = redisTemplate.opsForValue();
		result = (String) valueOperations.get(redisConf.getToken() + ":" + token);
		redisTemplate.expire(redisConf.getToken() + ":" + token, 30, TimeUnit.MINUTES);
		if (result == null) {
			throw new RuntimeException("token过期");
		}
		return result != null ? TaotaoResult.ok(JSONObject.parse(result)) : null;
	}

	@Override
	public void logout(HttpServletRequest request, String token) {
		HttpSession session = request.getSession();
		//删除redis内的信息
		redisTemplate.delete(redisConf.getToken() + ":" + token);
		//删除session内的信息
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("token");
		log.debug("session内的token:{}", map);
		if (map != null && map.size() > 0) {
			map.remove(token);
			session.setAttribute("token", map);
		}
		log.debug("session内的token:{}", session.getAttribute("token"));

	}


}
