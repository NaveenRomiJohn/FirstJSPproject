<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd" >
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.chainsys.jspproject.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new emp</title>
</head>
<body>
	<form action="/FirstJSPproject/Employees" method="post">
		<center>
			<div>
				ID: <input type='text' name='id'><br>
			</div>
			<div>
				FirstName: <input type='text' name='fName'><br>
			</div>
			<div>
				LastName: <input type='text' name='lName'><br>
			</div>
			<div>
				EMail: <input type='text' name='eMail'><br>
			</div>
			<!-- <div>
				HireDate: <input type='text' name='emp_HireDate'><br>
			</div> -->
			<div>
				JobID: <input type='text' name='jobId'><br>
			</div>
			<div>
				Salary: <input type='text' name='salary'><br>
			</div>
			<div>
				<input type='submit' name='submit' value='Add'>
			</div>
		</center>
	</form>
</body>
</html>