<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Product</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<html>
    <body>
        <jsp:include page="template/loginTemplate.jsp">
            <jsp:param name="from" value="product.do"/>
        </jsp:include>
        <p align="left"><b><a href="/productAll.do">ALL PRODUCT PAGE</a></b><p>
        <br><br>
        <p align="center"> <b>PRODUCT PAGE</b>
        <br>id: ${product.id}
        <br>name: ${product.name}
        <br><a href="/productAddToBucket.do?id=${product.id}">Add to bucket</a>
        </p>
        <br><br><br>
        <b>Products in bucket</b>
        <ul>
            <c:forEach var="productInBucket" items="${productsInBucket}">
                <li>
                    <a href="/product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>: =
                        ${productInBucket.value}
                    <a href="/productRemoveFromBucket.do?id=${productInBucket.key.id}&ref=${product.id}">X</a>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${not empty productsInBucket}">
            <br><a href="bucket.jsp">Buy</a>
            <br><a href="/productRemoveAllFromBucket.do?id=${product.id}">Clean bucket</a>
        </c:if>
    </body>
</html>
