<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.PrintWriter , java.sql.ResultSet , java.sql.SQLException , java.sql.Connection , java.io.IOException , java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
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

.flip-container {
	perspective: 1000;
}

.flipper {
	padding: 30px;
	width: 500px;
	height: 500px;
	position: relative;
	background-color: rgb(180, 180, 180);
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	border-radius: 20px;
	transition: 0.6s;
	transform-style: preserve-3d;
}

.front {
	position: absolute;
	padding: 10px 30px;
	top: 0;
	left: 0;
	right: 0;
	backface-visibility: hidden;
}

input {
	width: 100%;
	margin-bottom: 15px;
	height: 40px;
	box-sizing: border-box;
	padding: 10px;
}

input[type=radio] {
	border: 0px;
	left: -1px;
	width: 15px;
	height: 15px;
	width: 100%;
	height: 2em;
}

.title {
	text-align: center;
}
.navbar{
margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333333;
	float: right;
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
h1{
 top :0;
 left :0;
}
ul {
  list-style-type: none;
  top: 0;
  left : 0;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  width:100%;
}

li {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

</style>
<script>
function func()
{
	
	document.getElementById('Book').style.display = "block";
}

</script>
</head>
<body>
<%
  String name = (String)session.getAttribute("name");
  int c_id = (int)session.getAttribute("cid");
%>
<div id="navbar">
  <ul>
    
    <li><%
				   final String QUERY = "SELECT ROOM_NO , NO_OF_PERSON , ALLOC_STATUS FROM ROOMS where ALLOC_STATUS='Available' ";
				   PrintWriter pw = null;
			       java.sql.Statement s = null;
			       ResultSet rs = null;
			       boolean flag = false;
			       pw = response.getWriter();
			       try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
						if (conn != null)
				           s = conn.createStatement();
				        // execute the query
				        if (s != null)
				           rs = ((java.sql.Statement) s).executeQuery(QUERY);

				        // process the result
				        if (rs != null) {
				        	pw.println("<h1 align='center'> Available Room Details </h1><br> <table border=1 align='center'>"
				        			+ "<tr> <th>Room No </th> <th> No_Of_Persons</th><th>Alloctaion </tr>");
				        			
				        			
				           while (rs.next()) {
				              // display result
				              flag = true;
				              pw.println("<tr><td>" 
				                   + "Room No. : " + rs.getString("ROOM_NO")  + "</td><td>"
				                  + "Status: " + rs.getString("ALLOC_STATUS") + "</td><td>"
				                   + "No. of persons: " + rs.getDouble("NO_OF_PERSON") + "</td></tr>" );
				           }
				           
				        }
				        else{
				        	   pw.println("<script>window.alert(' Room Not Found '); </script>");
				           }
			       }
				        catch (SQLException e) {
							// TODO Auto-generated catch block
							String msg1 =e.getMessage();
					    	   pw.println(msg1);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							String msg1 =e.getMessage();
					    	pw.println(msg1);
						}
				%></li>
				<li><h1>
		Welcome
		<% out.println(name); %>
	</h1></li>
	
    <li><form action="Logout" method="post"><input type="submit" value="Logout"></form></li>
  </ul>
	
	<%
	    session.setAttribute("name","name");
	    session.setAttribute("cid","cid");
	%>
	
	
</div>
	<div id="mySidepanel" class="sidepanel">
		<a href="contact.html">Contact US</a> 
		<a href="#" onclick="func()">Book
			Rooms</a>
		 <a href="roomData">View Rooms</a>
	</div>
	
	<div id="Book" style="display: none;">
		<div class="flip-container">
			<div class="flipper" id="flipper">
				<div class="front">
				
					<form action="RoomBook" method="post">
					<h2>Your Customer ID is : <% out.println(c_id); %></h2>
						<label>Customer ID</label><input type="number"  name="cid"> <label>Customer Name </label><input type="text"
							name="name" placeholder="Enter your name"><br> <label>No
							of persons</label><input type="number" name="nop"
							placeholder="How many people are staying"><br> <label>Check-In</label>
						<input type="text" name="cin" placeholder="dd-mm-yyy"><br>
						<label>Check-out</label><input type="text" name="cout"
							placeholder="dd-mm-yyyy"><br> <label>Room No.
							</label><input type="number" name="rno" placeholder =" Enter preferred room no. according to availability"><br><input type="submit"
							value="submit">
					</form>
				</div>
			</div>
		</div>
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