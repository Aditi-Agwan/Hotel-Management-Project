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
import java.sql.SQLException;

/**
 * Servlet implementation class SignUpAuth
 */
@WebServlet("/SignUp")
public class SignUpAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Query = "Insert into customer (name,contact_no,email,password) values (?,?,?,?)"; 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw = null;
	     pw = response.getWriter();
	     String nme = request.getParameter("name");
	     String mil=request.getParameter("mail");
	     String phn=request.getParameter("phno");
	     String pass=request.getParameter("pass");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021"); 
			PreparedStatement ps= conn.prepareStatement(Query);
			ps.setString(1,nme);
			ps.setString(2, phn);
			ps.setString(3, mil);
			ps.setString(4, pass);
			int s=ps.executeUpdate();
			if(s>0)
				pw.println("<script>window.alert(' Registered Successfully'); window.location.href='sign.html'; </script>");
			else
				pw.println("<script>window.alert(' Something went wrong . Please try later '); window.location.href='sigup.html'; </script>");
		}
       catch(ClassNotFoundException e)
		{
    	   String msg = e.getMessage();
    	   pw.println(msg);
		}
		catch(SQLException e)
		{
			String msg = e.getMessage();
			pw.println(msg);
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
