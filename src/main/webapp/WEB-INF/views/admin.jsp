<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Administrator</title>
        <jsp:include page="/resources/includes/headtag.jsp" />
    </head>
    <body>
        <jsp:include page="/resources/includes/header.jsp" />
        <section>
            <div class="container">
                <div class="row">
                    <table class="table table-hover">
                        <thead>
                        <td>From</td>
                        <td>To</td>
                        <td>Action</td>
                        </thead>
                        <tbody>
                        <td>${pendingSwitch.fromRegistration.course}</td>
                        <td>${pendingSwitch.toCourse}</td>
                        <td>
                            <form>
                                <input type="hidden" value="${pendingSwitch.id}"
                                       <input type="submit" value="reject" name="cmd" class="btn btn-success" value="Approve" /> &nbsp;
                                <input type="submit" value="reject" name="cmd" class="btn btn-danger" value="Reject" />
                            </form>
                        </td>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <jsp:include page="/resources/includes/footer.jsp" />
    </body>
</html>
