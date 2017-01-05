package com.motionblue.mi.api;

import portal.common.model.BaseVO;

public class ApiVo extends BaseVO{
	
	private int seq;			// 일련번호
	private String cd;			// 코드번호
	
	public String getCd() {
		return  cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
