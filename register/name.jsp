<!-- name.jsp -->
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
	<div class="container">
	<h1>Begin registration!</h1>
	<form action="name" method="post">
		<label for="firstName"><span>First name:</span></label>
		<input type="text" name="firstName" id="firstName" placeholder="First Name" value="${user.firstName}" required>
		<br>
		<label for="lastName"><span>Last name:</span></label>
		<input type="text" name="lastName" id="lastName" placeholder="Last Name" value="${user.lastName}" required>
		<br>
		<input type="submit" name="continue" value="continue">
	</form>
	</div>
</body>
</html>
