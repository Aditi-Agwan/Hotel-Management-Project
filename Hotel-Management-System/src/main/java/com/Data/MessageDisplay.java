package com.Data;
import java.sql.Driver;
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

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class ViewRoomDetails
 */
@WebServlet("/message")
public class MessageDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String QUERY = "SELECT * FROM contact_us; ";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// variables
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
	        	pw.println("<html><body style='bgcolor:lightgray'><h1 align='center'>Message Notifications </h1><br> <table border=1 align='center'>"
	        			+ "<tr> <th>Name </th> <th> Email</th><th>Mobile Number</th><th> Message </th> </tr>");
	        			
	           while (rs.next()) {
	              // display result
	              flag = true;
	              pw.println( "<tr><td>" +
	            		   rs.getString("NAME") + "</td><td>" 
	                  +  rs.getString("EMAIL") + "</td><td>" 
	                   +  rs.getString("MOB_NO") + "</td><td>"
	                   + rs.getString("MESSAGE") + "</td></tr>" );
	           }
	        }

	        // Room not found
	        if (!flag) {
	           pw.println("<h1>Room Not Found.</h1>");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
