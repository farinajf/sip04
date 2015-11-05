<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="pageTitle"           code="newUserRegistration.pageTitle"/>
<spring:message var="msgAllFieldsRequired" code="newUserRegistration.message.allFieldsRequired"/>

<html>
    <head><title>${pageTitle}</title></head>
    <body>
        <form:form cssClass="main" action="." modelAttribute="account">
            <form:errors path="*">
                <div><spring:message code="error.global"/></div>
            </form:errors>

            <h1>${pageTitle}</h1>
            <div>${msgAllFiledsRequired}</div>
            <div>
                <div>
                    <spring:message code="newUserRegistration.label.username"/>
                    <form:input path="username" cssClass="short" cssErrorClass="short error"/>
                </div>
                <form:errors path="username">
                    <div><form:errors path="username" htmlEscape="false"/></div>
                </form:errors>
            </div>

            <div>
                <div>
                    <spring:message code="newUserRegistration.label.password"/>
                    <form:password path="password"/>
                </div>
                <form:errors path="password">
                    <div><form:errors path="password" htmlEscape="false"/></div>
                </form:errors>
            </div>
            <div>
                <spring:message code="newUserRegistration.label.confirmPassword"/>
                <form:password path="confirmPassword"/>
            </div>
            <div>
                <div>
                    <spring:message code="newUserRegistration.label.email"/>
                    <form:input path="email"/>
                </div>
                <form:errors path="email">
                    <div><form:errors path="email" htmlEscape="false"/></div>
                </form:errors>
            </div>
            <div>
                <spring:message code="newUserRegistration.label.firstName"/>
                <form:input path="firstName"/>
            </div>
            <div>
                <spring:message code="newUserRegistration.label.lastName"/>
                <form:input path="lastName"/>
            </div>
            <div>
                <form:checkbox id="marketingOk" path="marketingOk"/>
                <spring:message code="newUserRegistration.label.marketingOk"/>
            </div>
            <div>
                <div>
                    <form:checkbox id="acceptTerms" path="acceptTerms"/>
                    <spring:message code="newUserRegistration.label.acceptTerms"/>
                </div>
                <form:errors path="acceptTerms">
                    <div><form:errors path="acceptTerms" htmlEscape="false"/></div>
                </form:errors>
            </div>
            <div>
                <input type="submit" value="Register"/>
            </div>
        </form:form>
    </body>
</html>
