<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add Contact</title>
<style>
body {
	font-family: system-ui, Arial;
	margin: 24px;
	max-width: 720px;
}

.form-row {
	margin-bottom: 12px;
}

label {
	display: block;
	margin-bottom: 4px;
}

input[type=text], input[type=email], textarea {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.actions {
	display: flex;
	gap: 10px;
}

.btn {
	padding: 6px 10px;
	border: 1px solid #888;
	background: #fff;
	border-radius: 4px;
	text-decoration: none;
}

.btn.primary {
	background: #1f6feb;
	color: white;
	border-color: #1f6feb;
}
</style>
</head>
<body>
	<h1>Add Contact</h1>

	<form method="post" action="${pageContext.request.contextPath}/addAddress" accept-charset="UTF-8">
		<div class="form-row">
			<label for="name">Name *</label> <input type="text" id="name"
				name="name" required maxlength="100">
		</div>
		<div class="form-row">
			<label for="phone">Phone *</label> <input type="text" id="phone"
				name="phone" required maxlength="20" pattern="[0-9+\-() ]+">
		</div>
		<div class="form-row">
			<label for="email">Email</label> <input type="email" id="email"
				name="email" maxlength="100">
		</div>
		<div class="form-row">
			<label for="address">Address</label>
			<textarea id="address" name="address" rows="3" maxlength="255"></textarea>
		</div>
		<div class="actions">
			<button class="btn primary" type="submit">Save</button>
			<a class="btn" href="list_contacts.jsp">Cancel</a>
		</div>
	</form>
</body>
</html>