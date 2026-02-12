<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Bus Booking</title>
</head>
<body>

<h2>Add Bus Ticket Booking</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="newRecord">

Passenger Name:
<input type="text" name="passengerName"><br><br>

Bus Number:
<input type="text" name="busNumber"><br><br>

Travel Date:
<input type="date" name="travelDate"><br><br>

Seat Number:
<input type="text" name="seatNo"><br><br>

Ticket Number:
<input type="text" name="ticketNo"><br><br>

Remarks:
<input type="text" name="remarks"><br><br>

<input type="submit" value="Add Booking">

</form>

</body>
</html>