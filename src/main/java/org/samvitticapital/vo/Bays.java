package org.samvitticapital.vo;

import java.util.ArrayList;
import java.util.List;

public class Bays 
{
	private List<Bay> bayList;
	
	public Bays()
	{
		this.bayList = new ArrayList<Bay>();
	}

	public List<Bay> getBayList() {
		return bayList;
	}

	public void setBayList(List<Bay> bayList) {
		this.bayList = bayList;
	}
	
	
}
