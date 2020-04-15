<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
            <p>Question:${question}</p>
             <security:authorize access="isAnonymous()">
                 <c:forEach var="i" begin="0" end="${fn:length(pollChoiceList)-1}" step="1">
            <li>
                    ${pollChoiceList[i]}, ${result[i]}
                      
            </li>
                 </c:forEach>
             </security:authorize>
            
             <security:authorize access="isAuthenticated()">
              <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="pollForm">

            <form:radiobuttons path="pollChoice" items="${pollChoiceList}"/><br/>

             <input type="submit" value="Vote"/>
            </form:form>    
             </security:authorize>
             
             <security:authorize access="hasAnyAuthority('ROLE_ADMIN')" var="isAdmin">
                <a href="<c:url value="/addPoll" />">Add a new poll</a>
           
             </security:authorize>
            <a href="<c:url value="/pollHistory" />">History</a>
        
    </body>
</html>

