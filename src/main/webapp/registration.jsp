<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>

        <c:if test="${empty user}">
            <br/><h2 align="center">Registration page</h2>
                <c:if test="${not empty loginOrEmailExist}">
                    <p align="center">
                        <br/>Such login or e-mail already exist, please input another!
                        <br/>
                        <br><a href="/productAll.do">all products page</a>
                    </p>
                </c:if>
                <c:if test="${not empty loginIncorrect}">
                    <p align="center">
                    <br/> Login or password are incorrect, please input another!
                    <br><a href="/productAll.do">all products page</a>
                    </p>
        </c:if>
            <br/><br/><p align="center"><b>Please input login and password:</b></p>
            <form action="/userRegistration.do" method="post">
                <p align="center">
                    <br/>Username  :<input type="text" name="login" />
                    <br/>Password  :<input type="password" name="password" />
                    <br/>
                    <br/><b>Please input your E-mail:</b>
                    <br/>
                    <br/>E-mail  :<input type="text" name="email" />
                    <br/><br/><input type="submit" value="Ok" />
                </p>
            </form>
        </c:if>

        <c:if test="${not empty user}">
            <p align="center">
            <br/>Registration has been successful! Your login is ${user.login}
            <br/> You may go to <a href="/productAll.do">ALL PRODUCTS PAGE</a>
            </p>
        </c:if>

    </body>
</html>
