package com.motionblue.mi.bbs;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author jsbak
 *
 */
@Repository
public interface BbsDao {
	/**
	 * 게시글 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insert(BbsVo vo) throws Exception;
	/**
	 * 게시글 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void update(BbsVo vo) throws Exception;
	/**
	 * 게시글 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void delete(BbsVo vo) throws Exception;
	/**
	 * 댓글 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void insertCmt(BbsVo vo) throws Exception;
	/**
	 * 댓글 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void updateCmt(BbsVo vo) throws Exception;
	/**
	 * 댓글 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteCmt(BbsVo vo) throws Exception;
	/**
	 * 게시글 파일 첨부
	 * @param vo
	 * @throws Exception
	 */
	public Map<String, Object> insertAttach(BbsFileVo vo) throws Exception;
	/**
	 * 게시글 파일 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteAttach(BbsFileVo vo) throws Exception;
	/**
	 * 게시판 목록 수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(BbsVo vo) throws Exception;
	/**
	 * 게시판 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BbsVo> selectList(BbsVo vo) throws Exception;
	/**
	 * 게시글 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<List> select(BbsVo vo) throws Exception;
	/**
	 * 게시글 첨부파일 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BbsFileVo> selectFileList(BbsVo vo) throws Exception;
	/**
	 * 게시글 댓글 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BbsVo> selectCommentList(BbsVo vo) throws Exception;
	/**
	 * 게시글 확인한 유저 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BbsVo> selectUserCheckList(BbsVo vo) throws Exception;
	/**
	 * 게시글 확인 안한 유저 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BbsVo> selectUserUncheckList(BbsVo vo) throws Exception;
	/**
	 * 게시글 확인관련 인원수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BbsVo selectUserCheckCnt(BbsVo vo) throws Exception;
}
