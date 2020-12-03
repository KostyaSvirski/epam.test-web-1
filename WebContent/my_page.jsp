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
    <link rel="stylesheet" href="styles/page.css">
    <title>My page</title>
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
         <section class="info" id="orders-info">
          <h2 id="info_h2"><fmt:message key="my_page.profile"/></h2>
          <div class="decor">
            <div class="inner">
              <p><fmt:message key="my_page.profile.name"/> ${sessionScope.user.name}</p>
              <p><fmt:message key="my_page.profile.surname"/> ${sessionScope.user.surname}</p>
              <p><fmt:message key="my_page.profile.gender"/> ${sessionScope.user.gender}</p>
              <p><fmt:message key="my_page.profile.email"/> ${sessionScope.user.login}</p>
              <p><fmt:message key="my_page.profile.passport_id_number"/> ${sessionScope.user.passportId}, ${sessionScope.user.passportNumber}</p>
              <p><fmt:message key="my_page.profile.date"/> ${sessionScope.user.dateOfBirth}</p>
              <p><fmt:message key="my_page.profile.phone"/> ${sessionScope.user.phoneNumber}</p>
            </div>
            <a href = "edit_user.jsp" class="button"> <fmt:message key="my_page.profile.button.edit"/> </a>
            <a href = "sign_out.jsp" class="button"> <fmt:message key="my_page.profile.button.log_out"/> </a>
          </div>  
          
          
         </section>
         <section class="info" id="orders-info">
         <c:if test="${sessionScope.user.roleInProject == 'ADMIN'}">
	         <h2 id="info_h2"><fmt:message key="my_page.admin.users.orders"/></h2>
	         <div class="decor">
	 			<form action="MainController" method="POST">
	            	<input type="hidden" name="command" value="SHOW_USERS_RENT_LIST_COMMAND">
	                <input type="submit" id="button-form" value="<fmt:message key="my_page.admin.users.orders"/>">
	            </form>         
	          </div>
	         <h2 id="info_h2" class="a1"><fmt:message key="my_page.admin.users.show"/></h2>
	         <div class="decor">
	 			<form action="MainController" method="POST">
	            	<input type="hidden" name="command" value="SHOW_USERS_COMMAND">
	                <input type="submit" id="button-form" value="<fmt:message key="my_page.admin.users.show"/>">
	            </form>         
	          </div>
	          <h2 id="info_h2" class="a1"><fmt:message key="my_page.admin.users.add_car"/></h2>
	          <a href = "add_car.jsp" class="button"><fmt:message key="my_page.admin.users.add_car"/></a>
          </c:if>
          <c:if test="${sessionScope.user.roleInProject == 'USER'}">
             <h2 id="info_h2"><fmt:message key="my_page.orders"/></h2>
	         <div class="decor">
	 			<form action="MainController" method="POST">
	            	<input type="hidden" name="command" value="SHOW_RENT_LIST_COMMAND">
	                <input type="submit" id="button-form" value="<fmt:message key="my_page.orders.button.more_orders"/>"/>
	            </form>         
	          </div>
          </c:if>         
          </section>
       </main>
</body>
</html>    

