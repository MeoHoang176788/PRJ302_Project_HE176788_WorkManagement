<%-- 
    Document   : login
    Created on : Mar 9, 2024, 5:47:17 PM
    Author     : LAPTOP 247
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all" /> <!-- Style-CSS --> 
        <link rel="stylesheet" href="css/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
        <link href="//fonts.googleapis.com/css?family=Snippet" rel="stylesheet"><!--online fonts-->
        <title>JSP Page</title>
        <style>
            .error{
                font-size: 18px;
                color: #f44336;
                font-weight: normal;
                font-style: italic;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div data-vide-bg="video/keyboard">
	<div class="main-container">
		<!--header-->
		<div class="header-w3l">
			<h1>Office Login</h1>
		</div>
		<!--//header-->
		<!--main-->
		<div class="main-content-agile">
			<div class="w3ls-pro">
				<h2>Login Now</h2>
			</div>
			<div class="sub-main-w3ls">	
				<form action="login" method="post">
					<input placeholder="Enter your E-mail" name="email" type="email" required="">
					<input  placeholder="Enter Password" name="password" type="password" required="">	
                                        <%
                                            if(request.getAttribute("rs")!=null){
                                                boolean status=(Boolean) request.getAttribute("rs");
                                        %>
                                            <div class="error">
                                                    User or password is wrong
                                            </div>

                                        <%
                                            }
                                        %>
					<input type="submit" value="Login">
				</form>
			</div>

		</div>
                
		<!--//main-->
	</div>
</div>
    </body>
</html>
