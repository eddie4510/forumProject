<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
            <p>Question:${question}</p>
            
           <form method="POST" modelAttribute="pollForm">
                <input type="radio" id="test1" name="poll">
                <label for="test1">${choiceone}</label><br/>
                <input type="radio" id="test2" name="poll">
                <label for="test2">${choicetwo}</label><br/>
                <input type="radio" id="test3" name="poll">
                <label for="test3">${choicethree}</label><br/>
                 <input type="radio" id="test4" name="poll">
                <label for="test4">${choicefour}</label><br/>
               
                
                <input type="submit" value="Vote"/>
            </form>
    </body>
</html>
