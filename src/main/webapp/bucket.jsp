<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bucket</title>
</head>
<body>
<p align="right">
    <c:if test="${empty user}">
        <br><a href="/login.jsp?ref=bucket.jsp">Login</a>
        <br><a href="/registration.jsp">Registration</a>
    </c:if>
    <c:if test="${not empty user}">
        <br/>You are ${user.login}!
        <br><a href="/userLogout.do?ref=bucket.jsp">Logout</a>
    </c:if>
</p>
<hr/>
<br/><h2 align="center">Bucket</h2>
<br/><b>Your bucket. Make sure this is everything what you want.</b>
<ul>
    <c:forEach var="productInBucket" items="${productsInBucket}">
        <li>
            <a href="/product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>: =
                ${productInBucket.value}
            <a href="/productRemoveFromBucket.do?id=${productInBucket.key.id}&ref=bucket">X</a>
        </li>
    </c:forEach>
</ul>

<c:if test="${not empty user and not empty productsInBucket}">
    <br><a href="/order.do">To order</a>
    <br/><a href="/productRemoveAllFromBucket.do?ref=bucket.jsp">Clean bucket</a>
</c:if>
<c:if test="${empty productsInBucket}">
    <br>Your backed is empty please choose up some things: <a href="/productAll.do">ALL PRODUCT PAGE</a>
</c:if>
<c:if test="${empty user and not empty productsInBucket}">
    <br>If you want make order you should <a href="/login.jsp?ref=bucket.jsp">login</a> or <a href="/registration.jsp">register</a>
    <br/><a href="/productRemoveAllFromBucket.do?ref=bucket.jsp">Clean bucket</a>
</c:if>

</body>
</html>
