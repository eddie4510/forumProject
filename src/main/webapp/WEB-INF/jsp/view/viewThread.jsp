
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${type}</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Threads of ${type}</h1>
        <c:if test="${empty threads}">
            empty
        </c:if>
        <c:if test="${not empty threads}">
            <ul>
                <c:forEach var="i" begin="0" end="${fn:length(threads)-1}" step="1">
                    <li>
                        <a href="<c:url value="./${type}/${threads[i].THREAD_ID}" />">${threads[i].TITLE}</a>
                        <div>${names[i]}</div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
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
