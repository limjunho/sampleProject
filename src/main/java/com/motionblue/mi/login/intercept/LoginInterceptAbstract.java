package com.motionblue.mi.login.intercept;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.motionblue.mi.login.LoginService;
import com.motionblue.mi.login.NoLoginCheck;

public class LoginInterceptAbstract extends HandlerInterceptorAdapter{

	private static Logger log = Logger.getLogger(LoginInterceptAbstract.class);

	@Autowired
	protected LoginService loginService;

	@Autowired
	private ServletContext context;
	
	private String defaultLoginUrl = "";			// context 경로를 제외한 실제 서비스 url만 기재
	public String getDefaultLoginUrl() {
		return defaultLoginUrl;
	}
	public void setDefaultLoginUrl(String defaultLoginUrl) {
		this.defaultLoginUrl = defaultLoginUrl;
	}
	
	/**
	 * 로그인 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		NoLoginCheck usingLogin = null;
		if(handler instanceof HandlerMethod)
		{
			usingLogin = ((HandlerMethod) handler).getMethodAnnotation(NoLoginCheck.class);
		}
		
		if(usingLogin == null)
		{
			boolean secure = request.isSecure(); // https 접근인지 구분
			String sslUrl = "";
			String host = request.getServerName();
			String queryString = request.getQueryString();
			String rurl = request.getRequestURI();
			int port = request.getServerPort(); // 포트추출
			if ( port != 80){
				host += ":" + String.valueOf(port);
			}
			
			if ( secure ){
				host = "https://" + host;
			}else{
				host = "http://" + host;
			}
			
			rurl = host + rurl;
			if ( request.getQueryString() != null ){
				rurl += "?" + request.getQueryString();
			}
			
			if ( !loginService.isLogin() ){
				// rurl = URLEncoder.encode(rurl, "utf-8");
				// rurl = SeedUtil.encryptStr(rurl);
				// 로그인 후 리턴 URL
				// request.getSession().setAttribute("loginReturnUrl", rurl);
				// 리턴 URL 저장.
				log.debug("====================== rurl : " + rurl);
				loginService.setLoginReturnUrl(rurl);
				
				// response.sendRedirect(ctx.getContextPath() + defaultLoginUrl + "?rurl=" + rurl);
				response.sendRedirect(context.getContextPath() + defaultLoginUrl);
				return false;
				
			}

		}
		
		return true;
	}

	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor{postHandle}............................................ end");
	}
	*/
}
