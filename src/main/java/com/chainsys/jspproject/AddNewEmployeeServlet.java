package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class AddNewEmployeeServlet
 */
@WebServlet("/AddNewEmployeeServlet")
public class AddNewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp = new Employee();
		PrintWriter out = response.getWriter();
		String source = "AddNewEmployee";
		String message = "<h1>Error while " + source + "</h1>";
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
			emp.setEmp_id(empId);

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
			emp.setFirst_name(fName);

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
			emp.setLast_name(lName);

			String eMail = request.getParameter("eMail");
			try {
				Validator.checkEmail(eMail);
			} catch (InvalidInputDataException err) {
				message += " Error in email input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			emp.setEmail(eMail);

			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("emp_HireDate");
			try {
				emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
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
			emp.setJob_id(jobId);

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
			emp.setSalary(salaryFloat);
			result = EmployeeDao.insertEmployee(emp);
		} catch (Exception e) {
			message += " Error while inserting record </p>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
		}
		request.setAttribute("addemp", result);
		RequestDispatcher rd = request.getRequestDispatcher("/addemployee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
