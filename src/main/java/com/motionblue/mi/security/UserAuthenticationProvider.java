package com.motionblue.mi.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.motionblue.mi.user.UserService;
import com.motionblue.mi.user.UserVo;

public class UserAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
	@Autowired
	UserService userService;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		UserVo userVo = null;
		
		Collection<? extends GrantedAuthority> authorities = null;
		
		try{
			userVo = userService.loadUserByUsername(username);
			String hashedPassword = "";
			if(userVo != null)
			{
				hashedPassword = passwordEncoder.encode(password);
				logger.info("username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
	            logger.info("username : " + userVo.getUsername() + " / password : " + userVo.getPassword());
				if(!hashedPassword.equals(userVo.getPassword())){
					throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
				}
				authorities = userVo.getAuthorities();
			}
			else
			{
				throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
			}
		}catch(UsernameNotFoundException e){
			logger.info(e.toString());
			throw new UsernameNotFoundException(e.getMessage());
		}catch(BadCredentialsException e){
			logger.info(e.toString());
			throw new BadCredentialsException(e.getMessage());
			/*
			try {
				throw new PortalServiceException(e.getMessage());
			} catch (PortalServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
		}catch(Exception e){
			logger.info(e.toString());
			throw new RuntimeException(e.getMessage());
		}
		
		
		return new UsernamePasswordAuthenticationToken(userVo, password, authorities);
	}

	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
