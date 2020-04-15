<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User: ${title}</title>
        <security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Edit User: ${username}</h1>
        <c:choose>
            <c:when test="${isAdmin}">
                <p style="color:red;">${errorMessage}</p>
                <form:form method="POST" enctype="multipart/form-data" modelAttribute="editUserForm">
                    <form:label path="username" >Username:</form:label><br/>
                    <form:input type="text" path="username" value= "${username}" readonly="true"/><br/><br/>

                    <form:label path="password" >Password:</form:label><br/>
                    <form:input type="password" path="password" value= "${pw}"/><br/><br/>

                    <form:label path="roles">Roles</form:label><br/>
                    <form:checkbox path="roles" value="ROLE_USER" checked="${role_userchecked}"/>ROLE_USER
                    <form:checkbox path="roles" value="ROLE_ADMIN" checked="${role_adminchecked}"/>ROLE_ADMIN
                    <br /><br />

                    <input type="submit" value="Submit"/>
                </form:form>
            </c:when>
            <c:otherwise>
                You are not an Admin!      
            </c:otherwise>
        </c:choose>
    </body>
</html>
