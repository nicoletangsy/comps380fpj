<!DOCTYPE html>
<html>
    <head>
        <title>Online Course Website</title>
    </head>
    <body>

        <a href="<c:url value="/login"/>" 
           <security:authorize access="hasRole('USER')">
               hidden="true"
           </security:authorize> >Login</a>
        <security:authorize access="hasRole('USER')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        <h1>COMP S380F Web Applications: Design and Development</h1>
        <h2>Online Course Website</h2><br/>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
            <a href="<c:url value="/lecture/create" />">Create a Lecture</a><br /><br />
            <a href="<c:url value="/poll/create" />">Create a poll</a><br /><br/>
        </security:authorize>
        <h2>Lectures:</h2>
        <c:choose>
            <c:when test="${fn:length(lecture) == 0}">
                <i>There are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                <ol>
                    <c:forEach items="${lecture}" var="lecture">
                        <li>
                            <a href="<c:url value="/lecture/view/${lecture.id}" />">
                                <c:out value="${lecture.title}" /></a>
                                <security:authorize access="hasRole('ADMIN')">
                                [<a href="<c:url value="/lecture/delete/${lecture.id}" />">Delete</a>]
                            </security:authorize>
                        </li>
                    </c:forEach>
                </ol>
            </c:otherwise>
        </c:choose><br/>
        <h2>Polls:</h2>
        <c:choose>
            <c:when test="${fn:length(poll) == 0}">
                <i>There are no polls in the system.</i><br/>
            </c:when>
            <c:otherwise>
                <security:authorize access="hasRole('USER')">
                    [<a href="<c:url value="/user/vote" />">Vote History</a>]<br><br>
                </security:authorize>
                <ol>
                    <c:forEach items="${poll}" var="poll">
                        <li>
                            <a href="<c:url value="/poll/view/${poll.id}" />">
                                <c:out value="${poll.question}" /></a>
                                <security:authorize access="hasRole('ADMIN')">
                                [<a href="<c:url value="/poll/delete/${poll.id}" />">Delete</a>]
                            </security:authorize>
                        </li>
                    </c:forEach>
                </ol>
            </c:otherwise>
        </c:choose>
    </body>
</html>