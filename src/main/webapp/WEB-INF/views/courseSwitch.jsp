<?xml version="1.0" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="app">
    <head>
        <title>Course Registration Manager</title>
        <jsp:include page="/resources/includes/headtag.jsp" />
    </head>
    <body>
        <jsp:include page="/resources/includes/header.jsp" />
        <section  ng-controller="MainController as vm">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Welcome</h2>
                        <h3 class="section-subheading text-muted">Here you can see courses you are enrolled in and choose your preferences
                            <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#addBlockModal">
                                Add block course
                            </button></h3>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-12">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default" ng-repeat="registration in registrations">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse{{registration.id}}" aria-expanded="false" aria-controls="collapse{{registration.id}}">
                                            {{registration.block.startDate.month + '/' + registration.block.startDate.day + '/' + registration.block.startDate.year}} - {{registration.course.code}} - {{registration.course.title}}
                                        </a>
                                        <input ng-click="vm.showNewPreferencesModal(registration)" type="button" class="btn btn-sm btn-success pull-right" value="Add preferece" />
                                    </h4>
                                </div>
                                <div id="collapse{{registration.id}}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <li class="list-group-item" ng-repeat="preferedCourse in registration.preferedCourses">
                                                {{preferedCourse.course.title}}
                                                <span class="label label-{{preferedCourse.isPendingApproval ? 'success' : 'warning'}}">{{preferedCourse.isPendingApproval ? "Pending Approval" : "Unavailable"}}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="addPreferrenceModal" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Select a prefered course</h4>
                        </div>
                        <div class="modal-body">
                            <ul class="list-group">
                                <li class="list-group-item" ng-repeat="course in blockCourses">
                                    {{course.course.title + " (" + course.course.code + ")"}} 
                                    <span class="label label-{{course.isAvailable ? 'success' : 'warning'}}">{{course.isAvailable ? "Available" : "Waiting List"}}</span>
                                    <input type="button" value="Add" ng-click="vm.addPreferedCourse(course)" class="btn btn-sm btn-default pull-right" />
                                </li>
                            </ul>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <div class="modal fade" id="alertModal" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Information</h4>
                        </div>
                        <div class="modal-body">
                            {{information}}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <div class="modal fade" id="addBlockModal" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Registration</h4>
                        </div>
                        <div class="modal-body">
                            <h5>Please add a course you are already enrolled in</h5>
                            <form id="contactForm" pt:novalidate="">
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-3">
                                        <div class="form-group">
                                            <select class="form-control" >
                                                <option>SELECT BLOCK</option>
                                                <option>Jan, 2016</option>
                                                <option>Feb, 2016</option>
                                                <option>Mar, 2016</option>
                                                <option>Apr, 2016</option>
                                                <option>May, 2016</option>
                                            </select>
                                            <p class="help-block text-danger"></p>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control" >
                                                <option>SELECT COURSE</option>
                                                <option>CS 430: Business Intelligence and Data Mining</option>
                                                <option>CS 456 Software Testing</option>
                                                <option>CS 465 Operating Systems</option>
                                                <option>CS 466 Computer Security</option>
                                                <option>CS 467 Secure Coding Practices</option>
                                            </select>
                                            <p class="help-block text-danger"></p>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" data-dismiss="modal">Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </section>                    
        <jsp:include page="/resources/includes/footer.jsp" />
    </body>
</html>
