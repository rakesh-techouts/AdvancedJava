package com.techouts;

import com.structure.Student;
import com.structure.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ListStudentsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final StudentDAO dao = new StudentDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> students = dao.findAll();

		resp.setContentType("text/html; charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("<!doctype html><html><head><meta charset='UTF-8'><title>Students</title>");
			out.println("<link rel='stylesheet' href='../assets/css/style.css'></head><body><div class='container'>");
			out.println("<h1>Students</h1>");
			out.println("<table><thead><tr><th>Roll</th><th>Name</th><th>CGPA</th><th>Gender</th></tr></thead><tbody>");
			for (Student s : students) {
				out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td></tr>%n", s.getRollNumber(),
						escape(s.getName()), s.getCgpa(), escape(s.getGender()));
			}
			out.println("</tbody></table>");
			out.println("<br><a class='button ghost' href='../index.html'>Back</a>");
			out.println("<a href='../students/export' class='button'>Download</a>");
			out.println("</div></body></html>");
		}
	}

	private String escape(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'",
				"&#39;");
	}
}
