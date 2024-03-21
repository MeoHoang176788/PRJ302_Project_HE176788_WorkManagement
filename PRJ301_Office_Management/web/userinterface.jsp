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
    <style>
        .welcome img{
                height: 60%;
                width: 60%;
            }
            #rounded-circle img{
                border-radius: 50% !important;
            }
            .box {
                background-color: #2e2929;
            border: 1px solid #ccc;
            padding: 10px;
            display: flex;
            flex-direction: column;
            width: 400px;
            border-radius: 4%;
            color: white;
            font-family: sans-serif;
        }

        /* Style for the box title */
        .box h2 {
            margin-top: 0;
            color: #fff;
        }

        /* Style for the box content */
        .box p {
            margin: 0;
            color: whitesmoke;
            font-size: 22px;
        }
    </style>
    <!-- Add your stylesheets or other head elements here -->
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
                <li><a href="Profile?uid=<%= uid %>">Profile</a></li>
                <li><a href="ManageWork?uid=<%= uid %>">Work List</a></li>
                <li><a href="CheckStatus">Work Status Check</a></li>
            </ul>
        </div>
        
        <div class="container">
                <!-- Box 1 -->
                <div class="box">
                    <h2>View Profile</h2>
                    <p>Display user profile and edit user profile like name, gender, email, address,etc... and update it into database.</p>
                </div>

                <!-- Box 2 -->
                <div class="box">
                    <h2>Work List</h2>
                    <p>Display list of work that currently have in system and can view and edit when click on work id</p>
                </div>

                <!-- Box 3 -->
                <div class="box">
                    <h2>Check Status</h2>
                    <p>Display work list that are in status pending or had been checked like finished or failed</p>
                </div>

                
            </div>
        <!-- Add more content or UI elements as needed -->

    </body>
</html>