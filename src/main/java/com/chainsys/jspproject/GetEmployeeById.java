package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAllEmployee
 */
@WebServlet("/GetEmployeeById")
public class GetEmployeeById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEmployeeById() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String drivername = "oracle.jdbc.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "oracle";
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
//		String source = "GetEmployeeByID";
//		String message = "<h1>Error while " + source + "</h1>";
		Employee emp = new Employee();
		String id = request.getParameter("id");
		try {
			Validator.checkStringForParseInt(id);
		} catch (InvalidInputDataException err) {
//				message += " Error in Employee ID input </p>";
//				String errorPage = ExceptionManager.handleException(err, source, message);
//				out.print(errorPage);
			out.print(err.getMessage());
			return;
		}
		int empId = Integer.parseInt(id);
		try {
			Validator.checkNumberForGreaterThanZero(empId);
		} catch (InvalidInputDataException err) {
//				message += " Error in Employee ID input </p>";
//				String errorPage = ExceptionManager.handleException(err, source, message);
//				out.print(errorPage);
			out.print(err.getMessage());
			return;
		}
		emp.setEmp_id(empId);
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		String selectquery = "select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,HIRE_DATE,JOB_ID,SALARY  from Employees ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(selectquery);
			rs = ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setEmp_id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));
				emp.setEmail(rs.getString(4));
				java.util.Date date = new java.util.Date(rs.getDate(5).getTime());
				emp.setHire_date(date);
				emp.setJob_id(rs.getString(6));
				emp.setSalary(rs.getFloat(7));
				listOfEmployees.add(emp);
				int i = ps.executeUpdate();
				String msg = " ";
				if (i != 0) {
					msg = "Record has been inserted";
					out.println("<font size='6' color=blue>" + msg + "</font>");
				} else {
					msg = "Failed to insert the data";
					out.println("<font size='6' color=blue>" + msg + "</font>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
