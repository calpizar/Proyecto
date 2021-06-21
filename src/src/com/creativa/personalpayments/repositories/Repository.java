package com.creativa.personalpayments.repositories;

import com.creativa.personalpayments.entities.Payment;

import java.util.Date;
import java.util.List;

public interface Repository {

    default void save(Payment payment, Date date){

    }
    List<String> get();
}
