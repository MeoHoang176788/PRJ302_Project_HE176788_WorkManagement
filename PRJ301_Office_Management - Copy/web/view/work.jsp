<%-- 
    Document   : work
    Created on : Mar 12, 2024, 1:41:49 PM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%-- <jsp:useBean id="w" class="dal.WorkListDBContext" scope="request"></jsp:useBean> --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Information</title>
    </head>
    <body>
        
        <%-- String workId = request.getParameter("id"); --%>
        <h1>WorkID: ${requestScope.wid}</h1>
       
            
<!--        <table>
        <thead>
            <tr>
                <th>Work ID</th>
                <th>Work Request</th>
                <th>Work Submit</th>
                <th>Work Start Date</th>
                <th>Work Status</th>
            </tr>
        </thead>
        <tbody>-->
            <!-- getWorkbyWid(workId) -->
            <c:forEach items="${requestScope.rs}" var="wd">
        <%--        <tr>
                    <td> ${wd.getWorkid()}</td>
                    <td>${wd.getWorkrequest() }</td>
                    <td>${wd.getWorksubmit()}</td>
                    <td>${wd.getWorkstartdate()}</td>
                    <td>${wd.getWorkstatus()}</td>
                </tr>
        --%>
        
    <h2>Work Request</h2>
    <div>
        ${wd.getWorkrequest()}
        <textarea id="workRequest" name="workRequest" value="" rows="10" cols="190" required></textarea> <br>
   
    </div>

    <hr>

    <h2>Work Submission</h2>
    <div>
    <form action="workProcess.jsp" method="post">
        <textarea id="workSubmit" name="workSubmit" value=" ${wd.getWorksubmit()}"rows="10" cols="190" required></textarea> <br>
        <input type="submit" value="Submit Work">
    </form>
    </div>    
    <hr>

    <h2>Work Status: ${wd.getWorkstatus()}</h2> 
        </c:forEach>            
<!--    </tbody>
    </table>            -->
    </body>
</html>
