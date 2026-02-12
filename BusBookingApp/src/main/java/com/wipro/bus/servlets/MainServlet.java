package com.wipro.bus.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.bus.bean.BusBookingBean;
import com.wipro.bus.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator admin = new Administrator();
    public String addRecord(HttpServletRequest request) {

        try {
            BusBookingBean bean = new BusBookingBean();

            bean.setPassengerName(request.getParameter("passengerName"));
            bean.setBusNumber(request.getParameter("busNumber"));
            bean.setSeatNo(request.getParameter("seatNo"));
            bean.setTicketNo(request.getParameter("ticketNo"));
            bean.setRemarks(request.getParameter("remarks"));
            String dateStr = request.getParameter("travelDate");

            if (dateStr == null || dateStr.isEmpty()) {
                return "INVALID DATE";
            }

            Date date = java.sql.Date.valueOf(dateStr); 
            bean.setTravelDate(date);

            return admin.addRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    public BusBookingBean viewRecord(HttpServletRequest request) {

        try {
            String passengerName = request.getParameter("passengerName");
            String dateStr = request.getParameter("travelDate");

            if (dateStr == null || dateStr.isEmpty()) {
                return null;
            }

            Date date = java.sql.Date.valueOf(dateStr);

            return admin.viewRecord(passengerName, date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<BusBookingBean> viewAllRecords(HttpServletRequest request) {

        return admin.viewAllRecords();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation.equals("newRecord")) {

            String result = addRecord(request);

            if (result.equals("FAIL") ||
                result.equals("INVALID INPUT") ||
                result.equals("INVALID PASSENGER NAME") ||
                result.equals("INVALID DATE") ||
                result.equals("ALREADY EXISTS")) {

                response.sendRedirect("error.html");

            } else {
                response.sendRedirect("success.html");
            }
        }
        else if (operation.equals("viewRecord")) {

            BusBookingBean bean = viewRecord(request);

            if (bean == null) {

                request.setAttribute("message",
                        "No matching records exists! Please try again!");

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayBusBooking.jsp");
                rd.forward(request, response);

            } else {

                request.setAttribute("busBean", bean);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayBusBooking.jsp");
                rd.forward(request, response);
            }
        }
        else if (operation.equals("viewAllRecords")) {

            List<BusBookingBean> list = viewAllRecords(request);

            if (list == null || list.isEmpty()) {

                request.setAttribute("message", "No records available!");

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllBusBookings.jsp");
                rd.forward(request, response);

            } else {

                request.setAttribute("list", list);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllBusBookings.jsp");
                rd.forward(request, response);
            }
        }
    }
}
