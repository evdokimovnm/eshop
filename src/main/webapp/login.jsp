<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:if test="${not empty loginIncorrect}">
            <br/> Username or password incorrect, please enter again or <br><a href="/registration.jsp">register!</a>
        </c:if>
        <br/>
        <br/>
        <b>Please input login and password:</b>
            <form action="/userLogin.do?ref=<%=request.getParameter("ref")%>&id=<%=request.getParameter("id")%>" method="post">
                <br/>Username  :<input type="text" name="login">
                <br/>Password  :<input type="password" name="password">
                <br/><input type="submit" value="Ok">
            </form>
    </body>
</html>
