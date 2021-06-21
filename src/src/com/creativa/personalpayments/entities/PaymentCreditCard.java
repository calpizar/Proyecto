package com.creativa.personalpayments.entities;



public class PaymentCreditCard extends Payment {

    private String ccType;

    public PaymentCreditCard(String paymentType, String paymentName, String ccType, String amount, String paymentDate) {
        super(paymentType, paymentName, amount, paymentDate);
        this.ccType = ccType;
    }


    public String getCcType() {
        return ccType;
    }
}
