package org.samvitticapital.Dao;

import java.util.List;

import org.hibernate.Session;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Seat;
import org.samvitticapital.vo.Bays;
import org.samvitticapital.vo.SaveVO;

public interface SeatDao 
{
	public Session getCurrentsession();
	public void save(Employee emp);
	public Employee get(String name);
	public List<Seat> getSeatDetails(String bayNo) throws Exception;
	public Bays getBays(int count) throws Exception;
	public int getSeatCountFromRequest(int requestId) throws Exception;
	public String getTypeFromRequest(int requestId) throws Exception;
	public Project getRequestedProjectDetails(int requestId) throws Exception;
	public void updateSeatDetails(String reqType, Project project, List<SaveVO> seatList) throws Exception;
	public void closeRequest(int requestId) throws Exception;
}
