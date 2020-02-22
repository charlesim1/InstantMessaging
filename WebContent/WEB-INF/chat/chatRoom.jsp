<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Stack" %>
<%@ page import="com.InstantMessaging.message.ChatLog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
	
	body {
		margin: 0;
	}
	
	.content {
		text-align: center;
	}
	
	.chatBox { 
		left: 300px;
		width: 35%; 
		height: 220px; 
		overflow: auto;  
		margin-left: 32.5%;
		text-align: center;
		border: 2px solid black;
 	} 
 	
 	#chatInput {
 		left: 300px;
		width: 525px; 
 	}
</style>
</head>
<script>
	<%
		// sent from the servlet
		String userLogs = (String) request.getAttribute("userLogs");
		String userId = (String) request.getAttribute("userId");
		String friendId = (String) request.getAttribute("friendId");
		
		pageContext.setAttribute("userLogs", userLogs);
		pageContext.setAttribute("userId", userId);
		pageContext.setAttribute("friendId", friendId);
	%> 
	var userLogs = ${userLogs};
	var userId = '${userId}'
	var friendId = '${friendId}';
	var lastUpdatedChatNum = 0; // this variable is changed after page is loaded 
								// to represent the number at which the current chat 
								// messages are 
	
	function printOut() {
		console.log(userLogs);
		console.log(friendId);
	}
	
	function loadChat() {
		for (var i = 0; i < userLogs.length; i += 1) {
			if (userLogs[i].isUser) {
				$('.chatBox').append(
					$('<p style="text-align:right; margin-right: 15px;">me: ' + userLogs[i].message + '</p>')
				);
			} else {
				$('.chatBox').append(
					$('<p style="text-align:left; margin-left: 15px;">' + friendId + ': ' + userLogs[i].message + '</p>')
				);
			}
			if (lastUpdatedChatNum < userLogs[i].num) {
				lastUpdatedChatNum = userLogs[i].num;
				console.log(lastUpdatedChatNum);
			}
		    $('.chatBox').animate({ scrollTop: lastUpdatedChatNum * 20}, 50);
// 			$('#chatBox').scroll();
		}
	}
	
	function testfunction(data) {
		if (data === '') {
			return;
		}
		userLogs = jQuery.parseJSON(data);
		loadChat();
	}
	
	function updateChat() {
		userLogs = '';
		// will call another function that refreshes the chat log... 
		$.ajax({
	    		type : 'POST',
				url : 'refreshMessages',
				data : { 
							'chatNum' : lastUpdatedChatNum, 
							'userId'   : userId,
							'friendId' : friendId
						},
				dataType : 'text',
				success: testfunction
		})
		
	}
	
	$(document).ready(function() {
		loadChat();
		
		/*
			This function activates when the enter key is pressed inside the textbox.
			It creates an ajax call to the servlet that updates the new message inside the textbook
			and send it into the database.
		*/
		$('#chatInput').keypress(function(event){
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){
		    	console.log("ajax request called");
		    	$.ajax({
		    		type : 'POST',
					url : 'sendMessage',
					data : { 
								'message' : $('#chatInput').val(), 
								'userId'   : userId,
								'friendId' : friendId
							},
					dataType : 'text',
					success: updateChat,
		    	})
				$('#chatInput').val(''); // sets the value of the input box to an empty box
		    }
		});
		
		window.setInterval(function(){
			updateChat();
		}, 1000);
	});
</script>
<body>
	<div class="header">
		<br>
		<jsp:include page="/WEB-INF/include/header.jsp"/>
		<br>
	</div>
	
	<div class="content">
		<h3>Chatting with ${friendId}</h3>
		<div class="chatBox">
		
		</div>
		<br>
		<input id=chatInput type="text" placeholder="After typing, press enter to send the message!">
	</div>
	
	<script>printOut();</script>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
</html>


















