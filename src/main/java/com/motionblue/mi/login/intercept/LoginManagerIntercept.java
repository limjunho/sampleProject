package com.motionblue.mi.login.intercept;

import com.motionblue.mi.login.LoginService;

import portal.common.PortalProperty;

public class LoginManagerIntercept extends LoginInterceptAbstract {
	public LoginManagerIntercept(){
		// context 경로를 제외한 실제 서비스 url만 기재
		super.setDefaultLoginUrl(PortalProperty.get("PATH_ADMIN_ROOT") + "/login.jsp");
	}
	/**
	 * 로그인 서비스
	 * @return
	 */
	public LoginService getLoginManagerService() {
		return loginService;
	}

	public void setLoginManagerService(LoginService loginManagerService) {
		this.loginService = loginManagerService;
	}

}
