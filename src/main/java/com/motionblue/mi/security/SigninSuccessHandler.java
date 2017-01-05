package com.motionblue.mi.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.motionblue.mi.login.LoginService;
import com.motionblue.mi.user.UserVo;

import portal.common.util.StringUtil;


public class SigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SigninSuccessHandler.class);

	@Autowired
	LoginService loginService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private String targetUrlParameter;
	
	private String defaultUrl;
	
	private boolean useReferer;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public SigninSuccessHandler(){
		targetUrlParameter = "";
		defaultUrl = "/mypage";
		useReferer = false;
	}
	
	
	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}


	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}


	public String getDefaultUrl() {
		return defaultUrl;
	}


	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}


	public boolean isUseReferer() {
		return useReferer;
	}


	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}


	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException{
		String accept = request.getHeader("accept");
		// 세션 저장
		UserVo userVo = (UserVo)auth.getPrincipal();
		loginService.doLogin(userVo);
		
		clearAuthenticationAttributes(request);
		
		int intRedirectStrategy = decideRedirectStrategy(request, response);
		
		switch(intRedirectStrategy){
		case 1:
			useTargetUrl(request, response);
			break;
		case 2:
			useSessionUrl(request, response);
			break;
		case 3:
			useRefererUrl(request, response);
			break;
		default:
			useDefaultUrl(request, response);
		}
		
		if(org.apache.commons.lang.StringUtils.indexOf(accept, "html") > -1){
			super.onAuthenticationSuccess(request, response, auth);
		}else if(org.apache.commons.lang.StringUtils.indexOf(accept, "xml") > -1){
			response.setContentType("application/xml");
			response.setCharacterEncoding("utf-8");
			
			String data = org.apache.commons.lang.StringUtils.join(new String[] {
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
					"<response>",
					"<error>false</error>",
					"<message>로그인하였습니다.</message>",
					"</response>"
			});
			
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		}else if(org.apache.commons.lang.StringUtils.indexOf(accept, "json") > -1){
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String data = org.apache.commons.lang.StringUtils.join(new String[]{
				" {\"response\" : {",
				" \"error\" : false, ",
				" \"message\" : \"로그인하였습니다.\" ",
				"} } "
			});
			
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		}
	}
	/*
	private void clearAuthenticationAttributes(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if(session == null){
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	*/
	
	private int decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response){
		int result = 0;
		String rurl = StringUtil.isNull(request.getSession().getAttribute("loginReturnUrl"));
		//SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(!"".equals(targetUrlParameter)){
			String targetUrl = request.getParameter(targetUrlParameter);
			if(StringUtils.hasText(targetUrl)){
				result = 1;
			}
			else{
				if(rurl != null && !"".equals(rurl)){
					result = 2;
				}else{
					String refererUrl = request.getHeader("REFERER");
					if(useReferer && StringUtils.hasText(refererUrl)){
						result = 3;
					}else{
						result = 0;
					}
				}
			}
			return result;
		}
		
		if(rurl != null && !"".equals(rurl)){
			result = 2;
			return result;
		}
		
		String refererUrl = request.getHeader("REFERER");
		if(useReferer && StringUtils.hasText(refererUrl)){
			result = 3;
		}else{
			result = 0;
		}
		
		return result;
	}
	
	//대상 URL
	private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null){
			requestCache.removeRequest(request, response);
		}
		
		String targetUrl = request.getParameter(targetUrlParameter);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	//세션 URL
	private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//SavedRequest savedRequest = requestCache.getRequest(request, response);
		//String targetUrl = savedRequest.getRedirectUrl();
		String targetUrl = StringUtil.isNull(request.getSession().getAttribute("loginReturnUrl"));
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	//referer URL
	private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String targetUrl = request.getHeader("REFERER");
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	// Default URL
	private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}
}
