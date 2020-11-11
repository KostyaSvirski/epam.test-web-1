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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
        rel="shortcut icon"
        href="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
        type="image/x-icon"
     />
     <link
      href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@500&family=Roboto+Slab&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="styles/users.css">
    <title>All users</title>
</head>
<body>
    <header class="header">
        <a href="index.jsp"> <img
           class="logo"
           src="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
           alt="logo"
         />
         </a>
         <nav>
           <a class="nav" href="index.jsp#aboutUs"><fmt:message key="welcome_page.header.nav.about_us"/></a>
           <a class="nav" href="index.jsp#autopark"><fmt:message key="welcome_page.header.nav.autopark"/></a>
           <a class="nav" href="index.jsp#rent"><fmt:message key="welcome_page.header.nav.rent"/></a>
           <a class="nav" href="index.jsp#carBrands"><fmt:message key="welcome_page.header.nav.car_brands"/></a>
           <a class="nav" href="index.jsp#contacts"><fmt:message key="welcome_page.header.nav.contacts"/></a>
           <a class="nav" href="my_page.jsp"><fmt:message key="welcome_page.header.nav.my_page"/></a>
           <a class="lang" href="MainController?command=CHANGE_LOCALE&lang=en_EN&currentPage=<c:url value="/my_page.jsp"/>">En</a>
           <a class="lang" href="MainController?command=CHANGE_LOCALE&lang=ru_BY&currentPage=<c:url value="/my_page.jsp"/>">Ru</a>
         </nav>
    </header>
    <main>
        <section class="your_orders">
            <h2 id="orders_h2">Users</h2>
            <section class="decor1">
              <section class="inner2">
	        	<c:forEach var="user" items="${requestScope.users}">
                <section>
                  <form action="MainController" method="GET">
                  	<input type="hidden" name="command" value="SHOW_THIS_USER"/>
                  	<input type="hidden" name="id_user" value="${user.id}"/>
                  	<input class="name" type="submit" value="${user.name} ${user.surname}"/>
                  </form>
                  <div class="info_about_rent">
                   <p class="el">${user.email}</p>
                   <c:if test="${user.isBlocked == true}">
	                   <p class="el">Blocked</p>               
                   </c:if>
                   <c:if test="${user.isBlocked == false}">
	                   <p class="el">Active</p>               
                   </c:if>
                   <p class="el">${user.roleInProject}</p>
                   <c:if test="${user.roleInProject != 'ADMIN'}">
	                   <form action="MainController" method="GET">
		                  <input type="hidden" name="command" value="MAKE_ADMIN_COMMAND"/>
		                  <input type="hidden" name="id_user" value="${user.id}"/>
		                  <input class="button1" type="submit" value="MAKE ADMIN"/>
	                   </form>
                   </c:if>
                   <c:if test="${user.id != sessionScope.user.id}">
	                   <c:if test="${user.isBlocked == 'false'}">
		                   <form action="MainController" method="GET">
			                  <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
			                  <input type="hidden" name="id_user" value="${user.id}"/>
			                  <input type="hidden" name="action" value="block"/>
			                  <input class="button2" type="submit" value="BLOCK"/>
		                   </form>
	                   </c:if>
	                   <c:if test="${user.isBlocked == 'true'}">
		                   <form action="MainController" method="GET">
			                  <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
			                  <input type="hidden" name="id_user" value="${user.id}"/>
			                  <input type="hidden" name="action" value="unblock"/>
			                  <input class="button1" type="submit" value="UNBLOCK"/>
		                   </form>
	                   </c:if>
                   </c:if>
                  </div>
                </section>
	          </c:forEach>
              </section>
              <a href = "my_page.jsp" class="button"> BACK </a>
            </section>
       </section> 
    </main>
</body>
</html>