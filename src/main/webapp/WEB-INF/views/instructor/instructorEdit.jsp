<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit a Course</title>
</head>
<body>
	<form action="../${course.id}" method="post">
	<table>
		<tr>
			<td>Code:</td>
			<td><input type="text" name="code" value="${course.code}" /> </td>
		</tr>
		<tr>
			<td>Title:</td>
			<td><input type="text" name="title" value="${course.title}" /> </td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><input type="text" name="description" value="${course.description}" /> </td>
		</tr>
		<tr>
			<td>Instructor:</td>
			<td><input type="text" name="color" value="${course.instructor}" /> </td>
		</tr>
	</table>
	<input type="submit" value="update"/>
	</form>
	<form action="delete?courseId=${course.id}" method="post">
		<button type="submit">Delete</button>
	</form>
</body>
</html>