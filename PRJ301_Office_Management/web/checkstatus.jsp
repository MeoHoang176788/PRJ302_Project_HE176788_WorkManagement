<%-- 
    Document   : checkstatus
    Created on : Mar 18, 2024, 4:19:02 PM
    Author     : LAPTOP 247
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/table.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
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
                    margin: 20px;
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
                form {
                    display: inline;
                    margin: 0;
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
                .breadcrumb{
                    width: 80%;
                    padding: 15px;
                    margin-left: 25px;
                }
                .main-body {
                    padding: 15px;
                }
            </style>
    </head>
    <body>
        <c:set var="loginUid" value="${sessionScope.loginuid}" />
    
        <h1>Welcome, ${loginUid}</h1>
        <div class="main-body"> 
                
                    <nav aria-label="breadcrumb" class="main-breadcrumb">
                    <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Check Status</li>
                    </ol>
                    </nav>  
        <div class="">
            <h2 style="color: blue">Work in pending</h2>
            <div class="table-container">
        <table>
        <thead>
            <tr>
                <th>Work ID</th>
                <th>Work Title</th>
                <th>Work Request</th>
                <th>Work Submit Date</th>
                <th>Work Status</th>
            </tr>
        </thead>
        <tbody>
                    <c:forEach items="${requestScope.worklist}" var="work">
                        <tr>
                    <td><form method="post" action="WorkDetail">
        <!-- Add hidden input field to pass the workId -->
        <input type="hidden" name="workId" value="${work.getWorkid()}">
        <!-- Add submit button to submit the form -->
        <button class="format" type="submit">${work.getWorkid()}</button>
    </form></td>
                    <td>${work.worktitle}</td>
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmitdate}</td>
                    <td>${work.workstatus}</td>
                </tr>
            </c:forEach>   
        </tbody>
    </table> 
        </div>
            
    <c:if test="${not empty requestScope.wpass or not empty requestScope.wfailed}">   
        <div>
            <h2 style="color: blue">Work in checked</h2>            
            <table>
                <thead>
                   
                <tr>
                <th>Work ID</th>
                <th>Work Title</th>
                <th>Work Request</th>
                <th>Work Submit Date</th>
                <th>Work Status</th>
                </tr>
                     
                    
                </thead>
                <tbody>              
                    <c:forEach items="${requestScope.wpass}" var="work">
                        <tr>
                    <td><form method="post" action="WorkDetail">
        <!-- Add hidden input field to pass the workId -->
        <input type="hidden" name="workId" value="${work.getWorkid()}">
        <!-- Add submit button to submit the form -->
        <button class="format" type="submit">${work.getWorkid()}</button>
    </form></td>
                    <td>${work.worktitle}</td>
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmitdate}</td>
                    <td>${work.workstatus}</td>
                </tr>  
                    </c:forEach>   
                    <c:forEach items="${requestScope.wfailed}" var="work">
                        <tr>
                    <td><form method="post" action="WorkDetail">
        <!-- Add hidden input field to pass the workId -->
        <input type="hidden" name="workId" value="${work.getWorkid()}">
        <!-- Add submit button to submit the form -->
        <button class="format" type="submit">${work.getWorkid()}</button>
    </form></td>
                    <td>${work.worktitle}</td>
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmitdate}</td>
                    <td>${work.workstatus}</td>
                </tr>  
                    </c:forEach>                              
                </tbody>
            </table>
        </div>    
    </div>  
    </c:if>             
    </div>  
    </body>
</html>
