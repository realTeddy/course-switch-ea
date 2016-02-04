<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cars currently in the shop</title>
</head>
<body>
	<h1>Prerequisites for the course </h1>
	<table>
	<c:forEach var="course" items="${courses.prerequisites}">
	<tr>
		<td>${course.id}</td>
                <td>${course.code}</td>
		<td>${course.title}</td>
                <td>${course.description}</td>
		<td>${course.instructor}</td>
			
	</tr>
	</c:forEach>
	</table>
	
	
</body>
</html>