<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
	<h1>hello</h1>
	<ul>
		<li>Name: ${sessionScope.user.name}</li> 
		<li>Surname: ${sessionScope.user.surname}</li>
		<li>Email: ${sessionScope.user.email}</li>
		<li>Role: ${sessionScope.user.roleInProject}</li>
		<li>Passport ID: ${sessionScope.user.passportId}</li>
		<li>Passport Number: ${sessionScope.user.passportNumber}</li>
		<li>Date of Birth: ${sessionScope.user.dateOfBirth}</li>
		<li>Phone Number: ${sessionScope.user.phoneNumber}</li>
		<li>Gender: ${sessionScope.user.gender}</li>
	</ul>
	<a href="log_out.jsp"><button>LOG_OUT</button></a>
</body>
</html>