<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:pt="http://xmlns.jcp.org/jsf/passthrough">
    <h:head>
        <title>TODO supply a title</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </h:head>
    <h:body>
        <ui:composition template="/templates/master.xhtml">
            <ui:define name="header">
                <ui:include src="/sections/common/header.xhtml"/>
            </ui:define>
            <ui:define name="content">

                <!-- Contact Section -->
                <section id="contact">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 text-center">
                                <h2 class="section-heading">Register</h2>
                                <h3 class="section-subheading text-muted">Register into the system</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <h:form id="contactForm" pt:novalidate="">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <div class="form-group">
                                                <h:inputText value="#{userBean.firstName}" pt:placeholder="First Name *" class="form-control" required="true" pt:data-validation-required-message="Please enter your first name." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <h:inputText value="#{userBean.lastName}" pt:placeholder="Last Name *" class="form-control" required="true" pt:data-validation-required-message="Please enter your last name." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <h:inputText value="#{userBean.email}" pt:placeholder="Email *" class="form-control" required="true" pt:data-validation-required-message="Please enter your email address." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <h:inputText value="#{userBean.id}" pt:placeholder="ID Number *" class="form-control" required="true" pt:data-validation-required-message="Please enter your ID number." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <h:inputSecret value="#{userBean.password}" pt:placeholder="Password *" class="form-control" required="true" pt:data-validation-required-message="Please enter your password." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <h:inputSecret value="#{userBean.confirmPassword}" pt:placeholder="Confirm Password *" class="form-control" required="true" pt:data-validation-required-message="Please confirm your password." />
                                                <p class="help-block text-danger"></p>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="col-lg-12 text-center">
                                            <div id="success"></div>
                                            <h:commandButton value="Register" class="btn btn-xl" action="#{userBean.register()}" />
                                        </div>
                                    </div>
                                </h:form>
                            </div>
                        </div>
                    </div>
                </section>

            </ui:define>
        </ui:composition>
    </h:body>
</html>
