<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ChosenProductsPage</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
    <jsp:include page="template/loginTemplate.jsp">
        <jsp:param name="from" value="productChoose.do"/>
    </jsp:include>
    <br>
    <c:if test="${user.role eq 'manager'}">
        <h1 align="center">Manager version</h1>
    </c:if>
    <br/><h2>Chosen products</h2>
    <br/>
    <ul>
        <c:forEach var="productList" items="${productList}">
            <li>
                <a href="/product.do?id=${productList.id}">${productList.name}</a>
                <c:if test="${user.role eq 'manager'}">
                <a href="/productRemove.do?id=${productList.id}"> X</a>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${empty productList}">
        <b>Sorry, we don't have such products now</b>
    </c:if>
    <br><a href="productAll.do">all products page</a>
</body>
</html>
