package com.revature.repositories;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	
	List<Reimbursement> viewPending(int id);
	
	List<Reimbursement> viewResolved(int id);
	
	List<Reimbursement> viewAllPending(String name);
	
	List<Reimbursement> viewAllResolved(String name);

	List<Reimbursement> viewAllPending();
	
	List<Reimbursement> viewAllResolved();
	
	boolean createReimbursement(Reimbursement r);

}
