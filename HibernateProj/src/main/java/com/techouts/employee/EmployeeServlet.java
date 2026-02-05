package com.techouts.employee;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveEmployee")
public class EmployeeServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        Employee emp = new Employee(id, name, salary);
        try( SessionFactory sf = HibernateUtil.getSessionFactory()) {
        	Session session = sf.openSession();//open a session to connect database
            Transaction tx = session.beginTransaction();
            
            session.persist(emp); //for new Data INSERT
           
            tx.commit();//commit the transaction
            session.close();//close the session to reduce the load
            out.println("Employee inserted successfully!");
            out.println("<br>Do you Want to Check <a href='getAllEmployees'>Check</a><br><a href='index.jsp'>Home</a>");
        }catch(Exception e) {
        	out.println(e.getMessage()+"Something went Worng <a href='addEmployee.jsp'>addAgain</a>");
        }
    }
}
