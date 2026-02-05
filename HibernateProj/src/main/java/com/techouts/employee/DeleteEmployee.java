package com.techouts.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/deleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		try(Session session=HibernateUtil.getSessionFactory().openSession()) {
			Employee emp=session.get(Employee.class,id);
			if(emp!=null) {
				Transaction tk=session.beginTransaction();
				session.delete(emp);
				tk.commit();
				session.close();
				out.println("Employe Deleted SuccessFully");
				out.println("<a href='index.jsp'>Home</a>");
			}else {
				out.println("Employe Does Not existed");
			}
		}catch(Exception e) {
			
		}
	}
}
