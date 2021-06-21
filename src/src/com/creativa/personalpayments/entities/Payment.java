package com.creativa.personalpayments.entities;



public class Payment {
    private String paymentType;
    private String paymentName;
    private String amount;
    private String paymentDate;

    public Payment(String paymentType, String paymentName, String amount, String paymentDate) {
        this.paymentType = paymentType;
        this.paymentName = paymentName;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }


    public String getPaymentType() {
        return paymentType;
    }

    public String getName(){
      return paymentName;
    }

    public String getAmount(){
        return amount;
    }

    public String getPaymentDate(){
        return paymentDate;
    }
}
