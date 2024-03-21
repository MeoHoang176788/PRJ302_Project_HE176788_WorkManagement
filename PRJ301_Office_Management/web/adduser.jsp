<%-- 
    Document   : adduser
    Created on : Mar 17, 2024, 1:44:52 PM
    Author     : LAPTOP 247
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
        }
        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="date"],
        .form-group input[type="tel"],
        .form-group input[type="password"]{
            width: 80%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group input[type="radio"] {
            margin-right: 10px;
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
    <div class="container">
        <h2>Add New User</h2>
        <form action="AddUser" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label>Gender:</label>
                <input type="radio" name="gender" value="1" checked>Male
                <input type="radio" name="gender" value="0">Female
            </div>
            <div class="form-group">
                <label for="date">Start Work Date:</label>
                <input type="date" id="date" name="date" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <button type="submit" class="btn">Add New User</button>
        </form>
            <div>
                <c:if test="${requestScope.status eq 'success'}">
                    <h2 style="color: red">Add New User Successfully!</h2>
                </c:if>
                <c:if test="${requestScope.status eq 'failed'}">
                    <h2 style="color: red">Add New User Failed!!</h2>
                </c:if>    
            </div>
    </div>
</body>
</html>