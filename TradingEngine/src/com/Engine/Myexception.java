package com.Engine;

public class Myexception extends  Exception {
    private String str1;

    public Myexception(String str2) {
        this.str1=str2;
    }
    public String toString(){
        return (str1) ;
    }
}
