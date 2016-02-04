<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form:form  class="navbar-form navbar-right" action="j_spring_security_check">
                <div class="form-group">
                    <input type="email" value="${user.email}" class="form-control" pt:placeholder="username" />
                </div>
                <div class="form-group">
                    <input type="password" value="${user.password}" class="form-control" placeholder="password" />
                </div>
                <messages class="text-danger" />
                <input type="submit" class="btn btn-warning" value="Login"/>
            </form:form>
            <form:form class="navbar-form navbar-right" action="/logout">
                <input type="submit" class="btn btn-warning" value="Logout" action="${user.logout()}"/>
            </form:form>

            <ul class="nav navbar-nav navbar-right" style="display:${user.isLoggedIn ? 'none' : 'default'}">
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li>
                    <a href="<c:url value='/login' />" class="page-scroll">Register</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>