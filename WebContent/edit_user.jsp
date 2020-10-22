<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
	<link rel="stylesheet" href="styles/edit.css" />
	<title>Edit Page</title>
 </head>	

<body class="body">
	<main class="main">
	<h1  class="main_text">EDITING</h1>
	<div class="main">
	<form class="decor" action="MainController" method="GET">
		<div class="form-left-decoration"></div>
		<div class="form-right-decoration"></div>
		<div class="circle"></div>

		<div class="form-inner">
		<input type="hidden" name="command" value="EDIT_INFO_COMMAND">
		<input type="email" value="${sessionScope.user.login}" name="login" required>
		<input type="text" value="${sessionScope.user.surname}" name="surname" required>
		<input type="text" value="${sessionScope.user.name}" name="name" required>
		<div class="radio">
			<c:if test="${sessionScope.user.gender == 'Мужчина'}">
				<input type="radio" name="gender" value="male" checked>Male
				<input type="radio" name="gender" value="female">Female<br>			
			</c:if>
			<c:if test="${sessionScope.user.gender == 'Женщина'}">
				<input type="radio" name="gender" value="male">Male
				<input type="radio" name="gender" value="female" checked>Female<br>
			</c:if>
		</div>
		<input  type="text" value="${sessionScope.user.passportId}" name="passport_id" required>
		<input type="text" value="${sessionScope.user.passportNumber}" name="passport_number" required>
		<input type="text" value="${sessionScope.user.dateOfBirth}" name="date_of_birth" required>
		<input type="text" value="${sessionScope.user.phoneNumber}" name="phone" required>
		<input type="submit" value = "CONFIRM">
		<p style="color: ${collor}; text-align: center">${message}</p>
	</div>
	</form>
	</div>
	<a href = "my_page.jsp" class="register"> BACK </a>
</main>
</body>
</html>