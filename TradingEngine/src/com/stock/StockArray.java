package com.stock;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//this class created only for load stocks from xml file, after the load all the stocks pass to Allstocks.class that containing Hashmap that cant be use with JAXB
@XmlRootElement(name="rse-stocks")
public class StockArray {
    public StockArray(){
    }
    private List<Stock> arrayList=new ArrayList<Stock>();


    @XmlElement(name="rse-stock")
    public List<Stock> getArrayList(){
        return arrayList;
    }

    public void setArrayList(ArrayList<Stock> arrayList) {
        this.arrayList = arrayList;
    }
}