package com.Engine;

public class StockException extends Exception {

    private String name;
    private String element;

    public StockException(String name, String element) {

        this.name = name;
        this.element = element;
    }

    public String toString() {
        return "Stock with "+element+" : "+name +" already exist , you can't add stock with same "+ element +"\n Fix XML FILE";
    }
}

