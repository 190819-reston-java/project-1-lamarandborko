package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class ReimbursementDaoJDBC implements ReimbursementDao {

	@Override
	public Reimbursement viewPending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement viewResolved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement viewAllPending(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement viewAllResolved(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewEmployeePending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewEmployeeResolved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllPending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllResolved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createReimbursement(Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "INSERT INTO reimbursements VALUES (DEFAULT, ?, ?, ?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, r.employee_id);
			stmt.setString(2, r.title);
			stmt.setDouble(3, r.amountrequested);		
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}



}
