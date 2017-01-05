package com.motionblue.mi.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 사용자관리
 * @author 박진수
 *
 */
@Repository
public interface UserDao {
	
	
	/**
	 * 고객 기본 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UserVo select(UserVo vo) throws Exception;
	/**
	 * 고객 번호 조회 (아이디 + 비밀번호)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectByIdPwd(UserVo vo) throws Exception;
	
	/**
	 * 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UserVo insert(UserVo vo) throws Exception;
	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void update(UserVo vo) throws Exception;
	/**
	 * 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void delete(UserVo vo) throws Exception;
	/**
	 * 팝업 사용자 정보 변경
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public void updatePop(UserVo vo) throws Exception;
	/**
	 * 팝업 사용자 비밀번호 변경
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public void updateUserPw(UserVo vo) throws Exception;
	
	/**
	 * 사용자관리 목록 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(UserVo vo) throws Exception;
	/**
	 * 사용자관리 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectList(UserVo vo) throws Exception;
	/**
	 * 사용자관리 레벨 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectList2(UserVo vo) throws Exception;

	/**
	 * 사용자관리 상세 정보 조회2
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public UserVo select2(UserVo vo) throws Exception;
	/**
	 * 메일관리자 메일 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectMailManager(UserVo vo) throws Exception;
	/**
	 * 현재 사용자 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectNowCount(UserVo vo) throws Exception;
	/**
	 * 총 입사자 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectHireCount(UserVo vo) throws Exception;
	/**
	 * 총 퇴사자 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectRetireCount(UserVo vo) throws Exception;
	/**
	 * 모든 조직 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectOrganList(UserVo vo) throws Exception;
	/**
	 * 모든 직책 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectJcList(UserVo vo) throws Exception;
	/**
	 * 모든 직급 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectJgList(UserVo vo) throws Exception;
	/**
	 * 조직의 마지막 사용자번호 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String selectLastNum(UserVo vo);
	/**
	 * ID 중복 체크
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int selectIdCheck(UserVo vo);
	/**
	 * 프로필 파일 업로드 정보 저장
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int insertAttach(UserFileVo vo) throws Exception;
	/**
	 * 퇴사처리
	 * @param UserVo
	 * @return
	 * @throws Exception
	 */
	public void updateRetire(UserVo vo) throws Exception;
	/**
	 * 프로필 파일 업로드 정보 수정
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int updateAttach(UserFileVo vo) throws Exception;
	/**
	 * 현재 근무중인 사용자 목록 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<UserVo> selectNowList(UserVo vo) throws Exception;
	/**
	 * 사용자 업로드 파일 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public UserFileVo selectAttach(UserFileVo vo) throws Exception;
	
	/**
	 * 사용자 목록 조회, jsbak
	 */
	public List<UserVo> selectUserList(UserVo vo) throws Exception;
	
	/**
	 * 사용자 조회
	 */
	public UserVo findUserByName(String userId)throws Exception;
}
