<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.techouts.employee.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PrintDetails</title>
</head>
<body>
	<% Employee emp=(Employee) request.getAttribute("employee");%>
	<form action="updateEmployee" method="post">
		<input type="hidden" name="id" value=<%=emp.getId()%>> <br>
		Name:<input type="text" name="name" value=<%=emp.getName() %>><br>
		Salary:<input type="number" name="salary" value=<%=emp.getSalary()%>><br>
		<button type="submit">Save</button>
	</form>
</body>
</html>