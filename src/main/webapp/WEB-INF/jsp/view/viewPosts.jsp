
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>${title}</h1>

        <ol>
            <c:forEach var="post" items="${posts}">
                <li>
                    ${post.CONTENT}
                    
                </li>
                </c:forEach>
        </ol>


        <security:authorize access="isAuthenticated()">
            <br/>

            <p style="color:red;">${errorMessage}</p>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="postForm">
                <form:label path="content">Content</form:label><br/>
                <form:textarea path="content" rows="5" cols="30" /><br/>
                <form:label path="attachments">Attachments</form:label><br/>
                <input type="file" name="attachments" multiple="multiple"/>
                <input type="submit" value="Submit"/>
            </form:form>

        </security:authorize>



    </body>
</html>
