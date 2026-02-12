<%@ page import="com.wipro.bus.bean.BusBookingBean" %>

<html>
<head>
<title>Booking Details</title>
</head>
<body>

<h2>Bus Booking Details</h2>

<%
String message = (String)request.getAttribute("message");
BusBookingBean bean = (BusBookingBean)request.getAttribute("busBean");

if(message != null){
%>
<h3><%= message %></h3>
<%
} else if(bean != null){
%>

Record ID: <%= bean.getRecordId() %><br><br>
Passenger Name: <%= bean.getPassengerName() %><br><br>
Bus Number: <%= bean.getBusNumber() %><br><br>
Travel Date: <%= bean.getTravelDate() %><br><br>
Seat No: <%= bean.getSeatNo() %><br><br>
Ticket No: <%= bean.getTicketNo() %><br><br>
Remarks: <%= bean.getRemarks() %><br><br>

<%
}
%>

<a href="menu.html">Back to Menu</a>

</body>
</html>
