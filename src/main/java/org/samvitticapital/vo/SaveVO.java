package org.samvitticapital.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveVO 
{
	private String seatNo;
	@JsonProperty
	private boolean isSelected;
	
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
