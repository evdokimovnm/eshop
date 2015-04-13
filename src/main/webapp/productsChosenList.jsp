<%@ page import="net.evdokimov.eshop.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ChosenProductsPage</title>
</head>

<body>
<p align="right">
    <c:if test="${empty user}">
        <br><a href="/login.jsp?ref=productAll.do">Login</a>
        <br><a href="/registration.jsp">Registration</a>
    </c:if>
    <c:if test="${not empty user}">
        <br/>You are ${user.login}!
        <br><a href="/userLogout.do?ref=productAll.do">Logout</a>
    </c:if>
</p>
<hr/>
<br/>
<%if (session.getAttribute("user") != null && ((User)session.getAttribute("user")).getRole().equals("manager")) { %>
<h1 align="center">Manager version</h1>
<%}%>
<br/><h2>Chosen products</h2>
<br/>
<ul>
    <c:forEach var="productList" items="${productList}">
        <li>
            <a href="/product.do?id=${productList.id}">${productList.name}</a>
            <%if (session.getAttribute("user") != null && ((User)session.getAttribute("user")).getRole().equals("manager")) { %>
            <a href="/productRemove.do?id=${productList.id}"> X</a>
            <%}%>
        </li>
    </c:forEach>
</ul>
<c:if test="${empty productList}">
    <b>Sorry, we don't have such products now</b>
</c:if>
</body>
</html>
