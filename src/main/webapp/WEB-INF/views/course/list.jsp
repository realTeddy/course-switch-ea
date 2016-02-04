<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pending Switches</title>        
        <jsp:include page="/resources/includes/headtag.jsp" />
    </head>
    <body>
        <jsp:include page="/resources/includes/header.jsp" />

        <section id="services">
            <div class="container">
                <div class="row">
                    <form:form method="POST" > 
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Title</th>
                                    <th>Class Capacity</th>
                                    <th>Description</th>
                                    <th>Instructor</th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach items="${course}" var="course">
                                    <tr>
                                        <td>${course.code}</td>
                                        <td>${course.title}</td>
                                        <td>${course.classCapacity}</td>
                                        <td>${course.description}</td>
                                        <td>${course.instructor}</td>
                                        <td><a class="btn btn-default" href="course/${course.id}">edit</a></td>

                                        <td><a class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this?')" href="delete/${course.id}">delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form:form>

                </div>
            </div>
        </section>
        <jsp:include page="/resources/includes/footer.jsp" />
    </body>
</html>
