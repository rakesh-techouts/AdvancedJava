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
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/updateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		double salary=Double.parseDouble(request.getParameter("salary"));
		try(Session session =HibernateUtil.getSessionFactory().openSession()) {
			Transaction t= session.beginTransaction();
			Employee emp=session.get(Employee.class,id);
			emp.setName(name);
			emp.setSalary(salary);
			t.commit();
			out.println("Updated Successfully");
			out.println("<br>Do you Want to Check <a href='getAllEmployees'>Check</a><br><a href='index.jsp'>Home</a>");
		}catch(Exception e) {
			out.println("Error! "+e.getMessage());
		}
	}
}
