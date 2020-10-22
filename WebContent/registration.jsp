<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link
	  rel="shortcut icon"
	  href="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
	  type="image/x-icon"
	/>
	<link
	  href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@500&family=Roboto+Slab&display=swap"
	  rel="stylesheet"
	/>
	<link rel="stylesheet" href="styles/registration.css" />
	<title>Registration Page</title>
 </head>	

<body class="body">
	<main class="main">
	<h1  class="main_text">REGISTRATION</h1>
	<div class="main">
	<form class="decor" action="MainController" method="POST">
		<div class="form-left-decoration"></div>
		<div class="form-right-decoration"></div>
		<div class="circle"></div>

		<div class="form-inner">
		<input type="hidden" name="command" value="registration_command">
		<input type="email" placeholder="Email" name="login" required>
		<input type="password" placeholder="Password" name="pass" required>
		<input type="password" placeholder="Repeat password" name="pass_repeat" required>
		<input type="text" placeholder="Surname" name="surname" required>
		<input type="text" placeholder="Name" name="name" required>
		<div class="radio">
			<input type="radio" name="gender" value="male" checked>Male
			<input type="radio" name="gender" value="female">Female<br>
		</div>
		<input type="text" placeholder="Passport id" name="passport_id" required>
		<input type="text" placeholder="Passport number" name="passport_number" required>
		<input type="text" placeholder="Date of birth DD.MM.YYYY" name="date_of_birth" required>
		<input type="text" placeholder="Phone +123456789" name="phone" required>
		<input type="submit" value = "SIGN UP">
		<p style="color: ${collor}; text-align: center">${message}</p>
	</div>
	</form>
	</div>
</main>
</body>
</html>