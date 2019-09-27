package com.revature.repositories;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	
	List<Reimbursement> viewPending(int id);
	
	List<Reimbursement> viewResolved(int id);
	
	List<Reimbursement> viewPending(String name);
	
	List<Reimbursement> viewResolved(String name);

	List<Reimbursement> viewAllPending();
	
	List<Reimbursement> viewAllResolved();
	
	boolean changeStatus(Reimbursement r);
	
	boolean createReimbursement(Reimbursement r);

}
