package com.techouts;

import java.io.IOException;
import java.io.PrintWriter;
import com.structure.Student;
import com.structure.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UpdateStudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final StudentDAO dao = new StudentDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rollParam = req.getParameter("rollNumber");
		if (rollParam == null || rollParam.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/pages/update.html");
			return;
		}

		int roll = Integer.parseInt(rollParam);
		Student s = dao.findByRoll(roll);

		resp.setContentType("text/html; charset=UTF-8");//setting content type
		
		try (PrintWriter out = resp.getWriter()) {
			out.println("<!doctype html><html><head><meta charset='UTF-8'><title>Edit Student</title>");
			out.println("<link rel=\"stylesheet\" href=\"../assets/css/style.css\"></head><body><div class='container'>");
			out.println("<h1>Edit Student</h1>");
			if (s == null) {
				out.println("<p class='alert'>No student found with roll number " + roll + ".</p><br>");
				out.println("<a class='button ghost' href='" + req.getContextPath() + "/pages/update.html'>Back</a>");
			} else {
				out.printf("<form method='post' action='../students/update' autocomplete='off' novalidate><label>Roll Number<input type='number' name='rollNumber' value='%d' readonly></label>%n",
						s.getRollNumber());
				out.printf("<label>Name<input type='text' name='name' maxlength='20' value='%s' required></label>%n",
						escape(s.getName()));
				out.printf("<label>CGPA<input type='text' name='cgpa' value='%s' required></label>%n", s.getCgpa());
				out.printf("<label>Gender<input type='text' name='gender' maxlength='10' value='%s'></label>%n",
						escape(s.getGender()));
				out.println("<div class='row'><input type='submit' value='Update'>");
				out.println("</div></form>");
			}
			out.println("</div></body></html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			int roll = Integer.parseInt(req.getParameter("rollNumber"));
			String name = req.getParameter("name");
			double cgpa = Double.parseDouble(req.getParameter("cgpa"));
			String gender = req.getParameter("gender");
			System.out.println();
			dao.update(new Student(roll, name, cgpa, gender));
			resp.sendRedirect(req.getContextPath() + "/students/list");
		} catch (Exception e) {
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println("<p class='alert'>Update failed: " + e.getMessage() + "</p>"
					+ "<a class='button ghost' href='" + req.getContextPath() + "/pages/update.html'>Back</a>");
		}
	}

	private String escape(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'",
				"&#39;");
	}
}
