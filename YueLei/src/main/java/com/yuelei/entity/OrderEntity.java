package com.yuelei.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders", schema = "lay")
public class OrderEntity {
    private int orderId;
    private String roomNo;
    private String roomType;
    private String roomPrice;
    private String roomArea;
    private String customerNickname;
    private String customerRealname;
    private String phone;
    private String idCard;
    private Date startDate;
    private Date endDate;
    private String days;
    private String status;
    private String remarks;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "room_no")
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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
    @Column(name = "room_area")
    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    @Basic
    @Column(name = "customer_nickname")
    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    @Basic
    @Column(name = "customer_realname")
    public String getCustomerRealname() {
        return customerRealname;
    }

    public void setCustomerRealname(String customerRealname) {
        this.customerRealname = customerRealname;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (roomNo != null ? !roomNo.equals(that.roomNo) : that.roomNo != null) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (roomPrice != null ? !roomPrice.equals(that.roomPrice) : that.roomPrice != null) return false;
        if (roomArea != null ? !roomArea.equals(that.roomArea) : that.roomArea != null) return false;
        if (customerNickname != null ? !customerNickname.equals(that.customerNickname) : that.customerNickname != null)
            return false;
        if (customerRealname != null ? !customerRealname.equals(that.customerRealname) : that.customerRealname != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (days != null ? !days.equals(that.days) : that.days != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (roomNo != null ? roomNo.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (roomPrice != null ? roomPrice.hashCode() : 0);
        result = 31 * result + (roomArea != null ? roomArea.hashCode() : 0);
        result = 31 * result + (customerNickname != null ? customerNickname.hashCode() : 0);
        result = 31 * result + (customerRealname != null ? customerRealname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }
}
