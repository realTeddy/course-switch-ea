<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <jsp:include page="/resources/includes/headtag.jsp" />
    </head>
    <body id="page-top" class="index">
        <jsp:include page="/resources/includes/header.jsp" />
        <!-- Header -->
        <header>
            <div class="container">
                <div class="intro-text">
                    <div class="intro-lead-in">Welcome!</div>
                    <div class="intro-heading">It&apos;s Nice To Meet You</div>
                </div>
            </div>
        </header>

        <!-- Services Section -->
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">The developers</h2>
                        <h3 class="section-subheading text-muted">Meet the developers</h3>
                    </div>
                </div>
                <div class="row text-center">
                    <img class="img img-responsive" src="<c:url value='/resources/img/team.png' />" alt=""/>
                </div>
            </div>
        </section>
        <jsp:include page="/resources/includes/footer.jsp" />
    </body>
</html>