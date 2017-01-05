package com.motionblue.mi.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import portal.common.model.BaseVO;

public class UserVo extends BaseVO implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private long userSeq = 0;
	private String userId = "";
	private String userPw = "";
	private String userNm = "";
	private String userCls = "";
	private String userClsNm = "";
	private String userEmail = "";
	private String userHp = "";
	private String emailReceptionYn = "";
	private String smsReceptionYn = "";
	private String regDt = "";
	private String regId = "";
	private String menuSeqList = "";
	private String curUserPw = "";
	private String chkId = "";
	
	private String username;
	private String password;
	private List<Role> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public long getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(long userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
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
	public String getUserClsNm() {
		return userClsNm;
	}
	public void setUserClsNm(String userClsNm) {
		this.userClsNm = userClsNm;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserHp() {
		return userHp;
	}
	public void setUserHp(String userHp) {
		this.userHp = userHp;
	}
	public String getEmailReceptionYn() {
		return emailReceptionYn;
	}
	public void setEmailReceptionYn(String emailReceptionYn) {
		this.emailReceptionYn = emailReceptionYn;
	}
	public String getSmsReceptionYn() {
		return smsReceptionYn;
	}
	public void setSmsReceptionYn(String smsReceptionYn) {
		this.smsReceptionYn = smsReceptionYn;
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
	public String getMenuSeqList() {
		return menuSeqList;
	}
	public void setMenuSeqList(String menuSeqList) {
		this.menuSeqList = menuSeqList;
	}
	public String getCurUserPw() {
		return curUserPw;
	}
	public void setCurUserPw(String curUserPw) {
		this.curUserPw = curUserPw;
	}
	public String getChkId() {
		return chkId;
	}
	public void setChkId(String chkId) {
		this.chkId = chkId;
	}	
	
}
