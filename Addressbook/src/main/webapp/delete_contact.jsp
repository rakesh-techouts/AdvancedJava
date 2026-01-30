<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "com.techouts.address.DBConnection , java.sql.*"%>
<%
String idStr = request.getParameter("id");
Integer id = null;
try {
	id = Integer.valueOf(idStr);
} catch (Exception ex) {
	response.sendRedirect("list_contacts.jsp?msg=" + "Invalid ID.");
	return;
}

try (Connection con = DBConnection.getConnection(); 
		PreparedStatement ps = con.prepareStatement("DELETE FROM contacts WHERE id=?")) {
	ps.setInt(1, id);
	int rows = ps.executeUpdate();
	String m = rows > 0 ? "Contact deleted." : "Contact not found.";
	response.sendRedirect("list_contacts.jsp?msg=" + m);
} catch (Exception e) {
	response.sendRedirect("list_contacts.jsp?msg=" + "Error deleting contact: " + e.getMessage());
}
%>

