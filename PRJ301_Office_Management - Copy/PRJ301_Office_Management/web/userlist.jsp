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
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            width: 80%;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            margin: 20px;
            width: 90%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        
        .btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
    </head>
    <body>
        <div>
            <h1>Manage User</h1>
        </div>
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
        <c:forEach items="${u.wokerList}" var="user">
                <tr>
                    <td><a href="Profile?uid=${user.getUid()}">${user.getUid()}</a></td>
                    <td>${user.getName()}</td>
                    <td>${user.isGender() ? 'Male' : 'Female'}</td>
                    <td>${user.getStartdate()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getPhone()}</td>
                    <td>${user.getAddress()}</td>
                    <td><a href="DeleteUser?uid=${user.getUid()}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
        <div>
            <form action="AddUser" method="get">
                <input class="btn" type="submit" value="Add New User">
            </form>
        </div>    
    </body>
</html>
