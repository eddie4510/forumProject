<style>
    header{
        padding: 10px;
        color:#79d9a4;
        height:35px;
        padding-top:25px;
        border-bottom-color: #79d9d4;
        border-bottom:solid;
    }
    .user{
        float:right;
    }

    .user a{
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

    .logoutForm{
        display:inline-block;
    }
    .logoutForm input[type=submit]{
        width:80px;
        height:30px;
        background-color: #79d9a4;
        color:white;
        border:none;
        border-radius: 25px;
        font-size:15px;
    }
</style>
<security:authorize access="hasAnyAuthority('ROLE_ADMIN')" var="isAdmin">
</security:authorize>
<c:set var="userName" value="${pageContext.request.userPrincipal.name}"/>
<header>
    <a href="/Forum/" ">HOME</a>

    <c:if test="${userName != null}">
        <div class="user">
            Hi ${userName}  <c:if test="${isAdmin}">[admin]</c:if>! 


            <c:url var="logoutUrl" value="/logout"/>
            <form class="logoutForm" action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </c:if>



    <c:if test="${userName == null}">
        <div class="user">
            <a href="<c:url value="/register" />">SIGN UP</a>
            <a href="<c:url value="/login" />">LOGIN</a>
        </div>
    </c:if>
</header>
