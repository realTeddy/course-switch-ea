<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Current courses </title>
</head>
<body>
	<h1>Course list</h1>
	<table>
            <tr>
                <th>Course ID</th>
                <th>Course Code</th>
                <th>Course Title</th>
                <th>Course Description</th>
                <th>Course prerequisites</th>
                <th>Course instructor</th>
                <th>Actions</th>
                
            </tr>
	<c:forEach var="course" items="${courses}">
	<tr>
                <td>${course.id}</td>
		<td>${course.code}</td>
		<td>${course.title}</td>
		<td>${course.description}</td>
                <td>${course.prerequisites}  <a href="prerequisites.jsp">prerequisites</a> </td>
		<td>${course.instructor}</td>
                <td><a href="instructorEdit.jsp"> Edit</a> 
                    <a href="addCourse.html"> Add</a> 
                   
                </td>
	</tr>
	</c:forEach>
	</table>
        
        
        
	
	
</body>
</html>