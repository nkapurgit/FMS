package org.samvitticapital.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.samvitticapital.Dao.ProjectDao;
import org.samvitticapital.Dao.RequestDao;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Request;
import org.samvitticapital.model.RequestList;
import org.samvitticapital.vo.GenericResponse;
import org.samvitticapital.vo.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<GenericResponse> saveNewRequest(
			@RequestParam(value = "projectId", required = true) int projectId,
			@RequestParam(value = "typeOfRequest", required = true) String typeOfRequest,
			@RequestParam(value = "noOfSeats", required = true) int noOfSeats,
			@RequestParam(value = "preferredBay", required = true) String preferredBay,
			@RequestParam(value = "leadershipApprvl", required = true) String leadershipApprvl,
			@RequestParam(value = "requestComment", required = false) String requestComment) {
		try {
			System.out.println("projectId - "+projectId);
			Project p = requestDao.getProject(projectId);
			request.setProject(p);
			request.setTypeOfRequest(typeOfRequest);
			request.setRequestStatus("PENDING");
			request.setNoOfSeats(noOfSeats);
			request.setPreferredBay(preferredBay);
			request.setLeadershipApprvl(leadershipApprvl);
			request.setRequestComment(requestComment);

			requestDao.save(request);
			int newRequestId = request.getRequestId();
			request = requestDao.get(newRequestId);
			GenericResponse gresp = new GenericResponse(true,
					"Details saved successfully");
			return new ResponseEntity<>(gresp, HttpStatus.OK);
		} catch (Exception e) {
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping("/getAllRequests")
	public ResponseEntity fetchAllRequests() {
		try {
			Requests requestList = new Requests();
			requestList = requestDao.getAllReq();
			return new ResponseEntity<>(requestList, HttpStatus.OK);
		} catch (Exception e) {
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
