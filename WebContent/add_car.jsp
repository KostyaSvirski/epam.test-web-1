<%@ taglib prefix="ctg" uri="custom-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link rel="stylesheet" href="styles/edit_car.css" />
	<title>Adding Page</title>
 </head>	

<body class="body">
	<main class="main">
	<h1  class="main_text">ADDING</h1>
	<div class="main">
	<form class="decor" action="MainController" method="POST">
		<div class="form-left-decoration"></div>
		<div class="form-right-decoration"></div>
		<div class="circle"></div>

		<div class="form-inner">
        <input type="hidden" name="command" value="ADD_CARS_COMMAND">
        <input type="text" placeholder="Photo" name="photo">
		<input type="text" placeholder="Brand" name="brand">
		<input type="text" placeholder="Model" name="model">
		<div class="radio">
			<input type="radio" name="class" value="Суперкар">Sports cars
            <input type="radio" name="class" value="Внедорожник">SUVs<br>
            <input type="radio" name="classr" value="Представительская">Executive class<br>
            <input type="radio" name="class" value="Кабриолет">Convertibles<br>
		</div>
        <input type="text" placeholder="Power" name="power">
        <input type="text" placeholder="Engine" name="engine">
        <input type="text" placeholder="Acceleration" name="acceleration">
        <input type="text" placeholder="Drive unit" name="drive_unit">
        <input type="text" placeholder="Fuel" name="fuel">
        <input type="text" placeholder="Cost" name="cost">
		<input type="submit" value = "CONFIRM">
		<p style="color: ${collor}; text-align: center">${message}</p>
	</div>
	</form>
	</div>
	<a href = "my_page.jsp" class="register"> BACK </a>
</main>
</body>
</html>