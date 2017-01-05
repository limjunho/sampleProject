package com.motionblue.mi.code;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 코드 관리
 * @author administrator
 *
 */
@Repository
public interface CodeDao {
	/**
	 * 코드 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public CodeVo insert(CodeVo vo) throws Exception;
	/**
	 * 코드 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void update(CodeVo vo) throws Exception;
	/**
	 * 코드 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void delete(CodeVo vo) throws Exception;
	/**
	 * 코드 목록 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(CodeVo vo) throws Exception;
	/**
	 * 코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CodeVo> selectList(CodeVo vo) throws Exception;
	/**
	 * 코드 레벨 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CodeVo> selectList2(CodeVo vo) throws Exception;
	/**
	 * 코드 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public CodeVo select(CodeVo vo) throws Exception;
	
	/**
	 * 특정 코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CodeVo> selectSList(CodeVo vo) throws Exception;
}
