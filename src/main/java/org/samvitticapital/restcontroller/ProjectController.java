package org.samvitticapital.restcontroller;

import java.util.Date;

import org.samvitticapital.Dao.ProjectDao;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.vo.GenericResponse;
import org.samvitticapital.vo.ProjectReq;
import org.samvitticapital.vo.Projects;
import org.samvitticapital.vo.RequestParamBody;
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
public class ProjectController {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private Project project;

	@RequestMapping(value = "/newProject", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> saveNewProject(@RequestBody ProjectReq req) {
		try {
			System.out.println("manager email id - "+req.getProjectManagerEmailId());
			String projectManagerEmailId = req.getProjectManagerEmailId();
			Employee e = projectDao.getEmployeeDetails(projectManagerEmailId);
			System.out.println("got emp id - " + e.getEmployeeId());
			project.setProjectName(req.getProjectName());
			project.setManagerId(e.getEmployeeId());
			e = projectDao.getEmployeeDetails(req.getProjectDirectorEmailId());
			System.out.println("got emp id 2nd- " + e.getEmployeeId());
			project.setDirectorId(e.getEmployeeId());
			project.setStartDate(new Date(req.getProjectStartDate()));
			if (null != req.getProjectEndDate())
				project.setEndDate(new Date(req.getProjectEndDate()));
			projectDao.save(project);
			int newProjectId = project.getProjectId();
			System.out.println("newProjectId - "+newProjectId);
			project = projectDao.get(newProjectId);
			GenericResponse gresp = new GenericResponse(true,
					"Details saved successfully");
			return new ResponseEntity<>(gresp, HttpStatus.OK);
		} catch (Exception e) {
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/getProject", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity fetchProject(@RequestBody ProjectReq req) {
		String empName = "";
		try {
			int projectId = req.getProjectId();
			project = projectDao.get(projectId);
			int noOfSeats = projectDao.getSeatCount(projectId);
			project.setNoOfSeats(noOfSeats);
			empName = projectDao.getEmployeeName(project.getManagerId());
			project.setManagerName(empName);
			empName = projectDao.getEmployeeName(project.getDirectorId());
			project.setDirectorName(empName);
			return new ResponseEntity<>(project, HttpStatus.OK);
		} catch (Exception e) {
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/getAllProjects")
	public ResponseEntity fetchAllProjects() {
		Projects requestList = new Projects();
		try {
			requestList = projectDao.getAllProjects();
			// return requestList;
			return new ResponseEntity<>(requestList, HttpStatus.OK);
		} catch (Exception e) {
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
