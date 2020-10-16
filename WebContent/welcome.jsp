<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="shortcut icon"
      href="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
      type="image/x-icon"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@500&family=Roboto+Slab:wght@600&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@500&family=Roboto+Slab&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="styles/styles.css" />
    <title>Rent of auto</title>
  </head>

  <body>
    <header class="header">
      <img
        class="logo"
        src="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
        alt="logo"
      />
      <nav>
        <a class="nav" href="#aboutUs">About Us</a>
        <a class="nav" href="#autopark">Autopark</a>
        <a class="nav" href="#rent">Rent</a>
        <a class="nav" href="#tariffs">Tariffs</a>
        <a class="nav" href="#carBrands">Car Brands</a>
        <a class="nav" href="#contacts">Contacts</a>
        <c:if test="${sessionScope.user.login == null}">
	        <a class="nav" href="sign_in.jsp">Sign in</a>	        
        </c:if>
         <c:if test="${sessionScope.user.login != null}">
	        <a class="nav" href="my_page.jsp">My Page</a>	        
        </c:if>
      </nav>
    </header>

    <main>
      <section class="main">
        <p class="main_text">Premium car rental</p>
        <p class="main_text2">The best quality</p>
        <c:if test="${sessionScope.user.login == null}">
	        <p class="main_text3">authorize please</p>    
        </c:if>
        <c:if test="${sessionScope.user.login != null}">
	        <p class="main_text3">hello, ${sessionScope.user.name} ${sessionScope.user.surname}</p>    
        </c:if>
        <p class="main_text3">current count of users: ${count}</p> 
      </section>

      <section class="about_us" id="aboutUs">
        <div class="about_us_text">
          <p>
            <p id="about_us_text1">About us</p>
            Premium car rental service is your unique opportunity to touch your
            dream and get unforgettable emotions. Each car in our fleet is a
            pearl of the global automotive industry, capable of captivating
            with
            its dynamics, aesthetics and comfort. You don't have to be a
            millionaire to enjoy the best models of Ferrari, Porsche, Bentley
            and Lamborghini, you can choose a convenient and profitable
            rental!
          </p>
        </div>
        <iframe
          src="https://www.youtube.com/embed/WNeLUngb-Xg"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
      </section>
      
    <section id="autopark">
      <h2 id="autopark_h2">Autopark</h2>
      <section class="autopark">
        <section class="row">
          <section class="types_of_cars">
            <img
              class="cars_PH"
              src="https://i.imgur.com/oq3oyx3.jpg"
              alt="Sports cars"
            />
            <p class="autopark_p"><a class="autopark_p" href="/Sportscars">Sports cars</a></p>
          </section>
          <section class="types_of_cars">
            <img
              class="cars_PH"
              src="https://i.imgur.com/T4SfXoP.jpg"
              alt="SUVs"
            />
            <p class="autopark_p"><a class="autopark_p" href="/SUVs">SUVs</a></p>
          </section>
        </section>
        <section class="row">
          <section class="types_of_cars">
            <img
              class="cars_PH"
              src="https://i.imgur.com/tXE6BcT.jpg"
              alt="Executive class"
            />
            <p class="autopark_p">
              <a class="autopark_p" href="/Executive class">Executive class</a>
            </p>
          </section>
          <section class="types_of_cars">
            <img
              class="cars_PH"
              src="https://i.imgur.com/cpjy3r8.jpg"
              alt="Convertibles"
            />
            <p class="autopark_p"><a class="autopark_p" href="/Convertibles">Convertibles</a></p>
          </section>
        </section>
      </section>
    </section>

      <section class="rent" id="rent">
        <h2 id="rent_h2">Rental rules</h2>
        <section class="spis">
          <div class="spis1">
            <h3 id="rent_h3_1">ALLOWED</h3>
            <ul class="push">
              <li>
                The minimum age is from 19 years. Work experience-from 1 year.
                The lessee must provide an original passport with a residence
                permit, 
                a selfie with a passport and a driver's license on both sides.
              </li>
              <li>
                We provide a car rental service for foreign citizens. The
                amount
                of the security deposit can be increased.
              </li>
              <li>
                To rent a car, a returnable security deposit is required, the
                amount of which depends on the chosen car, as well as the
                driving experience and citizenship of the renter. The minimum
                deposit is 30,000 rubles, see the section
                <a class="rent_a" href="/tariffs">tariffs</a>.
              </li>
              <li>
                An additional driver must be declared before the start of the
                car rental, providing the original passport and driver's
                license. The price includes 2 drivers. The insurance of each
                subsequent driver takes place at an additional cost.
              </li>
              <li>
                To reserve a car for a certain date, you must make an advance
                payment of 50% of the cost.
              </li>
            </ul>
          </div>
          <div class="spis2">
            <h3 id="rent_h3_2">FORBIDDEN</h3>
            <ul class="push1">
              <li>
                Transfer control of the car to a third party not included in the
                contract
              </li>
              <li>
                Using the car for criminal purposes (fraud, smuggling, drug
                trafficking)
              </li>
              <li>Using a car for profit</li>
              <li>Violation of the established speed limits</li>
            </ul>
            <p id="atten">
              !!! In case of revealing any of these 
              violations, the car can be forcibly withdrawn,
              without reimbursement of the funds paid and 
              the insurance deposit !!!
            </p>
          </div>
        </section>
      </section>

      <section class="tariffs" id="tariffs">
        <h2>
          You can find out about our tariffs by the
          <a id="tariffs_a" href="/tariffs">link</a>.
        </h2>
      </section>

      <section class="carBrands" id="carBrands">
        <h2 id="carBrands_h2">Car brands</h2>
        <div class="spisok">
          <ul class="list3b">
            <section class="spisok1">
              <li><a class="spis_a" href="/MERCEDES">MERCEDES</a></li>
              <li><a class="spis_a" href="/BMW">BMW</a></li>
              <li><a class="spis_a" href="/AUDI">AUDI</a></li>
              <li><a class="spis_a" href="/PORSCHE">PORSCHE</a></li>
              <li><a class="spis_a" href="/BENTLEY">BENTLEY</a></li>
              <li><a class="spis_a" href="/ROLLS">ROLLS ROYCE</a></li>
            </section>
          </ul>
          <ul class="list3b">
            <section class="spisok1">
              <li><a class="spis_a" href="/RANGE">RANGE ROVER</a></li>
              <li><a class="spis_a" href="/FERRARI">FERRARI</a></li>
              <li><a class="spis_a" href="/LAMBORGHINI">LAMBORGHINI</a></li>
              <li><a class="spis_a" href="/MUSTANG">MUSTANG</a></li>
              <li><a class="spis_a" href="/CAMARO">CAMARO</a></li>
              <li><a class="spis_a" href="/NISSAN">NISSAN GT-R</a></li>
            </section>
          </ul>
        </div>
      </section>
    </main>

    <footer>
      <section class="contacts" id="contacts">
        <section>
          <img
            class="logo"
            src="https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png"
            alt="logo"
          />
        </section>
        <div class="cont">
          <p><strong>Contacts</strong></p>
          <p>+375(12)-345-67-89</p>
        </div>
        <div class="cont">
          <p><strong>Adress</strong></p>
          <p>Minsk, Yakub Kolas street, building 5, office 13</p>
        </div>
        <div class="cont">
          <p><strong>Mail</strong></p>
          <p>rentofauto@rent.com</p>
        </div>
      </section>
      <section class="logo2">
        <div>
          <svg
            class="t-sociallinks__svg"
            version="1.1"
            id="Layer_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            x="0px"
            y="0px"
            width="30px"
            height="30px"
            viewBox="0 0 48 48"
            enable-background="new 0 0 48 48"
            xml:space="preserve"
          >
            <desc>Facebook</desc>
            <path
              style="fill: #ffffff"
              d="M47.761,24c0,13.121-10.638,23.76-23.758,23.76C10.877,47.76,0.239,37.121,0.239,24c0-13.124,10.638-23.76,23.764-23.76C37.123,0.24,47.761,10.876,47.761,24 M20.033,38.85H26.2V24.01h4.163l0.539-5.242H26.2v-3.083c0-1.156,0.769-1.427,1.308-1.427h3.318V9.168L26.258,9.15c-5.072,0-6.225,3.796-6.225,6.224v3.394H17.1v5.242h2.933V38.85z"
            ></path>
          </svg>
        </div>
        <div>
          <svg
            class="t-sociallinks__svg"
            version="1.1"
            id="Layer_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            x="0px"
            y="0px"
            width="30px"
            height="30px"
            viewBox="0 0 48 48"
            enable-background="new 0 0 48 48"
            xml:space="preserve"
          >
            <desc>Youtube</desc>
            <path
              style="fill: #ffffff"
              d="M24 0.0130005C37.248 0.0130005 47.987 10.753 47.987 24C47.987 37.247 37.247 47.987 24 47.987C10.753 47.987 0.0130005 37.247 0.0130005 24C0.0130005 10.753 10.752 0.0130005 24 0.0130005ZM35.815 18.093C35.565 16.756 34.452 15.758 33.173 15.635C30.119 15.439 27.054 15.28 23.995 15.278C20.936 15.276 17.882 15.432 14.828 15.625C13.544 15.749 12.431 16.742 12.182 18.084C11.898 20.017 11.756 21.969 11.756 23.92C11.756 25.871 11.898 27.823 12.182 29.756C12.431 31.098 13.544 32.21 14.828 32.333C17.883 32.526 20.935 32.723 23.995 32.723C27.053 32.723 30.121 32.551 33.173 32.353C34.452 32.229 35.565 31.084 35.815 29.747C36.101 27.817 36.244 25.868 36.244 23.919C36.244 21.971 36.101 20.023 35.815 18.093ZM21.224 27.435V20.32L27.851 23.878L21.224 27.435Z"
            ></path>
          </svg>
        </div>
        <div>
          <svg
            class="t-sociallinks__svg"
            version="1.1"
            id="Layer_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            width="30px"
            height="30px"
            viewBox="0 0 30 30"
            xml:space="preserve"
          >
            <desc>Instagram</desc>
            <path
              style="fill: #ffffff"
              d="M15,11.014 C12.801,11.014 11.015,12.797 11.015,15 C11.015,17.202 12.802,18.987 15,18.987 C17.199,18.987 18.987,17.202 18.987,15 C18.987,12.797 17.199,11.014 15,11.014 L15,11.014 Z M15,17.606 C13.556,17.606 12.393,16.439 12.393,15 C12.393,13.561 13.556,12.394 15,12.394 C16.429,12.394 17.607,13.561 17.607,15 C17.607,16.439 16.444,17.606 15,17.606 L15,17.606 Z"
            ></path>
            <path
              style="fill: #ffffff"
              d="M19.385,9.556 C18.872,9.556 18.465,9.964 18.465,10.477 C18.465,10.989 18.872,11.396 19.385,11.396 C19.898,11.396 20.306,10.989 20.306,10.477 C20.306,9.964 19.897,9.556 19.385,9.556 L19.385,9.556 Z"
            ></path>
            <path
              style="fill: #ffffff"
              d="M15.002,0.15 C6.798,0.15 0.149,6.797 0.149,15 C0.149,23.201 6.798,29.85 15.002,29.85 C23.201,29.85 29.852,23.202 29.852,15 C29.852,6.797 23.201,0.15 15.002,0.15 L15.002,0.15 Z M22.666,18.265 C22.666,20.688 20.687,22.666 18.25,22.666 L11.75,22.666 C9.312,22.666 7.333,20.687 7.333,18.28 L7.333,11.734 C7.333,9.312 9.311,7.334 11.75,7.334 L18.25,7.334 C20.688,7.334 22.666,9.312 22.666,11.734 L22.666,18.265 L22.666,18.265 Z"
            ></path>
          </svg>
        </div>
        <!--<p id="rights">&copy; All rights reserved</p>-->
      </section>
    </footer>
  </body>
</html>
