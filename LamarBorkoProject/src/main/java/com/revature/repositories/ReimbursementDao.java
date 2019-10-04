package com.revature.repositories;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {	
	
	List<Reimbursement> viewEmployeePending();//
	
	boolean createReimbursement(Reimbursement r);//

	Reimbursement getReimbursement(int employee_id);//

	//implement: view single employee pending reimbursements by id
	List<Reimbursement> viewPending(int employee_id);
	
	List<Reimbursement> viewResolved(int employee_id);
	
	List<Reimbursement> viewPending(String user_name);
	
	List<Reimbursement> viewResolved(String user_name);

	List<Reimbursement> viewAllPending();
	
	List<Reimbursement> viewAllResolved();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	boolean requestAccepted(int id);
	
	boolean requestDenied(int id);
	
}
