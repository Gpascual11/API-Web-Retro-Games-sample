<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 20px;
            margin-top: 20px;
        }

        #title {
            text-align: center;
            font-size: 24px;
            color: #007bff;
            margin-bottom: 20px;
        }

        .card {
            border: 1px solid #dee2e6;
            border-radius: 10px;
            margin-bottom: 15px;
        }

        .card-title {
            color: #007bff;
        }

        #payButton {
            background-color: #28a745;
            border-color: #28a745;
        }
    </style>
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
                        <button class="btn btn-success" > Welcome, ${CustomerSession}</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-sm-12">
                <div id="title">Shopping Cart</div>
                <div class="row">
                    <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                    <span>VideoGames List:</span>
                    </div>
                </div>
                <c:forEach var="videogame" items="${RentList}">
                    <div class="card mb-3">
                        <div class="card-body">
                            <!--Print de videogame's name at rent list -->
                            <h5 class="card-title">${videogame.name}</h5>
                            <!--Print de videogame's price at rent list -->
                            <h6 class="card-title">${videogame.priceRent} €</h6>
                            
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
         <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                <span>Total Price: ${TotalPrice} €</span>
            </div>
        </div>

        <!-- pay Button -->
        <div class="row mt-3">
            <div class="col-sm-12">
                 <c:choose>
                    <c:when test="${not authenticated}">
                        <a href="<c:url value='/Web/login' />" class="btn btn-success">Pay</a>
                    </c:when>
                    <c:otherwise>
                         <form method="POST" action="${mvc.uri('Rent')}">
                            <a href="<c:url value='/Web/RentProceed' />" class="btn btn-success" id="payButton">Pay</a>
                            <input type="hidden" name="name" id="name" value="${RentList}">
                        </form>
                    </c:otherwise>
                </c:choose>  
               
            </div>
        </div>

    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
