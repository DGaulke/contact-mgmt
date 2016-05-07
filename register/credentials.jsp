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
	<h1>Even more registration!</h1>
	<h2>${requestScope.message}</h2>
	<form action="credentials" method="post">
		<label for="userName"><span>User name:</span></label>
		<input type="text" name="userName" id="userName" placeholder="username" value="${user.userName}" required>
		<br>
		<label for="password"><span>Password:</span></label>
		<input type="password" name="password" id="password" placeholder="********" required>
		<br>
		<input type="submit" name="previous" value="previous" formnovalidate>
		<input type="submit" name="complete" value="complete">
	</form>
	</div>
</body>
</html>
