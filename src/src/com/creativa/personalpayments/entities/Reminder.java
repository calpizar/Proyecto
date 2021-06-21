package com.creativa.personalpayments.entities;

import java.util.Date;

public class Reminder {

    private Payment payment;
    private Date date;

    public Reminder(Payment payment, Date date){
        this.payment = payment;
        this.date = date;
    }
    public Payment getPayment() {
        return payment;
    }

    public Date getDate() {
        return date;
    }

}
