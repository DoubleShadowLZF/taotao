package com.taotao.portal.interceptor;

import com.taotao.common.config.CookieConfig;
import com.taotao.common.config.UrlConfig;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 拦截用户请求，强制登陆
 * @Author Double
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UrlConfig urlConfig;
	@Autowired
	private CookieConfig cookieConfig;

	/**
	 * 执行前判断
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
		urlConfig = (UrlConfig) factory.getBean("urlConfig");
		cookieConfig = (CookieConfig) factory.getBean("cookieConfig");
		RestTemplate rest = new RestTemplate();
		String token = cookieConfig.getToken();
		String cookieValue = CookieUtils.getCookieValue(httpServletRequest, token);
		//浏览器中cookie过期
		if(cookieValue == null){
			httpServletResponse.sendRedirect("http://sso.taotao.com/page/login");
			return false;
		}
		Map<String,Object> params = new HashMap<>(1);
		params.put("token",cookieValue);
		TaotaoResult result = rest.getForObject(urlConfig.getUserToken(), TaotaoResult.class,params);
		//redis中用户信息过期
		if(result.getStatus() == 200){
			Object data = result.getData();
			if(data != null){
				return true;
			}
			httpServletResponse.sendRedirect("http://sso.taotao.com/page/login");
			return false;
		}
		httpServletResponse.sendRedirect("http://sso.taotao.com/page/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
