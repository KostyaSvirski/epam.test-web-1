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
	<link rel="stylesheet" href="styles/sign_in.css" />
	<title>Authorization Page</title>
 </head>

<body>
	<main>
	<h1 class="main_text">AUTHORITHATION</h1>
	<div>
	<form class="decor" action="MainController" method="GET">
		<div class="form-left-decoration"></div>
		<div class="form-right-decoration"></div>
		<div class="circle"></div>
		<div class="form-inner">
		<input type="hidden" name="command" value="authorization_command"> 
		<input type="email" placeholder="Login" name="login">
		<input type="password" placeholder="Password" name="pass">
		<input type="submit" value = "SIGN IN">
		<p style="color: ${collor}; text-align: center">${message}</p>
	</div>
	</form>
	</div>
	<a href = "registration.jsp" class="register"> REGISTRATION </a>
	</main>
</body>
</html>