package com.motionblue.mi.login.intercept;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.motionblue.mi.login.LoginService;

public class LoginUserIntercept extends LoginInterceptAbstract {
	
	private static Logger log = Logger.getLogger(LoginUserIntercept.class);
	
	public LoginUserIntercept(){
		// context 경로를 제외한 실제 서비스 url만 기재
		// ctx.getContextPath() + 
		// ServletContext ctx = ServletActionContext.getServletContext();
		super.setDefaultLoginUrl("/signin");
	}
	/**
	 * 로그인 서비스
	 * @return
	 */
	@Autowired
	public LoginService getLoginService;
	
}
