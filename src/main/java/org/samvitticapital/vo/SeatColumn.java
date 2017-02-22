package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

public class SeatColumn 
{
	List<SeatVO> seats;
	
	public SeatColumn()
	{
		this.seats = new ArrayList<SeatVO>();
	}

	public List<SeatVO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatVO> seats) {
		this.seats = seats;
	}
}
