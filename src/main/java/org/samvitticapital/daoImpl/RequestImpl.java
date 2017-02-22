package org.samvitticapital.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.samvitticapital.Dao.RequestDao;
import org.samvitticapital.model.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
@Component
public class RequestImpl implements RequestDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public Session getCurrentsession(){
		 
		 return sessionFactory.getCurrentSession();
	 }
	 
	 
	 public void save(Request request){
		 
		 getCurrentsession().save(request);
		 
		 
	 }
	 
	 @Override
	public Request get(int requestId) {
		 Request request = (Request)getCurrentsession().get(Request.class,requestId);
		return request;
	}


	@Override
	public ArrayList<Request> getAllReq() {
		// TODO Auto-generated method stub
		String hql = "SELECT R.typeOfRequest,R.requestStatus FROM Request R";
		ArrayList<Request> resultList = new ArrayList<Request>();
		Query query = getCurrentsession().createQuery(hql);
		List<Object[]> groupList = (List<Object[]>) query.list();
		for(Object[] arr : groupList) {
			Request r=new Request();
			 System.out.println(" 0 " + arr[0]);
			 System.out.println(" 1 " + arr[1]);
			 r.setTypeOfRequest((String) arr[0]);
			 r.setRequestStatus((String) arr[1]);
			 resultList.add(r);
			}
		
	/*	for(Object o : results){
			Request r=(Request)o;
			System.out.println(r.getNoOfSeats());
		}*/
		
		//results.getRequestList();
		//System.out.println("no of results - "+results.size());
		//System.out.println("1st req - "+results.get(0));
		
		return resultList;
	}

}
