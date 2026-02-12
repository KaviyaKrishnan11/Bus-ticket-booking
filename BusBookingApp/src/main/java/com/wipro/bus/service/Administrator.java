package com.wipro.bus.service;

import java.util.Date;
import java.util.List;

import com.wipro.bus.bean.BusBookingBean;
import com.wipro.bus.dao.BusBookingDAO;
import com.wipro.bus.util.InvalidInputException;

public class Administrator {

    BusBookingDAO dao = new BusBookingDAO();
    public String addRecord(BusBookingBean busBean) {

        try {
            if (busBean == null ||
                busBean.getPassengerName() == null ||
                busBean.getTravelDate() == null) {

                throw new InvalidInputException();
            }
            if (busBean.getPassengerName().length() < 2) {
                return "INVALID PASSENGER NAME";
            }
            if (busBean.getTravelDate() == null) {
                return "INVALID DATE";
            }
            boolean exists = dao.recordExists(
                    busBean.getPassengerName(),
                    busBean.getTravelDate());

            if (exists) {
                return "ALREADY EXISTS";
            }
            String recordId = dao.generateRecordID(
                    busBean.getPassengerName(),
                    busBean.getTravelDate());
            busBean.setRecordId(recordId);
            String result = dao.createRecord(busBean);

            return result;

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }
    public BusBookingBean viewRecord(String passengerName, Date travelDate) {

        return dao.fetchRecord(passengerName, travelDate);
    }
    public List<BusBookingBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }
}
