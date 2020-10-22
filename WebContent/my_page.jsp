<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <a href="index.jsp"> <img
           class="logo"
           src="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
           alt="logo"
         />
         </a>
         <nav>
           <a class="nav" href="welcome.jsp#aboutUs">About Us</a>
           <a class="nav" href="welcome.jsp#autopark">Autopark</a>
           <a class="nav" href="welcome.jsp#rent">Rent</a>
           <a class="nav" href="welcome.jsp#carBrands">Car Brands</a>
           <a class="nav" href="welcome.jsp#contacts">Contacts</a>
           <a class="nav" href="#">My page</a>
         </nav>
       </header>
       <main>
         <section class="info">
          <h2 id="info_h2">Your profile</h2>
          <div class="decor">
            <div class="inner">
              <p>Name: ${sessionScope.user.name}</p>
              <p>Surname: ${sessionScope.user.surname}</p>
              <p>Gender: ${sessionScope.user.gender}</p>
              <p>Email: ${sessionScope.user.login}</p>
              <p>Passport id and number: ${sessionScope.user.passportId}, ${sessionScope.user.passportNumber}</p>
              <p>Date of birth: ${sessionScope.user.dateOfBirth}</p>
              <p>Phone: ${sessionScope.user.phoneNumber}</p>
            </div>
            <a href = "edit_user.jsp" class="button"> EDIT </a>
            <a href = "log_out.jsp" class="button"> LOG OUT </a>
          </div>
         </section>
         <section class="your_orders">
          <h2 id="orders_h2">Your orders</h2>
          <section class="decor">
            <section class="inner2">
              <section>
                <p align="center"><b>NISSAN GT-R</b></p>
                <div class="info_about_rent">
                <p>11.09.2020</p>
                <p class="element" style="color:#FFB100">pending</p>
              </div>
              </section>
              <section>
                <p align="center"><b>MERCEDES</b></p>
                <div class="info_about_rent">
                  <p>01.04.2020</p>
                  <p class="element" style="color:#21A200">approved</p>
                </div>
              </section>
              <section>
                <p align="center"><b>CAMARO</b></p>
                <div class="info_about_rent">
                  <p>24.12.2019</p>
                  <p class="element" style="color: #FF0000">denied</p>
                </div>
              </section>
              <section>
                <p align="center"><b>ROLLS ROYCE</b></p>
                <div class="info_about_rent">
                  <p>30.06.2019</p>
                  <p class="element" style="color:#21A200">approved</p>
                </div>
              </section>
            </section>
            <a href = "#more_orders" class="button"> MORE ORDERS </a>
          </section>
         </section>
       </main>
</body>
</html>    

