package org.samvitticapital.restcontroller;

import org.samvitticapital.Dao.EmployeDao;
import org.samvitticapital.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeDao empDao;
	@Autowired
	private Employee emp;
	
	@RequestMapping("/")
	public String showMessage()
	{
    	emp.setName("sunil1");
    	emp.setAge(24);
    	emp.setAddress("Bangalore");
    	
    	empDao.save(emp);
    	
		emp = empDao.get("sunil1");
    	
    	
    	
		return "Boot is working " + emp.getName();
	}

}
