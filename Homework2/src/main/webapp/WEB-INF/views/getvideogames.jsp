<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Videojuegos</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <style>

        .card-title {
            font-size: 20px;
            color: #007bff; /* Color azul */
        }

        .row {
            margin-bottom: 20px; /* Ajusta el espacio entre las filas */
        }
    </style>
    <script src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
</head>
<body>
    <div class="container">
        <h1 class="mt-3 mb-3">RetroGamesShop </h1>

        <!-- Filtros -->
        <form class="mb-4">
            <div class="row">
                <div class="col-md-4 offset-md-8">
                   <c:choose>
                       <c:when test="${not authenticated}">
                           <a href="<c:url value='/Web/login' />" class="btn btn-success">LogIn</a>
                       </c:when>
                       <c:otherwise>
                           <a href="<c:url value='' />" class="btn btn-success">Welcome, ${CustomerSession} </a>
                       </c:otherwise>
                   </c:choose>
               </div>
                <div>
                    <a href="<c:url value='/Web/Cart' />" class="btn btn-success">Shopping Cart</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 mb-2">
                    <label for="typeFilter">Filter by Type:</label>
                    <select class="form-select" id="typeFilter" name="type">
                        <option value="">All</option>
                        <option value="ExcitingGame">ExcitingGame</option>
                        <option value="ThrillingGame">ThrillingGame</option>
                        <option value="AwesomeAdventure">AwesomeAdventure</option>
                        <option value="ExcitingChallenge">ExcitingChallenge</option>
                        <option value="ImmersiveStoryline">ImmersiveStoryline</option>
                        <option value="EpicQuest">EpicQuest</option>
                        <option value="ExcitingAdventure">ExcitingAdventure</option>
                        <option value="FantasticExperience">FantasticExperience</option>
                    </select>
                </div>
                <div class="col-md-4 mb-2">
                    <label for="consoleFilter">Filter by Console:</label>
                    <select class="form-select" id="consoleFilter" name="console">
                        <option value="">All</option>
                        <option value="PlayStation5">Play Station 5</option>
                        <option value="XBOX">XBOX</option>
                        <option value="GAMEBOY">Game Boy</option>
                        <option value="GAMEBOYADVANCE">Gameboy Advance</option>
                        <option value="GAMEBOYCOLOR">Gamemboy Color</option>
                        <option value="NINTENDODS">Nintendo DS</option>
                        <option value="NINTENDO3DS">Nintendo 3DS</option>
                    </select>
                </div>
                <div class="col-md-4 mb-2">
                    <button type="submit" class="btn btn-primary mt-4">Apply Filter</button>
                </div>
            </div>
        </form>

        <!-- Listado de Videojuegos en filas horizontales de tres columnas -->
        <div class="row">
            <c:forEach var="videogame" items="${videogames}" varStatus="loop">
                <div class="col-md-4 mb-4">
                    <div class="card videogame-card">
                        <div class="card-body">
                            <h5 class="card-title">${videogame.name}</h5>
                            <p class="card-text"><b>Console:</b> ${videogame.console.console}</p>
                            <p class="card-text"><b>Availability:</b> ${videogame.available}</p>
                            <p class="card-text"><b>Rent Price:</b> ${videogame.priceRent} euros</p>
                            <a href="<c:url value='videogames/details/${videogame.name}' />" class="btn btn-primary">Details</a>
                        </div>
                    </div>
                </div>

                <!-- Cerrar la fila y abrir una nueva cada tres videojuegos -->
                <c:if test="${loop.index % 3 == 2 || loop.last}">
                    </div>
                    <div class="row">
                </c:if>
            </c:forEach>
        </div>
    </div>
</body>
</html>
