<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .button{
            margin-top: 20px;
            float:right;
        }

        .button a{
            font-size:15px;
            text-decoration: none;
            margin:10px;
            width:80px;
            height:30px;
            padding:5px;
            background-color: #79d9a4;
            color:white;
            border:none;
            border-radius: 25px;
        }
        .content{
            margin-top: 30px;
            text-align:center;
        }
        table{
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
         border: 1px solid black;
         border-collapse: collapse;
        }
        th {
        text-align: left;
        }
        .total{
            display:inline-block;
             float:right;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <security:authorize access="hasAnyAuthority('ROLE_ADMIN')" var="isAdmin">
            <div class = "button">  <a href="<c:url value="/addPoll" />">Add a new poll</a></div>

        </security:authorize>
        <div class = "button"><a href="<c:url value="/pollHistory" />">History</a></div><br/>

        <br/>
        <div class="content">
            <h2>Question:${question}</h2>
            <security:authorize access="isAnonymous()">
                <table align="center">
                    <tr>
                        <th>Choice</th>
                        <th>Votes</th>
                    </tr>
                    <c:forEach var="i" begin="0" end="${fn:length(pollChoiceList)-1}" step="1">
                        <tr></tr>
                        <td>${pollChoiceList[i]}</td>
                        <td>${result[i]}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
            </security:authorize>


            <security:authorize access="isAuthenticated()">
                <c:choose>
                    <c:when test="${isVoted}">
                        <p style="color:red;">${errorMessage}</p>
                        <table align="center">
                            <tr>
                                <th>Choice</th>
                                <th>Votes</th>
                            </tr>
                            <c:forEach var="i" begin="0" end="${fn:length(pollChoiceList)-1}" step="1">
                                <tr></tr>
                                      <td>${pollChoiceList[i]}</td>
                                     <td>${result[i]}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br/>
                    </c:when>
                    <c:otherwise>
                        <form:form method="POST" enctype="multipart/form-data"
                                   modelAttribute="pollForm">

                            <form:radiobuttons path="pollChoice" items="${pollChoiceList}"/><br/><br/>

                            <input type="submit" value="Vote"/><br/>
                        </form:form>  
                    </c:otherwise>
                </c:choose>
            </security:authorize>
            <div class="total">Total User Voted: ${total}</div>
        </div>

    </body>
</html>

