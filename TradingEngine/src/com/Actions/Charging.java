package com.Actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Charging implements ActionsInterface {

    public Charging(int price){
        this.price=price;
        this.date= DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
    }

    private final int price;
    private final String date;

    @Override
    public int getTurnover() { return price; }
    @Override
    public String getDate() { return date; }

    @Override
    public String getType() {
        return "Charging";
    }
}
