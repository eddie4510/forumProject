
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Thread of ${type}</h1>
        <c:out value="${threads[0].THREAD_ID}"/>
        <c:if test="${empty threads}">
            empty
        </c:if>
        <c:forEach var="thread" items="${threads}">
            ${thread.TITLE}
        </c:forEach>


    </body>
</html>
