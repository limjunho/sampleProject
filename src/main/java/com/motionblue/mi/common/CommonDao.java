package com.motionblue.mi.common;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 공통
 * @author 서승현
 *
 */
@Repository
public interface CommonDao {
	/**
	 * 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public CommonVo insert(CommonVo vo) throws Exception;

	/**
	 * 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void delete(CommonVo vo) throws Exception;
	/**
	 * 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public CommonVo select(CommonVo vo) throws Exception;
	/**
	 * 사원 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommonVo> selectEmpList(CommonVo vo) throws Exception;
	/**
	 * 직급직책 코드 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommonVo> selectClsDrCode(CommonVo vo) throws Exception;
	/**
	 * 메뉴 권한 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> selectMenuCodeList() throws Exception;
	/**
	 * 메뉴 권한 프로젝트 진행 페이지 이동시 PM, PL 체크
	 * @return
	 * @throws Exception
	 */
	public int selectPojectAuthCheck(CommonVo vo) throws Exception;

	/**
	 * 사용자 권한 메뉴 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> selectPermitList(long userSeq) throws Exception;
}
