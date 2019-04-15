<!DOCTYPE html>
<html>
    <head>
        <title>Add Attachments</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Add Attachments</h2><br/>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="lecture">
            Title: ${lecture.title}<br/><br/>
            <b>Lecture Notes Attachment(s): </b><br>
            <input type="file" name="files" multiple="multiple"/><br/>
            <br/>
            <b>Tutorial Notes Attachment(s): </b><br>
            <input type="file" name="files2" multiple="multiple"/><br/>
            <br/>
            <input type="submit" value="Create"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>
