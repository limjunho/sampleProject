package com.motionblue.mi.bbs;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.common.util.ExceptionUtil;

@Service
public class BbsService{

	private static Logger log = Logger.getLogger(BbsService.class);

	@Autowired
    private BbsDao bbsDao;
	 
	public BbsVo add(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rtnMap = bbsDao.insert(vo);
		int bbsSeq = (Integer)rtnMap.get("val");
		BbsVo resultVo = null;
		if(bbsSeq > 0)
		{
			vo.setBbsSeq(bbsSeq);
			resultVo = this.get(vo);
		}
		return resultVo;
	}

	public void update(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.update(vo);
	}
	
	public void remove(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.delete(vo);
	}
	
	public void addCmt(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.insertCmt(vo);
	}
	
	public void edtCmt(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.updateCmt(vo);
	}
	
	public void removeCmt(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.deleteCmt(vo);
	}
	
	public int addAttach(BbsFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rtnMap = bbsDao.insertAttach(vo);
		int bbsAttachSeq = (Integer)rtnMap.get("val");
 
		return bbsAttachSeq;
	}
	
	public void removeAttach(BbsFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		bbsDao.deleteAttach(vo);
	}
	
	public int getCount(BbsVo vo) throws Exception{
		return bbsDao.selectCount(vo);
	}
	
	public List<BbsVo> getList(BbsVo vo) throws Exception{
		return bbsDao.selectList(vo);
	}
	
	
	public BbsVo get(BbsVo vo) throws Exception{
		List<List> list = bbsDao.select(vo);
		BbsVo bbsVo = new BbsVo();
		for ( int i=0; i<list.size(); i++ ){
			List<BbsVo> list2 = list.get(i);
			if ( i == 0){
				bbsVo = (list2.size() > 0) ? list2.get(0) : null;
			}
			
			try{
				if ( i == 1){
					if ( list2.size() > 0 ) bbsVo.setPrevBbsVo(list2.get(0));
				}
			}catch(Exception e){
				log.debug(ExceptionUtil.getStackTrace(e));
			}
			
			try{
				if ( i == 2){
					if ( list2.size() > 0 ) bbsVo.setNextBbsVo(list2.get(0));
				}
			}catch(Exception e){
				log.debug(ExceptionUtil.getStackTrace(e));
			}
		}
		
		return (bbsVo == null) ? null : bbsVo;
	}
	
	
	public List<BbsFileVo> getFileList(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return bbsDao.selectFileList(vo);
	}

	
	public List<BbsVo> getCommentList(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return bbsDao.selectCommentList(vo);
	}

	
	public List<BbsVo> getUserCheckList(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return bbsDao.selectUserCheckList(vo);
	}
	
	public List<BbsVo> getUserUncheckList(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return bbsDao.selectUserUncheckList(vo);
	}
	
	public BbsVo getUserCheckCnt(BbsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return bbsDao.selectUserCheckCnt(vo);
	}
}
