package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class ReimbursementDaoJDBC implements ReimbursementDao {

	@Override
	public List<Reimbursement> viewPending(int id) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "select * from project_1.reimbursements where id = ?;";
			try(PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1,  id);
				if(stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementFromRS(resultSet));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> viewResolved(int id) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "select * from project_1.reimbursements where id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if(stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementFromRS(resultSet));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> viewAllPending(String name) {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "select * from project_1.reimbursements where personusername = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, name);
				if(stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementFromRS(resultSet));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> viewAllResolved(String name) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "select * from project_1.reimbursements where personusername = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, name);
				if(stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementFromRS(resultSet));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> viewAllPending() {
		
		return null;
	}

	@Override
	public List<Reimbursement> viewAllResolved() {

		return null;
	}

	@Override
	public boolean createReimbursement(Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO project_1.reimbursements VALUES (DEFAULT, ?, ?, ?);";
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, r.getTitle());
			stmt.setDouble(2, r.getAmount_requested());
			stmt.setString(3, r.getStatus());
			stmt.setString(4, r.getPicture());
			stmt.setInt(5, r.getEmployeeid());
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
	
	private Reimbursement createReimbursementFromRS(ResultSet resultset) throws SQLException {
		return new Reimbursement(resultset.getInt("id"),
				resultset.getInt("employee_id"),
				resultset.getString("title"),
				resultset.getDouble("amount_requested"),
				resultset.getDate("date_requested"),
				resultset.getString("status"),
				resultset.getString("picture")
				);
	}

}
