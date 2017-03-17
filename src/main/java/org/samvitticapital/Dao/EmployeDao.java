package org.samvitticapital.Dao;

import org.hibernate.Session;
import org.samvitticapital.model.Employee;



public interface EmployeDao {
	
	public Session getCurrentsession();
	public void save(Employee emp);
	public Employee get(int empId);

}
