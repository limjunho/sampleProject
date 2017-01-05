package com.motionblue.mi.login;

import org.springframework.stereotype.Service;

/**
 * 사용자 로그인 처리
 * @author billyaki
 *
 */
@Service
public class LoginService extends LoginServiceAbstract {
		// 로그인 세션 키.
		private final static String _SESSION_KEY = "MemberVo";

		public LoginService(){
			super.setSessionKey(_SESSION_KEY);
		}
		
		public void doLogout() {
			
			// 로그아웃 처리
			super.doLogout();
		}
}
