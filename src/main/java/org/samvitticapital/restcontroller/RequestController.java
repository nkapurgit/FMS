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
import org.samvitticapital.vo.LayOutReq;
import org.samvitticapital.vo.RequestParamBody;
import org.samvitticapital.vo.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

	@Autowired
	private RequestDao requestDao;
	@Autowired
	private Request request;

	@RequestMapping(value = "/newRequest", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> saveNewRequest(@RequestBody RequestParamBody req) {
		try {
			System.out.println("project id - "+req.getProjectId());
			int projectId = req.getProjectId();
			Project p = requestDao.getProject(projectId);
			request.setProject(p);
			request.setTypeOfRequest(req.getTypeOfRequest());
			request.setRequestStatus("PENDING");
			request.setNoOfSeats(req.getNoOfSeats());
			request.setPreferredBay(req.getPreferredBay());
			request.setLeadershipApprvl(req.getLeadershipApprvl());
			request.setRequestComment(req.getRequestComment());

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
