<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.chainsys.jspproject.commonutil.*,
	com.chainsys.jspproject.*,java.io.PrintWriter,
	com.chainsys.jspproject.dao.EmployeeDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String source = "DeleteEmployee";
	String message = "<h1>Error while " + source + "</h1>";
	String emp_id = request.getParameter("id");
	try {
		Validator.checkStringForParseInt(emp_id);
	} catch (InvalidInputDataException e) {
		message += " Error in Employee id input </p>";
		String errorPage = ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1>
	<%
	}
	int id = Integer.parseInt(emp_id);
	int result = EmployeeDao.deleteEmployee(id);
	%><h1><%=result%> row deleted </h1>
	<%
	try {
		out.close();
	} catch (Exception e) {
		message += " Error in Employee id input </p>";
		String errorPage = ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1>
	<%
	}
	%>
</body>
</html>