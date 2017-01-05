package com.motionblue.mi.code;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	private static Logger log = Logger.getLogger(CodeService.class);
	
	@Autowired
	private CodeDao codeDao;

	public CodeVo add(CodeVo vo) throws Exception {
		// TODO Auto-generated method stub
		CodeVo resultVo = codeDao.insert(vo);;

		return resultVo;
	}
	
	public void update(CodeVo vo) throws Exception {
		// TODO Auto-generated method stub
		codeDao.update(vo);
	}
	
	public void remove(CodeVo vo) throws Exception {
		// TODO Auto-generated method stub
		codeDao.delete(vo);
	}
	
	public int getCount(CodeVo vo) throws Exception{
		return codeDao.selectCount(vo);
	}
	
	public List<CodeVo> getList(CodeVo vo) throws Exception{
		return codeDao.selectList(vo);
	}
	
	
	public List<CodeVo> getList2(CodeVo vo) throws Exception{
		return codeDao.selectList2(vo);
	}
	
	
	public CodeVo get(CodeVo vo) throws Exception{
		return codeDao.select(vo);
	}
	
	
	public List<CodeVo> getSList(CodeVo vo) throws Exception{
		return codeDao.selectSList(vo);
	}
}
