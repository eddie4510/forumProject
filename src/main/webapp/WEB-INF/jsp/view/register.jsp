<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>


         <p style="color:red;">${errorMessage}</p>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
            <form:label path="username">username:</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>

            <form:label path="password">password:</form:label><br/>
            <form:input type="password" path="password" /><br/><br/>

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
