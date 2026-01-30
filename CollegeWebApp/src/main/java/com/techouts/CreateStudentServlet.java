package com.techouts;

import java.io.IOException;

import com.structure.Student;
import com.structure.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateStudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final StudentDAO dao = new StudentDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try {
			int roll = Integer.parseInt(req.getParameter("rollNumber"));
			String name = req.getParameter("name");
			double cgpa = Double.parseDouble(req.getParameter("cgpa"));
			String gender = req.getParameter("gender");

			dao.insert(new Student(roll, name, cgpa, gender));

			resp.sendRedirect(req.getContextPath() + "/students/list");
		} catch (Exception e) {
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println("<p class='alert'>Create failed: " + e.getMessage() + "</p>"
					+ "<a class='button ghost' href='" + req.getContextPath() + "/pages/create.html'>Back</a>");
		}
	}
}
