<!-- address.jsp -->
<!-- David Gaulke -->
<!-- ICS 425 - Assignment 4 -->
<!DOCTYPE html>
<html>
<head>
	<title>ICS 425 Assignment 4</title>
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="../form.css">
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<div class="container">
	<h1>Continue registration!</h1>
	<h2>Enter address for ${user.firstName} ${user.lastName}</h1>
	<form action="address" method="post">
		<label for="street_address"><span>Street Address</span></label>
		<input type="text" name="street_address" id="street_address" placeholder="Street Address" value="${user.streetAddress}" required>
		<br>
		<label for="city"><span>City:</span></label>
		<input type="text" name="city" id="city" placeholder="City" value="${user.city}" required>
		<br>
		${stateoption}
		<br>
		<label for="zip"><span>Zip:</span></label>
		<input type="text" name="zip" id="zip" placeholder="Zip Code" pattern="([0-9]{5}(-[0-9{4})?)" value="${user.zip}" required>
		<br>
		<input type="submit" name="previous" value="previous" formnovalidate>
		<input type="submit" name="next" value="next">
	</form>
	</div>
</body>
</html>





