<%-- 
    Document   : display
    Created on : Mar 5, 2024, 3:03:27 PM
    Author     : LAPTOP 247
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User: <%= request.getAttribute("email")%></h1>
        <h1>Pass: <%= request.getAttribute("pass")%></h1>
        <div>
        <table class="tables">
            <tr>
                <td>UserName</td>
                <td>PassWord</td>
            </tr>
        <c:forEach items="${requestScope.rs}" var="a">
            <tr>
                <td>${a.getAccount()}</td>
                <td>${a.getPassword()}</td>
            </tr>
        </c:forEach> 
        </table>
        </div>
    </body>
</html>
