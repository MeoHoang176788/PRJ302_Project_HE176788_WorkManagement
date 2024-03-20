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
        <div>
            <form id="myForm" action="WorkList" method="post">
                <span style="margin: 20px">View work:</span>
                <select id="myDropdown" name="uid">
                    <option value="">All</option>
                    <c:forEach var="uid" items="${u.getUserIdlist()}">
                        <option value="${uid}">${uid}</option>
                    </c:forEach>
                </select>
            </form>
        </div>
        <div class="table-container">
        <table>
        <thead>
            <tr>
                <th>Work ID</th>
                <th>Work Request</th>
                <th>Work Submit</th>
                <th>Work Start Date</th>
                <th>Work Status</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
                <c:when test="${empty requestScope.worklist}">
                    <c:forEach items="${w.getWorkList()}" var="work">
                        <tr>
                    <td><form method="post" action="WorkDetail">
        <!-- Add hidden input field to pass the workId -->
        <input type="hidden" name="workId" value="${work.getWorkid()}">
        <!-- Add submit button to submit the form -->
        <button class="format" type="submit">${work.getWorkid()}</button>
    </form></td>
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmit}</td>
                    <td>${work.workstartdate}</td>
                    <td>${work.workstatus}</td>
                </tr>
            </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${requestScope.worklist}" var="work">
                        <tr>
                    <td><form method="post" action="WorkDetail">
        <!-- Add hidden input field to pass the workId -->
        <input type="hidden" name="workId" value="${work.getWorkid()}">
        <!-- Add submit button to submit the form -->
        <button class="format" type="submit">${work.getWorkid()}</button>
    </form></td>
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmit}</td>
                    <td>${work.workstartdate}</td>
                    <td>${work.workstatus}</td>
                </tr>
            </c:forEach>
                </c:otherwise>
            </c:choose>       
        </tbody>
    </table>
    </div>
    <script>
            const dropdown = document.getElementById("myDropdown");

            dropdown.addEventListener("change", function () {

                document.getElementById("myForm").submit();
            });
        </script>        
    </body>
</html>
