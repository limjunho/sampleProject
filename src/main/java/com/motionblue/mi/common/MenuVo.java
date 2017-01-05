package com.motionblue.mi.common;

import portal.common.model.BaseVO;

public class MenuVo extends BaseVO{
	
	//메뉴 관리 vo
	private long menuSeq = 0;		//일련번호
	private String menuCdUp = "";	//상위 메뉴코드
	private String menuCd = "";		//메뉴코드
	private String menuNm = "";		//메뉴이름
	private String url = "";		//권한 통제할 주소 (스트럿츠 namespace)
	private int menuDepth = 0;		//메뉴단계
	private int menuOrd = 0;		//상위 메뉴 별 순서
	private String memo = "";		//설명 메모
	
	public long getMenuSeq() {
		return menuSeq;
	}
	public void setMenuSeq(long menuSeq) {
		this.menuSeq = menuSeq;
	}
	public String getMenuCdUp() {
		return menuCdUp;
	}
	public void setMenuCdUp(String menuCdUp) {
		this.menuCdUp = menuCdUp;
	}
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
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
	public int getMenuDepth() {
		return menuDepth;
	}
	public void setMenuDepth(int menuDepth) {
		this.menuDepth = menuDepth;
	}
	public long getMenuOrd() {
		return menuOrd;
	}
	public void setMenuOrd(int menuOrd) {
		this.menuOrd = menuOrd;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
