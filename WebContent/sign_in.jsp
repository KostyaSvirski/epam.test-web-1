<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>hello page</title>
</head>
<body>
	<h1 style="text-align: center;">AUTHORITHATION</h1>
	<div style="text-align: center;">
	<form action="MainController" method="POST">
		<input type="hidden" name="command" value="authorization_command">
		<label>login: </label><br>
		<input type="email" name="login"><br><br>
		<label>password: </label><br>
		<input type="password" name="pass"><br><br>
		<input type="submit"><br>
	</form>
	</div>
	<p style="color: ${collor}; text-align: center">${message}</p>
	<hr>
	<a href = "registration.jsp"> Регистрация </a><br>
	<a href = "welcome.jsp"> Назад </a>
</body>
</html>