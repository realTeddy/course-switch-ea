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

                    <form:form modelAttribute="course" method="POST" >
                        <table class="table table-hover">
                            <tr>
                                <td>Code</td>
                                <td><input class="form-control" type="text" name="code" value="${course.code}" /></td>
                            </tr>
                            <tr>
                                <td>Title</td>
                                <td><input class="form-control" type="text" name="title" value="${course.title}"/> </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><input class="form-control" type="text" name ="description" value="${course.description}"/></td>
                            </tr>
                            <tr>
                                <td>Class Capacity</td>
                                <td><input class="form-control" type="text" name = "classCapacity" value="${course.classCapacity}"/></td>
                            </tr>
                            <tr>
                                <td>Instructor</td>
                                <td><input class="form-control" type="text" name ="instructor" value="${course.instructor}"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input class="btn btn-success" type="submit" value="Save"/></td>
                            </tr>        
                        </table>


                    </form:form>
                    <form action="<c:url value='/course/addPrerequisites' />" method="POST">
                        <table class="table table-hover">
                            <tr>  
                                <td>Prerequisite: </td>
                                <td>  
                                    <select class="form-control" name="prerequisiteId">
                                        <c:forEach var="course" items="${allCourses}">
                                            <option value="${course.id}">${course.title}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="courseId" value="${course.id}" />
                                </td>  
                                <td>  
                                    <input class="btn btn-success" type="submit" value="Add"/>  
                                </td>  
                            </tr>  

                        </table>
                    </form>
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
                            <c:forEach var="prerequisite" items="${course.prerequisites}">
                                <tr>
                                    <td>${prerequisite.code}</td>
                                    <td>${prerequisite.title}</td>
                                    <td>${prerequisite.classCapacity}</td>
                                    <td>${prerequisite.description}</td>
                                    <td>${prerequisite.instructor}</td>
                                    <td><a class="btn btn-danger" href="delete?prerequisiteId=${prerequisite.id}&courseId=${course.id}">delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody> 

                    </table>
                </div>
            </div>
        </section>
        <jsp:include page="/resources/includes/footer.jsp" />
    </body>
</html>
