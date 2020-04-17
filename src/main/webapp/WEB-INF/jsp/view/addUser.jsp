<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add user</title>
        <security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Creat user</h1>
        <c:choose>
            <c:when test="${isAdmin}">
                <p style="color:red;">${errorMessage}</p>
                <form:form method="POST" enctype="multipart/form-data" modelAttribute="addUser">
                 <form:label path="username">Username</form:label><br/>
                 <form:input type="text" path="username" /><br/><br/>
                 <form:label path="password">Password</form:label><br/>
                 <form:input type="text" path="password" /><br/><br/>
                 <form:label path="roles">Roles</form:label><br/>
                 <form:checkbox path="roles" value="ROLE_USER" checked="true" disabled="true"/>ROLE_USER
                 <form:checkbox path="roles" value="ROLE_ADMIN" />ROLE_ADMIN
                 <br /><br />
                 <input type="submit" value="Add User"/>
                </form:form>
            </c:when>
            <c:otherwise>
                You are not an Admin!      
            </c:otherwise>
        </c:choose>
    </body>
</html>
