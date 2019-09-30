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
	public List<Reimbursement> viewPending(int employee_id) {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE employee_id = ? AND resolved_status = 'Pending';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, employee_id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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
	public List<Reimbursement> viewResolved(int employee_id) {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE employee_id = ? AND resolved_status = 'Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, employee_id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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
	public List<Reimbursement> viewPending(String emp_username) {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements where emp_username = ? AND resolved_status = 'Pending';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, emp_username);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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
	public List<Reimbursement> viewResolved(String emp_username) {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements where emp_username = ? AND resolved_status='Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, emp_username);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE resolved_status='Pending';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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
	public List<Reimbursement> viewAllResolved() {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE resolved_status='Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							reimbursements.add(createReimbursementfromRS(resultSet));
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
	public boolean createReimbursement(Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO project_1.reimbursements VALUES (DEFAULT, ?, ?, ?) WHERE employee_id = ?;";
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, r.getTitle());
			stmt.setDouble(2, r.getAmount_requested());
			stmt.setString(3, r.getPicture());
			stmt.setInt(4, r.getEmployeeid());
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

	private Reimbursement createReimbursementfromRS(ResultSet resultset) throws SQLException {
		return new Reimbursement(resultset.getInt("id"),
				resultset.getInt("employee_id"),
				resultset.getString("title"),
				resultset.getDouble("amountrequested"),
				resultset.getDate("daterequested"),
				resultset.getString("status"),
				resultset.getString("resolved_status"),
				resultset.getString("picture"));
	}

	@Override
	public boolean requestAccepted(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String query = "UPDATE project_1.reimbursements SET status='Approved', resolved_status='Resolved' WHERE id = ?;";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
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

	@Override
	public boolean requestDenied(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String query = "UPDATE project_1.reimbursements SET status='Denied', resolved_status='Resolved' WHERE id = ?;";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
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
