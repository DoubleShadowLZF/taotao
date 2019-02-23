package com.taotao.sso.Controller;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.sso.model.Validator;
import com.taotao.sso.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 格式如：zhangsan/ 1，
	 * 其中param zhangsan是校验的数据，
	 * type为类型，可选参数1、2、3分别代表username、phone、email
	 *
	 * @param param
	 * @param type
	 * @return
	 */
	@GetMapping("/check/{param}/{type}")
	public Object checkParam(@PathVariable("param") String param, @PathVariable("type") Integer type, String callback) {
		TaotaoResult result = null;
		if (!(type == 1 || type == 2 || type == 3)) {
			result = TaotaoResult.build(400, "校验内容类型错误");
		}
		//回调函数支持jsonp
		if (result != null) {
			if (!StringUtils.isBlank(callback)) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
		}

		boolean b = userService.checkParam(param, type);
		return TaotaoResult.ok(b);
	}

	/**
	 * 用户注册
	 *
	 * @param user username //用户名
	 *             password //密码
	 *             phone //手机号
	 *             email //邮箱
	 * @return
	 */
	@PostMapping("/register")
	public TaotaoResult registerUser(@Validated(Validator.Insert.class) TbUser user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		int count = userService.save(user);
		return TaotaoResult.ok(count);
	}

	/**
	 * 用户登录
	 *
	 * @param user username //用户名
	 *             password //密码
	 *             //登录成功，返回token
	 * @return
	 */
	@PostMapping("/login")
	public TaotaoResult login(TbUser user, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return userService.login(user, request, response);
	}

	/**
	 * 根据token获取用户信息
	 *
	 * @param token
	 * @return
	 */
	@GetMapping("/token/{token}")
	public Object getUserByToken(HttpServletRequest request, @PathVariable("token") String token, String callback) {
		TaotaoResult result = null;
		try {
			result= userService.getUserByToken(request, token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}

	/**
	 * 退出
	 *
	 * @param token
	 * @return
	 */
	@GetMapping("/logout/{token}")
	public TaotaoResult logout(HttpServletRequest request, @PathVariable("token") String token) {
		userService.logout(request, token);
		return TaotaoResult.ok();
	}
}
