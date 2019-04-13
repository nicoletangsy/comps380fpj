<!DOCTYPE html>
<html>
    <head>
        <title>Vote History</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>Vote History</h1>
        <h2>Your Voting Records</h2>
        <c:choose>
            <c:when test="${fn:length(response) == 0}">
                <i>There are no voting records in the system.</i><br>
            </c:when>
            <c:otherwise>
                <table>
                    <tr><th>Question</th><th>Response</th></tr>
                            <c:forEach items="${response}" var="res">
                        <tr><td>${res.poll.question}</td><td>
                                <c:if test="${res.response == '1'}">${res.poll.option1}</c:if>
                                <c:if test="${res.response == '2'}">${res.poll.option2}</c:if>
                                <c:if test="${res.response == '3'}">${res.poll.option3}</c:if>
                                <c:if test="${res.response == '4'}">${res.poll.option4}</c:if>
                            </td></tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>