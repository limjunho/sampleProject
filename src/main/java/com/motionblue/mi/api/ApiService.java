package com.motionblue.mi.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService{

	private static Logger log = Logger.getLogger(ApiService.class);
	
	@Autowired
	private ApiDao apiDao;
	
	public List<AppDownVo> selectAppList(ApiVo vo) throws Exception {
		List<AppDownVo> resultList = new ArrayList<AppDownVo>();
		resultList = this.apiDao.selectAppList(vo);

		AppDownVo aVo = new AppDownVo();
		for ( int i=0; i<resultList.size(); i++ ){
			aVo.setSeq(resultList.get(i).getSeq());
			
			String userAgeArray = this.apiDao.selectAppUserAge(aVo); 
			String[] splitUserAgeArray = userAgeArray.split(",");
			
			List<Integer> splitList = new ArrayList<Integer>();
			for ( int j=0; j<splitUserAgeArray.length; j++ ){
				splitList.add(Integer.parseInt(splitUserAgeArray[j]));
			}
			resultList.get(i).setUserAge(splitList);
			resultList.get(i).setStudyContent(this.apiDao.selectAppStudyCont(aVo));
		}
		
		return resultList;
	}
	
	public int selectAppCount(ApiVo vo) throws Exception {
		return this.apiDao.selectAppCount(vo);
	}

}
