<%--
  Created by IntelliJ IDEA.
  User: imwoo
  Date: 2023/09/17
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%--    <%
            for (Member member : members) {
                out.write("     <tr>");
                out.write("         <td>" + member.getId() + "</td>");
                out.write("         <td>" + member.getUsername() + "</td>");
                out.write("         <td>" + member.getAge() + "</td>");
                out.write("     </tr>");

            }
        %>--%>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
