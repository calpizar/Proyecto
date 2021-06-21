package com.creativa.personalpayments.repositories;

import com.creativa.personalpayments.entities.Payment;
import com.creativa.personalpayments.entities.Reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryRepository implements Repository{

    private List<Reminder> db;

    public InMemoryRepository(){
        this.db = new ArrayList<>();
    }

    public void save(Payment payment, Date date){
        this.db.add(new Reminder(payment,date));
    }

    public List<String> get(){
        List<String> lines = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
        for (Reminder item : db){
            lines.add(item.getPayment() + "-" + format.format(item.getDate()));
        }
        return lines;
    }
}

