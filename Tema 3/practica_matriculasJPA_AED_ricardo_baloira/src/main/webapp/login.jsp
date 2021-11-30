<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1 style="text-align:center;">Login</h1>
	<br>
	<form method="post" action="Login" style="text-align:center;">
		<input type="text" name="user" id="user" placeholder="nick usuario" /><br>
		<br>
		<input type="password" name="password" id="password" placeholder="contraseña" /><br>
		<br>
		<input type="submit" name="submit" id="submit" />
	</form>
</body>
</html>