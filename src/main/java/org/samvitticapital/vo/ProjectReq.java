package org.samvitticapital.vo;

public class ProjectReq {

	private int projectId;
	private String projectName;
	private String projectManagerEmailId;
	private String projectDirectorEmailId;
	private String projectStartDate;
	private String projectEndDate;

	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManagerEmailId() {
		return projectManagerEmailId;
	}

	public void setProjectManagerEmailId(String projectManagerEmailId) {
		this.projectManagerEmailId = projectManagerEmailId;
	}

	public String getProjectDirectorEmailId() {
		return projectDirectorEmailId;
	}

	public void setProjectDirectorEmailId(String projectDirectorEmailId) {
		this.projectDirectorEmailId = projectDirectorEmailId;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

}
