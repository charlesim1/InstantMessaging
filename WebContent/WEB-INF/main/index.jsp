<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
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
	
	.content {
		position: static;
	}
	
	body {
		margin: 0;
	}
</style>
</head>
<body>
	<div class="header">
	<br>
		<jsp:include page="/WEB-INF/include/header.jsp"/>
		<br>
	</div>
	
	<div class="content">
		<jsp:include page="/WEB-INF/include/content.jsp"/>
	</div>
	<br>
	
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
</html>















