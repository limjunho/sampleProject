package com.motionblue.mi.api;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * API
 * @author jsbak
 *
 */
@Repository
public interface ApiDao {
	
	/**
	 * 앱 다운로드 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AppDownVo> selectAppList(ApiVo vo) throws Exception;
	
	/**
	 * 앱 다운로드 전체 카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectAppCount(ApiVo vo) throws Exception;
	
	/**
	 * 앱 다운로드 학습연령 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String selectAppUserAge(AppDownVo vo) throws Exception;
	
	/**
	 * 앱 다운로드 학습내용 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<String> selectAppStudyCont(AppDownVo vo) throws Exception;
	
}
