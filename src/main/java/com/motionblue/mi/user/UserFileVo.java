package com.motionblue.mi.user;

import portal.common.model.FileInfoVO;

public class UserFileVo extends FileInfoVO{
	private long userSeq; 
	private long userAttachSeq; 
	private String folder;
	private String nm;
	private String saveNm;
	private String ext;
	private String mimeTy;
	private long size;
	private String fieldNm;
	
	public long getUserAttachSeq() {
		return userAttachSeq;
	}
	public void setUserAttachSeq(long userAttachSeq) {
		this.userAttachSeq = userAttachSeq;
	}
	public long getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(long userSeq) {
		this.userSeq = userSeq;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
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
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getMimeTy() {
		return mimeTy;
	}
	public void setMimeTy(String mimeTy) {
		this.mimeTy = mimeTy;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFieldNm() {
		return fieldNm;
	}
	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}
}
