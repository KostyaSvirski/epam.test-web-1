<%@ taglib prefix="ctg" uri="custom-tags" %>
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
	     <a href="index.jsp"><img
	        class="logo"
	        src="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
	        alt="logo"
	      />
	      </a> 
    	<ctg:header-menu/>
  	</header>
    <main>
        <section class="your_orders">
            <h2 id="orders_h2"><fmt:message key="users.users"/></h2>
            <section class="decor1">
              <section class="inner2">
	        	<c:forEach var="user" items="${requestScope.users}">
                <section>
                <c:if test="${user.id != sessionScope.user.id}">
                  <form action="MainController" method="GET">
                  	<input type="hidden" name="command" value="SHOW_THIS_USER_COMMAND"/>
                  	<input type="hidden" name="id_user" value="${user.id}"/>
                  	<input class="name" type="submit" value="${user.name} ${user.surname}"/>
                  </form>                
                </c:if>
                <c:if test="${user.id == sessionScope.user.id}">
                	<a class="name" href = "my_page.jsp">${user.name} ${user.surname}</a>
                </c:if>
                  <div class="info_about_rent">
                   <p class="el">Email: ${user.email}</p>
                   <c:if test="${user.isBlocked == true}">
	                   <p class="el"><fmt:message key="users.status.blocked"/></p>               
                   </c:if>
                   <c:if test="${user.isBlocked == false}">
	                   <p class="el"><fmt:message key="users.status.active"/></p>               
                   </c:if>
                   <p class="el"><fmt:message key="users.role"/> ${user.roleInProject}</p>
                   <c:if test="${user.id != sessionScope.user.id}">
	                   <c:if test="${user.roleInProject != 'ADMIN'}">
		                   <form action="MainController" method="GET">
			                  <input type="hidden" name="command" value="MAKE_ADMIN_COMMAND"/>
			                  <input type="hidden" name="id_user" value="${user.id}"/>
			                  <input class="button1" type="submit" value="<fmt:message key="users.button.make_admin"/>"/>
		                   </form>
	                   </c:if>
	                   <c:if test="${user.isBlocked == 'false'}">
		                   <form action="MainController" method="GET">
			                  <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
			                  <input type="hidden" name="id_user" value="${user.id}"/>
			                  <input type="hidden" name="action" value="block"/>
			                  <input class="button2" type="submit" value="<fmt:message key="users.button.block"/>"/>
		                   </form>
	                   </c:if>
	                   <c:if test="${user.isBlocked == 'true'}">
		                   <form action="MainController" method="GET">
			                  <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
			                  <input type="hidden" name="id_user" value="${user.id}"/>
			                  <input type="hidden" name="action" value="unblock"/>
			                  <input class="button1" type="submit" value="<fmt:message key="users.button.unblock"/>"/>
		                   </form>
	                   </c:if>
                   </c:if>
                  </div>
                </section>
	          </c:forEach>
              </section>
              <a href = "my_page.jsp" class="button"> <fmt:message key="cars.button.back"/> </a>
            </section>
       </section> 
    </main>
</body>
</html>