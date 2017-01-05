package com.motionblue.mi.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	public UserVo loadUserByUsername(String userId) throws UsernameNotFoundException{
		UserVo userVo = new UserVo();
		
		logger.info("userId : " + userId);
		try {
			userVo = userDao.findUserByName(userId);
			if(userVo == null){
				throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
			}
			else
			{
				userVo.setUsername(userId);
				userVo.setPassword(userVo.getUserPw());
				Role role = new Role();
				role.setName("ROLE_USER");
				
				List<Role> roles = new ArrayList<Role>();
				roles.add(role);
				userVo.setAuthorities(roles);
			}
		} catch (UsernameNotFoundException e){
			throw new UsernameNotFoundException(e.getMessage());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userVo;
	}
	
	
	public UserVo get(UserVo vo) throws Exception{
		return userDao.select(vo);
	}
	
	public UserVo txLogin(UserVo vo) throws Exception{
		UserVo resultVo = null;
		
		Map<String, Object> rtnMap = userDao.selectByIdPwd(vo);
		long userSeq = (Long)rtnMap.get("val");
		if(userSeq > 0)
		{
			vo.setUserSeq(userSeq);
		
			resultVo = this.get(vo);
		}
		return resultVo;
	}
	
	public UserVo add(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		UserVo resultVo = userDao.insert(vo);

		return resultVo;
	}
	
	public void update(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		userDao.update(vo);
	}
	
	public void remove(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		userDao.delete(vo);
	}
	
	public void updatePop(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		userDao.updatePop(vo);
	}
	
	public void updateUserPw(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateUserPw(vo);
	}
	
	public int getCount(UserVo vo) throws Exception{
		return userDao.selectCount(vo);
	}
	
	public List<UserVo> getList(UserVo vo) throws Exception{
		return userDao.selectList(vo);
	}
	
	
	public List<UserVo> getList2(UserVo vo) throws Exception{
		return userDao.selectList2(vo);
	}
	
	public UserVo get2(UserVo vo) throws Exception{
		return userDao.select2(vo);
	}
	
	public List<UserVo> getMailManager(UserVo vo) throws Exception{
		return userDao.selectMailManager(vo);
	}

	
	public int getNowCount(UserVo vo) throws Exception{
		// TODO Auto-generated method stub
		return userDao.selectNowCount(vo);
	}

	
	public int getHireCount(UserVo vo) throws Exception{
		// TODO Auto-generated method stub
		return userDao.selectHireCount(vo);
	}

	
	public int getRetireCount(UserVo vo) throws Exception{
		// TODO Auto-generated method stub
		return userDao.selectRetireCount(vo);
	}

	
	public List<UserVo> getOrganList(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectOrganList(vo);
	}

	
	public List<UserVo> getJcList(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectJcList(vo);
	}

	
	public List<UserVo> getJgList(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectJgList(vo);
	}

	
	public String getLastNum(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectLastNum(vo);
	}

	
	public int getIdCheck(UserVo vo) {
		// TODO Auto-generated method stub
		return userDao.selectIdCheck(vo);
	}
	
	
	public int addAttach(UserFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		int result = userDao.insertAttach(vo);
		return result;
	}

	
	public void edtRetire(UserVo vo) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateRetire(vo);
	}

	
	public int edtAttach(UserFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		int result = userDao.updateAttach(vo);
		return result;
	}

	
	public List<UserVo> getNowList(UserVo vo) throws Exception{
		return userDao.selectNowList(vo);
	}

	
	public UserFileVo getAttach(UserFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectAttach(vo);
	}
	
	public List<UserVo> getUserList(UserVo vo) throws Exception{
		return userDao.selectUserList(vo);
	}

}
