<%-- 
    Document   : userinterface
    Created on : Mar 3, 2024, 11:14:35 AM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Interface</title>
    </head>
    <body>
        <h1>Hello User!</h1>
        <!-- header-->
        <div>
            
        </div>
        <!-- content -->
        <div>
    <h2>Work Request</h2>
    <form action="workProcess.jsp" method="post">
        <label for="workRequest">Work Request:</label> <br>
        <textarea id="workRequest" name="workRequest" rows="10" cols="190" required></textarea> <br>
        <input type="submit" value="Submit Work Request">
    </form>

    <hr>

    <h2>Work Submission</h2>
    <form action="workProcess.jsp" method="post">
        <label for="workSubmit">Work Submit:</label> <br>
        <textarea id="workSubmit" name="workSubmit" rows="10" cols="190" required></textarea> <br>
        <input type="submit" value="Submit Work">
    </form>

    <hr>

    <h2>Work Status</h2>
    <form action="" method="post">
        <label for="workStatus">Work Status:</label>
        <select id="workStatus" name="workStatus">
            <option value="Processing">Processing</option>
            <option value="Complete">Complete</option>
            <option value="Pass">Pass</option>
            <option value="Failed">Failed</option>
        </select>
        <input type="submit" value="Update Work Status">
    </form>
        </div>
        <!-- footer -->
        <div>
            
        </div>
    </body>
</html>
