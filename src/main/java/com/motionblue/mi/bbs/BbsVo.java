package com.motionblue.mi.bbs;

import portal.common.model.BaseVO;

public class BbsVo extends BaseVO{
	
	private int bbsSeq = 0;
	private String bbsInfoId;	// NOTICE(공지사항), FREE(자유게시판), ANONYM(익명게시판)
	private String title = "";
	private String content = ""; 
	private String regId;
	private String regDt;
	
	private String userId;
	private String userNm;
	private String userCls;
	
	private BbsVo prevBbsVo;
	private BbsVo nextBbsVo;
	
	private int bbsCmtSeq;
	private String bbsCmtCont;
	private String bbsCmtRegDt;
	
	private String bbsAttachSeqs;
	
	private int userCheck;
	private int userUncheck;
	
	private int cmtCnt;
	
	public int getUserUncheck() {
		return userUncheck;
	}
	public void setUserUncheck(int userUncheck) {
		this.userUncheck = userUncheck;
	}
	public int getCmtCnt() {
		return cmtCnt;
	}
	public void setCmtCnt(int cmtCnt) {
		this.cmtCnt = cmtCnt;
	}
	public int getUserCheck() {
		return userCheck;
	}
	public void setUserCheck(int userCheck) {
		this.userCheck = userCheck;
	}
	public String getBbsAttachSeqs() {
		return bbsAttachSeqs;
	}
	public void setBbsAttachSeqs(String bbsAttachSeqs) {
		this.bbsAttachSeqs = bbsAttachSeqs;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBbsCmtSeq() {
		return bbsCmtSeq;
	}
	public void setBbsCmtSeq(int bbsCmtSeq) {
		this.bbsCmtSeq = bbsCmtSeq;
	}
	public String getBbsCmtCont() {
		return bbsCmtCont;
	}
	public void setBbsCmtCont(String bbsCmtCont) {
		this.bbsCmtCont = bbsCmtCont;
	}
	public String getBbsCmtRegDt() {
		return bbsCmtRegDt;
	}
	public void setBbsCmtRegDt(String bbsCmtRegDt) {
		this.bbsCmtRegDt = bbsCmtRegDt;
	}
	public BbsVo getPrevBbsVo() {
		return prevBbsVo;
	}
	public void setPrevBbsVo(BbsVo prevBbsVo) {
		this.prevBbsVo = prevBbsVo;
	}
	public BbsVo getNextBbsVo() {
		return nextBbsVo;
	}
	public void setNextBbsVo(BbsVo nextBbsVo) {
		this.nextBbsVo = nextBbsVo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserCls() {
		return userCls;
	}
	public void setUserCls(String userCls) {
		this.userCls = userCls;
	}
	public String getBbsInfoId() {
		return bbsInfoId;
	}
	public void setBbsInfoId(String bbsInfoId) {
		this.bbsInfoId = bbsInfoId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public int getBbsSeq() {
		return bbsSeq;
	}
	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
}
