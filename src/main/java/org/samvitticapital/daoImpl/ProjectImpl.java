package org.samvitticapital.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.samvitticapital.Dao.ProjectDao;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.model.Seat;
import org.samvitticapital.vo.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public class ProjectImpl implements ProjectDao {

	@Autowired
	SessionFactory sessionFactory;

	public Session getCurrentsession() {

		return sessionFactory.getCurrentSession();
	}

	public void save(Project project) {

		getCurrentsession().save(project);

	}

	@Override
	public Project get(int projectId) {
		Project project = (Project) getCurrentsession().get(Project.class, projectId);
		return project;
	}

	@Override
	public Projects getAllProjects() {
		// TODO Auto-generated method stub
		String hql = "SELECT P.projectId, P.projectName FROM Project P";
		Projects resultList = new Projects();
		Query query = getCurrentsession().createQuery(hql);
		List<Object[]> projList = (List<Object[]>) query.list();
		for (Object[] arr : projList) {
			Project p = new Project();
			p.setProjectId((Integer) arr[0]);
			p.setProjectName((String) arr[1]);
			resultList.getProjectList().add(p);
		}

		return resultList;
	}

	@Override
	public String getEmployeeName(int empId) {
		// TODO Auto-generated method stub
		String empName = "";
		Employee emp = (Employee) getCurrentsession().get(Employee.class, empId);
		if (null != emp)
			empName = emp.getEmployeeName();
		return empName;
	}

	@Override
	public Employee getEmployeeDetails(String email) {
		Employee e = new Employee();
		Query query = (Query) getCurrentsession().createQuery("from Employee where employeeEmail = :employeeEmail");
		query.setString("employeeEmail", email);
		query.getFirstResult();
		List<Employee> list = (List<Employee>) query.list();
		System.out.println("list - " + list);
		Employee emp = (Employee) list.get(0);
		/*
		 * for (Object[] arr : list) { e.setEmployeeId((Integer) arr[0]);
		 * e.setEmployeeName((String) arr[1]); e.setEmployeeEmail((String)
		 * arr[2]); }
		 */

		System.out.println("empoyee id - " + emp.getEmployeeId());
		return emp;
	}

	@Override
	public int getSeatCount(int projectId) {
		// TODO Auto-generated method stub
		int noOfSeats = 0;
		/*Project p = new Project();
		p=get(projectId);*/
		String hql = "SELECT count(S.seatNo) FROM Seat S where project.projectId = :projectId";
		Query query = (Query) getCurrentsession().createQuery(hql);
		query.setInteger("projectId", projectId);
		List rows = query.list();
		System.out.println("row - "+rows.get(0));
		if(rows.size()>0){
			noOfSeats = Integer.parseInt(rows.get(0).toString());
		}
		System.out.println("no of seats found - "+noOfSeats);
		return noOfSeats;
	}

}
