package com.techouts.employee;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;

@WebServlet("/retrive")
public class RetriveById extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			int id =Integer.parseInt(request.getParameter("id"));
			Employee employee=session.get(Employee.class,id);
			if(employee!=null) {
				request.setAttribute("employee",employee);
				request.getRequestDispatcher("displayDetails.jsp").forward(request,response);
			}else {
				out.println("Employee details not!Found Enter Valid Id");
				response.sendRedirect("update.jsp");
			}
		}catch(Exception e) {
			out.println("Something went wrong! "+e.getMessage());
		}
	}
}
