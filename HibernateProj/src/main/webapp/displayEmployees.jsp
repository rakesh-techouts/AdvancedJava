<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>displayEmployees</title>
</head>
<body>
	<h1>Employees in Techouts</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Salary</th>
    </tr>
    <p:forEach var="e" items="${list}">
        <tr>
            <td>${e.id}</td>
            <td>${e.name}</td>
            <td>${e.salary}</td>
        </tr>
    </p:forEach>
</table>
</body>
</html>