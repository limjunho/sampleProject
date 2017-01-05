package com.motionblue.mi.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionblue.mi.user.UserVo;
import com.motionblue.mi.BaseController;

@Controller
public class LoginBaseController extends BaseController{
	
	@Autowired
	protected LoginService loginService;

	@Autowired
	protected HttpServletRequest request;
	
	/**
	 * 계정 정보. - From session
	 * @return
	 */
	public UserVo getUserVo(){
		Object obj = loginService.getUserVo();
		return (obj == null) ? null : (UserVo)obj;
	}
	
	/**
	 * 로그인 세션 저장.
	 * @param vo
	 * @return
	 */
	public void setUserVo(Object vo){
		loginService.setUserVo(vo);
	}
	
	/**
	 * 로그인 검사.
	 * @return
	 */
	public boolean isLogin(){
		return loginService.isLogin();
	}
	
	/**
	 * 로그아웃 처리
	 */
	public void doLogout(){
		loginService.doLogout();
	}
	
	/**
	 * 리턴 URL 세션 저장
	 * @param rurl
	 */
	public void setLoginReturUrl(String rurl){
		loginService.setLoginReturnUrl(rurl);
	}
	
	/**
	 * 리턴 URL 조회
	 * @return
	 */
	public String getLoginReturnUrl(){
		return loginService.getLoginReturnUrl();
	}
}
