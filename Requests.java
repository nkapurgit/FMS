package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;

public class Requests {

	private List<Request> requestList;
	
	public Requests()
	{
		this.requestList = new ArrayList<Request>();
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}
	
	

}
