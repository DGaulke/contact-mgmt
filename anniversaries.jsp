<!-- anniversaries.jsp -->
<!-- David Gaulke -->
<!-- ICS 425 - Assignment 4 -->
<!DOCTYPE html>
<html>
<head>
	<title>ICS 425 Assignment 4</title>
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="form.css">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<c:import url="/includes/header.jsp" />
	<div class="container">
	<h1>Add Important Dates</h1>
	<h3>${contact.firstName} ${contact.lastName}</h3>
	<h3>${contact.streetAddress} ${contact.city}, ${contact.state} ${contact.zip}</h3>
	<h3><c:forEach var="phone" items="${contact.phoneNumbers}">
			${phone.phoneType} phone: ${phone.phoneNumber}<br>
		</c:forEach>
	</h3>
	<h3><c:forEach var="email" items="${contact.emailAddresses}">
			${email.emailType} email: ${email.emailAddress}<br>
		</c:forEach>
	</h3>
	<form action="anniversaries" method="post">
		<label for="anniversary0"><span>Anniversary 1:</span></label>
		<br>
		<input class="option" type="text" name="anniversaryType0" id="anniversaryType0" placeholder="Occasion" value="${contact.anniversaries[0].anniversaryType}">
		<input class="anniversary" type="text" name="anniversary0" id="anniversary0" placeholder="mm/dd/yyyy" value="${contact.anniversaries[0].anniversary}">
		<br>

		<label for="anniversary1"><span>Anniversary 2:</span></label>
		<br>
		<input class="option" type="text" name="anniversaryType1" id="anniversaryType1" placeholder="Occasion" value="${contact.anniversaries[1].anniversaryType}">
		<input class="anniversary" type="text" name="anniversary1" id="anniversary1" placeholder="mm/dd/yyyy" value="${contact.anniversaries[1].anniversary}">
		<br>
		
		<label for="anniversary2"><span>Anniversary 3:</span></label>
		<br>
		<input class="option" type="text" name="anniversaryType2" id="anniversaryType2" placeholder="Occasion" value="${contact.anniversaries[2].anniversaryType}">
		<input class="anniversary" type="text" name="anniversary2" id="anniversary2" placeholder="mm/dd/yyyy" value="${contact.anniversaries[2].anniversary}">
		<br>

		<input type="submit" name="cancel" value="cancel" formnovalidate>
		<input type="submit" name="previous" value="previous" formnovalidate>
		<input type="submit" name="complete" value="complete">
	</form>
	</div>
</body>
</html>
