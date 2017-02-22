package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

public class SeatRow 
{
	List<SeatColumn> privateSeats;
	
	public SeatRow()
	{
		this.privateSeats = new ArrayList<SeatColumn>();
	}

	public List<SeatColumn> getPrivateSeats() {
		return privateSeats;
	}

	public void setPrivateSeats(List<SeatColumn> privateSeats) {
		this.privateSeats = privateSeats;
	}
}
