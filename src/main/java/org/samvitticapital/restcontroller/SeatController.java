package org.samvitticapital.restcontroller;

import java.net.Authenticator.RequestorType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.samvitticapital.Dao.EmployeDao;
import org.samvitticapital.Dao.SeatDao;
import org.samvitticapital.model.Employee;
import org.samvitticapital.model.Project;
import org.samvitticapital.model.Seat;
import org.samvitticapital.vo.BayReq;
import org.samvitticapital.vo.Bays;
import org.samvitticapital.vo.GenericResponse;
import org.samvitticapital.vo.LayOutReq;
import org.samvitticapital.vo.SaveVO;
import org.samvitticapital.vo.SeatColumn;
import org.samvitticapital.vo.SeatRow;
import org.samvitticapital.vo.SeatSaveReq;
import org.samvitticapital.vo.SeatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController 
{
	@Autowired
	private SeatDao seatDao;
	
	@RequestMapping(value = "/getSeatDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getSeatDetails(@RequestBody LayOutReq req)
	{
		try
		{
			String reqType = seatDao.getTypeFromRequest(req.getRequestId());
			List<Seat> list = seatDao.getSeatDetails(req.getBay());
			
			Map<Integer, Map<Integer, List<Seat>>> rowMap = new HashMap<Integer, Map<Integer, List<Seat>>>();
			for(Seat s : list)
			{
				if(rowMap.containsKey(s.getRowNo()))
				{
					Map<Integer, List<Seat>> colMap = rowMap.get(s.getRowNo());
					if(colMap.containsKey(s.getColNo()))
						colMap.get(s.getColNo()).add(s);
					else
					{
						List<Seat> l = new ArrayList<Seat>();
						l.add(s);
						colMap.put(s.getColNo(), l);
					}
				}
				else
				{
					Map<Integer, List<Seat>> colMap = new HashMap<Integer, List<Seat>>();
					List<Seat> l = new ArrayList<Seat>();
					l.add(s);
					colMap.put(s.getColNo(), l);
					rowMap.put(s.getRowNo(), colMap);
				}
			}
			
			Set<Integer> keys = rowMap.keySet();
			SeatRow sRow = new SeatRow();
			
			for(Integer i : keys)
			{
				Map<Integer, List<Seat>> colMap = rowMap.get(i);
				Set<Integer> colKeys = colMap.keySet();
				
				for(Integer j : colKeys)
				{
					List<Seat> listSeat = colMap.get(j);
					SeatColumn sCol = new SeatColumn();
					
					for(Seat s: listSeat)
					{
						SeatVO seatVO = new SeatVO();
						seatVO.setNo(s.getSeatNo());
						seatVO.setPname(s.getProject().getProjectName());
						
						if("RAMPUP".equals(reqType) && "Y".equals(s.getAvailable()))
							seatVO.setDisabled(false);
						else if("RAMPUP".equals(reqType) && "N".equals(s.getAvailable()))
							seatVO.setDisabled(true);
						else if("RAMPDOWN".equals(reqType) && "Y".equals(s.getAvailable()))
							seatVO.setDisabled(true);
						else if("RAMPDOWN".equals(reqType) && "N".equals(s.getAvailable()))
							seatVO.setDisabled(false);
						else
							seatVO.setDisabled(true);
						
						sCol.getSeats().add(seatVO);
					}
					
					sRow.getPrivateSeats().add(sCol);
				}
				
			}
			return new ResponseEntity<>(sRow, HttpStatus.OK);
		}
		catch(Exception e)
		{
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	
	@RequestMapping(value = "/getBays", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getBays(@RequestBody BayReq req)
	{
		try
		{
			int seatCount = seatDao.getSeatCountFromRequest(req.getRequestId());
			//System.out.println("seatCount = "+seatCount);
			Bays bays = seatDao.getBays(seatCount);
			//System.out.println("bays = "+bays.getBayList().size());
			return new ResponseEntity<>(bays, HttpStatus.OK);
		}
		catch(Exception e)
		{
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/saveSeatDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> saveSeatDetails(@RequestBody SeatSaveReq req)
	{
		try
		{
			List<SaveVO> seatList = req.getFinalList();
			String reqType = seatDao.getTypeFromRequest(req.getRequestId());
			Project project = seatDao.getRequestedProjectDetails(req.getRequestId());
			seatDao.updateSeatDetails(reqType, project, seatList);
			seatDao.closeRequest(req.getRequestId());
			GenericResponse gresp = new GenericResponse(true, "Details saved successfully");
			return new ResponseEntity<>(gresp, HttpStatus.OK);
		}
		catch(Exception e)
		{
			GenericResponse gresp = new GenericResponse(false, e.getMessage());
			return new ResponseEntity<>(gresp, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
