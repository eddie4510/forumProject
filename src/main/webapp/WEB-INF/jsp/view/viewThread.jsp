
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            ul{
                border:solid 1px;
                list-style-type: none;
                padding:10px;
                background-color:#fafffc;
            }
            li{
                border-bottom: solid 1px;
                border-top: solid 1px;
                padding:20px;
                margin:2px;
            }
            .title{
                font-size: 20px;
                display:inline-block;
            }
            .name{
                display:inline-block;
            }
            .delete{
                display:inline-block;
                float:right;
            }

        </style>
        <title>${type}</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Threads of ${type}</h1>
        <c:if test="${empty threads}">
            Write the first message topic!
        </c:if>
        <c:if test="${not empty threads}">
            <ul>
                <c:forEach var="i" begin="0" end="${fn:length(threads)-1}" step="1">
                    <li>
                        <div class="title"><a href="<c:url value="./${type}/${threads[i].THREAD_ID}" />">${threads[i].TITLE}</a></div>
                        <div class="name">by ${names[i]}</div>
                        <security:authorize access="hasAnyAuthority('ROLE_ADMIN')" var="isAdmin">
                            <div class="delete">                        
                                <a href="<c:url value="/thread/delete/${type}/${threads[i].THREAD_ID}" />">
                                    [Delete]
                                </a>
                            </div>
                        </security:authorize>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <security:authorize access="isAnonymous()">
            <div>Please login to write your topic!</div>
        </security:authorize>
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
