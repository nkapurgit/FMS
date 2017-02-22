package org.samvitticapital.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.samvitticapital.Dao.ProjectDao;
import org.samvitticapital.Dao.RequestDao;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.model.RequestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RequestController {

	@Autowired
	private RequestDao requestDao;
	@Autowired
	private Request request;

	@RequestMapping("/newRequest")
	public Request saveNewRequest(
			@RequestParam(value = "projectId", required = true) int projectId,
			@RequestParam(value = "typeOfRequest", required = true) String typeOfRequest,
			@RequestParam(value = "requestStatus", required = true) String requestStatus,
			@RequestParam(value = "noOfSeats", required = true) int noOfSeats,
			@RequestParam(value = "preferredBay", required = true) String preferredBay,
			@RequestParam(value = "leadershipApprvl", required = true) String leadershipApprvl,
			@RequestParam(value = "requestComment", required = false) String requestComment) {
		request.setProjectId(projectId);
		request.setTypeOfRequest(typeOfRequest);
		request.setRequestStatus(requestStatus);
		request.setNoOfSeats(noOfSeats);
		request.setPreferredBay(preferredBay);
		request.setLeadershipApprvl(leadershipApprvl);
		request.setRequestComment(requestComment);

		requestDao.save(request);
		int newRequestId = request.getRequestId();
		request = requestDao.get(newRequestId);
		return request;
	}

	@RequestMapping("/getAllRequests")
	public ArrayList<Request> fetchAllRequests(){
		ArrayList<Request> requestList = new ArrayList<Request>();
		requestList = requestDao.getAllReq();
		return requestList;
	}
}
