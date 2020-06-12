package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sales_management")

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    private String clientname;
    private String day;
    private String snumber;
    private String subject;
    private String quantity;
    private String deliveryday;
    private String deliveryday2;
    private String billingday;
    private String money;
    private String money2;
    private String status;
    private String delete_flg;
    private String mailadd;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClientname() {
        return clientname;
    }
    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getSnumber() {
        return snumber;
    }
    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getDeliveryday() {
        return deliveryday;
    }
    public void setDeliveryday2(String deliveryday2) {
        this.deliveryday2 = deliveryday2;
    }
    public String getDeliveryday2() {
        return deliveryday2;
    }
    public void setDeliveryday(String deliveryday) {
        this.deliveryday = deliveryday;
    }
    public String getBillingday() {
        return billingday;
    }
    public void setBillingday(String billingday) {
        this.billingday = billingday;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney2(String money2) {
        this.money2 = money2;
    }
    public String getMoney2() {
        return money2;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDelete_flg() {
        return delete_flg;
    }
    public void setDelete_flg(String delete_flg) {
        this.delete_flg = delete_flg;
    }
    public String getMailadd() {
        return mailadd;
    }
    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }

}
