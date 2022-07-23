<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.chainsys.jspproject.commonutil.*,
	com.chainsys.jspproject.*,java.text.SimpleDateFormat,
	java.text.ParseException,com.chainsys.jspproject.pojo.Employee,com.chainsys.jspproject.dao.EmployeeDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new employee</title>
</head>
<body>
	<%
	Employee emp = new Employee();
	String source = "AddNewEmployee";
	String message = "<h1>Error while " + source + "</h1>";
	try {
		String id = request.getParameter("id");
		try {
			Validator.checkStringForParseInt(id);
		} catch (InvalidInputDataException err) {
			message += " Error in Employee id input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
			return;
		}
		int empId = Integer.parseInt(id);
		try {
			Validator.checkNumberForGreaterThanZero(empId);
		} catch (InvalidInputDataException err) {
			message += " Error in Employee id input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
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
			%><h1><%=errorPage%></h1><%
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
			%><h1><%=errorPage%></h1><%
			return;
		}
		emp.setLast_name(lName);

		String eMail = request.getParameter("eMail");
		try {
			Validator.checkEmail(eMail);
		} catch (InvalidInputDataException err) {
			message += " Error in email input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
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
			%><h1><%=errorPage%></h1><%
			return;
		}

		String jobId = request.getParameter("jobId");
		try {
			Validator.checkJobId(jobId);
		} catch (InvalidInputDataException err) {
			message += " Error in Job Id input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
			return;
		}
		emp.setJob_id(jobId);

		String salary = request.getParameter("salary");
		try {
			Validator.checkStringForParseInt(salary);
		} catch (InvalidInputDataException err) {
			message += " Error in Salary input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
			return;
		}
		float salaryFloat = Float.parseFloat(salary);
		try {
			Validator.checkNumberForGreaterThanZero(salaryFloat);
		} catch (InvalidInputDataException err) {
			message += " Error in Salary input </p>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			%><h1><%=errorPage%></h1><%
			return;
		}
		emp.setSalary(salaryFloat);
		int result = EmployeeDao.insertEmployee(emp);
		%><h1><%=result%> row inserted</h1><%
	} catch (Exception e) {
		message += " Error while inserting record </p>";
		String errorPage = ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
	}
	%>
</body>
</html>