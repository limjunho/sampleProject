package com.motionblue.mi.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import portal.common.PortalProperty;
import portal.common.util.StringUtil;

public class UrlManagerIntercept extends HandlerInterceptorAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UrlManagerIntercept.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		
		boolean secure = request.isSecure();
		if ( secure ){
			return true;
		}
		
		String queryString = request.getQueryString();
		// String url = request.getRequestURL().toString();
		String url = request.getRequestURI();
		log.debug(" || intercept url : " + url);
		log.debug(" || queryString : " + queryString);
		
		
		// SSL 포트 구분 적용.
		String sslServicePort = StringUtil.isNull(PortalProperty.get("SSL_SERVICE_PORT"));

		int port = request.getServerPort();
		String host = request.getServerName();// request.getHeader("host");
		
		// ssl port 확인.
		if ( !"443".equals(sslServicePort) ){
			host += ":" + sslServicePort;
		}
		
		// url = url.replace("http", "https");
		url = "https://" + host + url;
		if ( queryString != null ){
			url += "?" + queryString;
		}
		log.debug(" || change url : " + url);
		response.sendRedirect(url);
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor{postHandle}............................................ end");
	}
}
