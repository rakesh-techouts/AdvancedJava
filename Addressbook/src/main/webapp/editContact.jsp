<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.techouts.address.DBConnection , java.sql.*, java.util.HashMap"%>
<%
request.setCharacterEncoding("UTF-8");
String idStr = request.getParameter("id");
if (idStr == null || idStr.isBlank()) {
	response.sendRedirect("list_contacts.jsp?msg=" + "Invalid contact ID.");
	return;
}
Integer id = null;
try {
	id = Integer.valueOf(idStr);
} catch (NumberFormatException ex) {
	response.sendRedirect("list_contacts.jsp?msg=" + "Invalid contact ID.");
	return;
}
String query = "SELECT name, phone, email, address FROM contacts WHERE id=?";
HashMap<String, String> details = new HashMap<>();
details.put("id",Integer.toString(id));
try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
	ps.setInt(1, id);
	try (ResultSet rs = ps.executeQuery()) {
		if (rs.next()) {
	details.put("name", rs.getString("name"));
	details.put("phone", rs.getString("phone"));
	details.put("email", rs.getString("email"));
	details.put("address", rs.getString("address"));
		} else {
	response.sendRedirect("list_contacts.jsp?msg=" + "Contact not found.");
	return;
		}
		request.setAttribute("details", details);
		request.getRequestDispatcher("edit_contact.jsp").forward(request,response);
	}
} catch (Exception e) {
	response.sendRedirect("list_contacts.jsp?msg=" + "Error loading contact: " + e.getMessage());
	return;
}
%>