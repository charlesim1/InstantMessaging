<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	.content {
		position: static;
	}
	
	#success {
		display: block;
		text-align: center;
	}
	
	#friendList { 
		left: 300px;
		width: 25%; 
		height: 200px; 
		overflow: auto;  
		margin-left: 38%;
		text-align: center;
		border: 2px solid black;
 	} 
 	
 	#addFriend {
 		display: block;
 		text-align: center;
 	}
 	
 	.friend {
 		cursor: pointer;
 	}
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/WEB-INF/include/header.jsp"/>
	</div>
	
	<div class="content">
		<h1 id="success">SUCCESSFUL LOGIN!!</h1>
		<h3 id="success">Friend List</h3>
		
		<!-- This Friends List is displayed via an ajax request from the user's friends -->
		<div id="friendList">
		</div>
		
		<br><br>
		<div id="addFriend">
			<input type="text" id="friendName" name="addFriend" placeholder="Enter a friend's id!">
			<button id="checkFriend">Add Friend!</button> 
			<br>
<!-- 			<p visibility="hidden"></p> 
				trying to make this part hidden so that when ajax is called from the button,
				it can say if the addFriend was successful or not.
-->
		</div>
	</div>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/include/footer.jsp"/>
	</div>
</body>
<script>
	
	$(document).ready(function(){
		
// 		setInterval(function() { // checks the user's friendlist and updates every 5 seconds (in case they added more friends)
	    	
// 		}, 5000);

		$.ajax({
			type : 'GET',
			url : 'getFriends',
			data : { 
				'userId'   : '${ id }'
			},
			dataType : 'text',
			success: displayFriends,
		});
		
	    $("#checkFriend").click(function(){
	       addFriend();
	    });
	    
	});
	
	function redirectPage(friendId) {
// 		$.ajax({
// 			type : 'POST',
// 			url : 'messagingPage',
// 			data : {
// 				'userId'   : '${ id }',
// 				'friendId' : friendId
// 			},
// 			success: function(response){
// // 			    window.location.href = response.redirect;
// 				console.log(response);
// 			}
// 		});
		return friendId
	}
	
	/*
		This is the function that is called after a user clicks on a friend's id in the friend list.
		This function uses jquery to submit the information of friend's id to the servlet that processes
		the chat logs from the two users. 
	*/
	function sendToServlet(formName) {
		var formId = '#' + formName + 'Form';
		console.log(formId);
		$(formId).trigger('submit');
	}
	
	/*
		This function gets the friends list from the database, splits all the separate friends
		into an array and adds as many links to the friend's messaging pages as needed. 
	*/
	function displayFriends(allFriends) {
		var friendList = allFriends.split(',');
		
		for (var i = 0; i < friendList.length; i += 1) {
			$('#friendList').append(
				$('<form id="' + friendList[i] + "Form" + '" method="POST" action="messagingPage">'+
				'<input type="hidden" name="userId" id="userId" value="${id}">'+
				'<input type="hidden" name="friendId" id="friendId" value="' + friendList[i] + '">'+
				'<br><a id="' + friendList[i] + '" onclick="sendToServlet(this.id)" class="friend">' + friendList[i] + '</a><br></form>')
			);	
		}
	}
	
	/*
		This function calls an ajax request to the addFriend servlet
		and checks if the entered "id" is available to be added to the user's
		friend list.
	*/
	function addFriend() {
		$.ajax({
			type : 'GET',
			url : 'addFriend',
			data : { 
						'friendId' : $('#friendName').val(), 
						'userId'   : '${ id }'
					},
			dataType : 'text',
		});
	};
	
	/*
		Updates the hidden text when a friend was either successfully or 
		unsuccessfully added.
	*/
	function whenSuccess() {
		
		
	};
	
</script>
</html>




















