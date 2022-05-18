package com.Data;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = null;
	    pw= response.getWriter();
	    int f=0;
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null)
		{
			session.invalidate();
			pw.println("<script>window.alert('Logged out successfully'); window.location.href='sign.html'; </script>");
			f=1;
		}
			
		else
			pw.println("<script>window.alert(' User already Logged out'); window.location.href='sign.html'; </script>");
		if(f==0)
			pw.println("<script>window.alert('Something went wrong'); window.location.href='sign.html'; </script>");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
