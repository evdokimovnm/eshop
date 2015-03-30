<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <body>
        <b>Login or password are wrong.</b>
        <b>Please try again! Input login and password:</b>
        <form action="/eshop/userLogin.do" method="post"/>
            <br/>Username  :<input type="text" name="login">
            <br/>Password  :<input type="password" name="password">
            <br/><input type="submit" value="Input">

            <br/>You also can register!</br>
            <br><a href="/eshop/registration.jsp">Registration</a>

    </body>
</html>
