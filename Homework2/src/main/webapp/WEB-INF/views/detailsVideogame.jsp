<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Game Details</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">

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
            <div class="col-md-6">
                <h1><b>${videogame.name}</b></h1>
                <p><b>Console:</b> ${empty videogame.console ? 'No disponible' : videogame.console.console}</p>
                <p><b>Availability:</b> ${videogame.available}</p>
                <p><b>Rent Price:</b> ${videogame.priceRent} €</p>
                <p><b>Description:</b> ${videogame.description}</p>
                <p><b>Game Type:</b> ${videogame.type.type}</p>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-md-6">
                <form method="POST" action="${mvc.uri('addCart')}">
                 <button type="submit" class="btn btn-primary" id="liveAlertBtn">Add to cart</button>
                  <input type="hidden" name="name" id="name" value="${videogame.name}"> 
                </form> 
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>
