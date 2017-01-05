package com.motionblue.mi.common;

import portal.common.PortalProperty;

import org.springframework.stereotype.Controller;

import com.motionblue.mi.login.LoginBaseController;
import com.motionblue.mi.login.LoginService;
import com.motionblue.mi.user.UserVo;

/**
 * 해당 사이트에 특화된 정보 정의
 * @author billyaki
 *
 */
@Controller
public class PortalBaseController extends LoginBaseController{

	/**
	 * 로그인 서비스 설정
	 * @param loginService
	 */
	public void setLoginService(LoginService loginService) {
		super.loginService = loginService;
	}
	
	/**
	 * 계정 정보. - From session
	 * @return
	 */
	public UserVo getUserVo(){
		Object obj = loginService.getUserVo();
		return (obj == null) ? null : (UserVo)obj;
	}
	/**
	 * 
	 * @author      : 박진수
	 * @date        : 2014. 9. 13.
	 * @description : 
	 */
	public String getUploadRoot()
	{
		return PortalProperty.get("FILES");
	}

}
