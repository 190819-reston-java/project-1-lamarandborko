package com.revature.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Reimbursement {
	
	public int id;
	public int employee_id;
	public String title;
	public double amountrequested;
	public Date daterequested;
	public String status;
	public String picture;
	public Date date_requested;
	static NumberFormat formatter = new DecimalFormat("#0.00");

	public Reimbursement(int id, int employeeid, String title, double amount_requested) {
		super();
		this.id = id;
		this.employee_id = employeeid;
		this.title = title;
		this.amountrequested = amount_requested;
		this.daterequested = date_requested;
		this.status = status;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeid() {
		return employee_id;
	}

	public void setEmployeeid(int employeeid) {
		this.employee_id = employeeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAmount_requested() {
		return amountrequested;
	}

	public void setAmount_requested(double amount_requested) {
		this.amountrequested = amount_requested;
	}

	public Date getDate_requested() {
		return daterequested;
	}

	public void setDate_requested(Date date_requested) {
		this.daterequested = date_requested;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employee_id=" + employee_id + ", title=" + title + ", amountrequested="
				+ amountrequested + ", daterequested=" + daterequested + ", status=" + status + ", picture="
				+ picture + "]";
	}

}
