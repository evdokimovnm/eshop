<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:if test="${not empty loginIncorrect}">
            <br/> <p align="center"> Username or password incorrect, please enter again or <a href="/registration.jsp">register!</a></p>
        </c:if>
        <br/>
        <br/>
        <h2 align="center">Please input login and password:</h2>
            <form action="/userLogin.do?ref=${param.get("ref")}&id=${param.get("id")}" method="post">
                <p align="center">
                <br/>Username  :<input type="text" name="login">
                <br/>Password  :<input type="password" name="password">
                <br/><input type="submit" value="Ok">
                </p>
            </form>
    </body>
</html>
