package com.Data;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Servlet implementation class RoomBook
 */
@WebServlet("/RoomBook")
public class RoomBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String Query = "Insert into booking (room_no,cust_id,cin,cout,book_status,nop) values (?,?,?,?,?,?);"; 
		int rn = 0 ;
		String cid=request.getParameter("cid");
		String name=request.getParameter("name");
		int nop=Integer.parseInt(request.getParameter("nop"));
		String cin=request.getParameter("cin");
		String cout=request.getParameter("cout");
		int rno =Integer.parseInt(request.getParameter("rno"));
		/*
		 Date date = new Date(rn);
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	     */ 
		 PrintWriter pw = null;
	     pw = response.getWriter();
	     /*
	     try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				int rno[]= new int[50];
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021"); 
				String q="Select rand(room_no) from rooms where alloc_status='available' and no_of_person=?;";
				PreparedStatement ps= conn.prepareStatement(q);
				ps.setString(1,nop);
				ResultSet rs=null;
				rs=ps.executeQuery();
			    if(rs!=null)
			    {
			    	int x=0;
			    	while(rs.next()) {
			    		rno[x]=rs.getInt("room_no");
			    		x++;
			    	}
			    		
			    }
			    int rnd=new Random().nextInt(rno.length);
			    rn=rno[rnd];
			  
			}
	       catch(ClassNotFoundException e)
			{
	    	   String mesg = e.getMessage();
	    	   pw.println(mesg);
			}
	    String indate = null;
	     java.sql.Date indate1 = null;
				indate = formatter.format(cin);
				indate1 = java.sql.Date.valueOf(indate);
			
		       String outdate = null;
		       java.sql.Date outdate1 = null;
		     
				outdate = formatter.format(cout);
				outdate1 = java.sql.Date.valueOf(outdate);
			*/
		      
	     try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021"); 
				PreparedStatement ps= conn.prepareStatement(Query);
				
				ps.setInt(1, rno);
				ps.setString(2,cid);
				ps.setString(3,cin);
				ps.setString(4, cout);
				ps.setString(5, "Pending");
				ps.setInt(6, nop);
				
				
				int s=ps.executeUpdate();
				if(s>0)
					pw.println("<script>window.alert(' Boking Request Sent'); window.location.href='user.jsp'; </script>");
				else
					pw.println("<script>window.alert('Some error occured');</script>");
			}
	       catch(ClassNotFoundException e)
			{
	    	   String mesg = e.getMessage();
				pw.println(mesg);
			}
			catch(SQLException e)
			{
				String mesg = e.getMessage();
				pw.println(mesg);
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
