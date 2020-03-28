package com.yuelei.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "financial_manager", schema = "lay")
public class FinancialManagerEntity {
    private int financialStatementsId;
    private int orderId;
    private String roomType;
    private String roomPrice;
    private Date startDate;
    private Date endDate;
    private String days;
    private String roomCharge;
    private String priceDifference;
    private String otherCost;

    @Id
    @Column(name = "financial_statements_id")
    public int getFinancialStatementsId() {
        return financialStatementsId;
    }

    public void setFinancialStatementsId(int financialStatementsId) {
        this.financialStatementsId = financialStatementsId;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "room_type")
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "room_price")
    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "days")
    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Basic
    @Column(name = "room_charge")
    public String getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(String roomCharge) {
        this.roomCharge = roomCharge;
    }

    @Basic
    @Column(name = "price_difference")
    public String getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(String priceDifference) {
        this.priceDifference = priceDifference;
    }

    @Basic
    @Column(name = "other_cost")
    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialManagerEntity that = (FinancialManagerEntity) o;

        if (financialStatementsId != that.financialStatementsId) return false;
        if (orderId != that.orderId) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (roomPrice != null ? !roomPrice.equals(that.roomPrice) : that.roomPrice != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (days != null ? !days.equals(that.days) : that.days != null) return false;
        if (roomCharge != null ? !roomCharge.equals(that.roomCharge) : that.roomCharge != null) return false;
        if (priceDifference != null ? !priceDifference.equals(that.priceDifference) : that.priceDifference != null)
            return false;
        if (otherCost != null ? !otherCost.equals(that.otherCost) : that.otherCost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = financialStatementsId;
        result = 31 * result + orderId;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (roomPrice != null ? roomPrice.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (roomCharge != null ? roomCharge.hashCode() : 0);
        result = 31 * result + (priceDifference != null ? priceDifference.hashCode() : 0);
        result = 31 * result + (otherCost != null ? otherCost.hashCode() : 0);
        return result;
    }
}
