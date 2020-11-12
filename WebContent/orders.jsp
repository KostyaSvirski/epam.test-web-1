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
    <link rel="stylesheet" href="styles/orders.css">
    <title>More user orders</title>
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
           <a class="lang" href="MainController?command=CHANGE_LOCALE&lang=en_EN&currentPage=<c:url value="/orders.jsp"/>">En</a>
           <a class="lang" href="MainController?command=CHANGE_LOCALE&lang=ru_BY&currentPage=<c:url value="/orders.jsp"/>">Ru</a>
         </nav>
       </header>
       <main>
        <section class="your_orders">
            <h2 id="orders_h2">User orders</h2>
            <section class="decor1">
              <section class="inner2">
              <c:forEach var="order" items="${requestScope.orders}">
                <section>
                  <p class="name">${order.signature}</p>
                  <div class="info_about_rent">
                   <p class="el">${order.dateOfStart} - ${order.dateOfFinish}</p>
                   <p class="el">${order.carBrand} ${order.carModel}</p>
                   <c:if test="${order.condition == 'обрабатывается'}">
	                   <p class="el" ><font color = "yellow">${order.condition}</font></p>                    
	                   <form action="MainController" method="POST">
	                   	   <input type="hidden" name="command" value="RELEASE_ORDER_COMMAND">
	                   	   <input type="hidden" name="order_id" value="${order.id}">
	                   	   <input type="submit" id="button-form" value="RELEASE">
	                   </form>
                   </c:if>
                   <c:if test="${order.condition == 'одобрено'}">
	                   <p class="el" ><font color = "green">${order.condition}</font></p> 
	                   <form action="MainController" method="POST">
	                   	   <input type="hidden" name="command" value="RELEASE_ORDER_COMMAND">
	                   	   <input type="hidden" name="order_id" value="${order.id}">
	                   	   <input type="submit" id="button-form" value="RELEASE">
	                   </form>                   
                   </c:if>
				   <c:if test="${order.condition == 'отказано'}">
	                   <p class="el"><font color = "red">${order.condition}</font></p>
	                   <p>${coase}</p>                   
                   </c:if>
                   <c:if test="${order.condition == 'завершено'}">
	                   <p class="el" ><font color = "black">${order.condition}</font></p>                   
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