package com.revature.model;

public class Employee {

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String emp_username;
	private String emp_password;
	private String emp_type;

	public Employee(int id, String first_name, String last_name, String email, String emp_username, String emp_password,
			String emp_type) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.emp_username = emp_username;
		this.emp_password = emp_password;
		this.emp_type = emp_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String firstname) {
		this.first_name = firstname;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmp_username() {
		return emp_username;
	}

	public void setEmp_username(String emp_username) {
		this.emp_username = emp_username;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getEmp_type() {
		return emp_type;
	}

	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}

	public static void employeeInformation() {

		System.out.println("Employee Information");

	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + first_name + ", lastname=" + last_name + ", email=" + email
				+ ", username=" + emp_username + ", userpassword=" + emp_password + ", type=" + emp_type + "]";
	}

}
