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

import jakarta.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class ViewRoomDetails
 */
@WebServlet("/roomData")
public class ViewRoomDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String QUERY = "SELECT ROOM_NO , NO_OF_PERSON , ALLOC_STATUS FROM ROOMS ";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// variables
		String cssTag="<link rel='stylesheet' type='text/css' href='css/style.css'>";
	     PrintWriter pw = null;
	     java.sql.Statement s = null;
	     ResultSet rs = null;
	     boolean flag = false;
	     pw = response.getWriter();
	     HttpSession session= (HttpSession) request.getSession(false);
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
	        	pw.println("<html><head>" + cssTag + "</head><body ><h1 align='center'>Room Details </h1><br> <table border=1 align='center'>"
	        			+ "<tr> <th>Room No </th> <th> No_Of_Persons</th><th>Alloctaion </tr>");
	        			
	        			
	           while (rs.next()) {
	              // display result
	              flag = true;
	              pw.println("<tr><td>" 
	                   + "Room No. : " + rs.getString("ROOM_NO")  + "</td><td>"
	                  + "Status: " + rs.getString("ALLOC_STATUS") + "</td><td>"
	                   + "No. of persons: " + rs.getDouble("NO_OF_PERSON") + "</td></tr>" );
	           }
	           if(session != null)
	        	   pw.println("<center><form action='sign.html'>");
	             
	           else
	        	   pw.println("<center><form action='signup.html'>");
	           
	        	 pw.println("<center><input type='submit' value='Book Now'></form></center>" );  
	        }
	      

	        // Room not found
	        if (!flag) {
	           pw.println("<script>window.alert(' Room Not Found '); </script>");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String msg1 =e.getMessage();
	    	   pw.println(msg1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			String msg1 =e.getMessage();
	    	pw.println(msg1);
		}
	}	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
