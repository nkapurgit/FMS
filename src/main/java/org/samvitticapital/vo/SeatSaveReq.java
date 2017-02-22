package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

public class SeatSaveReq 
{
	private int requestId;
	private List<SaveVO> finalList = new ArrayList<SaveVO>();

	public List<SaveVO> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<SaveVO> finalList) {
		this.finalList = finalList;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}
