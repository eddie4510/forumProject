<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Poll History</title>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
         <h1>Poll History</h1>
        <c:forEach var="i" begin="0" end="${fn:length(pollQuestion)-1}" step="1">
            <ol>
                ${pollQuestion[i].QUESTION}
                    <c:forEach var="j" begin="0" end="${fn:length(pollChoice)-1}" step="1">
                        <c:if test="${pollChoice[j].pollId == pollQuestion[i].pollId}">
                          
                        <li>
                            <div class="choice">${pollChoice[j].choice}, 
                                ${vote[j]}</div>
                        </li>
                        
                        </c:if>
                    </c:forEach>
            </ol>
         </c:forEach>
    </body>
</html>
