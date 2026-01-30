package com.techouts.address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAddress
 */
@WebServlet("/addAddress")
public class AddAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		if (name == null || name.isBlank() || phone == null || phone.isBlank()) {
			response.sendRedirect("add_contact.jsp");
			return;
		}

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO contacts (name, phone, email, address) VALUES (?, ?, ?, ?)")) {
			ps.setString(1, name.trim());
			ps.setString(2, phone.trim());
			ps.setString(3, (email == null ? null : email.trim()));
			ps.setString(4, (address == null ? null : address.trim()));
			int rows = ps.executeUpdate();
			String m = rows > 0 ? "Contact added successfully." : "No rows inserted.";
			response.sendRedirect("list_contacts.jsp?msg=" + m);
		} catch (Exception e) {
			response.sendRedirect("list_contacts.jsp?msg=" + "Error adding contact: " + e.getMessage());
		}

	}
}
