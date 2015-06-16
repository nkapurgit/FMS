package org.samvitticapital.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.samvitticapital.Dao.EmployeDao;
import org.samvitticapital.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
@Component
public class EmployeeImpl implements EmployeDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public Session getCurrentsession(){
		 
		 return sessionFactory.getCurrentSession();
	 }
	 
	 
	 public void save(Employee emp){
		 
		 getCurrentsession().save(emp);
		 
		 
	 }
	 
	 @Override
	public Employee get(String name) {
		Employee emp = (Employee)getCurrentsession().get(Employee.class,name);
		return emp;
	}

}
