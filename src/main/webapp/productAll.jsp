<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>AllProductsPage</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    <body>
        <jsp:include page="template/loginTemplate.jsp">
            <jsp:param name="from" value="productAll.do"/>
        </jsp:include>
        <br>
        <c:if test="${user.role eq 'manager'}">
            <h1 align="center">Manager version</h1>
        </c:if>
        <br><h2 align="center">ALL PRODUCTS PAGE</h2>
        <br><b>Choose type of product for getting list with this type</b>
        <ul>
            <c:forEach var="productTypeList" items="${productTypeList}">
                <li>
                    <a href="/productChoose.do?id=${productTypeList.id}">${productTypeList.type}</a>
                </li>
            </c:forEach>
        </ul>
        <br>
        <h2>ALL PRODUCTS LIST</h2>
        <ul>
            <c:forEach var="productList" items="${productList}">
                <li>
                    <a href="/product.do?id=${productList.id}">${productList.name}</a>
                    <c:if test="${user.role eq 'manager'}">
                        <a href="/productRemove.do?id=${productList.id}"> X</a>
                    </c:if>
                </li>
            </c:forEach>
        </ul><br>

        <c:if test="${user.role eq 'manager'}">
            <b>Add new product:</b>
            <form action="/productInsertNew.do" method="post">
                <br/>Product name:<input type="text" name="productName">
                <br/>Product type:
                    <select name="productType" size="1">
                        <c:forEach var="productTypeList" items="${productTypeList}">
                            <option value="${productTypeList.type}">${productTypeList.type}</option>
                        </c:forEach>
                    </select>
                <br><input type="submit" value="Add">
            </form>
        </c:if>
    </body>
</html>


