<security:authorize access="isAuthenticated()">
    <c:redirect url="/"/>
</security:authorize>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Sign Up</h1>


            <p style="color:red;">${errorMessage}</p>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
            <form:label path="username">Username:</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>

            <form:label path="password">Password:</form:label><br/>
            <form:input type="password" path="password" /><br/><br/>

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
