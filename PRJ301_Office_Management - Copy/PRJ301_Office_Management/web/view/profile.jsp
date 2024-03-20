<%-- 
    Document   : profile
    Created on : Mar 13, 2024, 9:05:24 PM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">


<title>profile with data and skills - Bootdey.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/profile.css"/>
<!--<style type="text/css">
    	body{
    margin-top:20px;
    color: #1a202c;
    text-align: left;
    background-color: #e2e8f0;    
}
.main-body {
    padding: 15px;
}
.card {
    box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
}

.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: .25rem;
}

.card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1rem;
}

.gutters-sm {
    margin-right: -8px;
    margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
    padding-right: 8px;
    padding-left: 8px;
}
.mb-3, .my-3 {
    margin-bottom: 1rem!important;
}

.bg-gray-300 {
    background-color: #e2e8f0;
}
.h-100 {
    height: 100%!important;
}
.shadow-none {
    box-shadow: none!important;
}

    </style>-->
</head>
<body>

<div class="container">
<div class="main-body">
    <nav aria-label="breadcrumb" class="main-breadcrumb">
<ol class="breadcrumb">
<li class="breadcrumb-item"><a href="index.html">Home</a></li>
<li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
<li class="breadcrumb-item active" aria-current="page">User Profile</li>
</ol>
</nav>
<div class="row gutters-sm">
<div class="col-md-4 mb-3">


</div>
      <c:set var="user" value="${requestScope.User}" />
    <div class="col-md-12">
        <div class="col-md-6 mb-4">
<div class="card">
<div class="card-body">
<div class="d-flex flex-column align-items-center text-center">
    
<c:set var="gender" value="${user.gender ?  'Female' : 'Male'}" />    
<c:if test="${gender == 'Male'}">
    <img src="/PRJ301_Office_Management/img/maleicon.png" alt="Admin" class="rounded-circle" width="150">
</c:if>
<c:if test="${gender == 'Female'}">
    <img src="/PRJ301_Office_Management/img/femaleicon.png" alt="Admin" class="rounded-circle" width="150">
</c:if>
    
<!--<img src="/PRJ301_Office_Management/img/femaleicon.png" alt="Admin" class="rounded-circle" width="150">-->
<div class="mt-3">
<h4>${user.uid}</h4>
</div>
</div>
</div>
</div>
</div>
        <div class="card mb-3">
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Full Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        ${user.name}
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Gender</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        ${user.gender ?  'Female' : 'Male'}
                    </div>
                </div>
                <hr>
                
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
<!--                        <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="51373821113b243a3c24397f303d">[email&nbsp;protected]</a>-->
                            ${user.email}
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        ${user.phone}
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Address</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        ${user.address}
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Start Work Date</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        ${user.startdate}
                    </div>
                </div>
                <hr>

    </div>
    </div>

    </div>
    </div>
    </div>
</div>

<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>
