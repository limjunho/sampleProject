package com.motionblue.mi.code;

import portal.common.model.BaseVO;

public class CodeVo extends BaseVO{
	
	private long cdSeq = 0;
	private String cd = "";
	private String cdNm = "";
	private String cdUp = "";
	private int cdLv = 0;
	private int cdOrd = 0;
	private String cdEtc = "";
	private String useYn = "";
	private int searchCdLv = 0;
	
	private long [] lowCdSeq;
	private String [] lowCd;
	private String [] lowCdNm;
	private String [] lowCdUp;
	private int [] lowCdLv;
	private int [] lowCdOrd;
	private String [] lowCdEtc;
	private String [] lowUseYn;
	
	private String lowCdList = "";
	private String lowCdNmList = "";
	private String lowCdUpList = "";
	private String lowCdLvList = "";
	private String lowCdOrdList = "";
	private String lowCdEtcList = "";
	private String lowUseYnList = "";
	public long getCdSeq() {
		return cdSeq;
	}
	public void setCdSeq(long cdSeq) {
		this.cdSeq = cdSeq;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getCdUp() {
		return cdUp;
	}
	public void setCdUp(String cdUp) {
		this.cdUp = cdUp;
	}
	public int getCdLv() {
		return cdLv;
	}
	public void setCdLv(int cdLv) {
		this.cdLv = cdLv;
	}
	public int getCdOrd() {
		return cdOrd;
	}
	public void setCdOrd(int cdOrd) {
		this.cdOrd = cdOrd;
	}
	public String getCdEtc() {
		return cdEtc;
	}
	public void setCdEtc(String cdEtc) {
		this.cdEtc = cdEtc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getSearchCdLv() {
		return searchCdLv;
	}
	public void setSearchCdLv(int searchCdLv) {
		this.searchCdLv = searchCdLv;
	}
	public long[] getLowCdSeq() {
		return lowCdSeq;
	}
	public void setLowCdSeq(long[] lowCdSeq) {
		this.lowCdSeq = lowCdSeq;
	}
	public String[] getLowCd() {
		return lowCd;
	}
	public void setLowCd(String[] lowCd) {
		this.lowCd = lowCd;
	}
	public String[] getLowCdNm() {
		return lowCdNm;
	}
	public void setLowCdNm(String[] lowCdNm) {
		this.lowCdNm = lowCdNm;
	}
	public String[] getLowCdUp() {
		return lowCdUp;
	}
	public void setLowCdUp(String[] lowCdUp) {
		this.lowCdUp = lowCdUp;
	}
	public int[] getLowCdLv() {
		return lowCdLv;
	}
	public void setLowCdLv(int[] lowCdLv) {
		this.lowCdLv = lowCdLv;
	}
	public int[] getLowCdOrd() {
		return lowCdOrd;
	}
	public void setLowCdOrd(int[] lowCdOrd) {
		this.lowCdOrd = lowCdOrd;
	}
	public String[] getLowCdEtc() {
		return lowCdEtc;
	}
	public void setLowCdEtc(String[] lowCdEtc) {
		this.lowCdEtc = lowCdEtc;
	}
	public String[] getLowUseYn() {
		return lowUseYn;
	}
	public void setLowUseYn(String[] lowUseYn) {
		this.lowUseYn = lowUseYn;
	}
	public String getLowCdList() {
		return lowCdList;
	}
	public void setLowCdList(String lowCdList) {
		this.lowCdList = lowCdList;
	}
	public String getLowCdNmList() {
		return lowCdNmList;
	}
	public void setLowCdNmList(String lowCdNmList) {
		this.lowCdNmList = lowCdNmList;
	}
	public String getLowCdUpList() {
		return lowCdUpList;
	}
	public void setLowCdUpList(String lowCdUpList) {
		this.lowCdUpList = lowCdUpList;
	}
	public String getLowCdLvList() {
		return lowCdLvList;
	}
	public void setLowCdLvList(String lowCdLvList) {
		this.lowCdLvList = lowCdLvList;
	}
	public String getLowCdOrdList() {
		return lowCdOrdList;
	}
	public void setLowCdOrdList(String lowCdOrdList) {
		this.lowCdOrdList = lowCdOrdList;
	}
	public String getLowCdEtcList() {
		return lowCdEtcList;
	}
	public void setLowCdEtcList(String lowCdEtcList) {
		this.lowCdEtcList = lowCdEtcList;
	}
	public String getLowUseYnList() {
		return lowUseYnList;
	}
	public void setLowUseYnList(String lowUseYnList) {
		this.lowUseYnList = lowUseYnList;
	}
	
}
