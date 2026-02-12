<%@ page import="java.util.List" %>
<%@ page import="com.wipro.bus.bean.BusBookingBean" %>

<html>
<head>
<title>All Bookings</title>
</head>
<body>

<h2>All Bus Booking Records</h2>

<%
String message = (String)request.getAttribute("message");
List<BusBookingBean> list =
    (List<BusBookingBean>)request.getAttribute("list");

if(message != null){
%>
<h3><%= message %></h3>
<%
} else if(list != null){
    for(BusBookingBean bean : list){
%>

<hr>
Record ID: <%= bean.getRecordId() %><br>
Passenger Name: <%= bean.getPassengerName() %><br>
Bus Number: <%= bean.getBusNumber() %><br>
Travel Date: <%= bean.getTravelDate() %><br>
Seat No: <%= bean.getSeatNo() %><br>
Ticket No: <%= bean.getTicketNo() %><br>
Remarks: <%= bean.getRemarks() %><br>

<%
    }
}
%>

<a href="menu.html">Back to Menu</a>

</body>
</html>
