<!-- confirmation.jsp -->
<!-- David Gaulke -->
<!-- ICS 425 - Assignment 4 -->
<!DOCTYPE html>
<html>
<head>
	<title>ICS 425 Assignment 4</title>
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="../form.css">
	<link rel="stylesheet" type="text/css" href="../button.css">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<div class="container">
	<h1>Registration Complete!</h1>
	<h2>A new account has been created for:</h2>
	<p>UserName: ${user.userName}</p>
	<p>Name: ${user.firstName} ${user.lastName}</p>
	<p>Address: ${user.streetAddress} ${user.city}, ${user.state} ${user.zip}</p> 	
	<c:remove var="user" scope="session" />
	</div>
	<div class="button_outer" id="view_contacts"><a href="../view_contacts.jsp"><div class="button_inner"><div class="button_label two_line">View All<br>Contacts</div></div></a></div>
	</body>
</html>

