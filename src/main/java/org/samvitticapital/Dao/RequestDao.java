package org.samvitticapital.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.vo.Requests;




public interface RequestDao {
	
	public Session getCurrentsession();
	public void save(Request request);
	public Request get(int requestId);
	public Requests getAllReq();
	public Project getProject(int projectId);
	public Project getRequestedProjectDetails(int requestId); 

}
