package com.Data;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Allocation
 */
@WebServlet("/allocation")
public class Allocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = (HttpSession) request.getSession(false);
		
		 String book_id = (String)session.getAttribute("bookid");
		  final String QUERY = "UPDATE BOOKING SET BOOK_STATUS='BOOKED' WHERE BOOK_ID=?";
		  final String QUERY1= "UPDATE ROOMS SET ALLOC_STATUS='Alloted'";
		  PrintWriter pw= response.getWriter();
		  java.sql.Statement s = null;
		  try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
				PreparedStatement ps=conn.prepareStatement(QUERY);
				ps.setString(1, book_id);
				
				int rs = ps.executeUpdate();
				int r =0;
				
				if (conn != null)
			           s = conn.createStatement();
			        // execute the query
			        if (s != null)
			           r = ((java.sql.Statement) s).executeUpdate(QUERY1);

				if(rs>0 && r>0)
					pw.println("<script>window.alert('Booking confirmed'); window.location.href('admin.jsp');</script>");
				else
					pw.println("<script>window.alert('Something went wrong'); window.location.href('allocation');</script>");
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
