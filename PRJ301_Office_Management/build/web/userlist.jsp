<%-- 
    Document   : userlist
    Created on : Mar 17, 2024, 3:46:29 PM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:useBean id="u" class="dal.UserDBContext" scope="request"></jsp:useBean>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/table.css">
    <title>Worklist</title>
    <style>
        /* Style the button text */
        .format {
            background-color: transparent; /* Đặt màu nền của button là trong suốt */
            border: none; /* Loại bỏ viền */
            color: black; /* Đặt màu chữ là đen */
            font-weight: bold; /* In đậm chữ */
            cursor: pointer; /* Hiển thị con trỏ khi di chuột qua button */
        }

        /* Style the button text when hovered */
        .button:hover {
            color: red; /* Đổi màu chữ sang đỏ khi di chuột qua button */
        }
    </style>
    </head>
    <body>
        <table>
        <thead>
            <tr>
                <th>UID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Start Work Date</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${u.userList}" var="user">
                <tr>
                    <td><a href="Profile?uid=${user.getUid()}">${user.getUid()}</a></td>
                    <td>${user.getName()}</td>
                    <td>${user.isGender() ? 'Male' : 'Female'}</td>
                    <td>${user.getStartdate()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getPhone()}</td>
                    <td>${user.getAddress()}</td>
                    <td><a href="Profile?uid=${user.getUid()}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
        
    </body>
</html>
