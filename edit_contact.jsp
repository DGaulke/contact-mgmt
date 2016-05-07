<!-- edit_contact.jsp -->
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
	<h1>Edit Contact</h1>
	<form action="updatecontact" method="post">
		<input type="text" name="contactId" id="contactId" value="${contact.contactId}" hidden>
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
		<input type="submit" name="cancel" value="cancel">
		<input type="submit" name="submit" value="submit">
	</form>
	</div>
</body>
</html>

