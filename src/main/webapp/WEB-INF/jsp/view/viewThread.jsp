
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${type}</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Thread of ${type}</h1>
        [THREAD_ID of the first thread: <c:out value="${threads[0].THREAD_ID}"/>]
        <c:if test="${empty threads}">
            empty
        </c:if>
        <ul>
            <c:forEach var="thread" items="${threads}">
                <li><a href="<c:url value="./${type}/${thread.THREAD_ID}" />">${thread.TITLE}</a></li>
                </c:forEach>
        </ul>

        <security:authorize access="isAuthenticated()">
            <br/>

            <p style="color:red;">${errorMessage}</p>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="threadForm">
                <form:label path="title">Title</form:label><br/>
                <form:input type="text" path="title" /><br/><br/>
                <form:label path="content">Content</form:label><br/>
                <form:textarea path="content" rows="5" cols="30" /><br/>

                <form:label path="attachments">Attachments</form:label><br/>
                <input type="file" name="attachments" multiple="multiple"/>
                <input type="submit" value="Submit"/>
            </form:form>

        </security:authorize>
    </body>
</html>
