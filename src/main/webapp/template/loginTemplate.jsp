<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="login">
    <p align="right">
        <c:if test="${empty user}">
            <br><a href="/login.jsp?ref=${param.from}&id=${param.id}">Login</a>
            <br><a href="/registration.jsp">Registration</a>
        </c:if>
        <c:if test="${not empty user}">
            <br>You are ${user.login}!
            <br><a href="/userLogout.do?ref=${param.from}&id=${param.id}">Logout</a>
        </c:if>
    </p>
    <hr>
</div>
