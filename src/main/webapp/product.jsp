<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <c:if test="${empty user}">
            <br><a href="/login.jsp?ref=product.do&id=${product.id}">Login</a>
            <br><a href="/registration.jsp">Registration</a>
        </c:if>
        <c:if test="${not empty user}">
            <br/>You are ${user.login}!
            <br><a href="/userLogout.do?ref=product.do&id=${product.id}">Logout</a>
        </c:if>
        <hr/>
        <br/>
        <br/>
        <br/>
        <b><a href="/productAll.do">ALL PRODUCT PAGE</a></b>
        <br><br/>

        <b>PRODUCT PAGE</b>
        <br>id: ${product.id} <%--EL=Expression Language--%><%--== request.getAttribute("product").getId()--%>
       <%-- <br>id: <%=((Product)request.getAttribute("product")).getId()%>--%> <%--Scriplet--%>
        <br>name: ${product.name}
        <br><a href="/productAddToBucket.do?id=${product.id}">Add to bucket</a>
        <br/>
        <br/>
        <br/>
        <b>Products in bucked</b>
        <ul>
            <c:forEach var="productInBucket" items="${productsInBucket}">
                <li>
                    <a href="/product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>: =
                        ${productInBucket.value}
                    <a href="/productRemoveFromBucket.do?id=${productInBucket.key.id}">X</a>
                </li>
            </c:forEach>
        </ul>
        <br/>
        <br><a href="/productRemoveAllFromBucket.do?id=${product.id}">Clean bucket</a>
    </body>
</html>
