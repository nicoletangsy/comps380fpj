<!DOCTYPE html>
<html>
    <head><title>${lecture.title}</title></head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>${lecture.title}</h1>
        <h2>Materials:</h2>
        <c:choose>
            <c:when test="${fn:length(Attachments)==0}">
                There are no materials.<br><br>
            </c:when> 
            <c:otherwise>
                <c:forEach items="${Attachments}" var="att">
                    <a href="<c:url value="/lecture/Attachment/download/${att.id}" />">${att.name}</a>
                    <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/lecture/Attachment/delete/${lecture.id}/${att.id}" />">Delete</a>]<br>
                    </security:authorize>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/lecture/Attachment/add/${lecture.id}" />">Add attachment(s)</a>]<br>
        </security:authorize>
            <h2>Comments: </h2>
        <c:choose>
            <c:when test="${fn:length(comments)==0}">
                There are no comments.<br>
            </c:when> 
            <c:otherwise>
                <c:forEach items="${comments}" var="com">
                    <b>${com.user}</b>: ${com.comment}
                    <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/lecture/comment/delete/${lecture.id}/${com.id}" />">Delete</a>]
                    </security:authorize><br>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <h2>Add Comment</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="commentForm" action="comment/${lecture.id}">
            <form:label path="user">User: ${user}</form:label>
            <form:input type="hidden" path="user" value="${user}" /><br/>
            <form:input type="hidden" path="lectureId" value="${lecture.id}" />
            <form:label path="content">Content: </form:label>
            <form:input type="text" path="content"/><br/>
            <input type="submit" value="Comment"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>
