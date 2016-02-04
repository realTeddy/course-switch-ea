<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand page-scroll" href="#page-top">CSP - Course Switch and Placement</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <%--<sec:authorize access="isAnonymous()">--%>
            <form  class="navbar-form navbar-right" action="j_spring_security_check" method="POST">
                <div class="form-group">
                    <input name="username" type="text" value="${user.email}" class="form-control" pt:placeholder="username" />
                </div>
                <div class="form-group">
                    <input name="password" type="password" value="${user.password}" class="form-control" placeholder="password" />
                </div>
                <messages class="text-danger" />
                <input type="submit" class="btn btn-warning" value="Login"/>
            </form>
            <%--</sec:authorize>--%>
            <%--<sec:authorize access="isAuthenticated()">--%>
            <form class="navbar-form navbar-right" action="<c:url value='/j_spring_security_logout' />" method="POST">
                <input type="submit" class="btn btn-warning" value="Logout" action="${user.logout()}"/>
            </form>
            <%--</sec:authorize>--%>
            <ul class="nav navbar-nav navbar-right">                
                <%--<sec:authorize url="/admin/pendingSwitches">--%>
                <li>
                    <a href="<c:url value='/admin/pendingSwitches' />">Pending Switches</a>
                </li>
                <%--</sec:authorize>--%>       
                <%--<sec:authorize url="/courses/list">--%>
                <li>
                    <a href="<c:url value='/course/list' />">Courses</a>
                </li>
                <%--</sec:authorize>--%>
                <%--<sec:authorize access="hasRole('ROLE_USER')">--%>
                <li>
                    <%--<sec:authorize url="/courseSwitch/">--%>
                    <a href="<c:url value='/courseSwitch/' />">Course Switch</a>
                    <%--</sec:authorize>--%>
                </li>
                <%--</sec:authorize>--%>
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <%--<sec:authorize access="isAnonymous()">--%>
                <li>
                    <a href="<c:url value='/login' />" class="page-scroll">Register</a>
                </li>
                <%--</sec:authorize>--%>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>