package com.yuelei.entity.bean;

import com.yuelei.util.ExcelUtil.ExcelColumn;

import java.sql.Date;

public class FinancialManagerBean {

    @ExcelColumn(name = "订单号")
    private int orderId;

    @ExcelColumn(name = "房型")
    private String roomType;

    @ExcelColumn(name = "房价")
    private String roomPrice;

    @ExcelColumn(name = "开始日期")
    private Date startDate;

    @ExcelColumn(name = "离店日期")
    private Date endDate;

    @ExcelColumn(name = "天数")
    private String days;

    @ExcelColumn(name = "房费")
    private String roomCharge;

    @ExcelColumn(name = "补差价")
    private String priceDifference;

    @ExcelColumn(name = "其它消费")
    private String otherCost;

    @ExcelColumn(name = "总消费")
    private String totalCost;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(String roomCharge) {
        this.roomCharge = roomCharge;
    }

    public String getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(String priceDifference) {
        this.priceDifference = priceDifference;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
}
