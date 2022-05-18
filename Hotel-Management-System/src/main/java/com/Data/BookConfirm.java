package com.Data;

import jakarta.servlet.ServletException;

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

/**
 * Servlet implementation class BookConfirm
 */
@WebServlet("/bookconfirm")
public class BookConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String QUERY = "SELECT * FROM booking where book_status='Pending' ; ";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		        	/* HttpSession session=(HttpSession) request.getSession();
		        	session.setAttribute("bookid", "rs.getString('book_id')");
		        	session.setAttribute("cusid", "rs.getString('cust_id')");
		        	session.setAttribute("cin", "rs.getString('cin')");
		        	session.setAttribute("cout", "rs.getString('cout')");
		        	session.setAttribute("nop", "rs.getString('nop')");
		        	*/
		        	HttpSession session=(HttpSession) request.getSession();
		        	pw.println("<html><body style='bgcolor:lightgray'><h1 align='center'>Booking Requests </h1><br> <table border=1 align='center'>"
		        			+ "<tr><th>Booking id</th> <th>Customer Id </th> <th>Check-in </th><th>Check-out</th><th>No. of persons</th><th>Room no</th><th> Edit</th> </tr>");	
		           while (rs.next()) {
		              // display result
		              flag = true;
		              session.setAttribute("bookid",rs.getString("book_id"));
		              pw.println( "<tr><td>" +
		            		   rs.getString("book_id") + "</td><td>" 
		                  +  rs.getString("cust_id") + "</td><td>" 
		                   +  rs.getString("cin") + "</td><td>"
		                   + rs.getString("cout") + "</td><td>"
		                   + rs.getString("nop") + "</td><td>"
		                   + rs.getString("room_no") +"</td><td>"
		                   + "<a href='allocation'><button style='background-color : green'>Allocate</button></a></td></tr>");
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
