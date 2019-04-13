<!DOCTYPE html>
<html>
    <head>
        <title>Edit User Account</title>
    </head>
    <body>
        <security:authorize access="hasRole('USER')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>

        <h2>User Information</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="Users">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" value="${user.username}"/><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" value="${user.password}"/><br/><br/>
            Lecturer<form:checkbox  path ="roles" value="ROLE_ADMIN"/>
            <form:input type="hidden" path ="roles" value="ROLE_USER"/>
            <br /><br />
            <input type="submit" value="Edit"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>
