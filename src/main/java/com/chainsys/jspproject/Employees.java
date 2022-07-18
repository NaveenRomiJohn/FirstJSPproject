package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nave3121
 * created on 21 May 2022
 */
/**
 * Servlet implementation class Employees
 */
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employees() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "GettingAllEmployeeDetails";
		String message = "<h1>Error while " + source + "</h1>";
		try {
			List<Employee> allEmployees = EmployeeDao.getAllEmployee();
			Iterator<Employee> empIterator = allEmployees.iterator();
			if (request.getParameter("input").equals("yes")) {
				response.setContentType("text/html");
				out.print("<html><head><title><Employees</title></head><body>");
				out.print("<table border=1px bgcolor=\"Tomato\" width=50%>");
				out.print("<tr bgcolor=\"Tomato\" align=center>");
				out.print("<th height=\"10\" width=\"90\">Employee_ID:</th>");
				out.print("<th height=\"10\" width=\"90\">First_Name:</th>");
				out.print("<th height=\"10\" width=\"90\">Last_Name:</th>");
				out.print("<th height=\"10\" width=\"90\">Job_ID:</th>");
				out.print("<th height=\"10\" width=\"90\">Hire_Date:</th>");
				out.print("<th height=\"10\" width=\"90\">Salary:</th>");
				while (empIterator.hasNext()) {
					out.print("<tr align=center>");
					Employee emp = empIterator.next();
					out.print("<td bgcolor=\"LightGray\">" + emp.getEmp_id() + "</td>");
					out.print("<td bgcolor=\"LightGray\">" + emp.getFirst_name() + "</td>");
					out.print("<td bgcolor=\"LightGray\">" + emp.getLast_name() + "</td>");
					out.print("<td bgcolor=\"LightGray\">" + emp.getJob_id() + "</td>");
					out.print("<td bgcolor=\"LightGray\">" + emp.getHire_date() + "</td>");
					out.print("<td bgcolor=\"LightGray\">" + emp.getSalary() + "</td>");
					out.print("</tr>");
				}
			} else if (request.getParameter("input").equals("no")) {
				out.println(" <br><h3>thank you for processing with us</h3>");
			}
			out.print("</body></html>");
		} catch (Exception e) {
			message += " Error while getting all records </p>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			message += "Message: " + e.getMessage();
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit").equals("Add")) {
			String source = "AddNewEmployee";
			String message = "<h1>Error while " + source + "</h1>";
			PrintWriter out = response.getWriter();
			Employee newemp = new Employee();
			int result = 0;
			try {
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParseInt(id);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				int empId = Integer.parseInt(id);
				try {
					Validator.checkNumberForGreaterThanZero(empId);
				} catch (InvalidInputDataException err) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setEmp_id(empId);

				String fName = request.getParameter("fName");
				try {
					Validator.checkStringOnly(fName);
					Validator.checkCharLessThanTwenty(fName);
				} catch (InvalidInputDataException err) {
					message += " Error in First Name input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setFirst_name(fName);

				String lName = request.getParameter("lName");
				try {
					Validator.checkStringOnly(lName);
					Validator.checkCharLessThanTwenty(lName);
				} catch (InvalidInputDataException err) {
					message += " Error in Last Name input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setLast_name(lName);

				String eMail = request.getParameter("eMail");
				try {
					Validator.checkEmail(eMail);
				} catch (InvalidInputDataException err) {
					message += " Error in email input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setEmail(eMail);

				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String emp_HireDate = request.getParameter("emp_HireDate");
				try {
					newemp.setHire_date(hire_dateFormate.parse(emp_HireDate));
				} catch (ParseException e) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}

				String jobId = request.getParameter("jobId");
				try {
					Validator.checkJobId(jobId);
				} catch (InvalidInputDataException err) {
					message += " Error in Job Id input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setJob_id(jobId);

				String salary = request.getParameter("salary");
				try {
					Validator.checkStringForParseInt(salary);
				} catch (InvalidInputDataException err) {
					message += " Error in Salary input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				float salaryFloat = Float.parseFloat(salary);
				try {
					Validator.checkNumberForGreaterThanZero(salaryFloat);
				} catch (InvalidInputDataException err) {
					message += " Error in Salary input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					return;
				}
				newemp.setSalary(salaryFloat);
				result = EmployeeDao.insertEmployee(newemp);
				out.println("<div> Add New Employee: " + result + " row inserted </div>");
			} catch (Exception e) {
				message += " Error while inserting record </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			try {
				out.close();
			} catch (Exception e) {
				message += "Message: " + e.getMessage();
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
		} else if (request.getParameter("submit").equals("update")) {
			doPut(request, response);
		} else if (request.getParameter("submit").equals("Delete")) {
			doDelete(request, response);
		} else if (request.getParameter("submit").equals("GetByID")) {
			doHead(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "UpdateEmployee";
		String message = "<h1>Error while " + source + "</h1>";
		Employee newemp = new Employee();
		int result = 0;
		try {
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParseInt(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.checkNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setEmp_id(empId);

			String fName = request.getParameter("fName");
			try {
				Validator.checkStringOnly(fName);
				Validator.checkCharLessThanTwenty(fName);
			} catch (InvalidInputDataException err) {
				message += " Error in First Name input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setFirst_name(fName);

			String lName = request.getParameter("lName");
			try {
				Validator.checkStringOnly(lName);
				Validator.checkCharLessThanTwenty(lName);
			} catch (InvalidInputDataException err) {
				message += " Error in Last Name input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setLast_name(lName);

			String eMail = request.getParameter("eMail");
			try {
				Validator.checkEmail(eMail);
			} catch (InvalidInputDataException err) {
				message += " Error in Email input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setEmail(eMail);

			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("emp_HireDate");
			try {
				newemp.setHire_date(hire_dateFormate.parse(emp_HireDate));
			} catch (ParseException e) {
				message += " Error in Hire Date input </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}

			String jobId = request.getParameter("jobId");
			try {
				Validator.checkJobId(jobId);
			} catch (InvalidInputDataException err) {
				message += " Error in Job ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setJob_id(jobId);

			String salary = request.getParameter("salary");
			try {
				Validator.checkStringForParseInt(salary);
			} catch (InvalidInputDataException err) {
				message += " Error in Salary input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			float salaryFloat = Float.parseFloat(salary);
			try {
				Validator.checkNumberForGreaterThanZero(salaryFloat);
			} catch (InvalidInputDataException err) {
				message += " Error in Salary input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newemp.setSalary(salaryFloat);
			result = EmployeeDao.updateEmployee(newemp);
			out.println("<div> Update Employee: " + result + " row updated </div>");
		} catch (Exception e) {
			message += " Error while updating record </p>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			message += "Message: " + e.getMessage();
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
	}

	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "GetEmployeeByID";
		String message = "<h1>Error while " + source + "</h1>";
		Employee emp = new Employee();
		try {
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParseInt(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.checkNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			emp.setEmp_id(empId);
			Employee result = EmployeeDao.getEmployeeById(empId);
			out.println("Employee ID : " + result.getEmp_id() + "<br>");
			out.println("First Name : " + result.getFirst_name() + "<br>");
			out.println("Last Name : " + result.getLast_name() + "<br>");
			out.println("Job ID : " + result.getJob_id() + "<br>");
			out.println("Email : " + result.getEmail() + "<br>");
			out.println("Salary : " + result.getSalary() + "<br>");
		} catch (Exception e) {
			message += " Error while getting the record by ID </p>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			message += "Message: " + e.getMessage();
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "DeleteEmployeeByID";
		String message = "<h1>Error while " + source + "</h1>";
		try {
			String id = request.getParameter("id");
			try {
				Validator.checkStringForParseInt(id);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.checkNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee ID input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int result = EmployeeDao.deleteEmployee(empId);
			out.println("Delete Status: "+result + " row deleted");
		} catch (Exception e) {
			message += " Error while deleting the record by ID </p>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			message += "Message: " + e.getMessage();
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
	}
}