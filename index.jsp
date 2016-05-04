<!-- index.jsp -->
<!-- David Gaulke -->
<!-- ICS 425 - Assignment 4 -->
<!DOCTYPE html>
<html>
<head>
	<title>ICS 425 Assignment 4</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="form.css">
	<link rel="stylesheet" type="text/css" href="button.css">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<c:import url="/includes/header.jsp" />
	<div class="container">
	<h1>Main Menu</h1>
	<div class="button_outer" id="register"><a href="register/name.jsp"><div class="button_inner"><div class="button_label two_line">Add<br>User</div></div></a></div>
	<div class="button_outer" id="register"><a href="view_users.jsp"><div class="button_inner"><div class="button_label two_line">View<br>Users</div></div></a></div>
	<c:choose>
		<c:when test="${sessionScope.login == null}">
			<div class="button_outer" id="register"><a href="login.jsp"><div class="button_inner"><div class="button_label">Log In</div></div></a></div>
		</c:when>
		<c:otherwise>
			<div class="button_outer" id="register"><a href="view_contacts.jsp"><div class="button_inner"><div class="button_label two_line">View<br>Contacts</div></div></a></div>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>
