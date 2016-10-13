<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights search</title>
</head>
<body>
<h3>Entrada a la aplicación</h3>
<%--  <form:form method="POST" modelAttribute="search" action="allflights"> --%>
<form:form method="POST" modelAttribute="search" action="allflights"> 
   <table>
    <tr>
        <td><form:label path="originAirport">Aeropuerto de origen</form:label></td>
        <td><form:input path="originAirport" /></td>
    </tr>
    <tr>
        <td><form:label path="destinationAirport">Aeropuerto de destino</form:label></td>
        <td><form:input path="destinationAirport" /></td>
    </tr>
    <tr>
        <td><form:label path="adultPassengerNum">Número de pasageros adultos</form:label></td>
        <td><form:input path="adultPassengerNum" /></td>
    </tr>
    <tr>
        <td><form:label path="childPassengerNum">Número de pasageros child</form:label></td>
        <td><form:input path="childPassengerNum" /></td>
    </tr>
    <tr>
        <td><form:label path="infantPassengerNum">Número de pasageros infant</form:label></td>
        <td><form:input path="infantPassengerNum" /></td>
    </tr>
    <tr>
        <td><form:label path="daysPriorDepartureNum">Días que quedan para la salida</form:label></td>
        <td><form:input path="daysPriorDepartureNum" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
 	
 
</body>
</html>