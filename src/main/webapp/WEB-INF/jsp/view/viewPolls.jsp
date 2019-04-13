<!DOCTYPE html>
<html>
    <head><title>Poll ${Poll.id}</title></head>

    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>${Poll.question}</h1>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="Vote" action="vote">
            <table>
                <c:if test="${fn:length(Poll.option1)!=0}">
                    <tr><td><form:radiobutton path="option" value="1" checked="${fn:length(userOption1)!=0 ? 'checked' :''}" />${Poll.option1}</td>
                        <td>${option1Count} user selected</td></tr>
                    </c:if>
                    <c:if test="${fn:length(Poll.option2)!=0}">
                    <tr><td><form:radiobutton path="option" value="2" checked="${fn:length(userOption2)!=0 ? 'checked' :''}" />${Poll.option2}</td>
                        <td>${option2Count} user selected</td></tr>
                    </c:if>
                    <c:if test="${fn:length(Poll.option3)!=0}">
                    <tr><td><form:radiobutton path="option" value="3" checked="${fn:length(userOption3)!=0 ? 'checked' :''}"/>${Poll.option3}</td>
                        <td>${option3Count} user selected</td></tr>
                    </c:if>
                    <c:if test="${fn:length(Poll.option4)!=0}">
                    <tr><td><form:radiobutton path="option" value="4" checked="${fn:length(userOption4)!=0 ? 'checked' :''}"/>${Poll.option4}</td>
                        <td>${option4Count} user selected</td></tr>
                    </c:if>
            </table>
            <form:hidden path="username" value="${user}" />                
            <form:hidden path="pollId" value="${Poll.id}" />
            <input type="submit" value="vote"/>
        </form:form>
        <h2>Comments: </h2>
        <c:choose>
            <c:when test="${fn:length(comments)==0}">
                There are no Comments.<br>
            </c:when> 
            <c:otherwise>
                <c:forEach items="${comments}" var="com">
                    <b>${com.user}</b>: ${com.comment}
                    <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/poll/comment/delete/${Poll.id}/${com.id}" />">Delete</a>]
                    </security:authorize>
                    <br/>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <h2>Add Comment</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="commentForm" action="comment/${Poll.id}">
            <form:label path="user">User: ${user}</form:label>
            <form:input type="hidden" path="user" value="${user}" /><br/>
            <form:input type="hidden" path="pollId" value="${Poll.id}" />
            <form:label path="content">Content: </form:label>
            <form:input type="text" path="content"/><br/>
            <input type="submit" value="Comment"/>
        </form:form><br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>
