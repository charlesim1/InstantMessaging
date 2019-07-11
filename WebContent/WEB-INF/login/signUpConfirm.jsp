<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Instant Messaging</title>
<style>
	.header {
  		position: fixed; 
		left: 0;
		top: 0;
		width: 100%;
		background-color: pink;
		text-align: center;
	}
	
	.content {
		position: relative;
		text-align: center;
		top: 270px;
	}
	
	.footer {
		position: fixed;
		left: 0;
		bottom: 0;
		width: 100%;
		background-color: skyblue;
		color: black;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/WEB-INF/include/header.jsp"/>
	</div>
	
	<div class="content">
		<h1>SIGN UP SUCCESSFUL!!</h1>	
		<a href="/InstantMessaging" id="redirect">Back to home page</a>
	</div>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
</html>