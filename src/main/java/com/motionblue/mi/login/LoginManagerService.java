package com.motionblue.mi.login;

import org.springframework.stereotype.Service;
@Service
public class LoginManagerService extends LoginServiceAbstract {
		// 관리자 로그인 세션 키.
		private final static String _SESSION_KEY = "AccountVo";
		
		public LoginManagerService(){
			super.setSessionKey(_SESSION_KEY);
		}
}
