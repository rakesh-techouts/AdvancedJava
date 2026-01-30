<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.techouts.address.*,java.sql.*"%>
<%
    request.setCharacterEncoding("UTF-8");
    String q = request.getParameter("q");
    String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Contacts -Address Book</title>
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<header>
		<h1>Address Book</h1>
		<a class="btn primary" href="add_contact.jsp">+ Add Contact</a>
	</header>

	<div class="topbar">
		<form method="get" action="list_contacts.jsp">
			<input type="text" name="q" placeholder="Search by name" value="<%= Use.toStr(q) %>">
			<button class="btn" type="submit">Search</button>
			<% if (q != null && !q.isBlank()) { %>
			<a class="btn" href="list_contacts.jsp">Clear</a>
			<% } %>
		</form>
	</div>
	
	<% if (msg != null && !msg.isBlank()) { %>
	<div class="msg"><%=msg%></div>
	<% } %>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Address</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
      String sql = "SELECT id, name, phone, email, address FROM contacts";
      boolean hasQ = (q != null && !q.isBlank());
      if (hasQ) sql += " WHERE name LIKE ? ";
      sql += " ORDER BY id ASC";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {
          if (hasQ) ps.setString(1, "%" + q.trim() + "%");
          try (ResultSet rs = ps.executeQuery()) {
              boolean any = false;
              while (rs.next()) {
                  any = true;
  %>
			<tr>
				<td><%= rs.getInt("id") %></td>
				<td><%= rs.getString("name") %></td>
				<td><%= rs.getString("phone") %></td>
				<td><%= rs.getString("email") %></td>
				<td><%= rs.getString("address") %></td>
				<td><a class="btn"
					href="editContact.jsp?id=<%= rs.getInt("id") %>">Edit</a> 
					<a class="btn" href="delete_contact.jsp?id=<%= rs.getInt("id") %>"
					onclick="return confirm('Delete this contact?');">Delete</a></td>
			</tr>
			<%
              }
              if (!any) {%>
			<tr>
				<td colspan="6" style="text-align: center; color: #b00020;">No contacts found.</td>
			</tr>
			<%
              }
          }
      } catch (Exception e) {%>
			<tr>
				<td colspan="6" style="color: #b00020;">Error loading contacts:
					<%= e.getMessage() %>
				</td>
			</tr>
			<%
      }%>
		</tbody>
	</table>

</body>
</html>