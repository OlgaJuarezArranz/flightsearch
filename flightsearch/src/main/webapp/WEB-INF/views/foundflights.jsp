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
	<c:choose>
		<c:when test="${foundFlightsList.size() eq 0}">
			<p>There is not flights available</p>
		</c:when>
		<c:otherwise>
			<c:forEach items="${foundFlightsList}" var="it">
				<c:out value="${it.foundFlightCode}" />
				<c:out value="${it.foundFlightTotalPrice}" />
				<br />
			</c:forEach>

		</c:otherwise>
	</c:choose>


</body>
</html>