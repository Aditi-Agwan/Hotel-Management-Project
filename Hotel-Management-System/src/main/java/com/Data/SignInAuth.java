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

import javax.servlet.RequestDispatcher;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class SignInAuth
 */
@WebServlet("/SignIn")
public class SignInAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	/* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = null;
	   
	    ResultSet rs = null;
	    
	    pw = response.getWriter();
	   
	    String name =request.getParameter("username");
	    String pass=request.getParameter("pass");
		String us_type = request.getParameter("usertype");
		switch(us_type)
		{
		 case "User" : 
			final String QUERY1 = "SELECT * from customer where NAME=? AND password=?";
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
					PreparedStatement ps=conn.prepareStatement(QUERY1);
					ps.setString(1, name);
					ps.setString(2, pass);
					rs=ps.executeQuery();
					
					if(rs!=null)
						
					{
						
						while(rs.next())
						{
							String name1 = rs.getString("name");
							int cid= rs.getInt("c_id"); 
							HttpSession oldSession = request.getSession(false);
							if(oldSession!=null)
								oldSession.invalidate();
							HttpSession session=(HttpSession) request.getSession(true);
							session.setMaxInactiveInterval(5*60);
							session.setAttribute("name", name1);
							session.setAttribute("cid", cid);
							response.sendRedirect("user.jsp");
							
						}
						
					}
					
					if(rs.next()==false){
						pw.println("<script>window.alert(' Either Username or Password is Incorrect . Please Relogin'); window.location.href='sign.html'; </script>");
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
			
			break;
		 case "Admin" :
			 final String QUERY2 = "SELECT * from employee where name=? AND password=? AND post=? ";
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
					PreparedStatement ps=conn.prepareStatement(QUERY2);
					ps.setString(1, name);
					ps.setString(2, pass);
					ps.setString(3, "Admin");
					rs=ps.executeQuery();
					if(rs!=null)
						
					{
						while(rs.next())
						{
							String name1 = rs.getString("name");
							int eid= rs.getInt("e_id"); 
							HttpSession oldSession = request.getSession(false);
							if(oldSession!=null)
								oldSession.invalidate();
							HttpSession session=(HttpSession) request.getSession();
							session.setMaxInactiveInterval(5*60);
							session.setAttribute("name", name1);
							session.setAttribute("eid", eid);
							response.sendRedirect("admin.jsp");
						}
						
					}
					if(rs.next()==false) {
						pw.println("<script>window.alert(' Either Username or Password is Incorrect . Please Relogin'); window.location.href='sign.html'; </script>");
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
			
			break;
		 case "Receptionist" :
			 final String QUERY3 = "SELECT * from employee where name=? AND password=? AND post=? ";
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
					PreparedStatement ps=conn.prepareStatement(QUERY3);
					ps.setString(1, name);
					ps.setString(2, pass);
					ps.setString(3, "Receptionist");
					rs=ps.executeQuery();
					if(rs!=null)
						
					{
						while(rs.next())
						{
							String name1 = rs.getString("name");
							int eid= rs.getInt("e_id"); 
							HttpSession oldSession = request.getSession(false);
							if(oldSession!=null)
								oldSession.invalidate();
							HttpSession session=(HttpSession) request.getSession();
							session.setMaxInactiveInterval(5*60);
							session.setAttribute("name", name1);
							session.setAttribute("eid", eid);
							response.sendRedirect("reception.jsp");
						}
						
					}
					else {
						pw.println("<script>window.alert(' Either Username or Password is Incorrect . Please Relogin'); window.location.href='sign.html'; </script>");
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
			break;
		case "Staff" :	
			 final String QUERY4 = "SELECT * from employee where name=? AND password=? AND post=? ";
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021");
					PreparedStatement ps=conn.prepareStatement(QUERY4);
					ps.setString(1, name);
					ps.setString(2, pass);
					ps.setString(3, "Staff");
					rs=ps.executeQuery();
					if(rs!=null)
						
					{
						while(rs.next())
						{
							String name1 = rs.getString("name");
							int eid= rs.getInt("e_id"); 
							HttpSession oldSession = request.getSession(false);
							if(oldSession!=null)
								oldSession.invalidate();
							HttpSession session=(HttpSession) request.getSession();
							session.setMaxInactiveInterval(5*60);
							session.setAttribute("name", name1);
							session.setAttribute("eid", eid);
							response.sendRedirect("staff.jsp");
						}
						
					}
					else {
						pw.println("<script>window.alert(' Either Username or Password is Incorrect . Please Relogin'); window.location.href='sign.html'; </script>");
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
			break;
			
		}
	
		
	}

}
