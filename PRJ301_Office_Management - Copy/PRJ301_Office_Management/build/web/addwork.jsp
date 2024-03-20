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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">

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
        .form-group textarea {
            width: calc(100% - 16px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        .form-group select{
/*            width: 15%;*/
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group input[type="date"]{
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
        .wr {
            width: calc(100% - 16px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            height: 10%; /* Set the height to at least 10% */
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Work</h2>
        <form action="AddWork" method="post">
            <div class="form-group">
                <label for="name">Work Title</label>
                <input type="text" name="wt" required=>
            </div>
            <div class="form-group">
                <label for="name">Work Request</label>
                <textarea  name="wr" rows="10" required></textarea> <br>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                <label for="address">Work Start Date</label>
                <input type="date" name="wsd" required="">
                </div>
                <div class="col-sm-4">
                <label for="address">Work End Date</label>
                <input type="date" name="wed" required="">
                </div>
                <div class="col-sm-4">
                <label for="uid">Choose Worker:</label>
                
                <select name="uid" required>
                        <option >Choose</option>
                        <c:forEach var="uid" items="${u.getUserIdlist()}">
                            <option value="${uid}" <c:if test="${requestScope.preuid eq uid}">selected</c:if>>${uid}</option>
                        </c:forEach>
                    </select>
                </div>  
            </div>
            <div class="form-group">
                <button type="submit" class="btn">Add New Work</button>
            </div>
            <div>
                <c:if test="${requestScope.status eq 'success'}">
                    <h2 style="color: green">Add New Work Successfully!!</h2>
                </c:if>
                <c:if test="${requestScope.status eq 'failed'}">
                    <h2 style="color: red">Add New Work Failed!!</h2>
                </c:if>
                    
            </div>
        

        </form>
    </div>
</body>
</html>