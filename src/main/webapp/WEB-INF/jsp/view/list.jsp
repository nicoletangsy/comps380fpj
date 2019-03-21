<!DOCTYPE html>
<html>
    <head>
        <title>Online Course Website</title>
    </head>
    <body>
        <h2>Online Course Website</h2>
        <c:choose>
            <c:when test="${fn:length(courseDatabase) == 0}">
                <i>There are no courses in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${courseDatabase}" var="entry">
                    Course ${entry.key}:
                    <a href="<c:url value="/course/view/${entry.key}" />">
                        <c:out value="${entry.value.subject}" /></a>
                    (customer: <c:out value="${entry.value.customerName}" />)<br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
