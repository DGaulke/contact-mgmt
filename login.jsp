<!-- name.jsp -->
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
	<c:choose>
		<c:when test="${sessionScope.login == null}">
			<div class="container">
			<h1>Log in</h1>
			<h3>${message}</h3>
			<form action="login" method="post">
				<label for="userName"><span>User name:</span></label>
				<input type="text" name="userName" id="userName" placeholder="username" required>
				<br>
				<label for="password"><span>Password:</span></label>
				<input type="password" name="password" id="password" placeholder="********" required>
				<br>
				<input type="submit" name="submit" value="submit">
			</form>
			</div>
			<div class="button_outer" id="register"><a href="register/name.jsp"><div class="button_inner"><div class="button_label two_line">Add<br>User</div></div></a></div>
		</c:when>
		<c:otherwise>
			<div class="container">
			<h1>Log in</h1>
			<h3>${sessionScope.login} is alraedy logged in!</h3>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
