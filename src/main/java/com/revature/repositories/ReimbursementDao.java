package com.revature.repositories;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	
	List<Reimbursement> viewPending(int employee_id);
	
	List<Reimbursement> viewResolved(int employee_id);
	
	List<Reimbursement> viewPending(String user_name);
	
	List<Reimbursement> viewResolved(String user_name);

	List<Reimbursement> viewAllPending();
	
	List<Reimbursement> viewAllResolved();
	
	boolean requestAccepted(int id);
	
	boolean requestAccepted(Reimbursement r);
	
	boolean requestDenied(int id);
	
	boolean requestDenied(Reimbursement r);
	
	boolean createReimbursement(Reimbursement r);

}
