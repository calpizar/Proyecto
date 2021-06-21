package com.creativa.personalpayments.services;

import com.creativa.personalpayments.entities.Payment;
import com.creativa.personalpayments.entities.PaymentCreditCard;
import com.creativa.personalpayments.entities.PaymentService;
import com.creativa.personalpayments.repositories.Repository;

import java.util.Date;

public class PPService {

    private Repository repository;
    Payment payment;

    public PPService(Repository repository){
        this.repository = repository;
    }

    public void save(String paymentType, String paymentName, String serviceType, String ccType, String amount, String paymentDate) {
        if (paymentType.equals("Tarjeta de credito")){
            payment = new PaymentCreditCard(paymentType, paymentName, ccType, amount, paymentDate);
        }else{
            payment = new PaymentService(paymentType, paymentName,serviceType,amount,paymentDate);
        }
        this.repository.save(payment, new Date());
    }
    public String get(){
            return "Se ha registrado la siguiente informacion:" + "Tipo de pago: "+ payment.getPaymentType() +" - "+"Nombre del pago: "+ payment.getName() + " - "  + "Monto: " + payment.getAmount() + " - " + "pagar el: "  + payment.getPaymentDate() +  "\n";
    }
}
