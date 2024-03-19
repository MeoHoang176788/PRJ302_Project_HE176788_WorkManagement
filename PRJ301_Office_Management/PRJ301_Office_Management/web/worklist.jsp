<%-- 
    Document   : worklist
    Created on : Mar 12, 2024, 1:49:58 PM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <jsp:useBean id="w" class="dal.WorkListDBContext" scope="request"></jsp:useBean>
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
                /*        button.format {
                            background-color: #007bff;
                            color: #fff;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                        }*/
                /*        .table-container {
                            overflow-x: auto;
                            border: 1px solid #ddd;
                            border-radius: 5px;
                            margin-top: 20px;
                        }*/
            </style>
        </head>
        <body>


        <c:if test="${requestScope.per eq true}">
            <div>
                <form id="myForm" action="WorkList" method="post">
                    <input type="hidden" name="per" value="${requestScope.per}">
                    <span style="margin: 20px">View work:</span>
                    <select id="myDropdown" name="uid">
                        <option value="">All</option>
                        <c:forEach var="uid" items="${u.getUserIdlist()}">
                            <option value="${uid}" <c:if test="${requestScope.preuid eq uid}">selected</c:if> >${uid}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </c:if>
        <br>
        <c:if test="${not empty requestScope.preuid}">
            <h1>Work list of ${requestScope.preuid}</h1>
        </c:if>
        <div class="table-container">
            <c:choose>
                <c:when test="${empty requestScope.worklist}">

                    <table>
                        <thead>
                            <tr>
                                <th>Work ID</th>
                                <th>Work Title</th>
                                <th>Work Request</th>
                                <th>Work Start Date</th>
                                <th>Work End Date</th>
                                <th>Work Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>               
                            <c:forEach items="${w.getAdminWorkList()}" var="work">

                                <tr>
                                    <td>
                                        <form method="post" action="WorkDetail?uid=${work.getWorkid()}">
                                            <!-- Add hidden input field to pass the workId -->
                                            <input type="hidden" name="workId" value="${work.getWorkid()}">
                                            <!-- Add submit button to submit the form -->
                                            <button class="format" type="submit">${work.getWorkid()}</button>
                                        </form></td>
                                    <td>${work.worktitle}</td>
                                    <td>${work.workrequest}</td>
                                    <td>${work.workstartdate}</td>
                                    <td>${work.workenddate}</td>
                                    <td>${work.workstatus}</td>
                                    <td><a href="DeleteWork?id=${work.getWorkid()}&uid=${requestScope.uid}">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>

                        <table>
                            <thead>
                                <tr>
                                    <th>Work ID</th>
                                    <th>Work Title</th>
                                    <th>Work Request</th>
                                    <th>Work Start Date</th>
                                    <th>Work End Date</th>
                                    <th>Work Status</th>
                                    <th>User ID</th>
                                    <th></th>
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
                                        <td>${work.workstartdate}</td>
                                        <td>${work.workenddate}</td>
                                        <td>${work.workstatus}</td>
                                        <td><a href="DeleteWork?id=${work.getWorkid()}&uid=${requestScope.preuid}">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>       
                    </tbody>
                </table> 
        </div>
        <c:if test="${requestScope.per eq true}">       
            <div>
                <form action="AddWork" method="get">
                    <c:if test="${not empty requestScope.preuid}">
                        <input type="hidden" name="preuid" value="${requestScope.preuid}">    
                    </c:if>
                    <input class="btn" type="submit" value="Add New Work">
                </form>
            </div>
        </c:if>    
        <script>
            const dropdown = document.getElementById("myDropdown");

            dropdown.addEventListener("change", function () {

                document.getElementById("myForm").submit();
            });
        </script>        
    </body>
</html>
