package com;

public class ChartData {
    public ChartData(String data,int pric) {
        this.date=data;
        this.price=pric;
    }

    private  String date;
    private int price;

    public String getDate() { return date; }

    public int getPrice() { return price; }
}
