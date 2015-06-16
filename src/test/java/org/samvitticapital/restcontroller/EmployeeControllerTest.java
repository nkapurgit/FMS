package org.samvitticapital.restcontroller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.samvitticapital.Dao.EmployeDao;
import org.samvitticapital.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	@InjectMocks
	private EmployeeController empCtrl;
	@Mock
	private EmployeDao empDao;
	@Spy
	private Employee emp;
	@Before
	public void setUp()
	{
		when(empDao.get("sunil1")).thenReturn(emp);
		//when(emp.getName()).thenReturn("sunil1");
	}

	@Test
	public void test() {
	assertEquals("Boot is working sunil1", empCtrl.showMessage());
	}

}
