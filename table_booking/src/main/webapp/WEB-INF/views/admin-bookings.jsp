<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01.05.2024
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Администратор</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do" rel="stylesheet">

    <link rel="stylesheet" href="/static/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/animate.css">

    <link rel="stylesheet" href="/static/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/static/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/static/css/magnific-popup.css">

    <link rel="stylesheet" href="/static/css/aos.css">

    <link rel="stylesheet" href="/static/css/ionicons.min.css">

    <link rel="stylesheet" href="/static/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/static/css/jquery.timepicker.css">


    <link rel="stylesheet" href="/static/css/flaticon.css">
    <link rel="stylesheet" href="/static/css/icomoon.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>

<body>


<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand"><span class="flaticon-pizza-1 mr-1"></span>${admin.firstName}<br><small>администратор</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="/profile" class="nav-link">Профиль</a></li>
                <li class="nav-item"><a href="/admin/catalog" class="nav-link">Каталог</a></li>
                <li class="nav-item"><a href="/admin/bookings" class="nav-link">Бронирования</a></li>
                <li class="nav-item"><a href="/admin/promos" class="nav-link">Акции</a></li>
                <li class="nav-item"><a href="/admin/accounts" class="nav-link">Аккаунты</a></li>
                <li class="nav-item"><a href="/admin/comments" class="nav-link">Отзывы</a></li>
                <li class="nav-item"><a href="/logout" class="nav-link">Выйти</a></li>

            </ul>
        </div>
    </div>
</nav>

<section class="ftco-section">

    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <h2 class="mb-4">История бронирований</h2>
                <p>Здесь вы можете посмотреть историю бронирований</p>
            </div>
        </div>
    </div>



    <div class="container-wrap">
        <div class="row no-gutters d-flex">
            <c:forEach var="booking" items="${bookings}">

                <div class="col-lg-4 d-flex ftco-animate">
                    <div class="services-wrap d-flex">

                        <img src="/image/${booking.table.image.id}" class="img">

                        <div class="text p-4">
                            <h3>№${booking.table.id}</h3>
                            <p>${booking.table.description}</p>
                            <p>клиент: ${booking.user.firstName} ${booking.user.lastName}</p>
                            <p>время: ${booking.startTime} - ${booking.endTime}</p>
                            <p><span>посадка ${booking.table.capacity} человек</span></p>


                            <p class="price"><span>${booking.bookingPrice} BYN</span>


                                <c:if test="${not booking.cancelled}">

                                <c:if test="${booking.confirmed}">
                            <p>бронирование подтверждено</p>
                            </c:if>

                            <c:if test="${not booking.confirmed}">
                                <p>бронирование на рассмотрении</p>
                            </c:if>

                            </c:if>
                            <c:if test="${booking.cancelled}">
                                <p>бронирование было отменено</p>
                            </c:if>
                            <c:if test="${not booking.cancelled and not booking.confirmed}">
                                <a href="/admin/confirmedBooking/${booking.id}" class="ml-2 btn btn-white btn-outline-white">Подтвердить</a>
                            </c:if>


                            </p>
                        </div>
                    </div>
                </div>


            </c:forEach>
        </div>
    </div>



</section>
+
<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/static/js/popper.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.easing.1.3.js"></script>
<script src="/static/js/jquery.waypoints.min.js"></script>
<script src="/static/js/jquery.stellar.min.js"></script>
<script src="/static/js/owl.carousel.min.js"></script>
<script src="/static/js/jquery.magnific-popup.min.js"></script>
<script src="/static/js/aos.js"></script>
<script src="/static/js/jquery.animateNumber.min.js"></script>
<script src="/static/js/bootstrap-datepicker.js"></script>
<script src="/static/js/jquery.timepicker.min.js"></script>
<script src="/static/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/static/js/google-map.js"></script>
<script src="/static/js/main.js"></script>

</body>
</html>
