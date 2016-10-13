<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights search</title>
</head>
<body>
	<h3>Flights</h3>
	<!--  <p>a</p> -->
	<%--   	<c:out value="${flights}"/> --%>
	<!--   	 <p>Ciudad origen</p>  -->
	<%-- <a href="<c:url value='/flightslist' />">List of All Flights</a> --%>
	<%--  	<c:out value="${airportOrigin.airportCity}"/> --%>
	<!--  	<p>Ciudad destino</p>  -->
	<%--  	<c:out value="${airportDestination.airportCity}"/> --%>
	<!--  	<p>Num adultos</p>  -->
	<%--  	<c:out value="${passengerTypes.numAdultPassengers}"/> --%>
	<!--  	<p>Num niños</p>  -->
	<%--  	<c:out value="${passengerTypes.numChildPassengers}"/> --%>
	<!--  	<p>Num bebes</p>  -->
	<%--  	<c:out value="${passengerTypes.numInfantPassengers}"/> --%>
	<!--  	<p>Num días antes de la salida</p>  -->
	<%--  	<c:out value="${daysPriorDepartureDate.numDaysPriorDepartureDate}"/> --%>
	<!--  	<p>Prueba de aeropuertos</p> -->
	<%--  	<c:out value="${cityIdsLits}"/> --%>
	<%--  	<c:forEach items="${cityIdsLits}" var="prueba"> --%>
	<%--  	<c:out value="${prueba}"/> --%>
	<%--  	</c:forEach> --%>
	<!-- 	<p>objeto entero</p> -->
	<%-- 	<c:out value="${foundFlightsList}" /> --%>

	<!-- 	 <p>dentro del forEach</p> -->
	<%-- 		<c:forEach items="${foundFlightsList}" var="it"> --%>
	<%-- 		<c:out value="${it.foundFlightCode}" /> --%>
	<%-- 		<c:out value="${it.foundFlightTotalPrice}" /><br/> --%>
	<%-- 	</c:forEach> --%>

	<c:choose>
		<c:when test="${foundFlightsList.size()==0}">
			<p>There is not flights available</p>
		</c:when>
		<c:otherwise>
			
			<p>dentro del forEach</p>
			<c:forEach items="${foundFlightsList}" var="it">
				<c:out value="${it.foundFlightCode}" />
				<c:out value="${it.foundFlightTotalPrice}" />
				<br />
			</c:forEach>
			
		</c:otherwise>
	</c:choose>
	
	
</body>
</html>