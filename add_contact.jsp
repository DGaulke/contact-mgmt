<!-- add_contact.jsp -->
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
	<h1>Add Contact Details</h1>
	<form action="contactdetails" method="post">
		<label for="firstName"><span>First name:</span></label>
		<input type="text" name="firstName" id="firstName" placeholder="First Name" value="${contact.firstName}" required>
		<br>
		<label for="lastName"><span>Last name:</span></label>
		<input type="text" name="lastName" id="lastName" placeholder="Last Name" value="${contact.lastName}" required>
		<br>
		<label for="streetAddress"><span>Address Line 1:</span></label>
		<input type="text" name="streetAddress" id="streetAddress" placeholder="Street Address" value="${contact.streetAddress}" required>
		<br>
		<label for="city"><span>City:</span></label>
		<input type="text" name="city" id="city" placeholder="City" value="${contact.city}" required>
		<br>
		${stateoption}
		<br>
		<label for="zip"><span>Zip:</span></label>
		<input type="text" name="zip" id="zip" placeholder="Zip Code" pattern="([0-9]{5}(-[0-9{4})?)" value="${contact.zip}" required>
		<br>
		${phoneoption[0]}
		<input type="phone" name="phone0" id="phone0" value="${contact.phoneNumbers[0].phoneNumber}">	
		<br>
		${phoneoption[1]}
		<input type="phone" name="phone1" id="phone1" value="${contact.phoneNumbers[1].phoneNumber}">	
		<br>
		${emailoption[0]}
		<input type="email" name="email0" id="email0" value="${contact.emailAddresses[0].emailAddress}">	
		<br>
		${emailoption[1]}
		<input type="email" name="email1" id="email1" value="${contact.emailAddresses[1].emailAddress}">	
		<br>
		<input type="submit" name="cancel" value="cancel" formnovalidate>
		<input type="submit" name="next" value="next">
	</form>
	</div>
</body>
</html>

