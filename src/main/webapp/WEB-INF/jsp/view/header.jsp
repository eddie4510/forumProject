<style>
    header{
        padding: 10px;
        box-shadow: 5px 10px;
        color:#79d9a4;
        height:35px;
        padding-top:25px;
    }
    .user{
        float:right;
    }
</style>

<header>
    <a href="<c:url value="" />">HOME</a>
    <c:if test="${abc}">


    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <label>
            Hi ${pageContext.request.userPrincipal.name} ! Welcome to our site
        </label>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <div class="user">
            <a href="<c:url value="/register" />">SIGN UP</a>
            <a href="<c:url value="/login" />">LOGIN</a>
        </div>
    </c:if>
</header>
