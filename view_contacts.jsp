<!-- view_contacts.jsp -->
<!-- David Gaulke -->
<!-- ICS 425 - Assignment 4 -->
<!DOCTYPE html>
<html>
<head>
	<title>ICS 425 Assignment 4</title>
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="form.css">
	<link rel="stylesheet" type="text/css" href="button.css">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<c:import url="/includes/header.jsp" />
	<div class="container">
	<h1>View All Contacts</h1>
	<h3>${message}</h3>
	<table>
		<tr>
		<th>First Name:</th>
		<th>Last Name:</th>
		<th></th>
		<th></th>
		<th></th>
		</tr>
		<c:forEach var="contact" items="${requestScope.contacts}">	
			<tr>
				<td>${contact.firstName}</td>
				<td>${contact.lastName}</td>
				<td><a href="viewcontact?id=${contact.contactId}">View Details</a></td>
				<td><a href="editcontact?id=${contact.contactId}">Edit</a></td>
				<td><a href="confirmdeletecontact?id=${contact.contactId}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div class="button_outer" id="add_contact"><a href="addcontact"><div class="button_inner"><div class="button_label two_line">Add<br>Contact</div></div></a></div>
	<div class="button_outer" id="add_contact"><a href="index.jsp"><div class="button_inner"><div class="button_label">Home</div></div></a></div>
</body>
</html>
