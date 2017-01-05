package com.motionblue.mi.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import portal.common.util.DateUtil;
import portal.common.util.StringUtil;

/**
 * 로그인 처리 추상화
 * @author billyaki
 *
 */
public class LoginServiceAbstract{

	private static Logger log = Logger.getLogger(LoginServiceAbstract.class);
	//private ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	
	@Autowired
	private HttpServletRequest request;
	
	private String sessionKey = "";
	protected String getSessionKey() {
		return sessionKey;
	}

	protected void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public boolean isLogin() {
		// TODO Auto-generated method stub
		//HttpServletRequest request = request.getRequest();
		return isLogin(request);
	}

	public boolean isLogin(HttpServletRequest request) {
		log.debug("request session key : " + this.sessionKey);
		
		// TODO Auto-generated method stub
		Object obj = request.getSession().getAttribute(this.sessionKey);
		if ( obj == null){
			return false;
		}
		return true;
	}

	public void doLogin(Object obj){
		//HttpServletRequest request = request.getRequest();
		//HttpServletResponse response = request.getResponse();
		
		///////////////////////////////////////////
		// 로그인 전 세션 저장.
		///////////////////////////////////////////
		// 예약 관련 객체
		Object reservObj = request.getSession().getAttribute("RESERVE_DATA");
		
		// 기존 세션 destroy
		// this.doLogout();
		
		// 로그인 세션 저장.
		this.setUserVo(obj);
		
		// 로그인 전 세션 변수 다시 저장.
		if ( reservObj != null ) request.getSession().setAttribute("RESERVE_DATA", reservObj);
	}

	public void doLogout() {
		// TODO Auto-generated method stub
		// setUserVo(null);
		//HttpServletRequest request = request.getRequest();
		request.getSession().invalidate();
	}

	public Object getUserVo() {
		// TODO Auto-generated method stub
		//HttpServletRequest request = request.getRequest();
		return request.getSession().getAttribute(sessionKey);
	}

	public void setUserVo(Object vo) {
		// TODO Auto-generated method stub
		log.debug("setUserVo ==== request session key : " + this.sessionKey);
		//HttpServletRequest request = request.getRequest();
		request.getSession().setAttribute(sessionKey, vo);
	}

	public int getRemainSessionTime() {
		// TODO Auto-generated method stub
		if ( !this.isLogin() ){
			return -1;		// 해당 없음.
		}
		
		String keyName = sessionKey + "_SessionTime";
		//HttpServletRequest request = request.getRequest();
		request.getSession().getAttribute(keyName);
		
		return 0;
	}

	public void resetEndSessionTime() {
		// TODO Auto-generated method stub
		if ( !this.isLogin() ){
			return;			// 해당 없음.
		}
		
		String keyName = sessionKey + "_SessionTime";
		//HttpServletRequest request = request.getRequest();
		request.getSession().setAttribute(keyName, DateUtil.getTimestamp());
	}

	public void setLoginReturnUrl(String rurl){
		//HttpServletRequest request = request.getRequest();
		request.getSession().setAttribute("loginReturnUrl", rurl);
	}
	
	/**
	 * 리턴 URL 조회
	 * @return
	 */
	public String getLoginReturnUrl(){
		//ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//HttpServletRequest request = sra.getRequest();
		
		// 조회
		String rurl = StringUtil.isNull(request.getSession().getAttribute("loginReturnUrl"));
		log.debug("getLogtinReturnUrl rurl : " + rurl);
		// Reset
		this.setLoginReturnUrl("");
		
		return rurl;
	}
}
