<%-- 
    Document   : admininterface
    Created on : Mar 9, 2024, 1:09:11 PM
    Author     : LAPTOP 247
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <link rel="stylesheet" href="css/admincss.css"/>
        <style>
            .welcome img{
                height: 60%;
                width: 60%;
            }
            #rounded-circle img{
                border-radius: 50% !important;
            }
        </style>
    </head>
    <body>
        <div id="sidebar">
            <div class="welcome">
            <div id="rounded-circle">
                <img src="img/male.png" alt="Admin" width="150">
            </div>
            <% 
                String uid=(String)request.getAttribute("uid");
            %>
            <h2>Welcome, <%= uid %></h2>
            <%
                request.setAttribute("uid",uid); %>  
            </div>
            <ul>
                <li><a href="Profile?uid=<%= uid %>">View Profile</a></li>
                <li><a href="ManagerUser?uid=<%= uid %>">Manage User</a></li>
                <li><a href="ManageWork?uid=<%= uid %>">Manage Work</a></li>
                <li><a href="WorkList?uid=<%= uid %>&action=check">Check Work Status</a></li>               
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
<!--        <div id="background-content">  
            <div id="content">
                <h2>Profile:</h2>
                <div> Display your information like: name, date of birth, account, gender, current work</div>
                <h2>Add New Work</h2>
                <div> Select a worker and add a new work to the worker worklist</div>
                <h2>Create New Account</h2>
                <div> Create new worker account</div>
                <h2>Check Work Status</h2>
                <div> Display work that has been done but haven't review by admin and start review it</div>
                <h2>Check Workers</h2>
                <div> Display worker list and if click on workid it display that worker's worklist and its status</div>
            </div>
        </div>      -->
            
    </body>
</html>
