<!DOCTYPE html>
<html>
    <head>
        <title>Create a User Account</title>
    </head>
    <body>
        <security:authorize access="hasRole('USER')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        
        <h2>Create a User Account</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="Users"
                   >
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <security:authorize access="hasRole('ADMIN')">
                Lecturer<form:checkbox  path ="roles" value="ROLE_ADMIN"/>
            </security:authorize>
            <form:input type="hidden" path ="roles" value="ROLE_USER"/>
            <security:authorize access="hasRole('ADMIN')">
                <form:input type="hidden" path ="Admin" value="admin"/>
            </security:authorize>
            <br /><br />
            <input type="submit" value="Add User" />
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>