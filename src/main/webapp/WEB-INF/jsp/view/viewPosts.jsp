
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            ol{
                border:solid 1px;
                padding:10px;
                background-color:#fafffc;
            }
            li{
                border-bottom: solid 1px;
                border-top: solid 1px;
                padding:20px;

                margin:2px;
                margin-left:30px;
            }
            .content{
                display:block;
                border:solid 1px;
                border-radius: 2px;
                background-color: #fcfaff;
                padding:15px;
            }
            .name{
                display:inline-block;
            }
            .delete{
                display:inline-block;
                float:right;
            }

        </style>
        <title>${title}</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>${title}</h1>
        <h2>Number of replies: ${fn:length(posts) -1}</h2>
        <ol>
            <c:forEach var="post" items="${posts}" varStatus="status">
                <li>
                    <div class="name">by ${post.USERNAME}</div>
                    <security:authorize access="hasAnyAuthority('ROLE_ADMIN')" var="isAdmin">
                        <c:if test="${status.first}">
                            <div class="delete">
                                <a href="<c:url value="/thread/delete/${type}/${post.threadId}" />">
                                    [Delete Thread]
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${!status.first}">
                            <div class="delete">
                                <a href="<c:url value="/thread/delete/${type}/${post.threadId}/${post.POST_ID}" />">
                                    [Delete Post]
                                </a>
                            </div>
                        </c:if>
                    </security:authorize>
                    <div class="content">${post.CONTENT}</div>
                    <c:if test="${fn:length(post.attachments) >0 }">
                        <div>Attachment(s):</div>
                        <security:authorize access="isAnonymous()">
                            <div>Please login to see the attachments!</div>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <c:forEach items="${post.attachments}" var="attachment">
                                <div>
                                    <a href="<c:url value="/thread/attachment/${attachment.ATTACH_ID}" />">
                                        <c:out value="${attachment.FILENAME}" />
                                    </a>
                                </div>
                            </c:forEach>
                        </security:authorize>
                    </c:if>
                </li>
            </c:forEach>
        </ol>
        
        
        <security:authorize access="isAnonymous()">
            <div>Please login to write your post!</div>
        </security:authorize>

        <security:authorize access="isAuthenticated()">

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
