package com.techouts.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;

/**
 * Servlet implementation class GetAllEmployees
 */
@WebServlet("/getAllEmployees")
public class GetAllEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try(Session session=HibernateUtil.getSessionFactory().openSession()) {
			List<Employee> list=session.createQuery("FROM Employee",Employee.class).list();
			session.close();
			if(list!=null) {
				request.setAttribute("list",list);
				request.getRequestDispatcher("displayEmployees.jsp").forward(request,response);
				
			}else {
				out.println("Records Not Found!");
			}
		}
	}

}
