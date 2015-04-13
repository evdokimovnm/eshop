<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h2 align="center">Bucket</h2>
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
<br><a href="/order.do">To order</a>
<br/><a href="/productRemoveAllFromBucket.do">Clean bucket</a>
</body>
</html>
