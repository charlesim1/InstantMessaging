<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	/* #content {
		float: center
	}   */
	#login {
		text-decoration: underline;
/* 		margin: 10px; */
		align: center;
	}
	#user {
		position: relative;
		left: 1px;
	}
	#sendLogin {
		position: relative; 
		top: 10px;
		float: left;
		margin: 3px;
		margin-right: 7px;
	}
	#newUser {
		position: static;
		float: right;
		margin-right: 10px;
	}
</style>
</head>
<body id="">
	<div id="content">
	
	<!-- test making table for login and new users -->
	<!-- rowspan and colspan dont matter if there is only one entry -->
		<table border="1">
			<tr>
				<td>
					<h3 id="login">Login:</h3> <p></p>
					<form action="send" method="POST">
						user: <input type="text" id="user" name="user"/> <br><br>
						pass: <input type="password" id="pass" name="pass"/> <br><br>
						<input type="submit"/>
					</form>
<%-- 					<a href="${pageContext.request.contextPath}/newUser.jsp" >click</a> --%>
					<a href="/InstantMessaging/signupForm" id="newUser">new user?</a>
				</td>
			</tr>
			
		</table>
	
		<!-- <h3 id="login">Login:</h3>
		
		<form action="send">
			user: <input type="text" id="user" name="user"/> <br>
			pass: <input type="password" id="pass" name="pass"/> <br>
			<input type="submit"/>
		</form> -->
		
	</div>
</body>
</html>












