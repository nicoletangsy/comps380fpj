<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <c:if test="${param.error != null}">
            <p>Login failed.</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>You have logged out.</p>
        </c:if>
        <h2>Login</h2>
        <form action="login" method="POST">
            <label for="username">Username: </label><br/>
            <input type="text" id="username" name="username" /><br/><br/>
            <label for="password">Password: </label><br/>
            <input type="password" id="password" name="password" /><br/><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Log In"/>
        </form><br/><br/>
        <a href="<c:url value="/user/create" />">Sign Up</a><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>