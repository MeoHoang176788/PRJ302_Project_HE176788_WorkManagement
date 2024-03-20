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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div>
            <div class="table-container">
        <table>
        <thead>
            <tr>
                <th>Work ID</th>
                <th>Work Request</th>
                <th>Work Submit</th>
                <th>Work Start Date</th>
                <th>Work Status</th>
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
                    <td>${work.workrequest}</td>
                    <td>${work.worksubmit}</td>
                    <td>${work.workstartdate}</td>
                    <td>${work.workstatus}</td>
                    <td>Delete</td>
                </tr>
            </c:forEach>   
        </tbody>
    </table> 
        </div>
    </body>
</html>
