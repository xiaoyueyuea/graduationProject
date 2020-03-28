package com.yuelei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "customer_message", schema = "lay")
public class CustomerMessageEntity {
    private int messageId;
    private String username;
    private String sex;
    private String phone;
    private Timestamp messageDate;
    private String status;
    private String messageContent;

    @Id
    @Column(name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
    @Column(name = "message_date")
    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
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
    @Column(name = "message_content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerMessageEntity that = (CustomerMessageEntity) o;

        if (messageId != that.messageId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (messageDate != null ? !messageDate.equals(that.messageDate) : that.messageDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (messageContent != null ? !messageContent.equals(that.messageContent) : that.messageContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (messageDate != null ? messageDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        return result;
    }
}
