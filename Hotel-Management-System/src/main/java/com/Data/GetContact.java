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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class GetContact
 */
@WebServlet("/Contact")
public class GetContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String Query = "Insert into contact_us values (?,?,?,?)";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// variables
	     PrintWriter pw = null;
	     pw = response.getWriter();
	     String nme = request.getParameter("name");
	     String mil=request.getParameter("mail");
	     String phn=request.getParameter("phno");
	     String msg=request.getParameter("message");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021"); 
			PreparedStatement ps= conn.prepareStatement(Query);
			ps.setString(1,nme);
			ps.setString(2, mil);
			ps.setString(3, phn);
			ps.setString(4, msg);
			int s=ps.executeUpdate();
			if(s>0)
			{
				pw.println("<script>window.alert(' Message Sent Successfully'); window.location.href='index.html'; </script>");
				
			}
				
			   
			else
			{
				pw.println("<script>window.alert(' Something went wrong . Please try later '); window.location.href='contact.html'; </script>");
				
			}
				
			
		}
       catch(ClassNotFoundException e)
		{
    	   String msg1 =e.getMessage();
    	   pw.println(msg1);
		}
		catch(SQLException e)
		{
			String msg1 =e.getMessage();
	    	pw.println(msg1);
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
