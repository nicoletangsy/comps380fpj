<!DOCTYPE html>
<html>
    <head>
        <title>Create a Poll</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Create a Poll</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="Poll">
            <form:label path="question">Question</form:label><br/>
            <form:input type="text" path="question" /><br/><br/>
            <form:label path="option1">Option1</form:label><br/>
            <form:input type="text" path="option1" /><br/><br/>
            <form:label path="option2">Option2</form:label><br/>
            <form:input type="text" path="option2" /><br/><br/>
            <form:label path="option3">Option3</form:label><br/>
            <form:input type="text" path="option3" /><br/><br/>
            <form:label path="option4">Option4</form:label><br/>
            <form:input type="text" path="option4" /><br/><br/>
            <br />
            <input type="submit" value="Add Poll"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>