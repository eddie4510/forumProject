 <security:authorize access="hasAnyAuthority('ROLE_ADMIN')">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Add Poll</title>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
         <h1>Add Poll</h1>
         
         <form:form method="POST" enctype="multipart/form-data" modelAttribute="addPollForm">
            <form:label path="question">Question:</form:label><br/>
            <form:input type="text" path="question" /><br/><br/>

            <p>
                Answer(At most 4):
            </p>
            <form:label path="choiceOne">Choice1:</form:label><br/>
            <form:input type="text" path="choiceOne" /><br/><br/>
            
            <form:label path="choiceTwo">Choice2:</form:label><br/>
            <form:input type="text" path="choiceTwo" /><br/><br/>
            
            <form:label path="choiceThree">Choice3:</form:label><br/>
            <form:input type="text" path="choiceThree" /><br/><br/>
            
            <form:label path="choiceFour">Choice4:</form:label><br/>
            <form:input type="text" path="choiceFour" /><br/><br/>

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
 </security:authorize>
