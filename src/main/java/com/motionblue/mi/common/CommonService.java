package com.motionblue.mi.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.common.exception.PortalServiceException;
import portal.common.security.seed.SeedUtil;
import portal.common.util.StringUtil;

@Service
public class CommonService {

	private static Logger log = Logger.getLogger(CommonService.class);
	
	@Autowired
	private CommonDao commonDao;

	public CommonVo add(CommonVo vo) throws Exception {
		// TODO Auto-generated method stub

		CommonVo resultVo = commonDao.insert(vo);;
		
		return resultVo;
	}

	
	public void remove(CommonVo vo) throws Exception {
		// TODO Auto-generated method stub

	}

	
	public CommonVo get(CommonVo vo) throws Exception{
		return commonDao.select(vo);
	}

	
	public List<CommonVo> getEmpList(CommonVo vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.selectEmpList(vo);
	}

	
	public List<CommonVo> empSelectbox(CommonVo vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.selectClsDrCode(vo);
	}

	
	public List<MenuVo> getMenuCodeList() throws Exception {
		// TODO Auto-generated method stub
		return commonDao.selectMenuCodeList();
	}

	
	public int getPojectAuthCheck(CommonVo vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.selectPojectAuthCheck(vo);
	}
	
	/**
	 * 사용자 권한 메뉴 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> getPermitList(long userSeq) throws Exception{
		return commonDao.selectPermitList(userSeq);
	}
}
