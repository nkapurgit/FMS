package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

import org.samvitticapital.model.Project;

public class Projects {

	private List<Project> projectList;
	
	public Projects()
	{
		this.projectList = new ArrayList<Project>();
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
	

}
