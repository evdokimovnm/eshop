<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ChosenProductsPage</title>
</head>

<body>
<h1>Chosen products</h1>
<br/>
<ul>
    <c:forEach var="productList" items="${productList}">
        <li>
            <a href="/product.do?id=${productList.id}">${productList.name}</a>
            <%if (session.getAttribute("manager") != null) { %>
            <a href="/productRemove.do?id=${productList.id}">Remove</a>
            <%}%>
        </li>
    </c:forEach>
</ul>
<c:if test="${empty productList}">
    <b>Sorry, we don't have such products now</b>
</c:if>
</body>
</html>
