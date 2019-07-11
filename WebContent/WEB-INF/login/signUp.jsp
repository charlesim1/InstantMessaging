<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instant Messaging</title>
<style>
	.header {
  		position: static; 
		left: 0;
		top: 0;
		width: 100%;
		background-color: pink;
		text-align: center;
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
	
	<h2 align="center">Thanks for singing up!</h2>
	<p align="center" href="/InstantMessaging">Return back to home page</p>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
</html>