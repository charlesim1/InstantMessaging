<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.InstantMessaging.connections.LoginConnection" %>
<%@ page import="java.sql.*" %>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Instant Messaging</title>
<script>
	/*
		This function confirms to see if the user has inputted all the required 
		fields before submitting their information to the database.
	*/
	function confirmEverything() {
		console.log('test');
		if (document.getElementById('name').value == '') {
			alert('Please enter your name.');
			return false;
		} else if (document.getElementById('email').value == '') {
			alert('Please enter your email.');
			return false;
		} else if (document.getElementById('id').value == '') {
			alert('Please enter your id.');
			return false;
		}
		else if (document.getElementById('password').value == '') {
			alert('Please enter your password.');
			return false;
		}
		// checks to see whether or not the two passwords are the same
		if ((document.getElementById('password').value).localeCompare(document.getElementById('password2').value)) {
			alert('The two passwords must be the same.');
			return false;
		}
		return true;
	};

</script>
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
		top: 200px;
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
		<form action="signUpComplete" onsubmit="return confirmEverything()" method="POST">
		
			<table id="user_info" border="1" align="center">
				<tr>
					<td>Name: <input type="text" id="name" name="name"></td>
				</tr>
				<tr>
					<td>Email: <input type="text" id="email" name="email"></td>
				</tr>
				<tr>
					<td>
						Id: <input type="text" id="id" name="id">
						<!-- This is not implemented yet... Will be implemented later with ajax -->
						<input type="button" id="checkId" value="See Availability"> 
					</td>

				</tr>
				<tr>
					<td>Password: <input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td>Confirm Password: <input type="password" id="password2"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
				</tr>
				<tr>
					<td><a href="/InstantMessaging" id="redirect">Back to home page</a></td>
				</tr>
			</table>
			
		</form>
	</div>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
</html>