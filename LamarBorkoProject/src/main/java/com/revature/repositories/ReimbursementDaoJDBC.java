package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.servlets.LoginRegister;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class ReimbursementDaoJDBC implements ReimbursementDao {
	
	

	@Override
	public Reimbursement getReimbursement(int employee_id) {
		Reimbursement r = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE employee_id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, employee_id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							r = createReimbursementFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	@Override
	public List<Reimbursement> viewEmployeePending() {
		// Statement and ResultSet (and Connection) interfaces
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		// List to return
		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
		try {
			conn = ConnectionUtil.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(
					"SELECT * FROM project_1.reimbursements WHERE employee_id = " + LoginRegister.currentEmployee.id + ";");

			// loop through ResultSet
			while (resultSet.next()) {
				// At each row in the ResultSet, do the following:
				reimbursement.add(createReimbursementFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close all open resources to prevent memory leak
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
			StreamCloser.close(conn);
		}
		return reimbursement;
	}
	
	@Override
	public boolean createReimbursement(Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO project_1.reimbursements VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?);";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, r.employee_id);
			stmt.setString(2, r.title);
			stmt.setDouble(3, r.amountrequested);
			stmt.setString(4, r.status);
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
	
	
	private Reimbursement createReimbursementFromRS(ResultSet resultSet) throws SQLException {
		return new Reimbursement(
				resultSet.getInt("id"),
				resultSet.getInt("employee_id"),
				resultSet.getString("title"),
				resultSet.getDouble("amountrequested"),
				resultSet.getTimestamp("daterequested"),
				resultSet.getString("status"), 
				resultSet.getString("resolved_status"),
				resultSet.getInt("resolved_by"));
	}

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
	public List<Reimbursement> viewResolved(int employee_id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE employee_id = ? AND resolved_status = 'Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, employee_id);
				if (stmt.execute()) {
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
	public List<Reimbursement> viewPending(String emp_username) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements where emp_username = ? AND resolved_status = 'Pending';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, emp_username);
				if (stmt.execute()) {
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
	public List<Reimbursement> viewResolved(String emp_username) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements where emp_username = ? AND resolved_status='Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, emp_username);
				if (stmt.execute()) {
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
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				if (stmt.execute()) {
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
	public List<Reimbursement> viewAllResolved() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.reimbursements WHERE resolved_status='Resolved';";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				if (stmt.execute()) {
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
	public boolean requestAccepted(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String query = "UPDATE project_1.reimbursements SET status='Resolved', resolved_status='Approved', resolved_by=" + LoginRegister.currentEmployee.id + " WHERE id = ?;";

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

		final String query = "UPDATE project_1.reimbursements SET status='Resolved', resolved_status='Denied', resolved_by=" + LoginRegister.currentEmployee.id + " WHERE id = ?;";

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
