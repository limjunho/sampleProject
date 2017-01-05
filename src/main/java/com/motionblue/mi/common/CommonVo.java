package com.motionblue.mi.common;

import portal.common.model.BaseVO;

public class CommonVo extends BaseVO{
	private String organCd;
	private String userNm;
	private String empNo;
	private String userCls;
	private String userDr;
	
	private String cd;
	private String codeNm;
	private String upCode;
	
	private String addColumn = null;		// 예)이름 + 직책으로 조회시 "dr"
	
	// 개인정보 변경 관련 변수
	private String userPw;
	private String userBirth;
	private String userHp;
	private String emgcPhone;
	private String userAddress;
	private String userEmail;
	private String cpPhone = "";
	
	//권한 관리 vo
	private int authSeq;		//일련번호
	private String upMenuCode;	//상위 메뉴코드
	private String upMenuNm;	//상위 메뉴이름
	private String menuCode;	//메뉴코드
	private String menuNm;		//메뉴이름
	private String url;			//권한 통제할 주소 (스트럿츠 namespace)
	private int authOrd;		//상위 메뉴 별 순서
	private String memo;		//설명 메모
	private String regDt;		//저장한 날짜
	private String regId;		//저장한 아이디
	
	public String getCpPhone() {
		return cpPhone;
	}
	public void setCpPhone(String cpPhone) {
		this.cpPhone = cpPhone;
	}
	public int getAuthSeq() {
		return authSeq;
	}
	public void setAuthSeq(int authSeq) {
		this.authSeq = authSeq;
	}
	public String getUpMenuCode() {
		return upMenuCode;
	}
	public void setUpMenuCode(String upMenuCode) {
		this.upMenuCode = upMenuCode;
	}
	public String getUpMenuNm() {
		return upMenuNm;
	}
	public void setUpMenuNm(String upMenuNm) {
		this.upMenuNm = upMenuNm;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getAuthOrd() {
		return authOrd;
	}
	public void setAuthOrd(int authOrd) {
		this.authOrd = authOrd;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserHp() {
		return userHp;
	}
	public void setUserHp(String userHp) {
		this.userHp = userHp;
	}
	public String getEmgcPhone() {
		return emgcPhone;
	}
	public void setEmgcPhone(String emgcPhone) {
		this.emgcPhone = emgcPhone;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getUpCode() {
		return upCode;
	}
	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}
	public String getOrganCd() {
		return organCd;
	}
	public void setOrganCd(String organCd) {
		this.organCd = organCd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getUserCls() {
		return userCls;
	}
	public void setUserCls(String userCls) {
		this.userCls = userCls;
	}
	public String getUserDr() {
		return userDr;
	}
	public void setUserDr(String userDr) {
		this.userDr = userDr;
	}
	public String getAddColumn() {
		return addColumn;
	}
	public void setAddColumn(String addColumn) {
		this.addColumn = addColumn;
	}
	
}
