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
    <jsp:useBean id="w" class="dal.WorkListDBContext" scope="request"></jsp:useBean> 
    <meta charset="UTF-8">
    <title>Work Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #007bff;
            margin-top: 0;
        }
        h2 {
            margin-top: 20px;
        }
        div.container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        div.work-title {
            margin-bottom: 10px;
        }
        div.work-description {
            margin-bottom: 20px;
        }
        div.work-submission {
            margin-bottom: 20px;
        }
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: vertical;
        }
        input[type="submit"],
        button{
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover ,
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="file"] {
            margin-top: 20px;
        }
        .white-box {
            background-color: white;
            border: 1px solid #ccc;
            padding: 20%;
            margin: 20px auto;
            text-align: center;
            border-radius: 5px;

        }
        .date{
            display: flex;
        }
        .date h2{
            padding-right: 20%;
        }
        .blue{
            color: blue !important;
        }
        .red{
            color:red;
        }
        .green{
            color: green;
        }
        .gray{
            color: graytext;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>WorkID: ${requestScope.wid}</h1>
    
        <form action="WorkProcess" method="post"> 
        <c:forEach items="${requestScope.rs}" var="wd">
            <div class="work-title">
                <h1>Work Title: ${wd.worktitle}</h1>
            </div>

            <div class="work-description">
                <h2>Work Request:</h2>
                <div>${wd.getWorkrequest()}</div>
            </div>

            <hr>
            <c:if test="${requestScope.status eq 'edit'}">
            <div class="work-submission">
                <h2>Work Submission</h2>
                <div>
                       
                        <input type="hidden" name="wid" value="${requestScope.wid}">
                        <textarea id="workSubmit" name="workSubmit" rows="10" cols="100" required></textarea>
                        <hr>
                        <button type="submit" name="status" value="Ongoing">Save Work</button>
                        <button type="submit" name="status" value="Pending">Submit Work</button>
                </div>
            </div>
            </c:if>
            <c:if test="${ requestScope.status ne 'edit'}">
            <div class="work-submission">
                <h2>Work Submission</h2>
                <div>
                    ${wd.getWorksubmit()}
                </div>
            </div>
                    <c:if test="${wd.getWorkstatus() ne 'Finished' and wd.getWorkstatus() ne 'Pending'}">    
                        <input type="hidden" name="wid" value="${requestScope.wid}">
                        <input type="hidden" name="status" value="edit">
                        <input type="submit" value="Edit Work">                
                    </c:if>
      
            </c:if>    
            <hr>
            <div class="date blue">
                <h2>Work Start Date: ${wd.getWorkstartdate()}</h2>
                <h2>Work End Date: ${wd.getWorkstartdate()}</h2>
            </div>
            <hr>
            <div class="work-status">
                <h2>Work Status: 
                    <c:if test="${requestScope.status eq 'Finished'}">
                    <span class="green">${wd.getWorkstatus()}</span>
                    </c:if>
                    <c:if test="${requestScope.status eq 'Failed'}">
                    <span class="red">${wd.getWorkstatus()}</span>
                    </c:if>
                    <c:if test="${requestScope.status eq 'Pending'}">
                    <span class="blue">${wd.getWorkstatus()}</span>
                    </c:if>
                    <c:if test="${requestScope.status ne 'Pending' and requestScope.status ne 'Failed' and requestScope.status ne 'Finished'}">
                    <span class="gray">${wd.getWorkstatus()}</span>
                    </c:if>
                </h2>
            </div>
            <c:if test="${requestScope.check eq 'check'}">
                <input type="hidden" name="wid" value="${requestScope.wid}">
                <button type="submit" name="status" value="Finished">Pass!</button>
                <button type="submit" name="status" value="Failed">Failed!</button>
            </c:if>
         
        </c:forEach>
        </form>
    </div>
</body>
</html>
