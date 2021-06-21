package com.creativa.personalpayments.entities;


public class PaymentService extends Payment {

    private String serviceType;

    public PaymentService(String paymentType, String paymentName, String serviceType, String amount, String paymentDate) {
        super(paymentType, paymentName, amount, paymentDate);
        this.serviceType = serviceType;
    }


    public String getServiceType() {
        return serviceType;
    }
}
