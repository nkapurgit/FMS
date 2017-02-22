package org.samvitticapital.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_seat_info")
public class Seat 
{
	@Id
	@Column(name="seat_number")
	private String seatNo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="proj_id", referencedColumnName="project_id")
	private Project project;
	
	@Column(name="is_available")
	private String isAvailable;
	
	@Column(name="seat_bay_area")
	private String bayArea;
	
	@Column(name="seat_row_no")
	private int rowNo;
	
	@Column(name="seat_col_no")
	private int colNo;
	
	public Seat()
	{
		
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public Project getProject() {
		return project;
	}

	public void setProjectName(Project project) {
		this.project = project;
	}

	public String getBayArea() {
		return bayArea;
	}

	public void setBayArea(String bayArea) {
		this.bayArea = bayArea;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getColNo() {
		return colNo;
	}

	public void setColNo(int colNo) {
		this.colNo = colNo;
	}

	public String getAvailable() {
		return isAvailable;
	}

	public void setAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	
}
