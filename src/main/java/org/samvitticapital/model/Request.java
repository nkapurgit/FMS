package org.samvitticapital.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_request")
public class Request 
{
	@Id
	@GeneratedValue
	@Column(name="request_id")
	private int requestId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="proj_id", referencedColumnName="project_id")
	private Project project;
	@Column(name="type_of_request")
	private String typeOfRequest;;
	@Column(name="no_of_seats")
	private int noOfSeats;
	@Column(name="preferred_bay")
	private String preferredBay;
	@Column(name="apprvl_leadership")
	private String leadershipApprvl;
	@Column(name="request_comment")
	private String requestComment;
	@Column(name="request_status")
	private String requestStatus;
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
