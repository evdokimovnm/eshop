<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>

        <c:if test="${empty user}">
            <br/>Registration page.</b>
                <c:if test="${not empty loginExist}">
                    <br/>This login is already exist, please input another!
                </c:if>
                <c:if test="${not empty loginIncorrect}">
                    <br/> Login or password are incorrect, please input another!
                </c:if>
                <br/>
            <br/>Please input login and password:</b>
            <form action="/eshop/userRegistration.do" method="post">
                <br/>Username  :<input type="text" name="login" />
                <br/>Password  :<input type="password" name="password" />
                <br/><input type="submit" value="Ok" />
            </form>
        </c:if>

        <c:if test="${not empty user}">
            <br/>Registration has been successful! Your login is ${user.login}
            <br/> You may go to <a href="/eshop/productAll.do">ALL PRODUCTS PAGE</a>
        </c:if>

    </body>
</html>
