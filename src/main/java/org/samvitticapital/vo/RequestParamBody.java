package org.samvitticapital.vo;

public class RequestParamBody {

	private int projectId;
	private String typeOfRequest;
	private int noOfSeats;
	private String preferredBay;
	private String leadershipApprvl;
	private String requestComment;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getPreferredBay() {
		return preferredBay;
	}

	public void setPreferredBay(String preferredBay) {
		this.preferredBay = preferredBay;
	}

	public String getLeadershipApprvl() {
		return leadershipApprvl;
	}

	public void setLeadershipApprvl(String leadershipApprvl) {
		this.leadershipApprvl = leadershipApprvl;
	}

	public String getRequestComment() {
		return requestComment;
	}

	public void setRequestComment(String requestComment) {
		this.requestComment = requestComment;
	}
}
