<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
	
	.content {
		position: static;
	}
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/WEB-INF/include/header.jsp" />
	</div>
	
	<div class="content">
		<h1>Either your username or password was entered incorrectly. Please try again.</h1>
		<a href="/InstantMessaging">Return to login screen</a>
	</div>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp" />
	</div>
</body>
</html>