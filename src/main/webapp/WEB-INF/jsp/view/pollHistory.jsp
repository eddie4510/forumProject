<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Poll History</title>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
         <h1>Poll History</h1>
        <c:forEach var="i" begin="0" end="${fn:length(pollQuestion)-1}" step="1">
            <ul>
                <h2>${pollQuestion[i].QUESTION}</h2>
                    <c:forEach var="j" begin="0" end="${fn:length(pollChoice)-1}" step="1">
                        <c:if test="${pollChoice[j].pollId == pollQuestion[i].pollId}">
                          
                        <li>
                            <div class="choice">Choice: ${pollChoice[j].choice} <br/>
                               Votes: ${vote[j]}</div>
                        </li>
                        
                        </c:if>
                    </c:forEach>
            </ul>
         </c:forEach>
    </body>
</html>
