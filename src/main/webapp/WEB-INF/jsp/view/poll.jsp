<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
            <p>Question:${question}</p>
             <security:authorize access="isAnonymous()">
                      ${welcome}!     
             </security:authorize>
             <security:authorize access="isAuthenticated()">
              <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="pollForm">
            <form:radiobuttons path="pollChoice" items="${pollChoiceList}"/><br/>
             <input type="submit" value="Vote"/>
            </form:form>    
             </security:authorize>
            
        
    </body>
</html>

