<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:pt="http://xmlns.jcp.org/jsf/passthrough"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <title>IGNORED</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </h:head>
    <body>

        <f:view>
            <f:event type="preRenderView" listener="#{userBean.checkAdminLogin}"/> 

            <ui:composition template="/templates/master.xhtml">
                <ui:define name="header">
                    <ui:include src="/sections/common/header.xhtml"/>
                </ui:define>
                <ui:define name="content">
                    <section >
                        <div class="container" >
                            <div class="row">
                                <p:dataTable class="table table-hover" var="pendingSwitch" value="#{adminBean.pendingSwitches}">
                                    <p:column headerText="From">
                                        <h:outputText value="#{pendingSwitch.fromRegistration.course}" />
                                    </p:column>

                                    <p:column headerText="To">
                                        <h:outputText value="#{pendingSwitch.toCourse}" />
                                    </p:column>

                                    <p:column headerText="Action">
                                        <h:form>
                                            <h:commandButton class="btn btn-success" action="#{adminBean.approve(pendingSwitch)}" value="Approve" /> #{" "}
                                            <h:commandButton class="btn btn-danger" action="#{adminBean.reject(pendingSwitch)}" value="Reject" />
                                        </h:form>
                                    </p:column>
                                </p:dataTable>
                            </div>
                        </div>
                    </section>
                </ui:define>
            </ui:composition>

        </f:view>
    </body>
</html>
