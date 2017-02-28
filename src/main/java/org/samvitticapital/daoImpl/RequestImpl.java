package org.samvitticapital.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.samvitticapital.Dao.RequestDao;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.vo.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public class RequestImpl implements RequestDao {

	@Autowired
	SessionFactory sessionFactory;

	public Session getCurrentsession() {

		return sessionFactory.getCurrentSession();
	}

	public void save(Request request) {

		getCurrentsession().save(request);

	}

	@Override
	public Request get(int requestId) {
		Request request = (Request) getCurrentsession().get(Request.class,
				requestId);
		return request;
	}

	@Override
	public Project getProject(int projectId) {
		Project project = (Project) getCurrentsession().get(Project.class,
				projectId);
		return project;
	}

	@Override
	public Project getRequestedProjectDetails(int requestId) 
	{
		Request req = (Request) getCurrentsession().get(Request.class, requestId);
		return req.getProject();
	}
	
	@Override
	public Requests getAllReq() {
		// TODO Auto-generated method stub
		String hql = "SELECT R.requestId, R.typeOfRequest,R.requestStatus, R.noOfSeats ,R.preferredBay, R.leadershipApprvl , R.requestComment FROM Request R";
		Requests resultList = new Requests();
		Query query = getCurrentsession().createQuery(hql);
		List<Object[]> reqList = (List<Object[]>) query.list();
		for (Object[] arr : reqList) {
			Request r = new Request();
			Project p = getRequestedProjectDetails((Integer) arr[0]);
			r.setRequestId((Integer) arr[0]);
			//p.setProjectId((Integer) arr[1]);
			r.setProject(p);
			r.setTypeOfRequest((String) arr[1]);
			r.setRequestStatus((String) arr[2]);
			r.setNoOfSeats((Integer) arr[3]);
			r.setPreferredBay((String) arr[4]);
			r.setLeadershipApprvl((String) arr[5]);
			r.setRequestComment((String) arr[6]);
			resultList.getRequestList().add(r);
			System.out.println("results added for - "+resultList.getRequestList().get(0).getRequestId());
		}
		System.out.println("results added - "+resultList.getRequestList().size());
		return resultList;
	}

}
