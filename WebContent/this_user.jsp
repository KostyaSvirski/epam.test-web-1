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
    <title>User page</title>
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
          <h2 id="info_h2"><fmt:message key="users.this.info"/></h2>
          <div class="decor">
            <div class="inner">
              <p><fmt:message key="my_page.profile.name"/> ${requestScope.user.name}</p>
              <p><fmt:message key="my_page.profile.surname"/> ${requestScope.user.surname}</p>
              <p><fmt:message key="my_page.profile.gender"/> ${requestScope.user.gender}</p>
              <p><fmt:message key="my_page.profile.email"/> ${requestScope.user.login}</p>
              <p><fmt:message key="my_page.profile.passport_id_number"/> ${requestScope.user.passportId}, ${requestScope.user.passportNumber}</p>
              <p><fmt:message key="my_page.profile.date"/> ${requestScope.user.dateOfBirth}</p>
              <p><fmt:message key="my_page.profile.phone"/> ${requestScope.user.phoneNumber}</p>
            </div>
	            <c:if test="${requestScope.user.roleInProject != 'ADMIN'}">
		            <form action="MainController" method="POST">
			        	<input type="hidden" name="command" value="MAKE_ADMIN_COMMAND"/>
			            <input type="hidden" name="id_user" value="${requestScope.user.id}"/>
			            <input class="button1" type="submit" value="<fmt:message key="users.button.make_admin"/>"/>
		        	</form>
		        </c:if>
		        <c:if test="${requestScope.user.isBlocked == 'false'}">
		            <form action="MainController" method="POST">
			            <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
		                <input type="hidden" name="id_user" value="${user.id}"/>
		                <input type="hidden" name="action" value="block"/>
		                <input class="button2" type="submit" value="<fmt:message key="users.button.block"/>"/>
		            </form>
		        </c:if>
		     	<c:if test="${requestScope.user.isBlocked == 'true'}">
		            <form action="MainController" method="POST">
			            <input type="hidden" name="command" value="BLOCK_UNBLOCK_COMMAND"/>
			            <input type="hidden" name="id_user" value="${user.id}"/>
			            <input type="hidden" name="action" value="unblock"/>
			            <input class="button1" type="submit" value="<fmt:message key="users.button.unblock"/>"/>
		            </form>
	  			</c:if>
          </div>   
         </section>
         <c:if test="${requestScope.user.roleInProject == 'USER'}">
         <section class="info" id="orders-info">
             <h2 id="info_h2"><fmt:message key="users.this.orders"/></h2>
	         <div class="decor">
	 			<form action="MainController" method="POST">
	            	<input type="hidden" name="command" value="SHOW_THIS_USER_RENT_LIST_COMMAND">
	                <input type="hidden" name="id_user" value="${user.id}"/>
	                <input type="submit" id="button-form" value="<fmt:message key="my_page.orders.button.more_orders"/>"/>
	            </form>         
	          </div>
          </section>
          </c:if>         
       </main>
</body>
</html>    