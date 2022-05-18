<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%

@ page import="java.sql.Driver";


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpSession;


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  int nop = (int)session.getAttribute("nop");
  final String QUERY = "SELECT room_no from rooms where no_of_person=? AND alloc_status=?";
  PrintWriter pw= request.getWriter();
  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
		PreparedStatement ps=conn.prepareStatement(QUERY);
		ps.setString(1, nop);
		ps.setString(2, "Available" );
		rs=ps.executeQuery();
		pw.println("<h1>Select a room </h1><br><form action='allocate'>");
		if(rs!=null)
			
		{
			while(rs.next())
			{
				int rn = rs.getInt("room_no");
			    pw.println("<label>" + rs.getInt("room_no") + "</label><input type = 'radio' name='room' value='rn'>");
			}
		}
		pw.println("<button>Submit</button></form>");
  }
  catch (SQLException e) {
		// TODO Auto-generated catch block
		String msg1 =e.getMessage();
  	   pw.println(msg1);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		String msg1 =e.getMessage();
  	pw.println(msg1);
	};
  

%>
</body>
</html>