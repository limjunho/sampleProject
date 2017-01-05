package com.motionblue.mi.api;

import java.util.List;

public class AppDownVo {

	private int seq;				// 앱 다운로드 일련번호
	private String name;			// 앱 다운로드 이름
	private String iconUrl;			// 앱 다운로드 아이콘 url
	private String description;		// 앱 다운로드 간단 설명
	private List<Integer> userAge;	// 앱 다운로드 학습 연령 최소값
	private List<String> studyContent;	// 앱 다운로드 학습 내용
	private String packageName;		// 앱 다운로드 패키지 이름
	private String versionName;		// 앱 다운로드 패키지 버전
	private int versionCode;		// 앱 다운로드 버전 코드
	private String imgUrl;			// 앱 다운로드 이미지 url
	private String marketUrl;		// 앱 다운로드 마켓 url
	private String videoUrl;		// 앱 다운로드 비디오 url
	
	public List<Integer> getUserAge() {
		return userAge;
	}
	public void setUserAge(List<Integer> userAge) {
		this.userAge = userAge;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getStudyContent() {
		return studyContent;
	}
	public void setStudyContent(List<String> studyContent) {
		this.studyContent = studyContent;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getMarketUrl() {
		return marketUrl;
	}
	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	@Override
	public String toString() {
		return "AppDownVo [seq=" + seq + ", name=" + name + ", iconUrl="
				+ iconUrl + ", description=" + description + ", userAge="
				+ userAge + ", studyContent=" + studyContent + ", packageName="
				+ packageName + ", versionName=" + versionName
				+ ", versionCode=" + versionCode + ", imgUrl=" + imgUrl
				+ ", marketUrl=" + marketUrl + ", videoUrl=" + videoUrl + "]";
	}
}
