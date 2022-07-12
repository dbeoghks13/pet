package com.lec.pet.dto;

public class PDogDto {
	private int dNo;
	private String dName;
	private String dSize;
	private String dCategory;
	private String dContent;
	private String dPhoto;
	public PDogDto() { }
	public PDogDto(int dNo, String dName, String dSize, String dCategory, String dContent, String dPhoto) {
		this.dNo = dNo;
		this.dName = dName;
		this.dSize = dSize;
		this.dCategory = dCategory;
		this.dContent = dContent;
		this.dPhoto = dPhoto;
	}
	public int getdNo() {
		return dNo;
	}
	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdSize() {
		return dSize;
	}
	public void setdSize(String dSize) {
		this.dSize = dSize;
	}
	public String getdCategory() {
		return dCategory;
	}
	public void setdCategory(String dCategory) {
		this.dCategory = dCategory;
	}
	public String getdContent() {
		return dContent;
	}
	public void setdContent(String dContent) {
		this.dContent = dContent;
	}
	public String getdPhoto() {
		return dPhoto;
	}
	public void setdPhoto(String dPhoto) {
		this.dPhoto = dPhoto;
	}
	@Override
	public String toString() {
		return "PDogDto [dNo=" + dNo + ", dName=" + dName + ", dSize=" + dSize + ", dId=" + dCategory + ", dContent="
				+ dContent + ", dPhoto=" + dPhoto + "]";
	}
	
}
