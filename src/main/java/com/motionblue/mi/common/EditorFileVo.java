package com.motionblue.mi.common;

import portal.common.model.FileInfoVO;

public class EditorFileVo extends FileInfoVO{
	@Override
	public String toString() {
		return "EditorFileVo [id=" + id + ", toString()=" + super.toString()
				+ ", getName()=" + getName() + ", getSaveName()="
				+ getSaveName() + ", getMimeType()=" + getMimeType()
				+ ", getFieldName()=" + getFieldName() + ", getSize()="
				+ getSize() + ", getExt()=" + getExt() + ", getFolder()="
				+ getFolder() + ", getScroll_pageSize()="
				+ getScroll_pageSize() + ", getPageSize()=" + getPageSize()
				+ ", getGotoPage()=" + getGotoPage() + ", getSearchItem()="
				+ getSearchItem() + ", getSearchWord()=" + getSearchWord()
				+ ", getScroll_startNum()=" + getScroll_startNum()
				+ ", getStartNum()=" + getStartNum() + ", getEndNum()="
				+ getEndNum() + ", getActId()=" + getActId() + ", getActIp()="
				+ getActIp() + ", getActGbn()=" + getActGbn()
				+ ", getResultMsg()=" + getResultMsg() + ", getAdminYn()="
				+ getAdminYn() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	public int id = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
