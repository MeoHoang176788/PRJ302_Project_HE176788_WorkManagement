<%-- 
    Document   : userscreen
    Created on : Mar 9, 2024, 11:34:38 AM
    Author     : LAPTOP 247
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <link rel="stylesheet" href="css/usercss.css"/>

    <!-- Add your stylesheets or other head elements here -->
    </head>
    <body>
        <div id="sidebar">
            <% 
                String uid=(String)request.getAttribute("uid");
            %>
            <h2>Welcome, <%= uid %></h2>
            <%
                request.setAttribute("uid",uid); 
            %>  
            <ul>
                <li><a href="Profile?uid=<%= uid %>">Profile</a></li>
                <li><a href="WorkList?uid=<%= uid %>">Work List</a></li>
                <li><a href="WorkList?uid=<%= uid %>&action=status">Work Status Check</a></li>
            </ul>
        </div>
        
        <div class="container">
                <!-- Box 1 -->
                <div class="box">
                    <h2>Box 1 Title</h2>
                    <p>This is the content of Box 1.</p>
                </div>

                <!-- Box 2 -->
                <div class="box">
                    <h2>Box 2 Title</h2>
                    <p>This is the content of Box 2.</p>
                </div>

                <!-- Box 3 -->
                <div class="box">
                    <h2>Box 3 Title</h2>
                    <p>This is the content of Box 3.</p>
                </div>

                <!-- Box 4 -->
                <div class="box">
                    <h2>Box 4 Title</h2>
                    <p>This is the content of Box 4.</p>
                </div>
            </div>
        <!-- Add more content or UI elements as needed -->

    </body>
</html>