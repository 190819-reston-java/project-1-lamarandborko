package com.revature.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Reimbursement {

	private int id;
	private int employeeid;
	private String title;
	private double amount_requested;
	private Date date_requested;
	private String status;
	private String resolved_status;
	private String picture;
	static NumberFormat formatter = new DecimalFormat("#0.00");

	public Reimbursement(int id, int employeeid, String title, double amount_requested, Date date_requested,
			String status, String resolved_status, String picture) {
		super();
		this.id = id;
		this.employeeid = employeeid;
		this.title = title;
		this.amount_requested = amount_requested;
		this.date_requested = date_requested;
		this.status = status;
		this.resolved_status = resolved_status;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAmount_requested() {
		return amount_requested;
	}

	public void setAmount_requested(double amount_requested) {
		this.amount_requested = amount_requested;
	}

	public Date getDate_requested() {
		return date_requested;
	}

	public void setDate_requested(Date date_requested) {
		this.date_requested = date_requested;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolvedstatus() {
		return resolved_status;
	}

	public void setResolvedstatus(String resolved_status) {
		this.resolved_status = resolved_status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employeeid=" + employeeid + ", title=" + title + ", amount_requested="
				+ amount_requested + ", date_requested=" + date_requested + ", status=" + status + ", resolved_status="
				+ resolved_status + ", picture=" + picture + "]";
	}

}
