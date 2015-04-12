<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
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
        <br/>
        <b>ALL PRODUCTS PAGE</b>
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

        <%if (session.getAttribute("manager") != null) { %>
        <b>Add new product:</b>
        <form action="/productInsertNew.do" method="post">
            <br/>Product name:<input type="text" name="productName">
            <br/>Product type:
                <select name="productType" size="1">
                    <c:forEach var="productTypeList" items="${productTypeList}">
                        <option value="${productTypeList.type}">${productTypeList.type}</option>
                    </c:forEach>
                </select>
            <br/><input type="submit" value="Add">
        </form>
        <%}%>
    </body>
</html>


