<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${sessionScope.lang == null}">
  <c:set var="lang" value="en_EN" scope="session"/>
</c:if>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
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
		<h1  class="main_text"><fmt:message key="sign_up.registartion"/></h1>
			<div class="main">
				<form class="decor" action="MainController" method="POST">
					<div class="form-left-decoration"></div>
					<div class="form-right-decoration"></div>
					<div class="circle"></div>
					<div class="form-inner">
						<input type="hidden" name="command" value="registration_command">
						<input type="email" placeholder="<fmt:message key="sign_up.registartion.login"/>" name="login" required>
						<input type="password" placeholder="<fmt:message key="sign_up.registartion.password"/>" name="pass" required>
						<input type="password" placeholder="<fmt:message key="sign_up.registartion.repeat_password"/>" name="pass_repeat" required>
						<input type="text" placeholder="<fmt:message key="sign_up.registartion.surname"/>" name="surname" required>
						<input type="text" placeholder="<fmt:message key="sign_up.registartion.name"/>" name="name" required>
						<div class="radio">
							<input type="radio" name="gender" value="male"><fmt:message key="sign_up.registartion.gender.male"/>
							<input type="radio" name="gender" value="female"><fmt:message key="sign_up.registartion.gender.female"/><br>
						</div>
						<input type="text" placeholder="<fmt:message key="sign_up.registartion.passport_id"/>" name="passport_id" required>
						<input type="text" placeholder="<fmt:message key="sign_up.registartion.passport_number"/>" name="passport_number" required>
						<input type="date" placeholder="<fmt:message key="sign_up.registartion.date"/>" name="date_of_birth" required>
						<input type="text" placeholder="<fmt:message key="sign_up.registartion.phone"/>" name="phone" required>
						<input type="submit" value = "<fmt:message key="sign_up.registartion.submit"/>">
						<p style="color: ${collor}; text-align: center">${message}</p>
					</div>
				</form>
			</div>
		</main>
	</body>
</html>