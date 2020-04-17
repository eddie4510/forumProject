
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
            .edit{
                display:inline-block;
                float:right;
            }
            .create{
                display:inline-block;
                float:right;
            }
        </style>
        <title>User information</title>
        <security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>User information</h1>
        <h2>${testthreadid}</h2>
        <c:choose>
            <c:when test="${isAdmin}">
            <ul>
                <c:forEach var="i" begin="0" end="${fn:length(usernames)-1}" step="1">
                    <li>
                        <div class="title">${usernames[i]}</div>
                        <div class="edit"><a href= "<c:url value="./edit/${usernames[i]}" />">[Edit]</a></div>
                        <div class="delete"><a href="<c:url value="./delete/${usernames[i]}" />">[Delete]</a></div>
                    </li>
                </c:forEach>
                    <li><div class="create"><a href="<c:url value="./create" />">[Create User]</a></div></li>
            </ul>
            </c:when>
            <c:otherwise>
                You are not an Admin!      
            </c:otherwise>
        </c:choose>
    </body>
</html>
