package org.samvitticapital.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.samvitticapital.model.Request;




public interface RequestDao {
	
	public Session getCurrentsession();
	public void save(Request request);
	public Request get(int requestId);
	public ArrayList<Request> getAllReq();

}
