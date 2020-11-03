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
	<link rel="stylesheet" href="styles/sign_in.css" />
	<title>Authorization Page</title>
 </head>

<body>

	<main>
	<h1 class="main_text"><fmt:message key="sign_in.authorization"/></h1>
	<div>
	<form class="decor" action="MainController" method="POST">
		<div class="form-left-decoration"></div>
		<div class="form-right-decoration"></div>
		<div class="circle"></div>
		<div class="form-inner">
		<input type="hidden" name="command" value="authorization_command"> 
		<input type="email" placeholder="<fmt:message key="sign_in.authorization.login"/>" name="login" required>
		<input type="password" placeholder="<fmt:message key="sign_in.authorization.password"/>" name="pass" required>
		<input type="submit" value = "<fmt:message key="sign_in.authorization.submit"/>">
		<p style="color: ${collor}; text-align: center">${message}</p>
	</div>
	</form>
	</div>
	<a href ="sign_up.jsp" class="register"> <fmt:message key="sign_in.authorization.registration"/> </a>
	</main>
</body>
</html>