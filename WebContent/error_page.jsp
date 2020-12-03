<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
    <link rel="stylesheet" href="styles/not_found.css" />
	<title>ERROR</title>
</head>
<body>
	<main>
      <section class="main">
        <p class="main_text">code: 500</p><br>
        <c:if test="${type_error != null}">
	        <p class="add_text">error: ${type_error}</p>        
        </c:if>
        <c:if test="${type_error == null}">
	        <p class="add_text">Type: <% pageContext.getException().getMessage(); %></p>
			<p class="add_text">Message: <% pageContext.getException().getMessage(); %></p>        
        </c:if>
      </section>
    </main></body>
</html>