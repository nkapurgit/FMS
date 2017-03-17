package org.samvitticapital.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.vo.Projects;



public interface ProjectDao {
	
	public Session getCurrentsession();
	public void save(Project project);
	public Project get(int projectId);
	public Projects getAllProjects();
	public String getEmployeeName(int empId);
	public Employee getEmployeeDetails(String email);
	public int getSeatCount(int projectId);
}
