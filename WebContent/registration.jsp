<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registration Page</title>
</head>
<body>
	<h1 style="text-align: center;">REGISTRATION</h1>
	<div style="text-align: left;">
	<form action="MainController" method="POST">
		<input type="hidden" name="command" value="registration_command">
		<label>login: </label>
		<input type="email" name="login"><br><br>
		<label>password: </label>
		<input type="password" name="pass"><br><br>
		<label>repeat password: </label>
		<input type="password" name="pass_repeat"><br><br>
		<label>surname: </label>
		<input type="text" name="surname"><br><br>
		<label>name: </label>
		<input type="text" name="name"><br><br>
		<label>gender: </label>
		<input type="radio" name="gender" value="male">Male
		<input type="radio" name="gender" value="female">Female
		<br><br>
		<label>passport id: </label>
		<input type="text" name="passport_id"><br><br>
		<label>passport number: </label>
		<input type="text" name="passport_number"><br><br>
		<label>date of birth: </label>
		<input type="text" name="date_of_birth"><br><br>
		<label>phone: </label>
		<input type="text" name="phone"><br><br>
		<input type="submit" value = "SIGN UP"><br><hr>
		<p style="color: ${collor}">${message}</p>
	</form>
	</div>
</body>
</html>