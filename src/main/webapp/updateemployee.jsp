<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update employee</title>
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
			<div>
				HireDate: <input type='text' name='emp_HireDate'><br>
			</div>
			<div>
				JobID: <input type='text' name='jobId'><br>
			</div>
			<div>
				Salary: <input type='text' name='salary'><br>
			</div>
			<div>
				<input type='submit' name='submit' value='update'>
			</div>
        </center>
        </form>
</body>
</html>