<!DOCTYPE html>
<html>
    <head>
        <title>Create a Lecture</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Create a Lecture</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="lecture">
            <form:label path="title">Title: </form:label>
            <form:input type="text" path="title" /><br/>
            <b>Attachment(s): </b><br/>
            <input type="file" name="files" multiple="multiple"/><br/>
            <br/>
            <input type="submit" value="Create"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>