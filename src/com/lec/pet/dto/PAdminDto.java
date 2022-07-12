package com.lec.pet.dto;

public class PAdminDto {
	private String aId;
	private String aPw;
	private String aName;
	public PAdminDto() { }
	public PAdminDto(String aId, String aPw, String aName) {
		this.aId = aId;
		this.aPw = aPw;
		this.aName = aName;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaPw() {
		return aPw;
	}
	public void setaPw(String aPw) {
		this.aPw = aPw;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	@Override
	public String toString() {
		return "pAdminDto [aId=" + aId + ", aPw=" + aPw + ", aName=" + aName + "]";
	}
	
}
