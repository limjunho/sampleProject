package com.motionblue.mi.bbs;

import portal.common.model.FileInfoVO;

public class BbsFileVo extends FileInfoVO {
	
	@Override
	public String toString() {
		return "BbsFileVo [bbsId=" + bbsId + ", bbsAttachId=" + bbsAttachId
				+ ", bbsAttachTy=" + bbsAttachTy + ", bbsAttachRegDt="
				+ bbsAttachRegDt + ", ext=" + ext + ", folder=" + folder
				+ ", gotoPage=" + gotoPage + "]";
	}
	private long bbsId = 0;
	private long bbsAttachId = 0;
	private String bbsAttachTy = "";
	private String bbsAttachRegDt = "";
	
	public BbsFileVo(){
	}
	
	public BbsFileVo(FileInfoVO fileInfoVO){
		super.setSaveName(fileInfoVO.getSaveName());
		super.setExt(fileInfoVO.getExt());
		super.setFieldName(fileInfoVO.getFieldName());
		super.setName(fileInfoVO.getName());
		super.setMimeType(fileInfoVO.getMimeType());
		super.setSize(fileInfoVO.getSize());
		super.setFolder(fileInfoVO.getFolder());
	}
	
	private int bbsSeq;
	private String nm;
	private String saveNm;
	private String mimeTy;
	private String fieldNm;
	
	private int bbsAttachSeq;
	private String regDt;
	
	public int getBbsAttachSeq() {
		return bbsAttachSeq;
	}
	public void setBbsAttachSeq(int bbsAttachSeq) {
		this.bbsAttachSeq = bbsAttachSeq;
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
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getSaveNm() {
		return saveNm;
	}
	public void setSaveNm(String saveNm) {
		this.saveNm = saveNm;
	}
	public String getMimeTy() {
		return mimeTy;
	}
	public void setMimeTy(String mimeTy) {
		this.mimeTy = mimeTy;
	}
	public String getFieldNm() {
		return fieldNm;
	}
	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}

	public long getBbsId() {
		return bbsId;
	}
	public void setBbsId(long bbsId) {
		this.bbsId = bbsId;
	}
	public long getBbsAttachId(){
		return this.bbsAttachId;
	}
	public void setBbsAttachId(long bbsAttachId){
		this.bbsAttachId = bbsAttachId;
	}
	public String getBbsAttachNm(){
		return super.getName();
	}
	public void setBbsAttachNm(String bbsAttachNm){
		super.setName(bbsAttachNm);
	}
	public String getBbsAttachSaveNm(){
		return super.getSaveName();
	}
	public void setBbsAttachSaveNm(String bbsAttachSaveNm){
		super.setSaveName(bbsAttachSaveNm);
	}
	public String getBbsAttachMimeTy(){
		return super.getMimeType();
	}
	public void setBbsAttachMimeTy(String bbsAttachMimeTy){
		super.setMimeType(bbsAttachMimeTy);
	}
	public long getBbsAttachSize(){
		return super.getSize();
	}
	public void setBbsAttachSize(long bbsAttachSize){
		super.setSize(bbsAttachSize);
	}
	public String getBbsAttachTy(){
		return this.bbsAttachTy;
	}
	public void setBbsAttachTy(String bbsAttachTy){
		this.bbsAttachTy = bbsAttachTy;
	}
	public String getBbsAttachRegDt(){
		return this.bbsAttachRegDt;
	}
	public void setBbsAttachRegDt(String bbsAttachRegDt){
		this.bbsAttachRegDt = bbsAttachRegDt;
	}
	public boolean isImage(){
		String tmp = super.getMimeType();
		tmp = ( tmp == null ) ? "" : tmp.toLowerCase();
		return ( tmp.indexOf("image") > -1 );
	}
}