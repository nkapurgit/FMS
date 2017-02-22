package org.samvitticapital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "tbl_request")
public class Request {

	@Id
	@GeneratedValue
	@Column(name = "request_id")
	int requestId;
	@Column(name = "project_id")
	int projectId;
	@Column(name = "type_of_request")
	String typeOfRequest;
	@Column(name = "request_status")
	String requestStatus;
	@Column(name = "no_of_seats")
	int noOfSeats;
	@Column(name = "preferred_bay")
	String preferredBay;
	@Column(name = "apprvl_leadership")
	String leadershipApprvl;
	@Column(name = "request_comment")
	String requestComment;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

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

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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
