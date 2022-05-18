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
 * Servlet implementation class AddRooms
 */
@WebServlet("/RoomAdd")
public class AddRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Query = "Insert into rooms values (?,?,?)"; 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rno=request.getParameter("rno");
		String nop=request.getParameter("nop");
		String stat=request.getParameter("roomstat");
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel" , "root" , "Root@2021"); 
			PreparedStatement ps= conn.prepareStatement(Query);
			ps.setString(1,rno);
			ps.setString(2, nop);
			ps.setString(3, stat);
			int s=ps.executeUpdate();
			if(s>0)
				pw.println("<script>window.alert('Rooms Added'); window.location.href='roomaddition.html'; </script>");
			else
				pw.println("<script>window.alert(' Something went wrong . Please try later '); window.location.href='roomaddition.html'; </script>");
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
