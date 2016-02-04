<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>From</td>
                                <td>To</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pendingSwitches}" var="pendingSwitch">
                                <tr>
                                    <td>${pendingSwitch.fromRegistration.course}</td>
                                    <td>${pendingSwitch.toCourse}</td>
                                    <td>
                                        <form method="POST">
                                            <input type="hidden" name="pendingSwitchId" value="${pendingSwitch.id}" />
                                            <input class="btn btn-success" type="submit" value="Approve" name="cmd" />
                                            <input class="btn btn-danger" type="submit" value="Reject" name="cmd" />
                                        </form>
                                    </td>
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
