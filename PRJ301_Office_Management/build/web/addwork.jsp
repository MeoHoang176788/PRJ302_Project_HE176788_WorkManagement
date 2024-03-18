<%-- 
    Document   : addwork
    Created on : Mar 17, 2024, 2:48:56 PM
    Author     : LAPTOP 247
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <jsp:useBean id="u" class="dal.UserDBContext" scope="request"></jsp:useBean>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New User</title>
    <style>
         body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #007bff;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="date"],
        .form-group select {
            width: calc(100% - 16px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
        <h2>Add New Work</h2>
        <form action="" method="post">
            <div class="form-group">
                <label for="name">Work Request</label>
                <input type="text" name="wr">
            </div>
            <div class="form-group">
                <label for="date">Work Submit</label>
                <input type="date" name="ws">
            </div>

            <div class="form-group">
                <label for="address">Work Start Date</label>
                <input type="text" name="wsd">
            </div>
            <div class="form-group">
                <button type="submit" class="btn">Add New Work</button>
            </div>
        
        
        <div>
            <label for="uid">Choose Worker:</label>
            <select class="form-group" name="uid">
                <c:forEach var="uid" items="${u.getUserIdlist()}">
                    <option value="${uid}">${uid}</option>
                </c:forEach>
            </select>
        </div>
        </form>
    </div>
</body>
</html>