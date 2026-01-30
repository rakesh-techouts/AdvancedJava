<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.techouts.address.DBConnection , java.sql.* " %>
<%
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
         PreparedStatement ps = con.prepareStatement(
             "INSERT INTO contacts (name, phone, email, address) VALUES (?, ?, ?, ?)")) {
        ps.setString(1, name.trim());
        ps.setString(2, phone.trim());
        ps.setString(3, (email == null ? null : email.trim()));
        ps.setString(4, (address == null ? null : address.trim()));
        int rows = ps.executeUpdate();
        String m = rows > 0 ? "Contact added successfully." : "No rows inserted.";
        response.sendRedirect("list_contacts.jsp?msg=" +m);
    } catch (Exception e) {
        response.sendRedirect("list_contacts.jsp?msg=" +"Error adding contact: " + e.getMessage());
    }
%>