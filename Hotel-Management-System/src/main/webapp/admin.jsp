<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<style>
/* The sidepanel menu */
.sidepanel {
	height: 250px; /* Specify a height */
	width: 0; /* 0 width - change this with JavaScript */
	position: fixed; /* Stay in place */
	z-index: 1; /* Stay on top */
	top: 0;
	left: 0;
	background-color: #111; /* Black*/
	overflow-x: hidden; /* Disable horizontal scroll */
	padding-top: 60px; /* Place content 60px from the top */
	transition: 0.5s;
	/* 0.5 second transition effect to slide in the sidepanel */
}

/* The sidepanel links */
.sidepanel a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
	transition: 0.3s;
}

/* When you mouse over the navigation links, change their color */
.sidepanel a:hover {
	color: #f1f1f1;
}

/* Position and style the close button (top right corner) */
.sidepanel .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
}

/* Style the button that is used to open the sidepanel */
.openbtn {
	font-size: 20px;
	cursor: pointer;
	background-color: #111;
	color: white;
	padding: 10px 15px;
	border: none;
}

.openbtn:hover {
	background-color: #444;
}
body, html {
	height: 100%;
}

body {
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: lightgray;
}
</style>
</head>
<body>
	<%  String name = (String)session.getAttribute("name"); %>
    <% session.setAttribute("name",name); %>
	<h1>Welcome <% out.println(name); %></h1>
	<form action="Logout" method="post"><input type="submit" value="Logout"></form>
	<div id="mySidepanel" class="sidepanel">
		<a href="message">View messages</a> <a href="roomManage">Manage Rooms</a> <a
			href="bookconfirm">Confirm Bookings</a>
	</div>

	<button class="openbtn" onclick="openNav()">&#9776;</button>
	<script>
function openNav() {
	  document.getElementById("mySidepanel").style.width = "250px";
	}

	/* Set the width of the sidebar to 0 (hide it) */
	function closeNav() {
	  document.getElementById("mySidepanel").style.width = "0";
	}
</script>
</body>
</html>