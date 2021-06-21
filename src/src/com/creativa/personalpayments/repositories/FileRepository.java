package com.creativa.personalpayments.repositories;

import com.creativa.personalpayments.entities.Payment;

import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepository implements Repository{

    private final String PP_REMINDERS = "db.txt";

    @Override
    public void save (Payment payment, Date date){

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String text = "Nombre del pago: " + payment.getName() + " - "  + "Monto: " + payment.getAmount() + " - " + "pagar el: " + payment.getPaymentDate() + " - Ingresado el: " + format.format(date) + "\n";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(PP_REMINDERS, true));
            writer.append(text);
            writer.close();
        } catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<String> get(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PP_REMINDERS));
            return reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
