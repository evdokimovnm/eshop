<%@ page import="net.evdokimov.eshop.entity.demo.MockEntityA" %>
<html>
    <body>
        <b>MVC Mock View</b>
        <br/>requestAttribute.str = ${requestAttribute.str}
        <br/>requestAttribute.map = ${requestAttribute.map['key0']}
        <br/>requestAttribute.mockEntityB.str = ${requestAttribute.mockEntityB.str}

        <br/>requestAttribute.mockEntityB.str = <%=((MockEntityA)(request.getAttribute("requestAttribute"))).getMockEntityB().getStr()%>

        <br/>sessionAttribute.array[1] = ${sessionAttribute.array[1]}
        <br/>servletContextAttribute.list[0] = ${servletContextAttribute.list[0]}
        <hr/>
        <jsp:useBean id="pageBean" scope="page" class="net.evdokimov.eshop.entity.demo.MockEntityB"></jsp:useBean>
        <br/>pageBean.str = ${pageBean.str}
        <hr/>
        <br/>(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10) =
            ${(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10)}
        <hr/>
        <br/>test = ${test}
    </body>
</html>
