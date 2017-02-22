package org.samvitticapital.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.samvitticapital.Dao.SeatDao;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.model.Seat;
import org.samvitticapital.vo.Bay;
import org.samvitticapital.vo.Bays;
import org.samvitticapital.vo.SaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public class SeatImpl implements SeatDao
{
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


	@Override
	public List<Seat> getSeatDetails(String bayNo) throws Exception
	{
		Query query = (Query) getCurrentsession().createQuery("from Seat where bayArea = :bayArea");
		query.setString("bayArea", bayNo);
		List<Seat> list = query.list();
		return list;
	}
	
	@Override
	public Bays getBays(int count) throws Exception
	{
		SQLQuery query = getCurrentsession().createSQLQuery("select seat_floor, seat_bay_area from "
				+ "(select seat_floor, seat_bay_area, count(seat_bay_area) cnt from tbl_seat_info where "
				+ "is_available = 'Y' group by seat_floor, seat_bay_area) a where a.cnt > :count");
		
		query.setParameter("count", count);
		List<Object[]> rows = query.list();
		Bays bays = new Bays();
		for(Object[] row : rows){
			Bay bay = new Bay();
			bay.setFloorNo(Integer.parseInt(row[0].toString()));
			bay.setBayName(row[1].toString());
			bays.getBayList().add(bay);
		}
		
		return bays;
	}


	@Override
	public int getSeatCountFromRequest(int requestId) throws Exception
	{
		Request req = (Request) getCurrentsession().get(Request.class, requestId);
		return ((req == null) ? 0 : req.getNoOfSeats());
	}
	
	@Override
	public String getTypeFromRequest(int requestId) throws Exception
	{
		Request req = (Request) getCurrentsession().get(Request.class, requestId);
		return ((req == null) ? "" : req.getTypeOfRequest());
	}


	@Override
	public Project getRequestedProjectDetails(int requestId) throws Exception
	{
		Request req = (Request) getCurrentsession().get(Request.class, requestId);
		return req.getProject();
	}


	@Override
	public void updateSeatDetails(String reqType, Project project, List<SaveVO> seatList) throws Exception
	{
		for(SaveVO s : seatList)
		{
			if(s.isSelected())
			{
				Seat seat = (Seat) getCurrentsession().get(Seat.class, s.getSeatNo());
				//seat.setProjectName(project);
				if("RAMPUP".equals(reqType))
				{
					seat.setProjectName(project);
					seat.setAvailable("N");
				}
				else if("RAMPDOWN".equals(reqType))
				{
					Project p = new Project(0);
					seat.setProjectName(p);
					seat.setAvailable("Y");
				}
				
				getCurrentsession().saveOrUpdate(seat);
			}
		}
	}


	@Override
	public void closeRequest(int requestId) throws Exception
	{
		Request req = (Request) getCurrentsession().get(Request.class, requestId);
		req.setRequestStatus("PROCESSED");
		getCurrentsession().saveOrUpdate(req);
	}
}
