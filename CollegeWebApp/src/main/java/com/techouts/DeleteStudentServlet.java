package com.techouts;


import com.structure.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class DeleteStudentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final StudentDAO dao = new StudentDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            int roll = Integer.parseInt(req.getParameter("rollNumber"));
         
            if(dao.delete(roll)==0) {
            	out.println("<!doctype html><html><head><meta charset='UTF-8'><title>Students</title>");
    			out.println("<link rel='stylesheet' href='../assets/css/style.css'></head><body><div class='container'>");
                resp.getWriter().println("<h3>No Details Found to delete</h3>"
                        + "<a class='button ghost' href='" + req.getContextPath() + "/pages/delete.html'>Back</a>");
                out.println("</body></html>");
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/students/list");
        } catch (Exception e) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<p class='alert'>Delete failed: " + e.getMessage() + "</p>"
                    + "<a class='button ghost' href='" + req.getContextPath() + "/pages/delete.html'>Back</a>");
        }
    }
}
