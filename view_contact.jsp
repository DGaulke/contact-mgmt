<!-- view_contact.jsp -->
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
	<h1>View Contact Details</h1>
	<p>Name: ${contact.firstName} ${contact.lastName}</p>
	<p>Address: ${contact.streetAddress} ${contact.city}, ${contact.state} ${contact.zip}</p> 	
	<c:forEach var="phone" items="${requestScope.contact.phoneNumbers}">
		<p>${phone.phoneType} phone: ${phone.phoneNumber}</p>
	</c:forEach>
	<c:forEach var="email" items="${requestScope.contact.emailAddresses}">
		<p>${email.emailType} email: ${email.emailAddress}</p>
	</c:forEach>
	<c:forEach var="anniversary" items="${requestScope.contact.anniversaries}">
		<p>${anniversary.anniversaryType}: ${anniversary.anniversary}</p>
	</c:forEach>
	</div>
	<div class="button_outer" id="view_contacts"><a href="view_contacts.jsp"><div class="button_inner"><div class="button_label two_line">View All<br>Contacts</div></div></a></div>
	</body>
</html>

