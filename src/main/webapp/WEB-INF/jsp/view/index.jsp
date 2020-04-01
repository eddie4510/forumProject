
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            nav{
                margin-top:100px;
                text-align:center;
                display:block;
                overflow:hidden;

            }
            nav a{
                margin:10px;
                border-radius:20px;
                width:300px;
                height:170px;
                padding-top:130px;
                display:inline-block;
                text-align:center;
                text-decoration:none;
                background-color: #79d9a4;
                color:white;
                font-weight:bold;
            }

            nav a:hover{
                transition: 0.3s;
                transform:scale(1.5); 
                -webkit-background-clip:text; 
                -webkit-text-fill-color:transparent;
                text-shadow: 1px 1px 1px #9AFF9A;
            }
            .poll{
                margin-top: 30px;
                border:solid;
                text-align:center;
            }
        </style>
        <title>Index</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="poll">
                poll
            </div>
            <nav>
                <a href="<c:url value="/thread/view/lecture" />">LECTURE</a>
            <a href="<c:url value="/thread/view/lab" />">LAB</a>
            <a href="<c:url value="/thread/view/other" />">OTHER</a>
        </nav>
    </body>
</html>
