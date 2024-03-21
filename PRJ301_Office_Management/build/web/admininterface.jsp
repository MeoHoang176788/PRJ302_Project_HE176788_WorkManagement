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
                <li><a href="ManageUser">Manage User</a></li>
                <li><a href="ManageWork">Manage Work</a></li>
                <li><a href="CheckStatus">Check Work Status</a></li>               
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
                    <h2>Manage User</h2>
                    <p>Display list of worker that currently have in system and can create,add or delete user in the list.
                        Can edit profile of user when click on user id</p>
                </div>

                <!-- Box 3 -->
                <div class="box">
                    <h2>Manage Work</h2>
                    <p>Display list work of all worker that currently have in system and can create,add or delete user in the list. 
                        Can be click on work id to view work detail </p>
                </div>

                <!-- Box 4 -->
                <div class="box">
                    <h2>Check Work Status</h2>
                    <p>Checking work that are in status pending need to be checked that been submitted by worker.</p>
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
