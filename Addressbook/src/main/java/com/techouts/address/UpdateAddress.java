package com.techouts.address;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * Servlet implementation class EditAddress
 */
@WebServlet("/updateAddress")
public class UpdateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		Integer id = null;
		try {
			id = Integer.valueOf(idStr);
		} catch (Exception ex) {
			response.sendRedirect("list_contacts.jsp?msg=" + "Invalid ID.");
			return;
		}
		if (name == null || name.isBlank() || phone == null || phone.isBlank()) {
			response.sendRedirect("edit_contact.jsp?id=" + id + "&msg=" +"Name and Phone are required.");
			return;
		}

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con
				.prepareStatement("UPDATE contacts SET name=?, phone=?, email=?, address=? WHERE id=?")) {
			ps.setString(1, name.trim());
			ps.setString(2, phone.trim());
			ps.setString(3, (email == null ? null : email.trim()));
			ps.setString(4, (address == null ? null : address.trim()));
			ps.setInt(5, id);
			int rows = ps.executeUpdate();
			String m = rows > 0 ? "Contact updated successfully." : "Contact not found.";
			response.sendRedirect("list_contacts.jsp?msg=" + m);
		} catch (Exception e) {
			response.sendRedirect("list_contacts.jsp?msg=" +"Error updating contact: " + e.getMessage());
		}
	}

}
