<!DOCTYPE html>
<html>
    <head><title>All Users</title></head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br /><br />
        <a href="<c:url value="/user/create" />">Create user</a><br>
        <h2>Users</h2>
        <c:choose>
            <c:when test="${fn:length(Users) == 0}">
                <i>There are no users in the system.</i>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Username</th><th>Password</th><th>Roles</th><th>Action</th>
                    </tr>
                    <c:forEach items="${Users}" var="user">
                        <tr>
                            <td>${user.username}</td><td>${user.password}</td>
                            <td>
                                <c:forEach items="${user.roles}" var="role" varStatus="status">
                                    <c:if test="${!status.first}">, </c:if>
                                    ${role.role}
                                </c:forEach>
                            </td>
                            <td>
                                [<a href="<c:url value="/user/edit/${user.username}" />">Edit</a>]
                            </td>         
                            <td>
                                [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]
                            </td>                   
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br/>
        <a href="<c:url value="/course" />">Return to Home Page</a>
    </body>
</html>