package com.wipro.bus.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.bus.bean.BusBookingBean;
import com.wipro.bus.util.DBUtil;

public class BusBookingDAO {
    public String createRecord(BusBookingBean busBean) {

        String recordId = busBean.getRecordId();
        String status = "FAIL";

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "INSERT INTO BUSBOOK_TB VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, busBean.getRecordId());
            ps.setString(2, busBean.getPassengerName());
            ps.setString(3, busBean.getBusNumber());

            java.sql.Date sqlDate =
                    new java.sql.Date(busBean.getTravelDate().getTime());

            ps.setDate(4, sqlDate);
            ps.setString(5, busBean.getSeatNo());
            ps.setString(6, busBean.getTicketNo());
            ps.setString(7, busBean.getRemarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = recordId;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    public BusBookingBean fetchRecord(String passengerName, java.util.Date travelDate) {

        BusBookingBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "SELECT * FROM BUSBOOK_TB WHERE PASSENGERNAME=? AND TRAVEL_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, passengerName);

            java.sql.Date sqlDate =
                    new java.sql.Date(travelDate.getTime());

            ps.setDate(2, sqlDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new BusBookingBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setPassengerName(rs.getString("PASSENGERNAME"));
                bean.setBusNumber(rs.getString("BUSNUMBER"));
                bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
                bean.setSeatNo(rs.getString("SEATNO"));
                bean.setTicketNo(rs.getString("TICKETNO"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
    public String generateRecordID(String passengerName, java.util.Date travelDate) {

        String recordId = "";

        try {
            Connection con = DBUtil.getDBConnection();
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            String datePart = format.format(travelDate);
            String namePart = passengerName.substring(0, 2).toUpperCase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT BUSBOOK_SEQ.NEXTVAL FROM DUAL");

            if (rs.next()) {
                int seq = rs.getInt(1);
                recordId = datePart + namePart + seq;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recordId;
    }
    public boolean recordExists(String passengerName, java.util.Date travelDate) {

        boolean flag = false;

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "SELECT RECORDID FROM BUSBOOK_TB WHERE PASSENGERNAME=? AND TRAVEL_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, passengerName);

            java.sql.Date sqlDate =
                    new java.sql.Date(travelDate.getTime());

            ps.setDate(2, sqlDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
    public List<BusBookingBean> fetchAllRecords() {

        List<BusBookingBean> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "SELECT * FROM BUSBOOK_TB";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BusBookingBean bean = new BusBookingBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setPassengerName(rs.getString("PASSENGERNAME"));
                bean.setBusNumber(rs.getString("BUSNUMBER"));
                bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
                bean.setSeatNo(rs.getString("SEATNO"));
                bean.setTicketNo(rs.getString("TICKETNO"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list; 
    }
}
